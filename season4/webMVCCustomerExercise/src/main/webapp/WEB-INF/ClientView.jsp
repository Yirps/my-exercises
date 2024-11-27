<%--
  Created by IntelliJ IDEA.
  User: codecadet
  Date: 27/11/2024
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="card" style="width: 18rem;">
    <div class="card-header">
        <h2>Javabank - Customer #${customer.id}</h2>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><h1>${customer.name}</h1></li>
        <li class="list-group-item">${customer.email}</li>
        <li class="list-group-item">${customer.phoneNumber}</li>
    </ul>
</div>
</body>
</html>
