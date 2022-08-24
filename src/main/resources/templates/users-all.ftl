<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=2700" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="contact-inf-display-filter" checked/>
    <input type="checkbox" id="login-display-filter" checked/>
    <input type="checkbox" id="password-display-filter" checked/>
    <input type="checkbox" id="type-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of users</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/sql/ui/v1/keys/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                    <tr>
                        <th id="id">Id</th>
                        <th id="contact-inf">Contact Inf</th>
                        <th id="personal-inf">Login</th>
                        <th id="transport-category">Password</th>
                        <th id="working-book">Type</th>
                        <th id="created-at">Created at</th>
                        <th id="updated-at">Updated at</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list users as user>
                        <tr>
                            <td class="align-middle hover-td" id="id">${user.id}</td>
                            <td class="align-middle hover-td" id="contact-inf">
                                <b>Name:</b> ${user.firstName} ${user.middleName} ${user.lastName}<br>
                                <b>Telephone:</b> ${user.telephoneNumber}<br>
                                <b>E-mail:</b> ${user.getEMail()}
                            </td>
                            <td class="align-middle hover-td" id="login">${user.login}</td>
                            <td class="align-middle hover-td" id="password">${user.password}</td>
                            <td class="align-middle hover-td" id="type">${user.userType}</td>
                            <td class="align-middle hover-td" id="created-at">${user.createdAt}</td>
                            <td class="align-middle hover-td" id="updated-at">${user.updatedAt}</td>
                            <td class="align-middle">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/sql/ui/v1/keys/edit/${user.id}'">Edit</button>
                            </td>
                            <td class="align-middle">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/sql/ui/v1/keys/delete/${user.id}'">Delete</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-contact-inf" for="contact-inf-display-filter">Contact inf</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-login" for="login-display-filter">Login</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-password" for="password-display-filter">Password</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-type" for="type-display-filter">Type</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/sql/tables'">Home</button>
            </div>
        </div>
    </div>
</body>
</html>