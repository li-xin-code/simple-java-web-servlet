import {xhrGet, xhrJsonPost} from "../common.js";

export function register(data) {
    return new Promise((resolve, reject) => {
        const register = xhrJsonPost("/register");
        register.onreadystatechange = () => {
            if (register.readyState === 4) {
                let context = JSON.parse(register.responseText);
                if (register.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        }
        register.send(JSON.stringify(data));
    });
}

export function checkUsername(username) {
    return new Promise((resolve, reject) => {
        const check = xhrGet("/username/" + username);
        check.addEventListener("readystatechange", () => {
            if (check.readyState === 4) {
                let context = JSON.parse(check.responseText);
                if (check.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        check.send();
    });
}