<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Routes</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=300" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1110" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of routes</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/ui/v1/routes/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                    <tr>
                        <th id="id">Id</th>
                        <th id="distance">Distance</th>
                        <th id="departure-time">Departure time</th>
                        <th id="arrival-time">Arrival time</th>
                        <th id="city-from">City from</th>
                        <th id="city-to">City to</th>
                        <th id="created-at">Created at</th>
                        <th id="updated-at">Updated at</th>
                        <th>Delete</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list routes as route>
                            <tr>
                                <td class="align-middle hover-td" id="id">${route.id}</td>
                                <td class="align-middle hover-td" id="distance">${route.distance}</td>
                                <td class="align-middle hover-td" id="departureTime">${route.departureTime}</td>
                                <td class="align-middle hover-td" id="arrivalTime">${route.arrivalTime}</td>
                                <td class="align-middle hover-td" id="fromCity">${route.fromCity}</td>
                                <td class="align-middle hover-td" id="toCity">${route.toCity}</td>
                                <td class="align-middle hover-td" id="createdAt">${route.createdAt}</td>
                                <td class="align-middle hover-td" id="updatedAt">${route.updatedAt}</td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/routes/delete/${route.id}'">Delete</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/routes/edit/${route.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>