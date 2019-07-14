<%-- 
    Document   : login
    Created on : Mar 17, 2018, 9:44:41 PM
    Author     : Granit Krasniqi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="loginFailed" type="java.lang.Boolean" --%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>OculusVision - Login</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
      <form class="form-signin" method="POST" name="loginform" action="">
        <img class="mb-4" src="resources/images/analysis.svg" alt="" width="72" height="72">
        <c:choose>
            <c:when test="${loginFailed}">
                <p class="h5 mb-5 font-weight-normal">The username or password you entered are not correct.</p>
            </c:when>
            <c:otherwise>
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            </c:otherwise>
        </c:choose>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="rememberMe"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted">Oculus<strong>Vision</strong> &copy; 2018</p>
    </form>
  </body>
</html>
