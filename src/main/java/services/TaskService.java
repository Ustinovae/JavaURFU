package services;

import DAO.Impl.TaskDAOImpl;
import entities.Task;
import entities.TaskScore;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskService {

    private TaskDAOImpl taskDAO;

    public TaskService(){
        taskDAO = new TaskDAOImpl();
    }

    public void addTask(Task task) throws SQLException {
        taskDAO.addTask(task);
    }

    public void updateTask(Task task) throws SQLException {
        taskDAO.updateTask(task);
    }

    public void connectTaskScoreWithTask(Long id_task, TaskScore score) throws SQLException {
        taskDAO.connectTaskScoreWithTask(id_task, score);
    }

    public Task getTaskByTaskName(String taskName) throws SQLException {
        return taskDAO.getTaskByTaskName(taskName);
    }

    public Task getTaskById(int id_task) throws SQLException {
        return taskDAO.getTaskById(id_task);
    }

    public Collection getAllTasks() throws SQLException {
        return taskDAO.getAllTasks();
    }

    public void deleteTask(Task task) throws SQLException {
        taskDAO.deleteTask(task);
    }
}
