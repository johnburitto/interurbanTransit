let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('routePage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/sql/ui/v1/routes/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('routePage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('routePage');

    pageNumber++;
    window.location.href = `http://localhost:8080/sql/ui/v1/routes/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('routePage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }