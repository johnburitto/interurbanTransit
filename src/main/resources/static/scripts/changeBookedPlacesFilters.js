let bookedPlacesDisplayFilters = {
    "id": true,
    "flight": true,
    "passenger": true,
    "dayOfBooking": true,
    "status": true,
}

function changeDisplay() {
    bookedPlacesDisplayFilters.id = document.getElementById("id-display-filter").checked;
    bookedPlacesDisplayFilters.flight = document.getElementById("flight-display-filter").checked;
    bookedPlacesDisplayFilters.passenger = document.getElementById("passenger-display-filter").checked;
    bookedPlacesDisplayFilters.dayOfBooking = document.getElementById("day-of-booking-display-filter").checked;
    bookedPlacesDisplayFilters.status = document.getElementById("status-display-filter").checked;

    window.location.href = `/ui/v1/booked-places/filters/${JSON.stringify(flightDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("flight-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("passenger-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("day-of-booking-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("status-display-filter").onchange = function () { changeDisplay(); }
