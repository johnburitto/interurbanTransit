let driverDisplayFilters = {
    "id": true,
    "personalInf": true,
    "transportCategory": true,
}

function changeDisplay() {
    driverDisplayFilters.id = document.getElementById("id-display-filter").checked;
    driverDisplayFilters.personalInf = document.getElementById("personal-inf-display-filter").checked;
    driverDisplayFilters.transportCategory = document.getElementById("transport-category-display-filter").checked;

    window.location.href = `../filters/${JSON.stringify(driverDisplayFilters)}`;
}

document.getElementById("id-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("personal-inf-display-filter").onchange = function () { changeDisplay(); }
document.getElementById("transport-category-display-filter").onchange = function () { changeDisplay(); }
