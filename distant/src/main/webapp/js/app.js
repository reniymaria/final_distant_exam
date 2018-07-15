function preventSubmit(e, text) {

    if(!confirm(text)) e.preventDefault();

}

function onFileSubmit(e) {

    var elem = document.getElementById("uploading");

    if(elem.value == '') {
        e.preventDefault();
    }

    return true;
}