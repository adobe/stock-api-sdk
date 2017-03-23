const assert = require('assert');
const expect = require('chai').expect;

describe('Array', () => {
  describe('#indexOf()', () => {
    it('should return -1 when the value is not present', () => {
      assert.equal(-1, [1, 2, 3].indexOf(4));
    });
  });
});


describe('Test using chai', () => {
  const foo = 'bar';
  const beverages = {
    tea: ['chai', 'matcha', 'oolong'],
  };

  it('should pass these tests with chai', () => {
    expect(foo).to.be.a('string');
    expect(foo).to.equal('bar');
    expect(foo).to.have.lengthOf(3);
    expect(beverages).to.have.property('tea').with.lengthOf(3);
  });
});
