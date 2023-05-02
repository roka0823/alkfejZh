<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="PokemonListController"></jsp:include>
<%@ page errorPage="errorpage.jsp" %>
<html>
<head>
    <title>Pokédex</title>
</head>
<body>
<h2>Pokemons</h2>
<table border="2">
    <tr style="font-weight: bold">
        <th>Name</th>
        <th>Type</th>
        <th>Gender</th>
        <th>Is caught?</th>
        <th>Control</th>
    </tr>
    <c:forEach var="item" items="${pokemons}">
        <tr>
            <td>${item.pokemonName}</td>
            <td>${item.type}</td>
            <td>${item.gender}</td>
            <td>${item.caught}</td>
            <td>
                <a href="${pageContext.request.contextPath}/PokemonUpdateController?id=${item.id}">Update</a>
                <a href="${pageContext.request.contextPath}/PokemonDeleteController?id=${item.id}">Delete</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br/>
<form action="index.jsp" method="GET">
    <input type="text" name="filter">
    <button value="Filter" type="submit">Szűrés</button>
</form>
<br/>
<form action = "addpokemon.jsp" method="GET">
    <button value="Uj pokemon" type="submit">új pokemon</button>
</form>
<br/>
<jsp:include page="parts/common-footer.jsp"/>
</body>
</html>
