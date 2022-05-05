let mainImg=document.getElementById("main-img");


document.addEventListener("DOMContentLoaded", loadPage);

function loadPage() {
}

function getMovie() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/movie/")
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            console.log(data);
            loadBestMovie(data);
            // console.log(movies);
        } else if (xhr.status === 404) {
            console.log("No records found")
        }
    }
}