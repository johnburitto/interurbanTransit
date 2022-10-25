let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('driverPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/sql/ui/v1/drivers/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('driverPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('driverPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/sql/ui/v1/drivers/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('driverPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }