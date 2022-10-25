let pageSize = 5;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('flightPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/sql/tables/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('flightPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('flightPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/sql/tables/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('flightPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }