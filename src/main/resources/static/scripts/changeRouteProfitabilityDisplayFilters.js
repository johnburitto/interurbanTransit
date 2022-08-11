let routeDisplayFilters = {
    "id": true,
    "route": true,
    "startDay": true,
    "endDay": true,
    "numberOfPassengers": true,
    "profitability": true,
}

function changeDisplay() {
    routeDisplayFilters.id = document.getElementById("id-display-filter").checked;
    routeDisplayFilters.route = document.getElementById("route-display-filter").checked;
    routeDisplayFilters.startDay = document.getElementById("start-day-display-filter").checked;
    routeDisplayFilters.endDay = document.getElementById("end-day-display-filter").checked;
    routeDisplayFilters.numberOfPassengers = document.getElementById("number-of-passengers-display-filter").checked;
    routeDisplayFilters.profitability = document.getElementById("profitability-display-filter").checked;

    window.location.href = `/ui/v1/route-profitability/filters/${JSON.stringify(flightDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("route-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("start-day-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("end-day-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("number-of-passengers-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("profitability-display-filter").onchange = function () { changeDisplay(); }
