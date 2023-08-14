function valForm(){
    if(document.dues.uName.value==""){
        alert("Enter User Name");
        document.dues.uName.focus();
        return false;
    }
    if(document.dues.uType.value=="select"){
        alert("Select a User Type");
        document.dues.uType.focus();
        return false;
    }
    if(document.dues.flatNo.value=="select"){
        alert("Select Flat No.");
        document.dues.flatNo.focus();
        return false;
    }
    if(document.dues.duesType.value=="select"){
        alert("Select a Dues Type");
        document.dues.duesType.focus();
        return false;
    }
    if(document.dues.date.value==""){
        alert("Enter the date");
        document.dues.date.focus();
        return false;
    }
    if(document.dues.payable.value==""){
        alert("Enter the Amount");
        document.dues.payable.focus();
        return false;
    }
    document.dues.submit();
}