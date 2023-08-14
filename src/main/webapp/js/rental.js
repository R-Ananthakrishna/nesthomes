function valForm(){
    if(document.rent.tenant.value=="select"){
        alert("Select tenant from the list");
        document.rent.tenant.focus();
        return false;
    }
    if(document.rent.flat.value=="select"){
        alert("Select flat from the list");
        document.rent.flat.focus();
        return false;
    }
    document.rent.submit();
}