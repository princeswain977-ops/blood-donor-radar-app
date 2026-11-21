<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Donors" />
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<div class="page-title">
    <h2>All Donors</h2>
    <a class="button" href="${pageContext.request.contextPath}/donors/new">+ Add Donor</a>
</div>

<c:if test="${not empty success}">
    <div class="alert alert-success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<c:choose>
    <c:when test="${empty donors}">
        <p>No donors yet. Click "Add Donor" to create one.</p>
    </c:when>
    <c:otherwise>
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Blood Group</th>
                    <th>Contact</th>
                    <th>Email</th>
                    <th>City</th>
                    <th>Last Donation</th>
                    <th>Status</th>
                    <th>Blood Bank</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${donors}" var="d">
                    <tr>
                        <td>${d.donorId}</td>
                        <td>${d.fullName}</td>
                        <td>${d.bloodGroup}</td>
                        <td>${d.contactNumber}</td>
                        <td>${d.email}</td>
                        <td>${d.city}</td>
                        <td>${d.lastDonationDate}</td>
                        <td>${d.availabilityStatus}</td>
                        <td>${d.bank.bankName}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/donors/${d.donorId}/edit">Edit</a>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/donors/${d.donorId}/delete"
                                  onsubmit="return confirm('Delete this donor?');">
                                <button type="submit" class="danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>