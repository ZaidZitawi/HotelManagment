package com.example.HotelManagment.DTO;



public class HousekeepingDTO {
    private int taskId;
    private int roomId;
    private int employeeId;
    private String taskDescription;
    private String status;


    public HousekeepingDTO(int taskId, int roomId, int employeeId, String taskDescription, String status) {
        this.taskId = taskId;
        this.roomId = roomId;
        this.employeeId = employeeId;
        this.taskDescription = taskDescription;
        this.status = status;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
