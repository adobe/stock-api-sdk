class HelloWorld {

  constructor(commandLineArgs) {
    this.commandLineArgs = commandLineArgs;
    this.greeting = 'Hello World';
  }

  run() {
    if (this.commandLineArgs && this.commandLineArgs.length) {
      console.log(this.commandLineArgs.join(' '));
    } else {
      console.log(this.greeting);
    }
  }
}

module.exports = HelloWorld;
