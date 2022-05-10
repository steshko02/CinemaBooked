let paswReq= document.getElementById("paswReq");

let passwordAndPsConfirm = document.getElementsByClassName("passw");

if(paswReq.value=="false"){
    alert("Bad password!")
}
$('#form').on('submit', (e) => {
    debugger
    if(passwordAndPsConfirm.item(0).value !=passwordAndPsConfirm.item(1).value) {
        e.preventDefault();
        alert("Password not equal");
    }
    // $('#form').submit();
});
