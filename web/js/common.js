export function xhrGet(url) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    return xhr;
}