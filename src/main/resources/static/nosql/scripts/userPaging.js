let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('userPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/ui/v1/keys/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('userPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('userPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/ui/v1/keys/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('userPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }