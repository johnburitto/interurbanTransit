let pageSize = 7;
let pageNumber = 0;

function previousPage() {
    pageNumber = localStorage.getItem('routeProfitabilityPage');

    if (pageNumber <= 0) {
        return;
    }

    pageNumber--;
    window.location.href = `http://localhost:8080/ui/v1/route-profitabilities/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('routeProfitabilityPage', pageNumber);
}

function nextPage() {
    pageNumber = localStorage.getItem('routeProfitabilityPage');

    pageNumber++;
    window.location.href = `http://localhost:8080/ui/v1/route-profitabilities/paging/${pageSize}&${pageNumber}`;

    localStorage.setItem('routeProfitabilityPage', pageNumber);
}

document.getElementById("previous").onclick = function () { previousPage(); }
document.getElementById("next").onclick = function () { nextPage(); }