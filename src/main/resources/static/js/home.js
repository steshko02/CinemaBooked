
let btn = document.getElementsByClassName("wrapper");
let ulList = document.getElementById("movie-ul");
let premiere = document.getElementById("premiere");

let contents = document.getElementById("contents");

let movies = [];

let movieByIds;

for (let i = 0; i < btn.length; i++) {
    btn.item(i).addEventListener("click", function () {
        console.log("TAG"+j);
    });
}

document.addEventListener("DOMContentLoaded", loadPage);

function loadPage() {
    ulList.innerHTML = '';
    getMovies();
    getBest();
    console.log(ulList)
    console.log(movies.length)
    // listenerLi();

}

// function listenerLi() {
//     console.log(movies.length);
//     for (let i = 0; i < ulList.childNodes.length; i++) {
//         movieByIds.add(document.getElementById(movies[i].id));
//         console.log(movies.id)
//     }
// }

function  loadFullMovie(moviess) {
    moviess.forEach(element => {
        addMovie(element);
        movies.push(element);
    });
    console.log(movies.length)
}

function getEventTarget(e) {
    e = e || window.event;
    return e.target || e.srcElement;
}
ulList.onclick = function(event) {
    let target = getEventTarget(event);
    console.log(target);

    if(target.classList.contains("span-movie")){
          alert(target.tagName);
        let id =target.parentNode.parentNode.parentNode.parentNode.id;
        console.log('ID='+id);
        sendData(id);
    }
};

function sendData(movie){
    localStorage.setItem('movie', movie);
    console.log(localStorage.getItem('movie'));
}

function addMovie(movie) {
    let id = movie.id;
    let name = movie.name;
    let description = movie.description;
    let rating = movie.rating;

    let img = movie.imgUrl;
    if(img==null) img = "https://avatars.mds.yandex.net/get-kinopoisk-image/1900788/f2848b86-dfe5-4273-8593-94ca3456830c/300x450";

    console.log(name);

    let completeLi = document.createElement("li");
    completeLi.classList.add("button-movie");
    completeLi.id = id;
    completeLi.innerHTML = '<h4>'+name +'<b style="color:red">&nbsp&nbsp&nbsp&nbsp&nbsp'+rating+'&nbsp&nbsp&#10029;</b></h4><img src="'+img+'" alt="" height="190px" width="286px"/>' +
        '<p>'+description+'</p>' +
        '<div class="wrapper button-movie-infoot"><a href="movie.html" class="link2"><span><span class="span-movie">Read More</span></span></a></div>';
    ulList.appendChild(completeLi);
}
//http://localhost:8080/moviePage

function getMovies() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8080/movie/getAll")
        xhr.send();
          xhr.onload = function () {
            if (xhr.status === 200) {
                data = JSON.parse(xhr.responseText);
                console.log(data);
                loadFullMovie(data);
            } else if (xhr.status === 404) {
                console.log("No records found")
            }
        }
    }

    function getBest() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8080/movie/bestMovie")
        xhr.send();

        xhr.onload = function () {
            if (xhr.status === 200) {
                data = JSON.parse(xhr.responseText);
                // console.log(data);
                loadBestMovie(data);
                // console.log(movies);
            } else if (xhr.status === 404) {
                console.log("No records found")
            }
        }
    }


function getMovieById(id) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/movie/get/?id="+id)
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            console.log(data);
            sendData(data);
            // console.log(movies);
        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}


    function loadBestMovie(movie) {
        let name = movie.name;
        let description = movie.description;
        let rating = movie.rating;
        let img = movie.imgUrl;
        if(img==null) img = "https://avatars.mds.yandex.net/get-kinopoisk-image/1900788/f2848b86-dfe5-4273-8593-94ca3456830c/300x450";
        let div = '<h3>Best premiere <b>in our</b> <span>Cinema</span></h3>\n' +
            ' <h4> <b></b> <span>'+name+'</span><b>&nbsp&nbsp&nbsp&nbsp&nbsp'+rating+'&nbsp&nbsp&#10029;</b></h4>\n' +
            '<div class="img-box1"><img src="'+img+'" alt="" height="190px" width="286px" />'+description+'</div>\n' +
            '<div class="wrapper"><a href="http://localhost:8080/moviePage" class="link2"><span><span>Read More</span></span></a></div>\n';
        premiere.innerHTML=div;
    }
    //http://localhost:8080/moviePage



    function addLastLi() {
        let completeLi = document.createElement("li");
        completeLi.classList.add("clear");
        completeLi.innerHTML = '&nbsp;';
        ulList.appendChild(completeLi);
    }