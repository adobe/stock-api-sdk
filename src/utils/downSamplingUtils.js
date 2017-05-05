import Constants from './../constants/constants';

export default class DownSamplingUtils {
  /**
  * Converts the data uri from a canvas to a blob that can be pushed to redpill
  *
  * @param {Object} dataURI
  * @return {Object} blob of uri data
  */
  static dataURItoBlob(dataURI) {
    // convert base64/URLEncoded data component to raw binary data held in a string
    let byteString;
    const uriSplit = dataURI.split(',');

    if (uriSplit[0].indexOf('base64') >= 0) {
      byteString = atob(uriSplit[1]);
    } else {
      byteString = decodeURI(uriSplit[1]);
    }

    // separate out the mime component
    const mimeString = uriSplit[0].split(':')[1].split(';')[0];

    // write the bytes of the string to a typed array
    const ia = new Uint8Array(byteString.length);
    for (let i = 0; i < byteString.length; i += 1) {
      ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ia], { type: mimeString });
  }
  /**
  * Pulls the orientation tag out of the EXIF information of the image if any
  *
  * @param {ArrayBuffer} result
  * @return {number} Orientation tag if any for the image in the array buffer
  */
  static getImageOrientation(result) {
    if (!(result && result instanceof ArrayBuffer && result.byteLength !== undefined)) {
      throw new Error('parameter must be of type  ArrayBuffer!');
    }

    const view = new DataView(result);
    let marker = 0;
    const length = view.byteLength;
    let little;
    let tags;
    let offset = 2;
    const defualtOrientation = -1;

    if (view.getUint16(0, false) !== Constants.EXIF_FILE_FORMAT_PARAMS.JPG_START_MARKER) {
      return -2;
    }

    const lastMarker = Constants.EXIF_FILE_FORMAT_PARAMS.LAST_MARKER;
    const markerAnded = (marker & lastMarker); // eslint-disable-line no-bitwise

    while (offset < length) {
      marker = view.getUint16(offset, false);
      offset += 2;

      if (marker === Constants.EXIF_FILE_FORMAT_PARAMS.APP1_MARKER) {
        offset += 2;
        if (view.getUint32(offset, false) !== Constants.EXIF_FILE_FORMAT_PARAMS.EXIF_HEADER) {
          return -1;
        }

        little = view.getUint16(offset += 6, false) ===
                 Constants.EXIF_FILE_FORMAT_PARAMS.TIF_HEADER;
        offset += view.getUint32(offset + 4, little);
        tags = view.getUint16(offset, little);
        offset += 2;

        for (let i = 0; i < tags; i += 1) {
          if (view.getUint16(offset + (i * 12), little) ===
              Constants.EXIF_FILE_FORMAT_PARAMS.ORIENTATION_TAG) {
            return view.getUint16(offset + (i * 12) + 8, little);
          }
        }
      } else if (markerAnded !== Constants.EXIF_FILE_FORMAT_PARAMS.LAST_MARKER) {
        break;
      } else {
        offset += view.getUint16(offset, false);
      }
    }

    return defualtOrientation;
  }

  /**
  * Converts a base64 string to an array buffer
  *
  * @param {Object} dataURI
  * @return {ArrayBuffer} The base64 blob is converted to an array buffer
  */
  static base64ToArrayBuffer(dataURI) {
    let byteString;
    const uriSplit = dataURI.split(',');

    if (uriSplit[0].indexOf('base64') >= 0) {
      byteString = atob(uriSplit[1]);
    } else {
      byteString = decodeURI(uriSplit[1]);
    }

    const len = byteString.length;
    const bytes = new Uint8Array(len);

    for (let i = 0; i < len; i += 1) {
      bytes[i] = byteString.charCodeAt(i);
    }

    return bytes.buffer;
  }

  /**
  * Checks to see if the Image needs to be downsampled or rotated and does it
  *
  * @param {Integer} originalWidth width of the image
  * @param {Integer} originalHeight height of the image
  * @return {Object} with two parameters 'width' and 'height'
  */
  static calculateResizeParams(originalWidth, originalHeight) {
    let imgWidth = originalWidth;
    let imgHeight = originalHeight;

    if (Math.max(originalWidth, originalHeight) >
        Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_MAXIMUM) {
      throw new Error('Image dropped is too large for visual search!');
    } else {
      const aspectRatio = originalWidth / originalHeight;
      if (originalWidth > originalHeight) {
        if (originalWidth > Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO) {
          imgWidth = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO;
          imgHeight = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO / aspectRatio;
        }
      } else if (originalHeight > Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO) {
        imgWidth = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO * aspectRatio;
        imgHeight = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO;
      }
    }

    const resizeParams = {};
    resizeParams.width = imgWidth;
    resizeParams.height = imgHeight;

    return resizeParams;
  }

  /**
  * Checks to see if the Image needs to be downsampled or rotated and does it
  *
  * @param {Image} img image to be downsampled
  * @param {Integer} orientation EXIF orientation value
  * @return {Blob} resized file
  */
  static resizeImage(img, orientation) {
    // grab the original image width and height
    const originalWidth = img.naturalWidth;
    const originalHeight = img.naturalHeight;

    const resizeParams = DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);

    let imgWidth = resizeParams.width;
    let imgHeight = resizeParams.height;

    let canvas;
    let ctx;
    let cx = 0;
    let cy = 0;
    let degree = 0;
    const orientationNeedsFlip = [2, 4, 5, 7];
    let resizedFile;


    // if we don't need to downsize but we do need to rotate or
    // flip then lets use the original width/height
    if (orientation !== 1 && imgWidth === 0 && imgHeight === 0) {
      imgWidth = originalWidth;
      imgHeight = originalHeight;
    }

    // if a new width and height was set then downsample to those dimensions
    if (imgWidth > 0 && imgHeight > 0) {
      // Create an empty canvas element
      canvas = document.createElement('canvas');

      // Copy the image contents to the canvas
      ctx = canvas.getContext('2d');

      // Calculate new x/y coorditates for image if needed
      switch (orientation) {
        case 5:
        case 6:
          degree = 90;
          cy = imgHeight * (-1);
          break;

        case 3:
        case 4:
          degree = 180;
          cx = imgWidth * (-1);
          cy = imgHeight * (-1);
          break;
        case 7:
        case 8:
          degree = 270;
          cx = imgWidth * (-1);
          break;
        default:
        // ignore any other values
      }

      // if the rotation changes the aspect ratio of the image
      // then use the image height for canvas width and vice versa
      if (degree === 0 || degree === 180) {
        canvas.width = imgWidth;
        canvas.height = imgHeight;
      } else {
        canvas.width = imgHeight;
        canvas.height = imgWidth;
      }

      // flip the image if needed
      if (orientationNeedsFlip.indexOf(orientation) > -1) {
        ctx.translate(imgHeight, 0);
        ctx.scale(-1, 1);
      }

      if (degree > 0) {
        ctx.rotate((degree * Math.PI) / 180);
      }

      ctx.drawImage(img, cx, cy, imgWidth, imgHeight);
      resizedFile = DownSamplingUtils.dataURItoBlob(canvas.toDataURL('image/jpeg'));
    }

    // we have a properly sized file
    return resizedFile;
  }

  /**
  * Checks to see if the file needs to be downsampled or rotated and does it
  *
  * @param {Object} file
  */
  static downsampleAndFixOrientationImage(file) {
    return new Promise((onSuccess, onFailure) => {
      try {
        if (!(/\.(png|jpeg|jpg|gif)$/i).test(file.name)) {
          throw new Error('Unsupported image extension.! AdobeStock supports only JPG, JPEG, PNG and GIF!');
        }

        const reader = new FileReader();
        let orientation = -1;
        // Since the File Object does not hold the size of an image
        // we need to create a new image and assign it's src, so when
        // the image is loaded we can calculate it's width and height:
        const img = new Image();

        reader.onload = () => {
          img.onerror = () => {
            onFailure('Unsupported filetype for visual search.!');
          };

          img.onload = () => {
            try {
              const resizedFile = DownSamplingUtils.resizeImage(img, orientation);
              onSuccess(resizedFile);
            } catch (err) {
              onFailure(err.message);
            }
          };

          // pull the orientation from the image
          orientation = DownSamplingUtils.getImageOrientation(
                        DownSamplingUtils.base64ToArrayBuffer(reader.result));

          img.src = reader.result;
        };

        reader.readAsDataURL(file);
      } catch (err) {
        onFailure(err.message);
      }
    });
  }
}
