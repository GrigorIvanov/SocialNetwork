<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.io.File" %>  
    <%@ page import="com.oreilly.servlet.MultipartRequest" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
String UPLOAD_DIR = "Book/src/main/webapp/static/img";
String applicationPath = request.getServletContext().getRealPath("");
String[] pat= applicationPath.split("/.metadata/");
applicationPath = pat[0];
String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

MultipartRequest m = new MultipartRequest(request, uploadFilePath);  
out.print("successfully uploaded");  
  
%>  
</body>
</html>