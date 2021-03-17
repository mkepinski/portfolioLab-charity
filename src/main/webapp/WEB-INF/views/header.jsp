<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <c:choose>
            <c:when test="${empty param.user}">
                <ul class="nav--actions">
                    <li><a href="<c:url value='/login'/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
                    <li><a href="<c:url value='/register'/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
                </ul>
            </c:when>
            <c:when test="${not empty param.user}">
                <ul class="nav--actions">
                    <li class="logged-user">
                        Witaj Agata
                        <ul class="dropdown">
                            <li><a href="#">Profil</a></li>
                            <li><a href="#">Moje zbiórki</a></li>
                            <li><a href="#">Wyloguj</a></li>
                        </ul>
                    </li>
                </ul>
            </c:when>
        </c:choose>

        <%@ include file="main_navbar.jsp"%>
    </nav>
