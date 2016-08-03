<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Task" %>
<%@ page import="model.User" %>
<%--
    Author: Prateek Prasher <prateekprshr@gmail.com>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task manager</title>
</head>
<body>
<h1>Task Manager: Organize yourself :)</h1>

<%
    if (session.getAttribute("error") != null) {
        String error = (String) session.getAttribute("error");
        out.print("<h3>Sorry, " + error + "</h3>");
    }

    if (session.getAttribute("logged") != null) {
        User user = (User) session.getAttribute("user");

        out.print("<h2>\n" +
                "    Welcome " + user.getUsername() + "\n" +
                "    <a href='/about.jsp'>\n" +
                "        <button type='button'>About</button>\n" +
                "    </a>\n" +
                "    <a href='/logout.do'>\n" +
                "       <button type='button'>Logout</button>\n" +
                "    </a>\n" +
                "</h2>");

        out.print("<h2>Add a new task</h2>\n" +
                "<form action=\"/tasks/add.do\" method=\"post\">\n" +
                "    <input hidden type=\"text\" name=\"requestType\" value=\"PUT\">\n" +
                "    <label for=\"description\">Description</label>\n" +
                "    <input type=\"text\" name=\"description\" id=\"description\" required>\n" +
                "    <button type=\"submit\">Add</button>\n" +
                "</form>\n" +
                "<h2>Current tasks</h2>");

        ArrayList<Task> tasks = (ArrayList<Task>) request.getAttribute("tasks");
        if (tasks.isEmpty()) {
            out.print("No tasks have been added!!!");
        } else {
            out.print("<ul>");
            for (Task task : tasks) {
                out.print("<li>" + task.getDescription() + "</li>");
                out.print("<form action=\"/tasks/delete.do\" method=\"post\">" +
                        "<input hidden type=\"text\" name=\"requestType\" value=\"DELETE\">" +
                        "<input hidden type=\"text\" name=\"id\" value=\"" + task.getId() + "\">" +
                        "<button type='submit'>Delete</button>" +
                        "</form>");
            }
            out.print("</ul>");
        }
    } else {
        out.print("<h2>Please login to continue" +
                "    <a href='/about.jsp'>\n" +
                "        <button type='button'>About</button>\n" +
                "    </a>\n" +
                " </h2>" +
                "<form method=\"post\" action=\"/login.do\">" +
                "    <label for=\"username\">Username:</label>" +
                "    <input type=\"text\" required name=\"username\" id=\"username\">" +
                "    <label for=\"password\">Password:</label>" +
                "    <input type=\"password\" required name=\"password\" id=\"password\">" +
                "    <button type=\"submit\">Login</button>" +
                "</form>");
    }
%>

</body>
</html>
