function valForm(){
    if(document.dHelp.uName.value==""){
        alert("Enter User Name");
        document.dHelp.uName.focus();
        return false;
    }
    if(document.dHelp.category.value=="select"){
        alert("Enter Category");
        document.dHelp.category.focus();
        return false;
    }
    if(document.dHelp.address.value==""){
        alert("Enter Address");
        document.dHelp.address.focus();
        return false;
    }
    if(document.dHelp.contact.value==""){
        alert("Enter Phone Number");
        document.dHelp.contact.focus();
        return false;
    }
    if(isNan(document.dHelp.contact.value)){
        alert("Enter Valid Phone No.")
        document.dHelp.contact.focus();
        return false;
    }
    document.dHelp.submit();
}