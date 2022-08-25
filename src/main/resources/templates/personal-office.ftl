<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal office</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/inputs.css?version=1610" type="text/css" rel="stylesheet"/>
    <link href="/static/css/project.css?version=4000" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="app-container">
        <div class="oswald" style="width: 50%; display: flex; align-content: center; justify-content: center;
                                        flex-wrap: wrap;">
            <h1 class="width-100" style="text-align: center">Personal office</h1>
            <div class="input-container" style="flex-wrap: nowrap;">
                <span class="input-text">First name:</span>
                <span class="personal-office-data">${personalInf.firstName}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <span class="input-text">Middle name:</span>
                <span class="personal-office-data">${personalInf.middleName}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <span class="input-text">Last name:</span>
                <span class="personal-office-data">${personalInf.lastName}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <span class="input-text">Telephone:</span>
                <span class="personal-office-data">${personalInf.telephoneNumber}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <span class="input-text">E-mail:</span>
                <span class="personal-office-data">${personalInf.getEMail()}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <span class="input-text">Role:</span>
                <span class="personal-office-data">${role}</span>
            </div>
            <div class="input-container" style="flex-wrap: nowrap">
                <button class="btn btn-outline-dark oswald-bold"
                        onclick="location.href='/personal-office/edit'">Change personal info</button>
                <button class="btn btn-outline-dark oswald-bold"
                        onclick="location.href='/'">Home</button>
            </div>
        </div>
    </div>
</body>
</html>