<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ include
file="../layout/header.jsp" %>

<h2>Chefs</h2>
<a href="/chefs/new" class="btn btn-primary mb-3">Add New Chef</a>

<div class="table-responsive">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Specialization</th>
        <th>Years of Experience</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${chefs}" var="chef">
        <tr>
          <td>${chef.name}</td>
          <td>${chef.email}</td>
          <td>${chef.specialization}</td>
          <td>${chef.yearsOfExperience}</td>
          <td>
            <a href="/chefs/${chef.id}/edit" class="btn btn-sm btn-warning"
              >Edit</a
            >
            <a href="/dishes/chef/${chef.id}" class="btn btn-sm btn-info"
              >View Dishes</a
            >
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp" %>
