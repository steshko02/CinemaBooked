let isNewUser= document.getElementById("usReq");
let mailReq= document.getElementById("mailReq");
let paswReq= document.getElementById("paswReq");

debugger
if(paswReq.value=="false"){
    alert("Password not equal!")
}
if(isNewUser.value=="false"){
    alert("User with this name already exists!\n Input new username!")
}
if(mailReq.value=="false"){
    alert("User with this email already exists!\n Input new email!")
}

$('#form').on('submit', (e) => {

});