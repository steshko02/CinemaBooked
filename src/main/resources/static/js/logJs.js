let registr = document.getElementById("reg");

registr.addEventListener('click',function (){
   window.open('/registration','_self')
});
var passw = document.getElementsByClassName('pasw').item(0);
var input = document.getElementsByClassName('user-input').item(0);

let  valid = true;
$('#form').on('submit', (e) => {
   if(passw.value.length<8) {
      e.preventDefault();
   }
   if(input.value.length<6) {
      e.preventDefault();
   }
   // $('#form').submit();
});