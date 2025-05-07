<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ include
file="../layout/header.jsp" %>

<h2>Dishes</h2>
<a href="/dishes/new" class="btn btn-primary mb-3">Add New Dish</a>

<div class="table-responsive">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Category</th>
        <th>Chef</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${dishes}" var="dish">
        <tr>
          <td>${dish.name}</td>
          <td>${dish.description}</td>
          <td>$${dish.price}</td>
          <td>${dish.category}</td>
          <td>${dish.chef.name}</td>
          <td>
            <a href="/dishes/${dish.id}/edit" class="btn btn-sm btn-warning"
              >Edit</a
            >
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp" %>
