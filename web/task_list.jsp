<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Task" %>
<%--
    Author: Prateek Prasher <prateekprshr@gmail.com>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task List</title>
</head>
<body>
<h1>Task List</h1>

<h2>Add a new task</h2>

<form action="/tasks/add.do" method="post">
    <input hidden type="text" name="requestType" value="PUT">
    <label for="description">Description</label>
    <input type="text" name="description" id="description">

    <button type="submit">Add</button>
</form>


<h2>Current tasks</h2>
<%
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
%>
</body>
</html>
