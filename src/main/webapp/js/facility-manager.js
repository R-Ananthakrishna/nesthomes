function valForm(){
    if(document.fManager.uName.value==""){
        alert("Enter User Name");
        document.fManager.uName.focus();
        return false;
    }
    if(document.fManager.designation.value==""){
        alert("Enter Designation");
        document.fManager.designation.focus();
        return false;
    }
    if(document.fManager.duties.value==""){
        alert("Enter Duties");
        document.fManager.duties.focus();
        return false;
    }
    if(document.fManager.contact.value==""){
        alert("Enter Phone Number");
        document.fManager.contact.focus();
        return false;
    }
    if(isNan(document.fManager.contact.value)){
        alert("Enter Valid Phone No.")
        document.fManager.contact.focus();
        return false;
    }
    document.fManager.submit();
}