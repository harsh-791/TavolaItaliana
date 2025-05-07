<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Chef App</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      .navbar {
        margin-bottom: 20px;
      }
      .container {
        padding-top: 20px;
      }
      .alert {
        margin-top: 20px;
      }
      .form-group {
        margin-bottom: 15px;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="/">Chef App</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="/chefs">Chefs</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/dishes">Dishes</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container">
      <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>
    </div>
  </body>
</html>
