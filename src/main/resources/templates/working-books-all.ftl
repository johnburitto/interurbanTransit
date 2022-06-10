<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Working books</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=700" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1410" type="text/css" rel="stylesheet"/>
</head>
<body>
    <input type="checkbox" id="id-display-filter" checked/>
    <input type="checkbox" id="places-of-work-display-filter" checked/>
    <input type="checkbox" id="created-at-display-filter"/>
    <input type="checkbox" id="updated-at-display-filter"/>
    <div class="app-container">
        <div class="table-container">
            <input class="custom-checkbox" type="checkbox" id="list" checked/>
            <label for="list" class="width-100 table-name oswald-bold">
                <h1>Table of working books</h1>
            </label>
            <div class="width-100">
                <table class="table table-hover text-center oswald">
                    <thead class="table-dark">
                    <tr>
                        <th id="id">Working book number</th>
                        <th id="places-of-work">Places of work</th>
                        <th id="created-at">Created at</th>
                        <th id="updated-at">Updated at</th>
                        <th>Delete</th>
                        <th>Add place of work</th>
                        <th>Delete place of work</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list workingBooks as workingBook>
                        <tr>
                            <td class="align-middle hover-td" id="id">${workingBook.numberOfWorkingBook}</td>
                            <td class="align-middle hover-td" id="places-of-work">
                                <div class="widget-holder">
                                    Places of work
                                    <div class="widget">
                                        <ul>
                                            <#list workingBook.placesOfWork as placeOfWork>
                                                <li>
                                                    "${placeOfWork.company}" <b>From:</b> ${placeOfWork.workFrom} <b>To:</b> ${placeOfWork.workTo}
                                                </li>
                                            </#list>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle hover-td" id="created-at">${workingBook.createdAt}</td>
                            <td class="align-middle hover-td" id="updated-at">${workingBook.updatedAt}</td>
                            <td class="align-middle">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/ui/v1/working-books/delete/${workingBook.numberOfWorkingBook}'">Delete</button>
                            </td>
                            <td class="align-middle">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/ui/v1/working-books/${workingBook.numberOfWorkingBook}/add/place-of-work'">Add</button>
                            </td>
                            <td class="align-middle">
                                <button type="button" class="btn btn-outline-dark oswald-bold"
                                        onclick="location.href='/ui/v1/working-books/${workingBook.numberOfWorkingBook}/delete/place-of-work'">Delete place of work</button>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="width-100 filters-container oswald-bold">
                    <label class="btn btn-outline-dark filter-hover" id="for-id" for="id-display-filter">Id</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-places-of-work-display" for="places-of-work-display-filter">Places of work</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-created-at" for="created-at-display-filter">Created at</label>
                    <label class="btn btn-outline-dark filter-hover" id="for-updated-at" for="updated-at-display-filter">Updated at</label>
                </div>
            </div>
        </div>
    </div>
</body>
</html>