<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp"%>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
<%--            <input type="email" name="email" placeholder="Email" />--%>
            <label for="email">Email</label>
            <form:input path="email"/>
            <form:errors path="email" cssClass="errors"/>
            <span id="email.errors" class="errors">Niepoprawny adres email</span>
        </div>
        <div class="form-group">
<%--            <input type="password" name="password" placeholder="Hasło" />--%>
            <label for="password">Hasło</label>
            <form:password path="password"/>
            <form:errors path="password" cssClass="errors"/>
            <span id="password.errors" class="errors">Hasło powinno mieć od 6 do 14 znaków</span>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="password">Powtórz hasło</label>--%>
<%--            <form:password path="password2"/>--%>
<%--            <input type="password" name="password2" placeholder="Powtórz hasło" />--%>
<%--        </div>--%>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="footer.jsp"%>