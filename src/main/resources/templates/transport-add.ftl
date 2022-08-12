<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create transport</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1610" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="input-hider"/>
    <div class="app-container">
        <fieldset class="oswald">
            <legend class="oswald">Create transport</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Brand:</span>
                    <input type="text" id="brand" name="brand" value="" placeholder="Brand"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Model:</span>
                    <input type="text" id="model" name="model" value="" placeholder="Model"/>
                </div>
                <label for="input-hider">
                    Transport passport
                </label>
                <div id="hidden-input">
                    <div class="input-container width-100">
                        <span class="input-text">Transport number:</span>
                        <input type="text" id="transportNumber" name="transportNumber" value="" placeholder="Transport number"/>
                    </div>
                    <div class="input-container width-100">
                        <span class="input-text">Transport category:</span>
                        <select class="oswald" id="neededTransportCategory" name="neededTransportCategory">
                            <#list categories as category>
                                <option value="${category}">${category}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="input-container width-100">
                        <span class="input-text">Number of places:</span>
                        <input type="text" id="numberOfPlaces" name="numberOfPlaces" value="" placeholder="Number of places"/>
                    </div>
                    <div class="input-container width-100">
                        <span class="input-text">Company name:</span>
                        <input type="text" id="companyName" name="companyName" value="" placeholder="Company name"/>
                    </div>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Create">
            </form>
        </fieldset>
    </div>
</body>
</html>