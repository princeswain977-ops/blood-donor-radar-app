<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Radar Search" />
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>

<h2>Radar Search</h2>
<p>Find donors by blood group and/or city. Leave a field as "Any" or empty to skip it.</p>

<div class="card">
    <form method="get" action="${pageContext.request.contextPath}/donors/search" class="inline-form">
        <div>
            <label for="bloodGroup">Blood Group</label>
            <select id="bloodGroup" name="bloodGroup">
                <option value="">Any</option>
                <c:forEach items="${bloodGroups}" var="bg">
                    <option value="${bg}" ${selectedBloodGroup == bg ? 'selected' : ''}>${bg}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="city">City</label>
            <input type="text" id="city" name="city" value="${selectedCity}" maxlength="50">
        </div>

        <div>
            <button type="submit">Search</button>
        </div>
    </form>
</div>

<c:if test="${results != null}">
    <div class="card">
        <h3>Results: ${results.size()} donor(s) found</h3>

        <c:choose>
            <c:when test="${empty results}">
                <p>No donors match your search.</p>
            </c:when>
            <c:otherwise>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Blood Group</th>
                            <th>Contact</th>
                            <th>City</th>
                            <th>Status</th>
                            <th>Blood Bank</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${results}" var="d">
                            <tr>
                                <td>${d.fullName}</td>
                                <td>${d.bloodGroup}</td>
                                <td>${d.contactNumber}</td>
                                <td>${d.city}</td>
                                <td>${d.availabilityStatus}</td>
                                <td>${d.bank.bankName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<%@ include file="/WEB-INF/jsp/common/footer.jspf" %>