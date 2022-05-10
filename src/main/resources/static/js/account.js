let editNameAndEmail = document.getElementsByClassName("editNameAndEmail").item(0);
let editPassword = document.getElementsByClassName("editPassword").item(0);
let logout = document.getElementsByClassName("logout").item(0);
let id = document.getElementById("id");



logout.onclick = function () {
    console.log("LOGOUT")
    window.open('/logout','_self');
}


function sendDataShow(show){
    localStorage.setItem('show', show);
    console.log(localStorage.getItem('show'));
}

function sendDataMovie(movie){
    localStorage.setItem('movie', movie);
    console.log(localStorage.getItem('movie'));
}
editNameAndEmail.onclick = function () {
    window.open('/editUsernameEmail','_self');
}

editPassword.onclick = function () {
    window.open('/editPassword','_self');
}

const  xhrb = new XMLHttpRequest();
xhrb.open("GET", "http://localhost:8080/booking/getByUser/?id="+id.value)
xhrb.send();
xhrb.onload = function () {
    if (xhrb.status === 200) {
        dataBooking = JSON.parse(xhrb.responseText);

        dataBooking.forEach(e=>createBookingContent(e));

        console.log(dataBooking);

    } else if (xhrb.status === 404) {
        console.log("No records found")
    }
}

let list = document.getElementById("list");
list.innerHTML = '';


function getPrice(dataBooking,completeLi) {
    completeLi.innerHTML += '<text style="color: crimson;">Price: </text><text class="price">'+' '+dataBooking.price+'</text></br>';
}

function createBookingContent(dataBooking){

    let completeLi = document.createElement("li");
    completeLi.id =dataBooking.id;
    getHall(dataBooking,completeLi);
    getMovie(dataBooking,completeLi);
    console.log(dataBooking.price);
}

function getHall(dataBooking,completeLi) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/hall/get/?id="+dataBooking.hall)
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            console.log(data.placeName);
            completeLi.innerHTML += '<text  class="hall">'+data.placeName+'</text></br>';
        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}

function getMovie(dataBooking,completeLi) {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/show/get/?id="+dataBooking.movieShow)
    xhr.send();
    xhr.onload = function () {
        if (xhr.status === 200) {
            show = JSON.parse(xhr.responseText);
            let date = show.date;
            let year = date.substr(0,10);
            let time = date.substr(11,5);

            console.log("Время:" +year+' '+time);
            const movieReq = new XMLHttpRequest();
            movieReq.open("GET", "http://localhost:8080/movie/get/?id="+show.movie);
            movieReq.send();
            movieReq.onload = function () {
                if (movieReq.status === 200) {
                    movie = JSON.parse(movieReq.responseText);
                    console.log("movie: " + movie.name);
                    completeLi.innerHTML += '<a href="/moviePage" id="'+movie.id+'" class="time">Movie: '+movie.name+'</a></br>';

                    getPrice(dataBooking,completeLi);


                    completeLi.innerHTML += '<a href="/hall" id="'+show.id+'" class="time">'+"Time: " +year+' '+time+'</a></br>';


                    list.appendChild(completeLi);

                    document.getElementById(movie.id).onclick=function (){
                        sendDataMovie(movie.id);
                        // window.open("/movie",'_self');
                    }

                    document.getElementById(show.id).addEventListener('click',function(){
                        sendDataShow(show.id);
                        // window.open("/hall",'_self');

                    });
                } else if (movieReq.status === 404) {
                    console.log("No records found")
                }
            }
        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}

