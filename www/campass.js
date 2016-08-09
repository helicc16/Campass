module.exports = {
    setAzimuth: function (successCallback) {
        cordova.exec(successCallback,
        null, // skipping error callback
        "Campass",
        "setAzimuth",
        []);
    }
};