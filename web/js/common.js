const baseUrl = "http://localhost:8080/simple"

export function xhrGet(url, parameter = {}) {
    let xhr = new XMLHttpRequest();
    const length = Object.keys(parameter).length;
    if (length !== 0) {
        url += "?";
        let count = 0;
        for (let key in parameter) {
            url += key + "=" + parameter[key]
            count++;
            if (count < length) {
                url += "&"
            }
        }
    }
    xhr.open('GET', baseUrl + url);
    return xhr;
}