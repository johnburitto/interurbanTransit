function getInterval() {
    let startDay = document.getElementById("startDay").value;
    let endDay = document.getElementById("endDay").value;

    if (startDay.length > 0 && endDay.length > 0) {
        window.location.href = `/ui/v1/booked-places/interval/${startDay}/${endDay}`;
    }
}

document.getElementById("interval").onclick = function () { getInterval(); }
