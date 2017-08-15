<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
request.setAttribute("rootPath", request.getContextPath());
request.setAttribute("service", request.getParameter("service"));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>client server login</title>
</head>
<body>
    <div id="page">
            <div id = "content">
                <div id = "login">
                    <form action="${rootPath }/user/login" method="POST" name="login" id="login">
                        <fieldset>
                            <legend>User Login</legend>
                            <p><label for="name">User name:</label><input type="text" name="username" id="username" value=""/></p>
                            <p><label for="password">Password:</label><input type="password" name="password" id="password"/></p>
                            <p><input type="hidden" name="service" value="${service}" /></p>
                            <p class="buttonP"><input type="submit" value="Login" /></p>
                        </fieldset>
                    </form>
                </div>
            </div>
    </div>
</body>
</html>