package DAO;

import entities.TaskScore;

import java.sql.SQLException;
import java.util.Collection;

public interface TaskScoreDAO {
    public void addTaskScore(TaskScore taskScore) throws SQLException;
    public void updateTaskScore(TaskScore taskScore) throws SQLException;
    public TaskScore getTaskScoreById(int id_task_score) throws SQLException;
    public Collection getAllTasksScores() throws SQLException;
    public void deleteTaskScore(TaskScore taskScore) throws SQLException;
}
