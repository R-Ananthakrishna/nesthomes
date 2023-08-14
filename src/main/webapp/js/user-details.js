function valForm(){
    if(document.user.uName.value==""){
        alert("Enter User Name");
        document.user.uName.focus();
        return false;
    }
    if(document.user.pass.value==""){
        alert("Enter Password");
        document.user.pass.focus();
        return false;
    }
    if(document.user.uType.value=="select"){
        alert("Select a User Type");
        document.user.uType.focus();
        return false;
    }

    document.owner.submit();
}