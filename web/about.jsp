<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Task" %>
<%@ page import="model.User" %>
<%--
    Author: Prateek Prasher <prateekprshr@gmail.com>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
</head>
<body>
<h1>Task Manager: Organize yourself</h1>

<%
    if (session.getAttribute("error") != null) {
        String error = (String) session.getAttribute("error");
        out.print("<h3>Sorry, " + error + "</h3>");
    }

    if (session.getAttribute("logged") != null) {
        User user = (User) session.getAttribute("user");

        out.print("<h2>\n" +
                "    Welcome " + user.getUsername() + "\n" +
                "    <a href='/tasks.do'>\n" +
                "        <button type=\"button\">Home</button>\n" +
                "    </a>\n" +
                "    <a href='/logout.do'>\n" +
                "       <button type='button'>Logout</button>\n" +
                "    </a>\n" +
                "</h2>");
    } else {
        out.print("<h2>Welcome guest <a href='/tasks.do'><button type='button'>Login</button></a></h2>");
    }
%>

<p>This web app helps you to keep a track of your tasks. You my add and delete tasks.</p>

</body>
</html>
