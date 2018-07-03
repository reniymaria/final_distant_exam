function preventSubmit(e, text) {

    if(!confirm(text)) e.preventDefault();

}