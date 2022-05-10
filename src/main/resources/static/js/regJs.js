let nameAndEmail = document.getElementsByClassName("user-input");

let passwordAndPsConfirm = document.getElementsByClassName("passw");

let submit = document.getElementById("submit");

let isNewUser= document.getElementById("usReq");
let mailReq= document.getElementById("mailReq");


if(isNewUser.value=="false"){
    alert("User with this name already exists!\n Input new username!")
}
if(mailReq.value=="false"){
    alert("User with this email already exists!\n Input new email!")
}
$('#form').on('submit', (e) => {
    debugger
    if(passwordAndPsConfirm.item(0).value !=passwordAndPsConfirm.item(1).value) {
        e.preventDefault();
        alert("Password not equal");
    }
    // $('#form').submit();
});