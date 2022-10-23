let driverDisplayFilters = {
    "id": true,
    "transportCategory": true,
    "numberOfPlaces": true,
    "company": true
}

function changeDisplay() {
    driverDisplayFilters.id = document.getElementById("id-display-filter").checked;
    driverDisplayFilters.transportCategory = document.getElementById("transport-category-display-filter").checked;
    driverDisplayFilters.numberOfPlaces = document.getElementById("number-of-places-display-filter").checked;
    driverDisplayFilters.company = document.getElementById("company-display-filter").checked;

    window.location.href = `../filters/${JSON.stringify(driverDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("transportCategory-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("number-of-places-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("company-display-filter").onchange = function () { changeDisplay(); }
