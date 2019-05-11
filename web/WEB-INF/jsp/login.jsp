<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登陆</title>
    <style type="text/css">
        div{
            width: 500px;
            height: 200px;
            margin: 0 auto;
        }
        body{
            background: url(/pic/302797.jpg);
            background-size: cover;
        }
    </style>

</head>
<body>
<div></div>
<form action="${pageContext.request.contextPath }/login.action" method="post">
    <table align="center">
        <tr ></tr>
        <tr>
            <td>用户账号：</td>
            <td><input type="text" name="username" /><br/></td>
        </tr>
        <tr>
            <td>用户密码 ：</td>
            <td><input type="password" name="password" /><br/></td>
        </tr>
        <tr>
            <td align="center" colspan="2"><input type="submit" value="登陆"/></td>
        </tr>
    </table>
</form>
</body>
</html>