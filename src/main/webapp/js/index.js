function valForm(){

    if(document.loginForm.uName.value==""){
        alert("Enter the Username");
        document.loginForm.uName.focus();
        return false;
    }

    if(document.loginForm.pass.value==""){
        alert("Enter the Password");
        document.loginForm.pass.focus();
        return false;
    }

    document.loginForm.submit();
}
