<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flights</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=2200" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="transport-display-filter" checked/>
    <input type="checkbox" id="driver-display-filter" checked/>
    <input type="checkbox" id="route-display-filter" checked/>
    <input type="checkbox" id="day-display-filter" checked/>
    <input type="checkbox" id="ticket-cost-display-filter" checked/>
    <input type="checkbox" id="flight-status-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of flights</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        style="display: ${perms.create}"
                        onclick="location.href='/ui/v1/flights/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="transport">Transport</th>
                            <th id="driver">Driver</th>
                            <th id="route">Route</th>
                            <th id="day">Day</th>
                            <th id="ticket-cost">Ticket cost</th>
                            <th id="flight-status">Flight Status</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                            <th style="display: ${perms.delete}">Cancel</th>
                            <th style="display: ${perms.edit}">Postpone</th>
                            <th style="display: ${perms.edit}">Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list flights as flight>
                            <tr>
                                <td class="align-middle hover-td" id="id">
                                    <div class="widget-holder">
                                        ${flight.id}
                                        <div class="widget">
                                            <button class="btn btn-outline-dark oswald-bold"
                                                    onclick="location.href='/ui/v1/flights/${flight.id}/${flight.flightStatusToString()}/passengers'">
                                                Passengers
                                            </button>
                                        </div>
                                    </div>
                                </td>
                                <td class="align-middle hover-td" id="transport"
                                    onclick="location.href='/ui/v1/transports/${flight.transport.id}'">
                                    <b>Transport:</b> ${flight.transport.brand} ${flight.transport.model}<br>
                                    <b>Number:</b> ${flight.transport.passport.transportNumber}<br>
                                    <b>Mileage:</b> ${flight.transport.passport.mileage}
                                </td>
                                <td class="align-middle hover-td" id="driver">
                                    <b>Name:</b> ${flight.driver.personalInf.name}<br>
                                </td>
                                <td class="align-middle hover-td" id="route">
                                    <div class="widget-holder">
                                        <p style="margin: 0; padding: 0">
                                            <b>Route:</b> ${flight.route.fromCity}-${flight.route.toCity}<br>
                                            <b>Time:</b> ${flight.route.departureTime}-${flight.route.arrivalTime}
                                        </p>
                                        <div class="widget">
                                            <button class="btn btn-outline-dark oswald-bold"
                                                    onclick="location.href='/ui/v1/flights/canceled/route/${flight.route.id}'">
                                                Canceled
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/ui/v1/flights/waiting/route/${flight.route.id}'">
                                                Waiting
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/ui/v1/flights/completed/route/${flight.route.id}'">
                                                Completed
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/ui/v1/flights/postponed/route/${flight.route.id}'">
                                                Postponed
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/ui/v1/flights/in-road/route/${flight.route.id}'">
                                                In road
                                            </button>
                                        </div>
                                    </div>
                                </td>
                                <td class="align-middle hover-td" id="day">${flight.getDatesInRoad()}</td>
                                <td class="align-middle hover-td" id="ticket-cost">${flight.costOfTicket}</td>
                                <td class="align-middle hover-td" id="flight-status">${flight.flightStatus}</td>
                                <td class="align-middle hover-td" id="created-at">${flight.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${flight.updatedAt}</td>
                                <td class="align-middle" style="display: ${perms.delete}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/flights/cancel/${flight.id}'">Cancel</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/flights/postpone/${flight.id}'">Postpone</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/flights/edit/${flight.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold" style="display: ${perms.columnFilters}">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-transport" for="transport-display-filter">Transport</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-driver" for="driver-display-filter">Driver</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-route" for="route-display-filter">Route</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-day" for="day-display-filter">Day</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-ticket-cost" for="ticket-cost-display-filter">Ticket cost</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-flight-status" for="flight-status-display-filter">Flight status</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/ui/v1/flights/canceled'">Canceled</button>
                <button class="btn btn-outline-dark"
                        onclick="location.href='/ui/v1/flights/waiting'">Waiting</button>
                <button class="btn btn-outline-dark"
                        onclick="location.href='/ui/v1/flights/completed'">Completed</button>
                <button class="btn btn-outline-dark"
                        onclick="location.href='/ui/v1/flights/postponed'">Postponed</button>
                <button class="btn btn-outline-dark"
                        onclick="location.href='/ui/v1/flights/in-road'">In road</button>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/tables'">Home</button>
            </div>
        </div>
    </div>

    <script src="/static/scripts/flightDisplay/changeFlightDisplayFilter.js?version=400"></script>
</body>
</html>