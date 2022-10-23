let pageSize = 5;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('bookedPlacePage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/ui/v1/booked-places/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('bookedPlacePage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('bookedPlacePage');

    pageNumber++;
    window.location.href = `http://localhost:8080/ui/v1/booked-places/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('bookedPlacePage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }