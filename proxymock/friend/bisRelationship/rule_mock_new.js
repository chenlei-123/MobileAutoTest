/**
 * Created by chenlei on 2017/12/15.
 */

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
console.log("popWindow json:" + popWindow_json)

module.exports = {
        summary: function () {
            return "replace server response data";
        },
        * beforeSendRequest(requestDetail)
        {
            console.log(requestDetail.url)

            const popWindow_json_file = {
                statusCode: 200,
                header: {'Content-Type': 'application/json;charset=UTF-8'},
                body: fs.readFileSync(popWindow_json)
            }
            const local_json_file = {
                statusCode: 200,
                header: {'Content-Type': 'application/json;charset=UTF-8'},
                body: fs.readFileSync(local_json)
            }

            if(requestDetail.url.indexOf('/mybankv21/phpopui/opui/configs/getFloatingWindowInfo')>=0){
                return{
                    response:popWindow_json_file
                };
            }
            if(requestDetail.url.indexOf('/mybankv21/friend/bisRelationship')>=0){
                return{
                    response:local_json_file
                }
            }

        },
};
