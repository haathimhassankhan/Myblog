<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="mystyle.css">
<title>Login</title>
<style type="text/css">
.centre-top{
    margin-top: 212px;
    margin-bottom: 14px;
    }
    .centre-bottom{
    margin-bottom: 14px;
    }
    #container{
    border : 5px
   }
    </style>

</head>
<body class="white-skin">
<div class="center-box">

<form:form method="post"
          action="/shopon/helloagain" modelAttribute="user" class="form-inline justify-content-center">
          <div class="h-100 row align-items-center">
          <div class="col-sm-12">
          <div id="container">
          
		   <fieldset>
		   <form:input type="text" class="form-control centre-top" placeholder="username"
		   path="userName" disabled="disabled" />
		   </fieldset>
		   <fieldset >
		   <form:input type="password" class="form-control centre-bottom" placeholder="password"
		   path="password" disabled="disabled" />
		   </fieldset>
		   <fieldset >
		   <input class="btn btn-primary" value="Login" type="submit">
		   <a href="/shopon/signUp" class="btn btn-primary">Sign Up</a>
		   </fieldset>
		   <%if(session.getAttribute("greeting") != null && !session.getAttribute("greeting").equals("")) { %>
		   ${greeting}
		   <% } %>
		   </div>
		   </div>
		   </div>
		   </form:form>
		   </div>
							

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>