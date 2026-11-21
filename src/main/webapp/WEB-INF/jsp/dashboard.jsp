<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Dashboard" />
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<h2>Welcome, ${sessionScope.loggedFullName}</h2>
<p>Role: ${sessionScope.loggedRole}</p>

<div class="cards">
    <div class="card stat">
        <h3>Total Donors</h3>
        <p class="big">${totalDonors}</p>
        <a href="${pageContext.request.contextPath}/donors">View all donors</a>
    </div>

    <div class="card stat">
        <h3>Radar Search</h3>
        <p>Find donors by blood group and city.</p>
        <a href="${pageContext.request.contextPath}/donors/search">Open radar search</a>
    </div>

    <div class="card stat">
        <h3>Add Donor</h3>
        <p>Register a new donor.</p>
        <a href="${pageContext.request.contextPath}/donors/new">Add a donor</a>
    </div>

    <div class="card stat">
        <h3>Export</h3>
        <p>Download all donors as a CSV file.</p>
        <a href="${pageContext.request.contextPath}/export/donors">Download CSV</a>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>