<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Start page</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/sql/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/sql/css/display-filters.css?version=2200" type="text/css" rel="stylesheet"/>
</head>
<body class="start-page-app-container">
    <div class="start-page-container" style="margin-left: 10px; margin-top: 7%; display: ${perms.allTables}">
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/sql/ui/v1/transports/paging/7&0'">Transports</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/sql/ui/v1/drivers/paging/7&0'">Drivers</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/sql/ui/v1/routes/paging/7&0'">Routes</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/sql/ui/v1/booked-places/paging/5&0'">Booked places</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                onclick="location.href='/sql/ui/v1/route-profitabilities/paging/7&0'">Profitabilities</button>
        <button class="btn btn-outline-dark oswald-bold start-page-button"
                style="display: ${perms.keys}"
                onclick="location.href='/sql/ui/v1/keys/paging/7&0'">Keys</button>
    </div>
    <div class="start-page-container width-100" style="width: 150%; justify-content: flex-start">
        <div class="width-100" style="display: flex; justify-content: flex-end; align-items: baseline; padding-right: 5%">
            <p class="oswald-bold" style="margin-right: 10px; font-size: 20px"
               onclick="location.href='/personal-office'">
                ${user.firstName} ${user.middleName} ${user.lastName} (${perms.type})
            </p>
            <button class="btn btn-outline-dark oswald-bold start-page-button"
                    style="font-size: 16px"
                    onclick="location.href='/logout'">Log out</button>
        </div>
        <input type="checkbox" id="id-display-filter" ${filters[0]}/>
        <input type="checkbox" id="transport-display-filter" ${filters[1]}/>
        <input type="checkbox" id="driver-display-filter" ${filters[2]}/>
        <input type="checkbox" id="route-display-filter" ${filters[3]}/>
        <input type="checkbox" id="day-display-filter" ${filters[4]}/>
        <input type="checkbox" id="ticket-cost-display-filter" ${filters[5]}/>
        <input type="checkbox" id="flight-status-display-filter" ${filters[6]}/>
        <input type="checkbox" id="created-at-display-filter"/>
        <input type="checkbox" id="updated-at-display-filter"/>
        <div class="start-page-app-container">
            <div class="table-container" style="width: 90%">
                <input class="custom-checkbox" type="checkbox" id="list" checked/>
                <label for="list" class="width-100 table-name oswald-bold">
                    <h1>Table of flights</h1>
                    <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                            style="display: ${perms.create}"
                            onclick="location.href='/sql/ui/v1/flights/add'">Create</button>
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
                            <th>Book</th>
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
                                                    onclick="location.href='/sql/ui/v1/flights/${flight.id}/${flight.flightStatusToString()}/passengers'">
                                                Passengers
                                            </button>
                                        </div>
                                    </div>
                                </td>
                                <td class="align-middle hover-td" id="transport"
                                    onclick="location.href='/sql/ui/v1/transports/${flight.transport}'">
                                        ${flight.transport}
                                </td>
                                <td class="align-middle hover-td" id="driver">
                                    ${flight.driver}
                                </td>
                                <td class="align-middle hover-td" id="route">
                                    <div class="widget-holder">
                                        <p style="margin: 0; padding: 0">
                                            ${flight.route}
                                        </p>
                                        <div class="widget">
                                            <button class="btn btn-outline-dark oswald-bold"
                                                    onclick="location.href='/sql/ui/v1/flights/canceled/route/${flight.route}'">
                                                Canceled
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/sql/ui/v1/flights/waiting/route/${flight.route}'">
                                                Waiting
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/sql/ui/v1/flights/completed/route/${flight.route}'">
                                                Completed
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/sql/ui/v1/flights/postponed/route/${flight.route}'">
                                                Postponed
                                            </button>
                                            <button class="btn btn-outline-dark oswald-bold width-100"
                                                    onclick="location.href='/sql/ui/v1/flights/in-road/route/${flight.route}'">
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
                                            onclick="location.href='/sql/ui/v1/flights/cancel/${flight.id}'">Cancel</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/sql/ui/v1/flights/postpone/${flight.id}'">Postpone</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/sql/ui/v1/flights/edit/${flight.id}'">Edit</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/sql/ui/v1/booked-places/book/${flight.id}'">Book</button>
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
                            onclick="location.href='/sql/ui/v1/flights/canceled'">Canceled</button>
                    <button class="btn btn-outline-dark"
                            onclick="location.href='/sql/ui/v1/flights/waiting'">Waiting</button>
                    <button class="btn btn-outline-dark"
                            onclick="location.href='/sql/ui/v1/flights/completed'">Completed</button>
                    <button class="btn btn-outline-dark"
                            onclick="location.href='/sql/ui/v1/flights/postponed'">Postponed</button>
                    <button class="btn btn-outline-dark"
                            onclick="location.href='/sql/ui/v1/flights/in-road'">In road</button>
                </div>
                <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                    <button class="btn btn-outline-dark" id="previous">Previous</button>
                    <button class="btn btn-outline-dark" id="next">Next</button>
                </div>
            </div>
        </div>
    </div>

    <script src="/static/sql/scripts/changeFlightDisplayFilters.js?version=600"></script>
    <script src="/static/sql/scripts/flightPaging.js?version=500"></script>
</body>
</html>