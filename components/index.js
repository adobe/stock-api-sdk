const HelloWorld = require('./HelloWorld');

const c = new HelloWorld(process.argv.slice(2));
c.run();
