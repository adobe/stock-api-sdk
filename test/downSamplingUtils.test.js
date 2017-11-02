import { expect } from 'chai';
import sinon from 'sinon';
import DownSamplingUtils from './../src/utils/downSamplingUtils';
import Constants from './../src/constants/constants';

// encode-decode-image-with-base64-breaks-image (2013-04-21)
function fixBinary(bin) {
  const length = bin.length;
  const buf = new ArrayBuffer(length);
  const arr = new Uint8Array(buf);
  for (let i = 0; i < length; i += 1) {
    arr[i] = bin.charCodeAt(i);
  }
  return buf;
}

describe('downSamplingUtils', () => {
  // Tests for base64ToArrayBuffer function
  describe('base64ToArrayBuffer', () => {
    const base64ToArrayTestCases = [
      { value: 'data:,Hello%2C%20World!', byteLength: 15 },
      { value: 'data:text/plain;base64,SGVsbG8sIFdvcmxkIQ', byteLength: 13 },
      { value: 'data:image/jpeg;base64,/9j/4QQLRXhpZgAATU0AKgAAAAg', byteLength: 20 },
    ];

    base64ToArrayTestCases.forEach((dataURI) => {
      it('should return byte length correctly for each test case', () => {
        const blob = DownSamplingUtils.base64ToArrayBuffer(dataURI.value);
        expect(blob.byteLength).to.equal(dataURI.byteLength);
      });
    });
  });

  // Tests for getImageOrientation function
  describe('getImageOrientation', () => {
    const testCases = [
      { arrayBuffer: new ArrayBuffer(10), orientation: -2 },
    ];

    testCases.forEach((test) => {
      it('should match orientation correctly for each test case.', () => {
        const orientation = DownSamplingUtils.getImageOrientation(test.arrayBuffer);
        expect(orientation).to.equal(test.orientation);
      });
    });

    it('should throw error for non ArrayBuffer type parameter.', () => {
      let testFn = () => DownSamplingUtils.getImageOrientation({});
      expect(testFn).to.throw('parameter must be of type  ArrayBuffer!');

      testFn = () => DownSamplingUtils.getImageOrientation(1);
      expect(testFn).to.throw('parameter must be of type  ArrayBuffer!');

      testFn = () => DownSamplingUtils.getImageOrientation('test');
      expect(testFn).to.throw('parameter must be of type  ArrayBuffer!');
    });

    it('should not throw error for ArrayBuffer type parameter with range > 1', () => {
      const testFn = () => DownSamplingUtils.getImageOrientation(new ArrayBuffer(2));
      expect(testFn).to.not.throw(Error);
    });

    it('should throw error for ArrayBuffer type parameter with range = 1', () => {
      const testFn = () => DownSamplingUtils.getImageOrientation(new ArrayBuffer(1));
      expect(testFn).to.throw(/Out of bounds access/);
    });

    it('should return orientation as 1 for the given base64 data', () => {
      const base64Data =
      'data:image/jpeg;base64,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AAAAAAQAAAAeOEJJTQPzAAAAAAAJAAAAAAAAAAABADhCSU0ECgAAAAAAAQAAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADUQAAAAYAAAAAAAAAAAAAABAAAAAQAAAADgBEAFMAQwBOADAANgAxADQAXwBzAG0AYQBsAGwAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAABAAAAAQAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAEAAAAAAABudWxsAAAAAgAAAAZib3VuZHNPYmpjAAAAAQAAAAAAAFJjdDEAAAAEAAAAAFRvcCBsb25nAAAAAAAAAABMZWZ0bG9uZwAAAAAAAAAAQnRvbWxvbmcAAAAQAAAAAFJnaHRsb25nAAAAEAAAAAZzbGljZXNWbExzAAAAAU9iamMAAAABAAAAAAAFc2xpY2UAAAASAAAAB3NsaWNlSURsb25nAAAAAAAAAAdncm91cElEbG9uZwAAAAAAAAAGb3JpZ2luZW51bQAAAAxFU2xpY2VPcmlnaW4AAAANYXV0b0dlbmVyYXRlZAAAAABUeXBlZW51bQAAAApFU2xpY2VUeXBlAAAAAEltZyAAAAAGYm91bmRzT2JqYwAAAAEAAAAAAABSY3QxAAAABAAAAABUb3AgbG9uZwAAAAAAAAAATGVmdGxvbmcAAAAAAAAAAEJ0b21sb25nAAAAEAAAAABSZ2h0bG9uZwAAABAAAAADdXJsVEVYVAAAAAEAAAAAAABudWxsVEVYVAAAAAEAAAAAAABNc2dlVEVYVAAAAAEAAAAAAAZhbHRUYWdURVhUAAAAAQAAAAAADmNlbGxUZXh0SXNIVE1MYm9vbAEAAAAIY2VsbFRleHRURVhUAAAAAQAAAAAACWhvcnpBbGlnbmVudW0AAAAPRVNsaWNlSG9yekFsaWduAAAAB2RlZmF1bHQAAAAJdmVydEFsaWduZW51bQAAAA9FU2xpY2VWZXJ0QWxpZ24AAAAHZGVmYXVsdAAAAAtiZ0NvbG9yVHlwZWVudW0AAAARRVNsaWNlQkdDb2xvclR5cGUAAAAATm9uZQAAAAl0b3BPdXRzZXRsb25nAAAAAAAAAApsZWZ0T3V0c2V0bG9uZwAAAAAAAAAMYm90dG9tT3V0c2V0bG9uZwAAAAAAAAALcmlnaHRPdXRzZXRsb25nAAAAAAA4QklNBCgAAAAAAAwAAAACP/AAAAAAAAA4QklNBBQAAAAAAAQAAAABOEJJTQQMAAAAAAJfAAAAAQAAABAAAAAQAAAAMAAAAwAAAAJDABgAAf/Y/+0ADEFkb2JlX0NNAAH/7gAOQWRvYmUAZIAAAAAB/9sAhAAMCAgICQgMCQkMEQsKCxEVDwwMDxUYExMVExMYEQwMDAwMDBEMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMAQ0LCw0ODRAODhAUDg4OFBQODg4OFBEMDAwMDBERDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAAQABADASIAAhEBAxEB/90ABAAB/8QBPwAAAQUBAQEBAQEAAAAAAAAAAwABAgQFBgcICQoLAQABBQEBAQEBAQAAAAAAAAABAAIDBAUGBwgJCgsQAAEEAQMCBAIFBwYIBQMMMwEAAhEDBCESMQVBUWETInGBMgYUkaGxQiMkFVLBYjM0coLRQwclklPw4fFjczUWorKDJkSTVGRFwqN0NhfSVeJl8rOEw9N14/NGJ5SkhbSVxNTk9KW1xdXl9VZmdoaWprbG1ub2N0dXZ3eHl6e3x9fn9xEAAgIBAgQEAwQFBgcHBgU1AQACEQMhMRIEQVFhcSITBTKBkRShsUIjwVLR8DMkYuFygpJDUxVjczTxJQYWorKDByY1wtJEk1SjF2RFVTZ0ZeLys4TD03Xj80aUpIW0lcTU5PSltcXV5fVWZnaGlqa2xtbm9ic3R1dnd4eXp7fH/9oADAMBAAIRAxEAPwDrPTp3hjQ5z/3Toi3V1M0LYHP3fNWXmutpfpMe6wrJycqt+zY6fVO1pgn28PKaCZLjQD//2QA4QklNBCEAAAAAAFMAAAABAQAAAA8AQQBkAG8AYgBlACAAUABoAG8AdABvAHMAaABvAHAAAAASAEEAZABvAGIAZQAgAFAAaABvAHQAbwBzAGgAbwBwACAAQwBDAAAAAQA4QklNBAYAAAAAAAcAAwABAAEBAP/hDzhodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NDkxMSwgMjAxMy8xMC8yOS0xMTo0NzoxNiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiIHhtbG5zOnhtcFJpZ2h0cz0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3JpZ2h0cy8iIHhtcDpNb2RpZnlEYXRlPSIyMDE0LTA1LTEzVDE4OjE4OjE1KzAyOjAwIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDUzMgV2luZG93cyIgeG1wOkNyZWF0ZURhdGU9IjIwMDYtMDYtMTNUMTU6NDQ6NDUiIHhtcDpNZXRhZGF0YURhdGU9IjIwMTQtMDUtMTNUMTg6MTg6MTUrMDI6MDAiIGRjOmZvcm1hdD0iaW1hZ2UvanBlZyIgcGhvdG9zaG9wOkxlZ2FjeUlQVENEaWdlc3Q9IjEyRDc0M0EzNkFDOEM2NzkxOTNCRTBENEI5MEJGRDlGIiBwaG90b3Nob3A6RGF0ZUNyZWF0ZWQ9IjIwMDYtMDYtMTNUMTU6NDQ6NDUrMDI6MDAiIHBob3Rvc2hvcDpDb2xvck1vZGU9IjMiIHBob3Rvc2hvcDpJQ0NQcm9maWxlPSJzUkdCIElFQzYxOTY2LTIuMSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDphZjgyMzA0YS00NDA1LWZkNDgtYTU1Mi00MzVjZjE3ZGUwOTciIHhtcE1NOkRvY3VtZW50SUQ9InV1aWQ6RDVDQkNBRDVGRDFDREQxMTkwNzFFNzA5MzdFOUQ1QkQiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0idXVpZDpENUNCQ0FENUZEMUNERDExOTA3MUU3MDkzN0U5RDVCRCIgeG1wUmlnaHRzOk1hcmtlZD0iRmFsc2UiPiA8ZGM6ZGVzY3JpcHRpb24+IDxyZGY6QWx0PiA8cmRmOmxpIHhtbDpsYW5nPSJ4LWRlZmF1bHQiPiAgICAgICAgICA8L3JkZjpsaT4gPC9yZGY6QWx0PiA8L2RjOmRlc2NyaXB0aW9uPiA8eG1wTU06RGVyaXZlZEZyb20gcmRmOnBhcnNlVHlwZT0iUmVzb3VyY2UiLz4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6MTQ3OGUxYmItYzNjOS1lZTQxLWFlY2QtNDVhMGM2NGZiOTFiIiBzdEV2dDp3aGVuPSIyMDE0LTA1LTEzVDE4OjE4OjE1KzAyOjAwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDphZjgyMzA0YS00NDA1LWZkNDgtYTU1Mi00MzVjZjE3ZGUwOTciIHN0RXZ0OndoZW49IjIwMTQtMDUtMTNUMTg6MTg6MTUrMDI6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDQyAoV2luZG93cykiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9InciPz7/4gxYSUNDX1BST0ZJTEUAAQEAAAxITGlubwIQAABtbnRyUkdCIFhZWiAHzgACAAkABgAxAABhY3NwTVNGVAAAAABJRUMgc1JHQgAAAAAAAAAAAAAAAAAA9tYAAQAAAADTLUhQICAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABFjcHJ0AAABUAAAADNkZXNjAAABhAAAAGx3dHB0AAAB8AAAABRia3B0AAACBAAAABRyWFlaAAACGAAAABRnWFlaAAACLAAAABRiWFlaAAACQAAAABRkbW5kAAACVAAAAHBkbWRkAAACxAAAAIh2dWVkAAADTAAAAIZ2aWV3AAAD1AAAACRsdW1pAAAD+AAAABRtZWFzAAAEDAAAACR0ZWNoAAAEMAAAAAxyVFJDAAAEPAAACAxnVFJDAAAEPAAACAxiVFJDAAAEPAAACAx0ZXh0AAAAAENvcHlyaWdodCAoYykgMTk5OCBIZXdsZXR0LVBhY2thcmQgQ29tcGFueQAAZGVzYwAAAAAAAAASc1JHQiBJRUM2MTk2Ni0yLjEAAAAAAAAAAAAA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';
      const orientation = DownSamplingUtils.getImageOrientation(
                          DownSamplingUtils.base64ToArrayBuffer(base64Data));
      expect(orientation).to.equal(1);
    });
  });

  // Tests for calculateResizeParams function
  describe('calculateResizeParams', () => {
    it('should retain width and height for width < 1000 and height < 1000', () => {
      const originalWidth = 100;
      const originalHeight = 100;
      const resizedParams = DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(resizedParams.width).to.equal(originalWidth);
      expect(resizedParams.height).to.equal(originalHeight);
    });

    it('should change width to 1000 for width > 1000 and height > 1000', () => {
      const originalWidth = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO + 1;
      const originalHeight = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO + 1;
      const resizedParams = DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(resizedParams.width).to.equal(Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO);
      expect(resizedParams.height).to.equal(Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO);
    });

    it('should change width to 1000 for width > 1000 and height less than width', () => {
      const originalWidth = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO + 1;
      const originalHeight = 100;
      const resizedParams = DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(resizedParams.width).to.equal(Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO);
    });

    it('should change height to 1000 for height > 1000 and height less than width', () => {
      const originalWidth = 100;
      const originalHeight = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO + 1;
      const resizedParams = DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(resizedParams.height).to.equal(Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO);
    });

    it('should throw error if height is greater than 23000', () => {
      const originalWidth = 100;
      const originalHeight = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_MAXIMUM + 1;
      const testFn = () => DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(testFn).to.throw(/Image dropped is too large for visual search!/);
    });

    it('should throw error if width is greater than 23000', () => {
      const originalWidth = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_MAXIMUM + 1;
      const originalHeight = 100;
      const testFn = () => DownSamplingUtils.calculateResizeParams(originalWidth, originalHeight);
      expect(testFn).to.throw(/Image dropped is too large for visual search!/);
    });
  });

  // Tests for resizeImage function
  describe('resizeImage', () => {
    beforeEach(function () {
      this.img = new Image(2000, 2000);

      this.base64 = 'data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAB1klEQVR42n2TzytEURTHv3e8N1joRhZG' +
                   'zJsoCjsLhcw0jClKWbHwY2GnLGUlIfIP2IjyY2djZTHSMJNQSilFNkz24z0/Ms2MrnvfvMu8mcfZvPvu' +
                   'Pfdzz/mecwgKLNYKb0cFEgXbRvwV2s2HuWazCbzKA5LvNecDXayBjv9NL7tEpSNgbYzQ5kZmAlSXgsGG' +
                   'XmS+MjhKxDHgC+quyaPKQtoPYMQPOh5U9H6tBxF+Icy/aolqAqLP5wjWd5r/Ip3YXVILrF4ZRYAxDhCO' +
                   'J/yCwiMI+/xgjOEzmzIhAio04GeGayIXjQ0wGoAuQ5cmIjh8jNo0GF78QwNhpyvV1O9tdxSSR6PLl51F' +
                   'nIK3uQ4JJQME4sCxCIRxQbMwPNSjqaobsfskm9l4Ky6jvCzWEnDKU1ayQPe5BbN64vYJ2vwO7CIeLIi3' +
                   'ciYAoby0M4oNYBrXgdgAbC/MhGCRhyhCZwrcEz1Ib3KKO7f+2I4iFvoVmIxHigGiZHhPIb0bL1bQApFS' +
                   '9U/AC0ulSXrrhMotka/lQy0Ic08FDeIiAmDvA2HX01W05TopS2j2/H4T6FBVbj4YgV5+AecyLk+Ctvms' +
                   'QWK8WZZ+Hdf7QGu7fobMuZHyq1DoJLvUqQrfM966EU/qYGwAAAAASUVORK5CYII=';
      this.imgData = this.base64;
      this.img.src = this.imgData;
      this.calculateResizeParams = sinon.stub(DownSamplingUtils, 'calculateResizeParams').callsFake(() => {
        const resizedParams = {};
        resizedParams.width = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO;
        resizedParams.height = Constants.DOWNSAMPLE_PARAMS.LONGEST_SIDE_DOWNSAMPLE_TO;

        return resizedParams;
      });
      this.dataURItoBlob = sinon.spy(DownSamplingUtils, 'dataURItoBlob');
    });

    afterEach(function () {
      this.calculateResizeParams.restore();
      this.dataURItoBlob.restore();
    });


    it('should return the resized image correctly for orientation 2', function () {
      const orientation = 2;
      const resizedImg = DownSamplingUtils.resizeImage(this.img, orientation);
      sinon.assert.calledOnce(this.calculateResizeParams);
      sinon.assert.calledOnce(this.dataURItoBlob);
      expect(resizedImg.size).to.be.at.least(1);
    });

    it('should return the resized image correctly for orientation 5', function () {
      const orientation = 5;
      const resizedImg = DownSamplingUtils.resizeImage(this.img, orientation);
      sinon.assert.calledOnce(this.calculateResizeParams);
      sinon.assert.calledOnce(this.dataURItoBlob);
      expect(resizedImg.size).to.be.at.least(1);
    });

    it('should return the resized image correctly for orientation 3', function () {
      const orientation = 3;
      const resizedImg = DownSamplingUtils.resizeImage(this.img, orientation);
      sinon.assert.calledOnce(this.calculateResizeParams);
      sinon.assert.calledOnce(this.dataURItoBlob);
      expect(resizedImg.size).to.be.at.least(1);
    });

    it('should return the resized image correctly for orientation 7', function () {
      const orientation = 7;
      const resizedImg = DownSamplingUtils.resizeImage(this.img, orientation);
      sinon.assert.calledOnce(this.calculateResizeParams);
      sinon.assert.calledOnce(this.dataURItoBlob);
      expect(resizedImg.size).to.be.at.least(1);
    });

    it('should return the resized image correctly for orientation 1', function () {
      const orientation = 1;
      const resizedImg = DownSamplingUtils.resizeImage(this.img, orientation);
      sinon.assert.calledOnce(this.calculateResizeParams);
      sinon.assert.calledOnce(this.dataURItoBlob);
      expect(resizedImg.size).to.be.at.least(1);
    });
  });

  // Tests for downsampleAndFixOrientationImage function
  describe('downsampleAndFixOrientationImage', () => {
    const incorrectFileNametestCases = [
      { filename: 'test.txt', type: 'text/plain', expected: false },
      { filename: 'test.pdf', type: 'application/pdf', expected: false },
    ];

    incorrectFileNametestCases.forEach((test) => {
      it(`should resolve promise with error message for ${test.filename}`, (done) => {
        const file = {
          name: test.filename,
          type: test.type,
        };

        DownSamplingUtils.downsampleAndFixOrientationImage(file)
                          .then((response) => {
                            expect(response).to.not.be.ok;
                            done();
                          }, (error) => {
                            expect(error).to.equal('Unsupported image extension.! AdobeStock supports only JPG, JPEG, PNG and GIF!');
                            done();
                          })
                          .catch((error) => {
                            done(error);
                          });
      });
    });

    it('should resolve promise with success message valid file object size', (done) => {
      const base64Data = 'iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAB1klEQVR42n2TzytEURTHv3e8N1joRhZG' +
                    'zJsoCjsLhcw0jClKWbHwY2GnLGUlIfIP2IjyY2djZTHSMJNQSilFNkz24z0/Ms2MrnvfvMu8mcfZvPvu' +
                    'Pfdzz/mecwgKLNYKb0cFEgXbRvwV2s2HuWazCbzKA5LvNecDXayBjv9NL7tEpSNgbYzQ5kZmAlSXgsGG' +
                    'XmS+MjhKxDHgC+quyaPKQtoPYMQPOh5U9H6tBxF+Icy/aolqAqLP5wjWd5r/Ip3YXVILrF4ZRYAxDhCO' +
                    'J/yCwiMI+/xgjOEzmzIhAio04GeGayIXjQ0wGoAuQ5cmIjh8jNo0GF78QwNhpyvV1O9tdxSSR6PLl51F' +
                    'nIK3uQ4JJQME4sCxCIRxQbMwPNSjqaobsfskm9l4Ky6jvCzWEnDKU1ayQPe5BbN64vYJ2vwO7CIeLIi3' +
                    'ciYAoby0M4oNYBrXgdgAbC/MhGCRhyhCZwrcEz1Ib3KKO7f+2I4iFvoVmIxHigGiZHhPIb0bL1bQApFS' +
                    '9U/AC0ulSXrrhMotka/lQy0Ic08FDeIiAmDvA2HX01W05TopS2j2/H4T6FBVbj4YgV5+AecyLk+Ctvms' +
                    'QWK8WZZ+Hdf7QGu7fobMuZHyq1DoJLvUqQrfM966EU/qYGwAAAAASUVORK5CYII=';
      const binary = fixBinary(atob(base64Data));
      const blob = new Blob([binary], { type: 'image/png' });
      const file = new File([blob], 'image.png', { type: 'image/png' });
      DownSamplingUtils.downsampleAndFixOrientationImage(file)
                          .then((response) => {
                            expect(response.size).to.be.ok;
                            done();
                          }, (error) => {
                            expect(error).to.not.be.ok;
                            done();
                          })
                          .catch((error) => {
                            done(error);
                          });
    });

    it('should reject promise with error message for invalid file object', (done) => {
      const blob = new Blob(['test'], { type: 'image/png' });
      const file = new File([blob], 'image.png', { type: 'image/png' });
      DownSamplingUtils.downsampleAndFixOrientationImage(file)
                          .then((response) => {
                            expect(response).to.not.be.ok;
                            done();
                          }, (error) => {
                            expect(error).to.equal('Unsupported filetype for visual search.!');
                            done();
                          })
                          .catch((error) => {
                            done(error);
                          });
    });
  });
});
