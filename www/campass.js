module.exports = {
    setAzimuth: function (successCallback) {


        var win = function (result) {

            
            var resultString = result;
            successCallback(resultString);
        };

        


        cordova.exec(win,
        null, // skipping error callback
        "Campass",
        "setAzimuth",
        []);
    },
    
    getAString: function (successCallback) {
        cordova.exec(successCallback, null, "Campass", "echo", []);
    }
};
