function valForm(){
    if(document.tenant.tName.value==""){
        alert("Enter Tenant Name");
        document.tenant.tName.focus();
        return false;
    }
    if(document.tenant.address.value==""){
        alert("Enter Address");
        document.tenant.address.focus();
        return false;
    }
    if(document.tenant.contact.value==""){
        alert("Enter Phone Number");
        document.tenant.contact.focus();
        return false;
    }
    if(isNan(document.tenant.contact.value)){
        alert("Enter Valid Phone No.")
        document.tenant.contact.focus();
        return false;
    }
    document.tenant.submit();
}