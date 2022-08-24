<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
            <legend>Login</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">Login:</span>
                    <input type="text" id="login" name="login" value="" placeholder="Login"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Password:</span>
                    <input type="password" id="password" name="password" value="" placeholder="Password"/>
                </div>
                <div class="input-container" style="align-items: baseline">
                    <input class="btn btn-outline-dark oswald-bold" type=submit value="Login">
                    <button class="btn btn-outline-dark oswald-bold"
                            onclick="window.open('/register')">Register</button>
                </div>
            </form>
        </fieldset>
    </div>
</body>
</html>