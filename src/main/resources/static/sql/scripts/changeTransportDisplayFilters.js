let transportDisplayFilters = {
    "id": true,
    "brand": true,
    "model": true,
    "passport": true,
    "numberOfPlaces": true
}

function changeDisplay() {
    transportDisplayFilters.id = document.getElementById("id-display-filter").checked;
    transportDisplayFilters.brand = document.getElementById("brand-display-filter").checked;
    transportDisplayFilters.model = document.getElementById("model-display-filter").checked;
    transportDisplayFilters.passport = document.getElementById("passport-display-filter").checked;
    transportDisplayFilters.numberOfPlaces = document.getElementById("number-of-places-display-filter").checked;

    window.location.href = `../filters/${JSON.stringify(transportDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("brand-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("model-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("passport-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("number-of-places-display-filter").onchange = function () { changeDisplay(); }