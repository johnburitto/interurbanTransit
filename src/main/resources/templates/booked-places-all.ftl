<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booked places</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1310" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=2200" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" ${filters[0]}/>
    <input type="checkbox" id="flight-display-filter" ${filters[1]}/>
    <input type="checkbox" id="passenger-display-filter" ${filters[2]}/>
    <input type="checkbox" id="day-of-booking-display-filter" ${filters[3]}/>
    <input type="checkbox" id="status-display-filter" ${filters[4]}/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of booked places</h1>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                    <tr>
                        <th id="id">Id</th>
                        <th id="flight">Flight</th>
                        <th id="passenger">Passenger</th>
                        <th id="day-of-booking">Day of booking</th>
                        <th id="status">Status</th>
                        <th id="created-at">Created at</th>
                        <th id="updated-at">Updated at</th>
                        <th style="display: ${perms.delete}">Cancel</th>
                        <th style="display: ${perms.delete}">Return</th>
                        <th style="display: ${perms.edit}">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list bookedPlaces as bookedPlace>
                        <tr>
                            <td class="align-middle hover-td" id="id">${bookedPlace.id}</td>
                            <td class="align-middle hover-td" id="flight">
                                ${bookedPlace.flight}
                            </td>
                            <td class="align-middle hover-td" id="passenger">
                                <div class="widget-holder">
                                    <p style="margin: 0; padding: 0">
                                        ${bookedPlace.passenger}
                                    </p>
                                    <div class="widget">
                                        <button class="btn btn-outline-dark oswald-bold width-100"
                                                onclick="location.href='/sql/ui/v1/booked-places/name/${bookedPlace.passenger}'">
                                            Find by name
                                        </button>
                                        <button class="btn btn-outline-dark oswald-bold width-100"
                                                onclick="location.href='/sql/ui/v1/booked-places/last-name/${bookedPlace.passenger}'">
                                            Find by last name
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle hover-td" id="day-of-booking">${bookedPlace.dayOfBooking}</td>
                            <td class="align-middle hover-td" id="status">${bookedPlace.status}</td>
                            <td class="align-middle hover-td" id="created-at">${bookedPlace.createdAt}</td>
                            <td class="align-middle hover-td" id="updated-at">${bookedPlace.updatedAt}</td>
                            <td class="align-middle" style="display: ${perms.delete}">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/sql/ui/v1/booked-places/cancel/${bookedPlace.id}'">Cancel</button>
                            </td>
                            <td class="align-middle" style="display: ${perms.delete}">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/sql/ui/v1/booked-places/return/${bookedPlace.id}'">Return</button>
                            </td>
                            <td class="align-middle" style="display: ${perms.edit}">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/sql/ui/v1/booked-places/edit/${bookedPlace.id}'">Edit</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold" style="display: ${perms.columnFilters}">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-flight" for="flight-display-filter">Flight</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-passenger" for="passenger-display-filter">Passenger</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-day-of-booking" for="day-of-booking-display-filter">Day of booking</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-status" for="status-display-filter">Status</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="interval-container width-100 oswald-bold" style="margin-top: 2%">
                <div class="input-container" style="width: 40%">
                    <span class="input-text">Start day:</span>
                    <input type="date" id="startDay" name="startDay" value=""/>
                </div>
                <div class="input-container" style="width: 40%">
                    <span class="input-text">End day:</span>
                    <input type="date" id="endDay" name="endDay" value=""/>
                </div>
                <button class="btn btn-outline-dark" id="interval" style="height: 40px; margin-top: 2.75%">Search</button>
            </div>
            <div class="filters-container width-100 oswald-bold">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/sql/tables'">Home</button>
            </div>
        </div>
    </div>

    <script src="/static/scripts/bookedPlacesInterval.js?version=200"></script>
    <script src="/static/scripts/changeBookedPlacesFilters.js?version=500"></script>
</body>
</html>