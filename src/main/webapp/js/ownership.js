function valForm(){
    if(document.oShip.owner.value=="select"){
        alert("Select owner from the list");
        document.oShip.owner.focus();
        return false;
    }
    if(document.oShip.flat.value=="select"){
        alert("Select flat from the list");
        document.oShip.flat.focus();
        return false;
    }
    document.oShip.submit();
}