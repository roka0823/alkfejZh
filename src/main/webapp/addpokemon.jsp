<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorpage.jsp" %>
<%@ page import="hu.alkfejl.zh2.model.PokemonType" %>
<html>
<head>
    <title>ADD or EDIT</title>
</head>
<body>
<c:choose>
    <c:when test="${pokemon.id == null}">
        <h2>Uj pokemon felvétele</h2>
    </c:when>
    <c:otherwise>
        <h2>${pokemon.pokemonName} módositasa</h2>
    </c:otherwise>
</c:choose>

<form action="PokemonSaveController" method="get">
    <input type="hidden" name="id" value="${pokemon.id}"/>
    <table>
        <tr>
            <td>Nev</td>
            <td><input type="text" name="name" value="${pokemon.pokemonName}" required></td>
        </tr>
        <tr>
            <td>Típus</td>
            <td>
                <select name="type">
                    <c:forEach var="type" items="<%=PokemonType.values()%>">
                        <option value="${type.name}"
                            <c:if test="${type.name.equals(pokemon.type.name)}">
                                <c:out value="selected"/>
                            </c:if>
                        >${type.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Nem</td>
            <td><input type="text" name="gender" value="${pokemon.gender}" required></td>
        </tr>
        <tr>
            <td>El van kapva?</td>
            <td><input type="checkbox" name="caught"

            <c:if test="${pokemon.caught}">
                           checked
            </c:if>
            ></td>
        </tr>
    </table>
    <button value="Mentes" type="submit">Mentes</button>
</form>
<br/>
<jsp:include page="parts/common-footer.jsp"/>
</body>
</html>
