<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Postpone flight</title>
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
            <legend class="oswald">Postpone flight</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Start day:</span>
                    <input type="date" id="startDay" name="startDay" value="${form.startDay}"/>
                </div>
                <div class="input-container">
                    <span class="input-text">End day:</span>
                    <input type="date" id="endDay" name="endDay" value="${form.endDay}"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Departure time:</span>
                    <input type="time" id="departureTime" name="departureTime" value="${form.departureTime}"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Arrival time:</span>
                    <input type="time" id="arrivalTime" name="arrivalTime" value="${form.arrivalTime}"/>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Postpone">
            </form>
        </fieldset>
    </div>
</body>
</html>