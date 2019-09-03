<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title></title>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		body { font-size:12px; line-height:20px; font-family:宋体; }		
		button{width:77px; height:auto;text-align:center;}
		#login { border:1px solid #666; margin:80px auto; padding:4px; width:360px; }
		#login h2 {font-size:14px; line-height:30px;  5px center no-repeat; border-bottom:2px solid #ccc;; padding-left:30px; color:#9a0000; }
		#login dl { line-height:50px; padding:5px; }
		#login dl dt { float:left; clear:left; width:90px; text-align:right; }
		#login dl dd { margin-left:60px; }
		#login dl dd input { margin:10px; vertical-align:middle; }
		#login dl dd input.input-text { border:1px solid #999999; font-size:14px; height:18px; width:150px; padding:4px 4px; }
		#login dl dd .button input { height:26px; border:none; cursor:pointer;}
		#login dl dd input.input-btn { width:77px;height: 26px }
	</style>
</head>
<body background="img/chi.jpg">

<div id="login">
	<h2>用户登录</h2>
	<form method="post" action="ServletLogin">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="username" /></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="userpwd" /></dd>
			<dt>验证码：</dt>
			<dd><input class="input-text" type="text" name="check_code" />
			<img src="http://localhost:8080/ChiHeWanLeapp_Server/CheckServlet"> 
			</dd>
			
			<dt></dt>
			<dd class="button" ><input class="input-btn" type="button" name="submit" value="登录" /></dd>
		</dl>
	</form>
</div>

</body>
</html>


