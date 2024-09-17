<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in account</title>
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
	margin: 0;
	padding: 0;
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
	width: 300px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

h3 {
	text-align: center;
	font-size: 24px;
	font-weight: 500;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 20px;
	position: relative;
}

input {
	height: 50px;
	width: 100%;
	outline: none;
	border: 1px solid #dadce0;
	padding: 10px;
	border-radius: 5px;
	font-size: inherit;
	color: #202124;
	transition: all 0.3s ease-in-out;
}

button, input[type="submit"] {
	height: 50px;
	width: 100%;
	outline: none;
	border: 1px solid #dadce0;
	padding: 10px;
	border-radius: 5px;
	font-size: inherit;
	color: #fff;
	background-color: #1a73e8;
	cursor: pointer;
	transition: all 0.3s ease-in-out;
}

button:hover, input[type="submit"]:hover {
	opacity: 0.85;
}

label {
	position: absolute;
	padding: 0px 5px;
	left: 10px;
	top: 50%;
	transform: translateY(-50%);
	background: #fff;
	transition: all 0.3s ease-in-out;
	color: #70757a;
}

.form-group input:focus {
	border: 2px solid #1a73e8;
}

.form-group input:focus+label, .form-group input:valid+label {
	top: -10px;
	font-size: 13px;
	font-weight: 500;
	color: #1a73e8;
}
</style>

	<div id="wrapper">
		<form action="/vniot/forget" method="post">
			<h3>Setting new pass</h3>

			<div class="form-group">
				<input type="password" name="password" required> <label
					for="password">New pass</label>
			</div>

			<input type="submit" value="Confirm" id="btn-login">
		</form>
	</div>

</body>
</html>
