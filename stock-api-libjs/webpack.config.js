const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const prodEnv = (process.env.NODE_ENV) ?
                        (process.env.NODE_ENV === 'production') : true;

const config = {
  entry: {
    adobeStock: './src/adobeStock.js',
  },
  devtool: 'source-map',
  module: {
    loaders: [
      {
        test: /\.js$/,
        loader: 'babel-loader',
        query: {
          presets: ['es2015'],
        },
      },
    ],
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: prodEnv ? 'adobestocklib.min.js' : 'adobestocklib.js',
    library: 'AdobeStock',
  },
  plugins: [],
  devServer: {
    inline: true,
    port: 9000,
  },
};

if (!prodEnv) {
  config.plugins.push(new HtmlWebpackPlugin());
}

module.exports = config;
