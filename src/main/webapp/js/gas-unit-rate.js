function valForm(){
    if(document.rate.gasRate.value==""){
        alert("Enter Gas Rate");
        document.rate.gasRate.focus();
        return false;
    }
    document.rate.submit();
}