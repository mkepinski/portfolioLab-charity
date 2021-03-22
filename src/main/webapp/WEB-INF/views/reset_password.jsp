<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="header.jsp"%>
</header>
<body>
<form method="post">
    Czy na pewno chcesz zmienić hasło?
    <input type="radio" value="Tak" name="confirm">
    <input type="radio" value="Nie" name="confirm">
</form>
</body>
<%@include file="footer.jsp"%>
