package DAO;

import entities.Task;

import java.sql.SQLException;
import java.util.Collection;

public interface TaskDAO {
    public void addTask(Task task) throws SQLException;
    public void updateTask(Task task) throws SQLException;
    public Task getTaskById(int id_task) throws SQLException;
    public Collection getAllTasks() throws SQLException;
    public void deleteTask(Task task) throws SQLException;
}
