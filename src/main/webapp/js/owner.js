$(document).ready(function() {
    $(document.owner).submit(function() {
        $('.alert').show()
    })
});

function valForm(){
    if(document.owner.oName.value==""){
        alert("Enter Owner Name");
        document.owner.oName.focus();
        return false;
    }
    if(document.owner.address.value==""){
        alert("Enter Address");
        document.owner.address.focus();
        return false;
    }
    if(document.owner.contact.value==""){
        alert("Enter Phone Number"); 
        document.owner.contact.focus();
        return false;
    }
    if(isNan(document.owner.contact.value)){
        alert("Enter Valid Phone No.")
        document.owner.contact.focus();
        return false;
    }

    document.owner.submit();
   
    
}