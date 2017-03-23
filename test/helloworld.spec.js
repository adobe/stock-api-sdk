const HelloWorld = require('../components/HelloWorld');
const expect = require('chai').expect;

const DefaultGreeting = 'Hello World';
const Arg1 = 'Hello';
const Arg2 = 'there!';

describe('HelloWorld', () => {
  describe('Constructor', () => {
    it('should be created with 2 properties: commandLineArgs, greeting', () => {
      const sut = new HelloWorld();
      expect(sut).to.have.property('commandLineArgs');
      expect(sut).to.have.property('greeting');
    });

    it('should have default greeting', () => {
      const sut = new HelloWorld();
      expect(sut.greeting).to.equal(DefaultGreeting);
    });

    it('should have command line args set when supplied', () => {
      const sut = new HelloWorld([Arg1, Arg2]);
      expect(sut.commandLineArgs).to.have.lengthOf(2);
      expect(sut.commandLineArgs[0]).to.equal(Arg1);
      expect(sut.commandLineArgs[1]).to.equal(Arg2);
    });
  });
});
