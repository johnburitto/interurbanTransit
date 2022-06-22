<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Route profitabilities</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=300" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1810" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="route-display-filter" checked/>
    <input type="checkbox" id="start-day-display-filter" checked/>
    <input type="checkbox" id="end-day-display-filter" checked/>
    <input type="checkbox" id="number-of-passengers-display-filter" checked/>
    <input type="checkbox" id="profitability-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of routes</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/ui/v1/route-profitabilities/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="route">Route</th>
                            <th id="start-day">Start day</th>
                            <th id="end-day">End day</th>
                            <th id="number-of-passengers">Number of passengers</th>
                            <th id="profitability">Profitability</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list rps as rp>
                            <tr>
                                <td class="align-middle hover-td" id="id">${rp.id}</td>
                                <td class="align-middle hover-td" id="route">
                                    <b>Route:</b> ${rp.route.fromCity}-${rp.route.toCity}<br>
                                    <b>Time:</b> ${rp.route.departureTime}-${rp.route.arrivalTime}
                                </td>
                                <td class="align-middle hover-td" id="start-day">${rp.startDay}</td>
                                <td class="align-middle hover-td" id="end-day">${rp.endDay}</td>
                                <td class="align-middle hover-td" id="number-of-passengers">${rp.numberOfPassengers}</td>
                                <td class="align-middle hover-td" id="profitability">${rp.profitability}</td>
                                <td class="align-middle hover-td" id="created-at">${rp.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${rp.updatedAt}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-route" for="route-display-filter">Route</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-start-day" for="start-day-display-filter">Start day</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-end-day" for="end-day-display-filter">End day</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-number-of-passengers" for="number-of-passengers-display-filter">Number of passengers</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-profitability" for="profitability-display-filter">Profitability</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
        </div>
    </div>
</body>
</html>