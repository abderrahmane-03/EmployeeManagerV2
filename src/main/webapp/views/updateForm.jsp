<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
  <title>Employees</title>
  <link rel="stylesheet" type="text/css" href="static/css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
</head>
<body>
<div style="position: relative; width: 100%;">
  <img src="${pageContext.request.contextPath}/static/homebg.png" style="position: absolute; z-index: -3; width: 100%;" alt="Arstotzka Logo">
  <div style="position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.3); z-index: -3;"></div>
</div>

<div style="padding:50px ">
  <a href="http://localhost:2525/EmployManger/" class="button-50">Home</a>

<form action="http://localhost:2525/EmployManger/edit" method="post" enctype="multipart/form-data">
  <div class="card">
    <header class="card-header">
      <div class="logo">
        <img src="${pageContext.request.contextPath}/static/logo.png" alt="Arstotzka Logo">
      </div>
      <h1>A R S T O T Z K A</h1>
      <div class="visa-status"></div>
    </header>



    <section class="photo-section">
      <div class="photo" style="position: relative">

        <img src="static/${employee.picture}" alt="Profile Photo" style="height: 9.5rem; width:10rem;">

        <input required id="file-input" value="${employee.picture}" name="picture" type="file" style="display: none;">

        <label for="file-input" style="cursor: pointer; position: absolute; top: 100px; left: 10px;">
          <img src="static/pxu.png" style="height: 3rem; width: 3rem;">
        </label>
      </div>
      <div class="visa-status-container">
        <div class="visa-status">
          <div class="granted">
            <span>Granted</span>

            <div class="boxes">
              <div class="box"></div>
              <div class="box"></div>
              <div class="box yellow"></div>
              <div class="box yellow"></div>
            </div>

          </div>
          <hr width="100%;" color="#f5b5b5" size="3">
          <div class="entry-visa-status">ENTRY EMPLOY STATUS</div>
          <div class="dots-grid">

            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>

            <!-- Row 2 -->
            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>

            <!-- Row 3 -->
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>

            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle filled"></div>
            <div class="circle filled"></div>
          </div>
        </div>
      </div>
    </section>



    <section class="personal-info">
      <div class="info-row">
        <p class="info left">${employee.id}</p>
        <input required type="hidden" name="id" value="${employee.id}">
        <p class="info right"><input required class="inputs" value="${employee.name}" type="text" name="name" placeholder="Enter Name"></p>
      </div>
      <div class="label-row">
        <span class="label left">I.D</span>
        <span class="label right">FIRST NAME</span>
      </div>

      <div class="info-row">
        <p class="info left">
          <input required class="inputs" value="${employee.phone}" type="text" name="phone" placeholder="Enter Phone number"></p>
        <p class="info right"><input required class="inputs" value="${employee.email}" type="email" name="email" placeholder="Enter Email"></p>
      </div>
      <div class="label-row">
        <span class="label left">PHONE NUMBER</span>
        <span class="label right">EMAIL</span>
      </div>

      <div class="info-row">
        <p class="info left">
          <select class="inputs" name="department">
            <option value="RIPD">RIPD</option>
            <option value="IT">IT</option>
            <option value="TECH">TECH</option>
            <option value="INV">INV</option>
            <option value="TEST">TEST</option>
            <option value="PROD">PROD</option>
            <option value="DEV">DEV</option>
            <option value="ARCH">ARCH</option>
          </select>
        </p>
        <p class="info right"><input required class="inputs" type="text" value="${employee.position}" name="position" placeholder="Enter Position"></p>
      </div>
      <div class="label-row">
        <span class="label left">DEPARTMENT</span>
        <span class="label right">POSITION</span>
      </div>

      <div class="info-row">
        <p class="info left">ARSTOTZKA</p>
        <p class="info right">NO ALIAS</p>
      </div>
      <div class="label-row">
        <span class="label left">DISTRICT</span>
        <span class="label right">ALIAS</span>
      </div>
    </section>




    <sectionsty style="margin-left: 40px" class="update-delete">
      <button type="submit" class="update-button">Update employee</button>
    </sectionsty>
  </div>
</form>
</div>
</body>
</html>
