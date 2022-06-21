<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book place</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1310" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=1210" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="app-container">
        <fieldset class="oswald">
            <legend class="oswald">Book place</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Flight:</span>
                    <select class="oswald" id="flight" name="flight">
                        <option value="" selected hidden>Select...</option>
                        <#list flights as flight>
                            <option value="${flight.id}">
                                ${flight.route.fromCity}-${flight.route.toCity};
                                ${flight.route.departureTime}-${flight.route.arrivalTime};
                                ${flight.getDatesInRoad()}
                            </option>
                        </#list>
                    </select>
                </div>
                <div class="input-container">
                    <span class="input-text">Passenger:</span>
                    <select class="oswald" id="passenger" name="passenger">
                        <option value="" selected hidden>Select...</option>
                        <#list passengers as passenger>
                            <option value="${passenger.id}">${passenger.contactPerson.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="input-container">
                    <span class="input-text">Booked place:</span>
                    <input type="text" id="bookedPlace" name="bookedPlace" value=""/>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Book">
            </form>
        </fieldset>
    </div>
</body>
</html>