<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Start page</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
</head>
<body class="start-page-app-container">
    <div class="start-page-container">
        <div class="width-100" style="display: flex; justify-content: flex-end; align-items: baseline">
            <p class="oswald-bold" style="margin-right: 10px; font-size: 20px">
                ${perms.type}
            </p>
            <button class="btn btn-outline-dark oswald-bold start-page-button"
                    style="font-size: 16px"
                    onclick="location.href='/logout'">Log out</button>
        </div>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/transports/'">Transports</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/drivers/'">Drivers</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/routes/'">Routes</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/passengers/'">Passengers</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/flights/'">Flights</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/booked-places/'">Booked places</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/route-profitabilities/'">Profitabilities</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/transport-passports/'">Transport passports</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/ui/v1/working-books/'">Working books</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                style="display: ${perms.keys}"
                onclick="location.href='/ui/v1/keys/'">Keys</button>
    </div>
</body>
</html>