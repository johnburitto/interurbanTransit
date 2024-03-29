<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transport passports paging</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/nosql/css/project.css?version=3000" type="text/css" rel="stylesheet"/>
    <link href="/static/nosql/css/display-filters.css?version=1410" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" ${filters[0]}/>
    <input type="checkbox" id="transport-category-display-filter" ${filters[1]}/>
    <input type="checkbox" id="number-of-places-display-filter" ${filters[2]}/>
    <input type="checkbox" id="company-display-filter" ${filters[3]}/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="start-page-app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of transport passports</h1>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                    <tr>
                        <th id="id">Transport number</th>
                        <th id="transport-category">Transport category</th>
                        <th id="number-of-places">Number of places</th>
                        <th id="company">Company</th>
                        <th id="created-at">Created at</th>
                        <th id="updated-at">Updated at</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list passports as passport>
                        <tr>
                            <td class="align-middle hover-td" id="id">${passport.transportNumber}</td>
                            <td class="align-middle hover-td" id="transport-category">${passport.neededTransportCategory}</td>
                            <td class="align-middle hover-td" id="number-of-places">${passport.numberOfPlaces}</td>
                            <td class="align-middle hover-td" id="company">${passport.companyName}</td>
                            <td class="align-middle hover-td" id="created-at">${passport.createdAt}</td>
                            <td class="align-middle hover-td" id="updated-at">${passport.updatedAt}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold" style="display: ${perms.columnFilters}">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-transport-category" for="transport-category-display-filter">Transport category</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-number-of-places" for="number-of-places-display-filter">Number of places</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-company" for="company-display-filter">Company</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
            <div class="width-100 filters-container oswald-bold" style="margin-top: 2%">
                <button class="btn btn-outline-dark" id="previous">Previous</button>
                <button class="btn btn-outline-dark"
                        onclick="location.href='/tables/paging/5&0'">Home</button>
                <button class="btn btn-outline-dark" id="next">Next</button>
            </div>
        </div>
    </div>

    <script src="/static/nosql/scripts/changeTransportPassportDisplayFilters.js?version=500"></script>
    <script src="/static/nosql/scripts/transportPassportPaging.js?version=500"></script>
</body>
</html>