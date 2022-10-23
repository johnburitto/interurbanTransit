let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('transportPassportPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/ui/v1/transport-passports/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('transportPassportPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('transportPassportPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/ui/v1/transport-passports/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('transportPassportPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }