let flightDisplayFilters = {
    "id": true,
    "transport": true,
    "driver": true,
    "route": true,
    "day": true,
    "ticketCost": true,
    "flightStatus": true
}

function changeDisplay() {
    flightDisplayFilters.id = document.getElementById("id-display-filter").checked;
    flightDisplayFilters.transport = document.getElementById("transport-display-filter").checked;
    flightDisplayFilters.driver = document.getElementById("driver-display-filter").checked;
    flightDisplayFilters.route = document.getElementById("route-display-filter").checked;
    flightDisplayFilters.day = document.getElementById("day-display-filter").checked;
    flightDisplayFilters.ticketCost = document.getElementById("ticket-cost-display-filter").checked;
    flightDisplayFilters.flightStatus = document.getElementById("flight-status-display-filter").checked;

    window.location.href = `/ui/v1/flights/filters/${JSON.stringify(flightDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("transport-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("driver-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("route-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("day-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("ticket-cost-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("flight-status-display-filter").onchange = function () { changeDisplay(); }
