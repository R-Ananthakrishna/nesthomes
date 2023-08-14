function valForm(){
    if(document.flat.flatNo.value==""){
        alert("Enter Flat No.");
        document.flat.flatNo.focus();
        return false;
    }
    if(document.flat.flatType.value==""){
        alert("Enter Flat Type");
        document.flat.flatType.focus();
        return false;
    }
    if(document.flat.flatTower.value==""){
        alert("Enter Flat Tower");
        document.flat.flatTower.focus();
        return false;
    }

    document.flat.submit();
}