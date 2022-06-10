<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete place of work</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=2310" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=1210" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="app-container">
    <fieldset>
        <legend class="oswald">Delete place of work</legend>
        <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
            <div class="input-container">
                <span class="input-text">Company:</span>
                <select class="oswald" id="infAboutPlaceOfWork" name="infAboutPlaceOfWork">
                    <#list placesOfWork as placeOfWork>
                        <option value="${placeOfWork.company}${SPLITTER}${placeOfWork.workFrom}${SPLITTER}${placeOfWork.workTo}">
                            ${placeOfWork.company} Work to: ${placeOfWork.workTo}
                        </option>
                    </#list>
                </select>
            </div>
            <input  class="btn btn-outline-dark oswald-bold" type=submit value="Delete">
        </form>
    </fieldset>
</div>
</body>
</html>