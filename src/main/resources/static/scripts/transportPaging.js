let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('transportPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/ui/v1/transports/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('transportPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('transportPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/ui/v1/transports/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('transportPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }