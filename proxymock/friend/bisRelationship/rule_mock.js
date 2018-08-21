var options = process.argv
for (var i = 0; i < options.length; i++) {
    console.log("xxxxxxxxxx  " + options[i]);
}
console.log(__dirname);
var fs = require("fs");
var path = require('path');
var local_json = __dirname + "/" + options[4]
console.log("time : " + local_json)

var popWindow_json = __dirname + "/popWindow.json"
console.log("friend json:" + popWindow_json)
module.exports = {
    summary: function () {
        return "replace server response data";
    },

    shouldUseLocalResponse: function (req, reqBody) {
        if (/\/mybankv21\/friend\/bisRelationship/.test(req.url)) {
            console.log('=====================url interface be proxy==================')
            req.replaceLocalFile = 0;
            return true;
        } else if (/\/mybankv21\/phpopui\/opui\/configs\/getFloatingWindowInfo/.test(req.url)) {
            console.log('=====================url interface be proxy==================')
            req.replaceLocalFile = 2;
            return true;
        }
        return false;
    },
    dealLocalResponse: function (req, reqBody, callback) {
        if (req.replaceLocalFile == 0) {
            callback(200, {"content-type": "application/json;charset=UTF-8"}, fs.readFileSync(local_json));
        } else if (req.replaceLocalFile == 2) {
            callback(200, {"content-type": "application/json;charset=UTF-8"}, fs.readFileSync(popWindow_json));
        }

    }

};