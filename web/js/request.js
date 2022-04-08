import {xhrGet} from "./common.js";


export function getViewList() {
    return new Promise((resolve, reject) => {
        const listXhr = xhrGet("/view/list");
        let result;
        listXhr.onreadystatechange = () => {
            if (listXhr.readyState === 4) {
                if (listXhr.status === 200) {
                    let context = listXhr.responseText;
                    result = context.substring(1, context.length - 2).split(",");
                    resolve(result);
                } else {
                    reject({
                        status: listXhr.status,
                        message: "request 　fail",
                        data: listXhr.responseText
                    });
                }
            }
        }
        listXhr.send();
    });
}