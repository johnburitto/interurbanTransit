<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Passengers</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1610" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="name-display-filter" checked/>
    <input type="checkbox" id="contact-inf-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of passengers</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        style="display: ${perms.create}"
                        onclick="location.href='/ui/v1/passengers/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="name">Name</th>
                            <th id="contact-inf">Contact Inf</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                            <th style="display: ${perms.delete}">Delete</th>
                            <th style="display: ${perms.edit}">Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list passengers as passenger>
                            <tr>
                                <td class="align-middle hover-td" id="id">${passenger.id}</td>
                                <td class="align-middle hover-td" id="name">${passenger.contactPerson.name}</td>
                                <td class="align-middle hover-td" id="contact-inf">
                                    <b>Telephone:</b> ${passenger.contactPerson.telephoneNumber}<br>
                                    <b>E-mail:</b> ${passenger.contactPerson.getEMail()}
                                </td>
                                <td class="align-middle hover-td" id="created-at">${passenger.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${passenger.updatedAt}</td>
                                <td class="align-middle" style="display: ${perms.delete}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/passengers/delete/${passenger.id}'">Delete</button>
                                </td>
                                <td class="align-middle" style="display: ${perms.edit}">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/passengers/edit/${passenger.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold" style="display: ${perms.columnFilters}">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-name" for="name-display-filter">Name</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-contact-inf" for="contact-inf-display-filter">Contact Inf</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark"
                        onclick="location.href='/tables'">Home</button>
            </div>
        </div>
    </div>
</body>
</html>