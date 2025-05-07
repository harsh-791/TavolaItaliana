<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %> <%@ include
file="../layout/header.jsp" %>

<h2>${chef.id == null ? 'Add New Chef' : 'Edit Chef'}</h2>

<form:form
  action="${chef.id == null ? '/chefs' : '/chefs/'.concat(chef.id)}"
  method="post"
  modelAttribute="chef"
  class="needs-validation"
  novalidate="novalidate"
>
  <div class="form-group">
    <form:label path="name">Name</form:label>
    <form:input path="name" class="form-control" required="required" />
    <form:errors path="name" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="email">Email</form:label>
    <form:input
      path="email"
      type="email"
      class="form-control"
      required="required"
    />
    <form:errors path="email" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="specialization">Specialization</form:label>
    <form:input
      path="specialization"
      class="form-control"
      required="required"
    />
    <form:errors path="specialization" class="text-danger" />
  </div>

  <div class="form-group">
    <form:label path="yearsOfExperience">Years of Experience</form:label>
    <form:input
      path="yearsOfExperience"
      type="number"
      class="form-control"
      required="required"
    />
    <form:errors path="yearsOfExperience" class="text-danger" />
  </div>

  <button type="submit" class="btn btn-primary">Save</button>
  <a href="/chefs" class="btn btn-secondary">Cancel</a>
</form:form>

<%@ include file="../layout/footer.jsp" %>
