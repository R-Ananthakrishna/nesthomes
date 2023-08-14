function valForm(){
    if(document.gasUsage.flat.value=="select"){
        alert("Select flat no. from the list");
        document.gasUsage.flat.focus();
        return false;
    }
    if(document.gasUsage.unit.value==""){
        alert("Enter Gas Unit");
        document.gasUsage.unit.focus();
        return false;
    }
    document.gasUsage.submit();
}