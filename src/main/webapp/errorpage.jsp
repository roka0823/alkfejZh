<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Something happened</title>
</head>
<body>


<h3>Sorry an exception occured!</h3>

Exception is: <%= exception %>

<br/>
<jsp:include page="parts/common-footer.jsp"/>
</body>
</html>
