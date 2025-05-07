<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ include
file="../layout/header.jsp" %>

<h2>${dish.id == null ? 'Add New Dish' : 'Edit Dish'}</h2>

<form:form
  action="${dish.id == null ? '/dishes' : '/dishes/'.concat(dish.id)}"
  method="post"
  modelAttribute="dish"
  class="needs-validation"
  novalidate="novalidate"
>
  <div class="form-group">
    <form:label path="name">Name</form:label>
    <form:input path="name" class="form-control" required="required" />
    <form:errors path="name" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="description">Description</form:label>
    <form:textarea
      path="description"
      class="form-control"
      rows="3"
      required="required"
    />
    <form:errors path="description" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="price">Price</form:label>
    <form:input
      path="price"
      type="number"
      step="0.01"
      class="form-control"
      required="required"
    />
    <form:errors path="price" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="category">Category</form:label>
    <form:input path="category" class="form-control" required="required" />
    <form:errors path="category" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="chef.id">Chef</form:label>
    <form:select path="chef.id" class="form-control" required="required">
      <form:option value="">Select a Chef</form:option>
      <form:options items="${chefs}" itemValue="id" itemLabel="name" />
    </form:select>
    <form:errors path="chef.id" class="text-danger" />
  </div>

  <button type="submit" class="btn btn-primary">Save</button>
  <a href="/dishes" class="btn btn-secondary">Cancel</a>
</form:form>

<%@ include file="../layout/footer.jsp" %>
