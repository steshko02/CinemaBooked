
let ulGenres = document.getElementById("genres");
let deleteButts = document.getElementsByClassName("deleteGenre");
let editButts = document.getElementsByClassName("editGenre");
let editSeats = document.getElementsByClassName("editSeats");
let add = document.getElementsByClassName("add").item(0);
let roleUser = document.getElementById("roleUser");

if(roleUser.value=="ROLE_ADMIN") {
    let navLi = document.createElement("li");
    navLi.innerHTML = '<a href="/admin">Admin page</a>';
    document.getElementById("navli").appendChild(navLi);
}

function saveGenre(data) {
    xhr.open('POST', 'http://localhost:8080/hall/save', true);
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

add.onclick = function() {
    let completeLi = document.createElement("li");
    completeLi.innerHTML = '<input style ="color:black;" type="text"  value=""> <div class=\'deleteMe\'><text class="deleteGenre" >&#10006;</text><text class="editGenre">&#x2714;</text></div>';
    ulGenres.appendChild(completeLi);
    ulGenres.childNodes[ulGenres.childNodes.length - 1].childNodes[0].focus();

    console.log(deleteButts);

    deleteButts[deleteButts.length - 1].onclick = function () {
        this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
    }

    editButts[editButts.length - 1].onclick = function () {
        debugger
        console.log(deleteButts);

        if (this.parentNode.parentNode.childNodes[0].hasAttribute("disabled")) {
            this.parentNode.parentNode.childNodes[0].removeAttribute("disabled");
            this.parentNode.parentNode.childNodes[0].focus();
            this.innerHTML = "&#x2714";
        } else {
            debugger
            this.parentNode.parentNode.childNodes[0].setAttribute("disabled", "disabled");
            this.parentNode.parentNode.childNodes[0].blur();
            let genreId = this.parentNode.childNodes[0].id;
            this.innerHTML = "&#x270E";
            genre ={
                placeName: this.parentNode.parentNode.childNodes[0].value,
                seatsCount: 50,
            }
            saveGenre(JSON.stringify(genre));
        }
        location.reload();
    }
}
function deleteGenreRequest(id){
    xhr.open('DELETE', "http://localhost:8080/hall/delete/?id="+id, true);

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log("All is OK!")
                location.reload();
            } else {
                console.log("Something went wrong!")
            }
        }
    };
    xhr.send();
}

function editGenre(value,id) {
    xhr.open('PUT', "http://localhost:8080/hall/edit/"+id, true);

    debugger
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log("All is OK!")
            } else {
                console.log("Something went wrong!")
            }
        }
    };

    hall ={
        placeName: value,
        seatsCount: 50,
    }
    xhr.send(JSON.stringify(hall));
}

function addListeners() {
    for (i = 0, len = deleteButts.length; i < len; i++) {

        console.log(deleteButts)
        deleteButts[i].onclick = function () {
            if (confirm("Do you really want to delete this hall?")) {
                this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
                deleteGenreRequest(this.id);
            }
        }

        editSeats[i].onclick = function () {
            if (confirm("Do you really want to edit this hall?")) {
                debugger
                console.log(this.parentNode.childNodes[0].id);
                sendData(this.parentNode.childNodes[0].id);
                window.open("/edit-hall");
            }
        }

        editButts[i].onclick = function () {
            if (this.parentNode.parentNode.childNodes[0].hasAttribute("disabled")) {
                this.parentNode.parentNode.childNodes[0].removeAttribute("disabled");
                this.parentNode.parentNode.childNodes[0].focus();
                this.parentNode.parentNode.childNodes[0].setSelectionRange(this.parentNode.parentNode.childNodes[0].value.length,
                    this.parentNode.parentNode.childNodes[0].value.length);
                this.innerHTML = "&#x2714";
            } else {
                debugger
                this.parentNode.parentNode.childNodes[0].setAttribute("disabled", "disabled");
                this.parentNode.parentNode.childNodes[0].blur();
                let genreId = this.parentNode.childNodes[0].id;
                this.innerHTML = "&#x270E";
                editGenre(this.parentNode.parentNode.childNodes[0].value, parseInt(genreId));
            }
            // this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
        }
    }
}
function sendData(hall){
    localStorage.setItem('hallToEdit', hall);
    console.log(localStorage.getItem('hallToEdit'));
}

function loadGenres(genre) {
    let completeLi = document.createElement("li");
    completeLi.innerHTML = '<input style ="color:black;" type="text" disabled="disabled" value="' + genre.placeName + '"> <div class=\'deleteMe\'><text class="deleteGenre" id="' + genre.id + '">&#10006;</text><text class="editGenre">&#x270E;</text><text class="editSeats">seats</text></div>';
    ulGenres.appendChild(completeLi);
}

function loadFullGenres(genre) {
    genre.forEach(element => {
        loadGenres(element);
    });
}

const xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8080/hall/getAll")
xhr.send();
xhr.onload = function () {
    if (xhr.status === 200) {
        data = JSON.parse(xhr.responseText);
        console.log(data);
        let i = 0;
        loadFullGenres(data);
        addListeners();
        i++;
    } else if (xhr.status === 404) {
        console.log("No records found")
    }
}

