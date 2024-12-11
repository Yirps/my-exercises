const expect = require('expect.js');
const sinon = require('sinon');
const callbacksAnswers = require('../../src/js-async/01-callbacks');

describe('callbacks', function () {
    const originalPromise = global.Promise;
    let clock;

    beforeEach(function () {
        clock = sinon.useFakeTimers();
    });

    afterEach(function () {
        clock.restore();
        global.Promise = originalPromise;
    });

    it('you should understand how to use callbacks synchronously', function () {
        let called = false;
        const testDelay = 10000;

        var result = callbacksAnswers.profileFunc(function () {
            clock.tick(testDelay);
            called = true;
        });

        expect(called).to.equal(true);
        expect(result).to.equal(testDelay);
    });

    it('you should understand how to understand how to use callbacks to handle asynchronicity', function () {
        const value = {};
        let validateTime = false;
        let called = false;
        const testTime = 10000;

        const result = callbacksAnswers.returnWithDelay(value, testTime, function (error, data) {
            called = true;

            expect(error).to.not.be.ok();
            expect(data).to.equal(value);
            expect(validateTime).to.be(true);
        });

        expect(result).to.be(undefined);
        clock.tick(testTime - 1);
        validateTime = true;
        clock.tick(1);
        expect(called).to.be(true);
    });

    it('you should understand how to pass errors from inside an asynchronous callback', function () {
        let validateTime = false;
        let called = false;
        const testTime = 10000;

        const result = callbacksAnswers.failWithDelay(testTime, function (error, data) {
            called = true;

            expect(error).to.be.an(Error);
            expect(validateTime).to.be(true);
        });

        expect(result).to.be(undefined);
        clock.tick(testTime - 1);
        validateTime = true;
        clock.tick(1);
        expect(called).to.be(true);
    });

    it('you should understand how to use promises to wrap APIs that are not promise based', function () {
        const testTime = 10000;
        let resolved = false;
        let rejected = false;
        let rejectedError;

        // monkey patch the global promise object
        const FakePromise = Promise.resolve();
        global.Promise = function (cb) {
            cb(
                function () {
                    resolved = true;
                },
                function (e) {
                    rejected = true;
                    rejectedError = e;
                }
            );

            this.isPromise = Promise;
        };
        global.Promise.resolve = function () {
            resolved = true;
        };
        global.Promise.reject = function (e) {
            rejected = true;
            rejectedError = e;
        };
        // end monkey patching

        let promise = callbacksAnswers.promiseBasedDelay(testTime);
        expect(promise.isPromise || promise).to.equal(Promise);

        clock.tick(testTime - 1);
        expect(resolved).to.be(false);
        expect(rejected).to.be(false);

        clock.tick(1);
        expect(resolved).to.be(true);
        expect(rejected).to.be(false);

        promise = callbacksAnswers.promiseBasedDelay(-1);
        expect(rejected).to.be(true);
        expect(rejectedError).to.be.an(Error);

        promise = callbacksAnswers.promiseBasedDelay();
        expect(rejected).to.be(true);
        expect(rejectedError).to.be.an(Error);
    });
});
