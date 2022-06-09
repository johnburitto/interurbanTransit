<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Working books</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="/static/css/project.css?version=300" type="text/css" rel="stylesheet"/>
    <link href="/static/css/display-filters.css?version=1310" type="text/css" rel="stylesheet"/>
</head>
<body>
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
                        <th>Edit</th>
                        <th>Add place of work</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list workingBooks as workingBook>
                        <td class="align-middle hover-td" id="id">${workingBook.numberOfWorkingBook}</td>
                        <td class="align-middle hover-td" id="places-of-work">Places of work</td>
                        <td class="align-middle hover-td" id="created-at">${workingBook.createdAt}</td>
                        <td class="align-middle hover-td" id="updated-at">${workingBook.updatedAt}</td>
                        <td class="align-middle">
                            <button type="button" class="btn btn-outline-dark oswald-bold">Delete</button>
                        </td>
                        <td class="align-middle">
                            <button type="button" class="btn btn-outline-dark oswald-bold">Edit</button>
                        </td>
                        <td class="align-middle">
                            <button type="button" class="btn btn-outline-dark oswald-bold"
                                    onclick="location.href='/ui/v1/working-books/${workingBook.numberOfWorkingBook}/add/place-of-work'">Add</button>
                        </td>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>