<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Login" />
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<div class="card login-card">
    <h2>Login</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>
    <c:if test="${not empty param.logout}">
        <div class="alert alert-success">You have been logged out.</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" value="${username}"
               required minlength="3" maxlength="50">

        <label for="password">Password</label>
        <input type="password" id="password" name="password"
               required minlength="6">

        <button type="submit">Login</button>
    </form>

    <p class="hint">Default login: admin / admin123</p>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>