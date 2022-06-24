<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booked places</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=300" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1910" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="flight-display-filter" checked/>
    <input type="checkbox" id="passenger-display-filter" checked/>
    <input type="checkbox" id="booked-place-display-filter" checked/>
    <input type="checkbox" id="day-of-booking-display-filter" checked/>
    <input type="checkbox" id="status-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of booked places</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/ui/v1/booked-places/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="flight">Flight</th>
                            <th id="passenger">Passenger</th>
                            <th id="booked-place">Booked place</th>
                            <th id="day-of-booking">Day of booking</th>
                            <th id="status">Status</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                            <th>Cancel</th>
                            <th>Return</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list bookedPlaces as bookedPlace>
                            <tr>
                                <td class="align-middle hover-td" id="id">${bookedPlace.id}</td>
                                <td class="align-middle hover-td" id="flight">
                                    <b>Route:</b> ${bookedPlace.flight.route.fromCity}-${bookedPlace.flight.route.toCity}<br>
                                    <b>Time:</b> ${bookedPlace.flight.route.departureTime}-${bookedPlace.flight.route.arrivalTime}<br>
                                    <b>Date:</b> ${bookedPlace.flight.getDatesInRoad()}
                                </td>
                                <td class="align-middle hover-td" id="passenger">
                                    <b>Name:</b> ${bookedPlace.passenger.contactPerson.name}<br>
                                    <b>Telephone:</b> ${bookedPlace.passenger.contactPerson.telephoneNumber}<br>
                                    <b>E-mail:</b> ${bookedPlace.passenger.contactPerson.getEMail()}
                                </td>
                                <td class="align-middle hover-td" id="booked-place">${bookedPlace.bookedPlace}</td>
                                <td class="align-middle hover-td" id="day-of-booking">${bookedPlace.dayOfBooking}</td>
                                <td class="align-middle hover-td" id="status">${bookedPlace.status}</td>
                                <td class="align-middle hover-td" id="created-at">${bookedPlace.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${bookedPlace.updatedAt}</td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/booked-places/cancel/${bookedPlace.id}'">Cancel</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/booked-places/return/${bookedPlace.id}'">Return</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/booked-places/edit/${bookedPlace.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-flight" for="flight-display-filter">Flight</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-passenger" for="passenger-display-filter">Passenger</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-booked-place" for="booked-place-display-filter">Booked place</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-day-of-booking" for="day-of-booking-display-filter">Day of booking</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-status" for="status-display-filter">Status</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/'">Home</button>
            </div>
        </div>
    </div>
</body>
</html>