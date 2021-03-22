<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp"%>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
<%--            <label for="email">Email</label>--%>
            <form:input path="email" type="email" placeholder="Email"/>
            <form:errors path="email" cssClass="errors"/>

        </div>
        <div class="form-group">
<%--            <label for="password">Hasło</label>--%>
            <form:password path="password" placeholder="Hasło"/>
            <form:errors path="password" cssClass="errors"/>
        </div>
        <div class="form-group">
<%--            <label for="password">Powtórz hasło</label>--%>
            <form:password path="password2" placeholder="Powtórz hasło"/>
            <form:errors path="password2" cssClass="errors"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/user/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="footer.jsp"%>