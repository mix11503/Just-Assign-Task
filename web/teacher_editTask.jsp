<%-- 
    Document   : teacher_editTask
    Created on : Apr 21, 2017, 11:53:24 PM
    Author     : Kittisak
--%>
<%@page import="com.junior.jat.model.Task"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% Task task = (Task)request.getAttribute("task");
if(task == null){
response.sendRedirect("Teacher_EditTaskServlet?option=getForEdit&taskId="+request.getParameter("taskId"));
}  else {
pageContext.setAttribute("task", task);
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher Edit Task</title>  
        <style>
            body{
                font-family: 'Source Sans Pro', 'Helvetica Neue', Helvetica, Arial, sans-serif;
                text-align: center;
                -webkit-font-smoothing: antialiased;
            }
            .modal-header {
                border-bottom-color: #f4f4f4;
                min-height: 16.43px;
                padding: 15px;
                border-bottom: 1px solid #e5e5e5;
            }
            .modal-body {
                position: relative;
                padding: 15px;
            }
            .modal-footer {
                border-top-color: #f4f4f4;
                padding: 15px;
                text-align: right;
                border-top: 1px solid #e5e5e5;
            }
            h1{
                font-size: 36px;
                margin-top: 20px;
                margin-bottom: 10px;
                font-weight: 500;
                line-height: 1.1;
                color: inherit;
                margin: .67em 0;
            }
            *{
                box-sizing: border-box;
            }
            div {
                display: block;
            }
            .modal-footer {
                border-top-color: #f4f4f4;
                padding: 15px;
                text-align: center;
                border-top: 1px solid #e5e5e5;
            }
        </style>
    </head>
    <body>
        <div class="modal fade" id="assign-task-modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Teacher_EditTaskServlet?option=sendEdit" method="post">
                        <div class="modal-header">
                            <h1>Edit Task</h1>
                        </div>
                        <div class="modal-body">
                            <input class="form-control" name="taskName" required="" placeholder="Title" type="text" value="${task.taskName}"/><br/>
                            <textarea class="form-control" name="taskDescription" required="" placeholder="Description" rows="3" value="${task.taskDescription}"></textarea><br/>
                            <input class="form-control" name="status" required="" placeholder="" type="" disabled=""  hidden="" value="${task.status}"/><br/>
                            <input class="form-control" name="taskDeadlineDate" required="" placeholder="" type="date" value="${task.taskDeadlineDate}"/><br/>
                            <input name="taskId" type="hidden" value="${task.taskId}"/>
                            <select name="status">
                                <option value="1">In progress...</option>
                                <option value="2">Done</option>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" />&nbsp;&nbsp;
                            <input type="reset" class="btn btn-danger" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
