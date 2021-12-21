package services;

import DAO.Impl.TaskScoreDAOImpl;
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

public class TaskScoreService {

    private TaskScoreDAOImpl taskScoreDAO;

    public TaskScoreService(){
        taskScoreDAO = new TaskScoreDAOImpl();
    }

    public void addTaskScore(TaskScore taskScore) throws SQLException {
        taskScoreDAO.addTaskScore(taskScore);
    }

    public void updateTaskScore(TaskScore taskScore) throws SQLException {
        taskScoreDAO.updateTaskScore(taskScore);
    }

    public TaskScore getTaskScoreById(int id_task_score) throws SQLException {
        return taskScoreDAO.getTaskScoreById(id_task_score);
    }

    public Collection getAllTasksScores() throws SQLException {
        return taskScoreDAO.getAllTasksScores();
    }

    public void deleteTaskScore(TaskScore taskScore) throws SQLException {
        taskScoreDAO.deleteTaskScore(taskScore);
    }
}
