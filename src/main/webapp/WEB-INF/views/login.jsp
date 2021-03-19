<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp"%>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
<%--            <input type="email" name="email" placeholder="Email" />--%>
            <form:input type="email" path="email" placeholder="Email"/>
        </div>
        <div class="form-group">
<%--            <input type="password" name="password" placeholder="Hasło" />--%>
            <form:password path="password" placeholder="Hasło"/>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form:form>
</section>

<%@ include file="footer.jsp"%>