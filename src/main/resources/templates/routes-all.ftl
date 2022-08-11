<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Routes</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1310" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" ${filters[0]}/>
    <input type="checkbox" id="distance-display-filter" ${filters[1]}/>
    <input type="checkbox" id="departure-time-display-filter" ${filters[2]}/>
    <input type="checkbox" id="arrival-time-display-filter" ${filters[3]}/>
    <input type="checkbox" id="city-from-display-filter" ${filters[4]}/>
    <input type="checkbox" id="city-to-display-filter" ${filters[5]}/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of routes</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        style="display: ${perms.create}"
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
                            <th style="display: ${perms.delete}">Delete</th>
                            <th style="display: ${perms.edit}">Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list routes as route>
                            <tr>
                                <td class="align-middle hover-td" id="id">${route.id}</td>
                                <td class="align-middle hover-td" id="distance">${route.distance}</td>
                                <td class="align-middle hover-td" id="departure-time">${route.departureTime}</td>
                                <td class="align-middle hover-td" id="arrival-time">${route.arrivalTime}</td>
                                <td class="align-middle hover-td" id="city-from">${route.fromCity}</td>
                                <td class="align-middle hover-td" id="city-to">${route.toCity}</td>
                                <td class="align-middle hover-td" id="created-at">${route.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${route.updatedAt}</td>
                                <td class="align-middle" style="display: ${perms.delete}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/routes/delete/${route.id}'">Delete</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/routes/edit/${route.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold" style="display: ${perms.columnFilters}">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-distance" for="distance-display-filter">Distance</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-departure-time" for="departure-time-display-filter">Departure time</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-arrival-time" for="arrival-time-display-filter">Arrival time</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-city-from" for="city-from-display-filter">City from</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-city-to" for="city-to-display-filter">City to</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/tables'">Home</button>
            </div>
        </div>
    </div>

    <script src="/static/scripts/changeRouteDisplayFilters.js?version=400"></script>
</body>
</html>