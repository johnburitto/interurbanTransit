<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create driver</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1610" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="app-container">
    <input type="checkbox" id="input-hider"/>
    <fieldset class="oswald">
        <legend>Create driver</legend>
        <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
            <label for="input-hider">
                Personal Inf
            </label>
            <div id="hidden-input">
                <div class="input-container width-100">
                    <span class="input-text">First name:</span>
                    <input type="text" id="firstName" name="firstName" value="" placeholder="First name"/>
                </div>
                <div class="input-container width-100">
                    <span class="input-text">Middle name:</span>
                    <input type="text" id="middleName" name="middleName" value="" placeholder="Middle name"/>
                </div>
                <div class="input-container width-100">
                    <span class="input-text">Last name:</span>
                    <input type="text" id="lastName" name="lastName" value="" placeholder="Last name"/>
                </div>
                <div class="input-container width-100">
                    <span class="input-text">Date of birth:</span>
                    <input type="date" id="dateOfBirth" name="dateOfBirth" value=""/>
                </div>
                <div class="input-container width-100">
                    <span class="input-text">Bloody type:</span>
                    <select class="oswald" id="bloodType" name="bloodType">
                        <#list bloodTypes as bloodType>
                            <option value="${bloodType}">${bloodType}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="input-container">
                <span class="input-text">Category:</span>
                <select class="oswald" id="transportCategory" name="transportCategory">
                    <#list categories as category>
                        <option value="${category}">${category}</option>
                    </#list>
                </select>
            </div>
            <div class="input-container">
                <span class="input-text">Working book number:</span>
                <input type="text" id="workingBookNumber" name="workingBookNumber" value="" placeholder="Working book number"/>
            </div>
            <input  class="btn btn-outline-dark oswald-bold" type=submit value="Create">
        </form>
    </fieldset>
</div>
</body>
</html>