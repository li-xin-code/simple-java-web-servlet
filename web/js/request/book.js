import {xhrGet, xhrJsonDel, xhrJsonPost, xhrJsonPut} from "./common.js";

export function books() {
    return new Promise((resolve, reject) => {
        const request = xhrGet("/book")
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send();
    });
}

export function del(uuid) {
    return new Promise((resolve, reject) => {
        const request = xhrJsonDel("/book")
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send(JSON.stringify({uuid}));
    });
}

export function add(name) {
    return new Promise((resolve, reject) => {
        const request = xhrJsonPost("/book")
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send(JSON.stringify({name}));
    });
}

export function rename(uuid, name) {
    return new Promise((resolve, reject) => {
        const request = xhrJsonPut("/book")
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send(JSON.stringify({uuid, name}));
    });
}