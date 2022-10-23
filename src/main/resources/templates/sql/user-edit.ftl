<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create user</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/sql/css/inputs.css?version=1610" type="text/css" rel="stylesheet"/>
    <link href="/static/sql/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
</head>
<body>
    <div class="app-container">
        <input type="checkbox" id="input-hider"/>
        <fieldset class="oswald">
            <legend>Edit user</legend>
            <form name="item" action="" method="POST" class="inputs-container" autocomplete="off">
                <div class="input-container">
                    <span class="input-text">First name:</span>
                    <input type="text" id="firstName" name="firstName" value="${form.firstName}" placeholder="First name"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Middle name:</span>
                    <input type="text" id="middleName" name="middleName" value="${form.middleName}" placeholder="Middle name"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Last name:</span>
                    <input type="text" id="lastName" name="lastName" value="${form.lastName}" placeholder="Last name"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Telephone:</span>
                    <input type="tel" id="telephoneNumber" name="telephoneNumber" value="${form.telephoneNumber}" pattern="+[0-9]{12}" placeholder="+380000000000"/>
                </div>
                <div class="input-container">
                    <span class="input-text">E-mail:</span>
                    <input type="email" id="eMail" name="eMail" value="${form.EMail}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="example@example.com"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Login:</span>
                    <input type="text" id="login" name="login" value="${form.login}" placeholder="Login"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Password:</span>
                    <input type="text" id="password" name="password" value="${form.password}" placeholder="Password"/>
                </div>
                <div class="input-container">
                    <span class="input-text">Type:</span>
                    <select class="oswald" id="userType" name="userType">
                        <option value="${form.userType}">${form.userType}</option>
                        <#list types as type>
                            <option value="${type}">${type}</option>
                        </#list>
                    </select>
                </div>
                <input  class="btn btn-outline-dark oswald-bold" type=submit value="Update">
            </form>
        </fieldset>
    </div>
</body>
</html>