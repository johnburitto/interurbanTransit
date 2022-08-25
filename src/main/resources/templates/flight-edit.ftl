<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit flight</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1310" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="app-container">
        <fieldset class="oswald">
            <legend class="oswald">Edit flight</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Transport:</span>
                    <select class="oswald" id="transport" name="transport">
                        <option value="${form.transport}" selected>${currentTransport.brand} ${currentTransport.model}</option>
                        <#list transports as transport>
                            <option value="${transport.id}">${transport.brand} ${transport.model}</option>
                        </#list>
                    </select>
                </div>
                <div class="input-container">
                    <span class="input-text">Driver:</span>
                    <select class="oswald" id="driver" name="driver">
                        <option value="${form.driver}" selected>${currentDriver.personalInf.name}</option>
                        <#list drivers as driver>
                            <option value="${driver.id}">${driver.personalInf.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="input-container">
                    <span class="input-text">Route:</span>
                    <select class="oswald" id="route" name="route">
                        <option value="${form.route}" selected>${currentRoute.fromCity}-${currentRoute.toCity}</option>
                        <#list routes as route>
                            <option value="${route.id}">${route.fromCity}-${route.toCity}</option>
                        </#list>
                    </select>
                </div>
                <div class="input-container">
                    <span class="input-text">Start day:</span>
                    <input type="date" id="startDay" name="startDay" value="${form.startDay}"/>
                </div>
                <div class="input-container">
                    <span class="input-text">End day:</span>
                    <input type="date" id="endDay" name="endDay" value="${form.endDay}"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Ticket cost:</span>
                    <input type="text" id="costOfTicket" name="costOfTicket" value="${form.getStringCostOfTicket()}" placeholder="Ticket cost"/>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Update">
            </form>
        </fieldset>
    </div>
</body>
</html>