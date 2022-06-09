<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add place of work</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1310" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=1210" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="app-container">
        <fieldset>
            <legend class="oswald">Add place of work</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Company:</span>
                    <input type="text" id="company" name="company" value="" placeholder="Company"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Work from:</span>
                    <input type="date" id="workFrom" name="workFrom" value=""/>
                </div>
                <div class="input-container">
                    <span class="input-text">Work to:</span>
                    <input type="date" id="workTo" name="workTo" value=""/>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Add">
            </form>
        </fieldset>
    </div>
</body>
</html>