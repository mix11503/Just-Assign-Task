<%-- 
    Document   : teacher_editTask
    Created on : Apr 21, 2017, 11:53:24 PM
    Author     : Kittisak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Task task = (Task)request.getAttribute("task");
if(task == null){
response.sendRedirect("Teacher_EditTaskServlet?option=getForEdit"+request.getParameter("taskId"));
}  else {
pageContext.setAttribute("task", task);
}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher edit task</title>     
    </head>
    <body>
        <div class="modal fade" id="assign-task-modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="/Teacher_EditTaskServlet" method="post">
                        <div class="modal-header">
                            <h1>Edit Task</h1>
                        </div>
                        <div class="modal-body">
                            <input class="form-control" name="taskname" required="" placeholder="Title" type="text" /><br/>
                            <textarea class="form-control" name="taskdesc" required="" placeholder="Description" rows="3"></textarea><br/>
                            <input class="form-control" name="taskstatus" required="" placeholder="" type="" disabled="" value="In Progress..." hidden="" /><br/>
                            <input class="form-control" name="datelinedate" required="" placeholder="" type="date" /><br/>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success"/>&nbsp;&nbsp;
                            <input type="reset" class="btn btn-danger"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
