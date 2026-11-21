<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" value="${formTitle}" />
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<h2>${formTitle}</h2>

<c:choose>
    <c:when test="${empty donorForm.donorId}">
        <c:set var="actionUrl" value="${pageContext.request.contextPath}/donors/new" />
    </c:when>
    <c:otherwise>
        <c:set var="actionUrl" value="${pageContext.request.contextPath}/donors/${donorForm.donorId}/edit" />
    </c:otherwise>
</c:choose>

<div class="card">
    <form method="post" action="${actionUrl}">

        <label for="fullName">Full Name</label>
        <input type="text" id="fullName" name="fullName" value="${donorForm.fullName}"
               required minlength="3" maxlength="100">
        <form:errors path="fullName" cssClass="server-error" />

        <label for="bloodGroup">Blood Group</label>
        <select id="bloodGroup" name="bloodGroup" required>
            <option value="">-- Select --</option>
            <c:forEach items="${bloodGroups}" var="bg">
                <option value="${bg}" ${donorForm.bloodGroup == bg ? 'selected' : ''}>${bg}</option>
            </c:forEach>
        </select>
        <form:errors path="bloodGroup" cssClass="server-error" />

        <label for="contactNumber">Contact Number (10 digits)</label>
        <input type="text" id="contactNumber" name="contactNumber" value="${donorForm.contactNumber}"
               required pattern="[0-9]{10}" title="Enter exactly 10 digits">
        <form:errors path="contactNumber" cssClass="server-error" />

        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="${donorForm.email}" maxlength="100">
        <form:errors path="email" cssClass="server-error" />

        <label for="city">City</label>
        <input type="text" id="city" name="city" value="${donorForm.city}" required maxlength="50">
        <form:errors path="city" cssClass="server-error" />

        <label for="lastDonationDate">Last Donation Date</label>
        <input type="date" id="lastDonationDate" name="lastDonationDate" value="${donorForm.lastDonationDate}">
        <form:errors path="lastDonationDate" cssClass="server-error" />

        <label for="availabilityStatus">Availability Status</label>
        <select id="availabilityStatus" name="availabilityStatus" required>
            <option value="">-- Select --</option>
            <c:forEach items="${statuses}" var="st">
                <option value="${st}" ${donorForm.availabilityStatus == st ? 'selected' : ''}>${st}</option>
            </c:forEach>
        </select>
        <form:errors path="availabilityStatus" cssClass="server-error" />

        <label for="bankId">Blood Bank</label>
        <select id="bankId" name="bankId">
            <option value="">-- None --</option>
            <c:forEach items="${banks}" var="b">
                <option value="${b.bankId}" ${donorForm.bankId == b.bankId ? 'selected' : ''}>${b.bankName}</option>
            </c:forEach>
        </select>

        <div class="form-actions">
            <button type="submit">Save</button>
            <a href="${pageContext.request.contextPath}/donors">Cancel</a>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>