let routeDisplayFilters = {
    "id": true,
    "distance": true,
    "departureTime": true,
    "arrivalTime": true,
    "cityFrom": true,
    "cityTo": true,
}

function changeDisplay() {
    routeDisplayFilters.id = document.getElementById("id-display-filter").checked;
    routeDisplayFilters.distance = document.getElementById("distance-display-filter").checked;
    routeDisplayFilters.departureTime = document.getElementById("departure-time-display-filter").checked;
    routeDisplayFilters.arrivalTime = document.getElementById("arrival-time-display-filter").checked;
    routeDisplayFilters.cityFrom = document.getElementById("city-from-display-filter").checked;
    routeDisplayFilters.cityTo = document.getElementById("city-to-display-filter").checked;

    window.location.href = `../filters/${JSON.stringify(flightDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("distance-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("departure-time-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("arrival-time-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("city-from-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("city-to-display-filter").onchange = function () { changeDisplay(); }