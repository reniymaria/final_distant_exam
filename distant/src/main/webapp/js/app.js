function preventSubmit(e, text) {

    if(!confirm(text)) e.preventDefault();

}

function onFileSubmit(e, text) {

    var elem = document.getElementById("uploading");
    var value = elem.value;

    if(value == '') {
        e.preventDefault();
    }

    if(value != '' && !(/\.xlsx$/.test(value) || /\.xls$/.test(value))) {
        e.preventDefault();
        alert(text);
    }

    return true;
}