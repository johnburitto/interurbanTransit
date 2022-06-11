<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Drivers</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=500" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1510" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="personal-inf-display-filter" checked/>
    <input type="checkbox" id="transport-category-display-filter" checked/>
    <input type="checkbox" id="working-book-display-filter" checked/>
    <input type="checkbox" id="exp-of-work-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of drivers</h1>
                <button type="button" class="btn btn-outline-dark reverse-btn-outline-dark oswald-bold"
                        onclick="location.href='/ui/v1/drivers/add'">Create</button>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                        <tr>
                            <th id="id">Id</th>
                            <th id="personal-inf">Persona inf</th>
                            <th id="transport-category">Transport category</th>
                            <th id="working-book">Working book</th>
                            <th id="exp-of-work">Exp of work</th>
                            <th id="created-at">Created at</th>
                            <th id="updated-at">Updated at</th>
                            <th>Delete</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list drivers as driver>
                            <tr>
                                <td class="align-middle hover-td" id="id">${driver.id}</td>
                                <td class="align-middle hover-td" id="personal-inf">
                                    <b>Name:</b> ${driver.personalInf.name}<br>
                                    <b>Date of birth:</b> ${driver.personalInf.dateOfBirth}<br>
                                    <b>Blood type:</b> ${driver.personalInf.bloodType}<br>
                                </td>
                                <td class="align-middle hover-td" id="transport-category">${driver.transportCategory}</td>
                                <td class="align-middle hover-td" id="working-book"
                                    onclick="location.href='/ui/v1/working-books/${driver.workingBook.numberOfWorkingBook}'">
                                    ${driver.workingBook.numberOfWorkingBook}
                                </td>
                                <td class="align-middle hover-td" id="exp-of-work">${driver.workingBook.getExpOfWork()}</td>
                                <td class="align-middle hover-td" id="created-at">${driver.createdAt}</td>
                                <td class="align-middle hover-td" id="updated-at">${driver.updatedAt}</td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/drivers/delete/${driver.id}'">Delete</button>
                                </td>
                                <td class="align-middle">
                                    <button type="button" class="btn btn-outline-dark oswald-bold"
                                            onclick="location.href='/ui/v1/drivers/edit/${driver.id}'">Edit</button>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-personal-inf" for="personal-inf-display-filter">Personal Inf</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-transport-category" for="transport-category-display-filter">Transport category</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-working-book" for="working-book-display-filter">Working book</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-exp-of-work" for="exp-of-work-display-filter">Exp of work</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
        </div>
    </div>
</body>
</html>