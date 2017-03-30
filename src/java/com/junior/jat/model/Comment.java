package com.junior.jat.model;
public class Comment {
    private int commentId;
    private int taskId;
    private long byStudentId;
    private long byTeacherId;
    private String body;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public long getByStudentId() {
        return byStudentId;
    }

    public void setByStudentId(long byStudentId) {
        this.byStudentId = byStudentId;
    }

    public long getByTeacherId() {
        return byTeacherId;
    }

    public void setByTeacherId(long byTeacherId) {
        this.byTeacherId = byTeacherId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", taskId=" + taskId + ", byStudentId=" + byStudentId + ", byTeacherId=" + byTeacherId + ", body=" + body + '}';
    }
    
    
}
