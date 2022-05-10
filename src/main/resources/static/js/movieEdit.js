let genresToPost = document.getElementById("select1");
let genres = document.getElementById("select2");
let save = document.getElementsByClassName("link2");
document.addEventListener("DOMContentLoaded", loadPage);
let id = localStorage.getItem('movie');
let roleUser = document.getElementById("roleUser");

if(roleUser.value=="ROLE_ADMIN") {
    let navLi = document.createElement("li");
    navLi.innerHTML = '<a href="/admin">Admin page</a>';
    document.getElementById("navli").appendChild(navLi);
}

function loadPage() {
    let id = localStorage.getItem('movie');
    console.log(id);
}

let allGenres = [];

const xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8080/genre/getAll")
xhr.send();
xhr.onload = function () {
    if (xhr.status === 200) {
        let data = JSON.parse(xhr.responseText);
        allGenres=data;
        // data.forEach(e=>addSelect(e.name));
        console.log(data);
        let i = 0;
        getMovieById(id);

        save.item(0).addEventListener("click", function () {
            indexs = [];
            debugger
            data.forEach(e=>{
                for (let j = 0; j < genresToPost.length; j++) {
                    if(e.name == genresToPost[j].value){
                        indexs.push(e.id);
                    }
                }
            });
            console.log(indexs);

            let name =document.getElementById("name").value;
            let rating = parseFloat(document.getElementById("rating").value);
            let hours =  parseInt(document.getElementById("hours").value);
            let minutes =  parseInt((document.getElementById("mnts").value));
            let imgUrl  = document.getElementById("imgUrl").value;
            let trailerUrl = document.getElementById("urlTrailer").value;
            let description = document.getElementById("description").value;

            debugger
            let movie = {
                name: name,
                rating:rating,
                duration: hours*60+minutes,
                imgUrl: imgUrl,
                trailerUrl:trailerUrl,
                genres:  indexs,
                description: description,
            }

            movieEdit(JSON.stringify(movie));
        });
        i++;
    } else if (xhr.status === 404) {
        console.log("No records found")
    }
}
function getMovieById(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/movie/get/?id="+id)
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            setData(data);

            let movieGenres = [];
            let afterFilter = [];

            afterFilter = allGenres.filter((el) =>!data.genres.includes(el.id));

            movieGenres = allGenres.filter((el) =>data.genres.includes(el.id));


            afterFilter.forEach(e=>addSelect(e.name));
            movieGenres.forEach(e=>addSelectToPost(e.name));
        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}


// function createGenres(genres){
//     console.log(genres);
//
//     addSelectToPost(data.name);
//     // for (let i = 0; i < genres.length; i++) {
//     //     getGenreById(genres[i]);
//     // }
// }
function  setData(movie){

    document.getElementById("name").value=movie.name;
    document.getElementById("rating").value=movie.rating;
    hours = parseInt(movie.duration/60);
    minutes = movie.duration-hours*60;
    document.getElementById("hours").value=hours;
    document.getElementById("mnts").value=minutes;
    document.getElementById("imgUrl").value = movie.imgUrl;
    document.getElementById("urlTrailer").value = movie.trailerUrl;
    document.getElementById("description").value = movie.description;
    // let description = document.getElementById("description").value;


}

function movieEdit(data){

    const xhr = new XMLHttpRequest();
    xhr.open('PUT', 'http://localhost:8080/movie/edit/'+id , true);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
    debugger
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log("All is OK!")
            } else {
                console.log("Something went wrong!")
            }
        }
    };
}
$("#select2 option:selected").remove();

function  deleteFromAllGenres(){

}

function getGenreById(id) {
    debugger
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/genre/get/?id="+id)
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            console.log(data);

        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}

$( "#select2" )
    .change(function () {
        // i = 0;
        var str = "";
        $( "#select2 option:selected" ).each(function() {
            str = $( this ).text();
            let opt = document.createElement('option');
            opt.value = str;
            opt.innerHTML = str;
            genres.options[genres.selectedIndex].remove();
            genresToPost.appendChild(opt);
        });
    })
    .change();

$("#select1 option:selected").remove();

$( "#select1" )
    .change(function () {
        // i = 0;
        var str = "";
        $( "#select1 option:selected" ).each(function() {
            str = $( this ).text();
            let opt = document.createElement('option');
            opt.value = str;
            opt.innerHTML = str;
            genresToPost.options[genresToPost.selectedIndex].remove();
            genres.appendChild(opt);
        });
    })
    .change();



function addSelect(genre){
    // let opt = document.createElement('option');
    // opt.value = genre;
    // opt.innerHTML = genre;
    // genres.appendChild(opt);
    $("#select2").append('<option value='+genre+'>'+genre+'</option>');

}


function addSelectToPost(genre){
    $("#select1").append('<option value='+genre+'>'+genre+'</option>');

}
function update(){
    $('#select1').select2({

    });
    $('#select2').select2({

    });
}