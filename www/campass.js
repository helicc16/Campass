module.exports = {
    setAzimuth: function (successCallback) {


        var win = function (result) {

            alert('Hello');
            var resultString = result;
            successCallback(resultString);
        };

        var fail = function () {
            alert('Fail to return result');
            console.log('Fail to return result');
        };


        cordova.exec(win,
        fail, // skipping error callback
        "Campass",
        "setAzimuth",
        []);
    },
    getAString: function (successCallback) {
        cordova.exec(successCallback, null, "Campass", "echo", []);
    }
};
