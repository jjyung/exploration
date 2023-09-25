<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax Table</title>
    <link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery-3.7.1.js"/>"></script>
    <script>
        function myFunction() {
            $.get("/book/", function (books) {
                let table = $('<table class="table" />');
                table.append("<thead><tr><th>ISBN</th><th>Name</th><th>Author</th></tr></thead>");

                let tbody = $('<tbody />');

                books.forEach(function (book) {
                    let tr = $('<tr />');

                    tr.append("<td>" + book['isbn'] + "</td>");
                    tr.append("<td>" + book['name'] + "</td>");
                    tr.append("<td>" + book['author'] + "</td>");

                    tbody.append(tr);
                });

                table.append(tbody);

                $("#tableDiv").html(table);
            });
        }
    </script>
</head>
<body>
<button onclick="myFunction()" class="btn btn-default">Click me</button>
<div id="tableDiv"></div>
</body>
</html>