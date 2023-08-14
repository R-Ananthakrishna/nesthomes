function valForm(){
    if(document.category.categoryName.value==""){
        alert("Enter Category Name");
        document.category.categoryName.focus();
        return false;
    }
    document.category.submit();
}