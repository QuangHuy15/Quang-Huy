<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500&display=swap')
	;

* {
	box-sizing: border-box;
}

body {
	font-family: 'Montserrat', sans-serif;
	font-size: 17px;
}

#wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 80vh;
}

form {
	border: 1px solid #dadce0;
	border-radius: 5px;
	padding: 30px;
}

h3 {
	text-align: center;
	font-size: 24px;
	font-weight: 500;
}

p {
	text-align: center;
	font-size: 18px;
	font-weight: 500;
}

.form-group {
	margin-bottom: 15px;
	position: relative;
}

input {
	height: 50px;
	width: 300px;
	outline: none;
	border: 1px solid #dadce0;
	padding: 10px;
	border-radius: 5px;
	font-size: inherit;
	color: #202124;
	transition: all 0.3s ease-in-out;
}

button {
	height: 50px;
	width: 300px;
	outline: none;
	border: 1px solid #dadce0;
	padding: 10px;
	border-radius: 5px;
	font-size: inherit;
	color: #202124;
	transition: all 0.3s ease-in-out;
}

label {
	position: absolute;
	padding: 0px 5px;
	left: 5px;
	top: 50%;
	/* Quan trọng  */
	pointer-events: none;
	transform: translateY(-50%);
	background: #fff;
	transition: all 0.3s ease-in-out;
}

.form-group input:focus {
	border: 2px solid #1a73e8;
}

.form-group input:focus+label, .form-group input:valid+label {
	top: 0px;
	font-size: 13px;
	font-weight: 500;
	color: #1a73e8;
}

input#btn-login {
	background: #1a73e8;
	color: #fff;
	cursor: pointer;
}

input#btn-login:hover {
	opacity: 0.85;
}

button#btn-signup {
	background: #1a73e8;
	color: #fff;
	cursor: pointer;
}

button#btn-signup:hover {
	opacity: 0.85;
}
</style>
	<div id="wrapper">
		<form action="/vniot/login" method="post">
			<h3>Log in</h3>
			<c:if test="${alert !=null}">
				<h3 class="alert alert danger">${alert}</h3>
			</c:if>
			<div class="form-group">
				<input type="text" name="username" required> <label for="">Username</label>
			</div>
			<div class="form-group">
				<input type="password" name="password" required> <label
					for="">Password</label>
			</div>
			<div>
				<a href="/vniot/views/forget.jsp">Forgot Password?</a>
			</div>
			<input type="submit" value="Log in" id="btn-login"> <br>

			<p style="text-align: center;">You don't have an account?</p>
			<button type="button" id="btn-signup"
				onclick="window.location.href='/vniot/views/register.jsp'">Sign
				up</button>
		</form>
	</div>
</body>
</html>