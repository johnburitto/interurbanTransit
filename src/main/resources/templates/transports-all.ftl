<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transports</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=300" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1410" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="brand-display-filter" checked/>
    <input type="checkbox" id="model-display-filter" checked/>
    <input type="checkbox" id="passport-display-filter" checked/>
    <input type="checkbox" id="number-of-places-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of transports</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/ui/v1/transports/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="brand">Brand</th>
                            <th id="model">Model</th>
                            <th id="passport">Transport passport</th>
                            <th id="number-of-places">Number of booked places</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                            <th>Delete</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list transports as transport>
                            <tr>
                                <td class="align-middle hover-td" id="id">${transport.id}</td>
                                <td class="align-middle hover-td" id="brand">${transport.brand}</td>
                                <td class="align-middle hover-td" id="model">${transport.model}</td>
                                <td class="align-middle hover-td" id="passport"
                                    onclick="location.href='/ui/v1/transport-passports/${transport.passport.transportNumber}'">
                                    <b>Company:</b> ${transport.passport.companyName}<br>
                                    <b>Transport number:</b> ${transport.passport.transportNumber}<br>
                                </td>
                                <td class="align-middle hover-td" id="number-of-places">${transport.numberOfBookedPlaces}/${transport.passport.numberOfPlaces}</td>
                                <td class="align-middle hover-td" id="created-at">${transport.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${transport.updatedAt}</td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/transports/delete/${transport.id}'">Delete</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/transports/edit/${transport.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-brand" for="brand-display-filter">Brand</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-model" for="model-display-filter">Model</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-passport" for="passport-display-filter">Transport passport</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-number-of-places" for="number-of-places-display-filter">Number of booked places</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
        </div>
    </div>
</body>
</html>