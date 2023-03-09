/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author MSL
 */
public class TaskController {

    public void save(Task task) {
        
        String sql = "INSERT INTO tasks (idProject, name, description, "
                + "isCompleted, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isItCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
            
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, "
                + "isCompleted = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?;";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isItCompleted());
            statement.setString(5,task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar da tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int taskId) {
        
        String sql = "DELETE FROM tasks WHERE id = ?;";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar elemento" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?;";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> selectedList = new ArrayList<Task>();
        
        try {
            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
                        
            while(resultSet.next()) {
                
                Task temp = new Task();
                
                temp.setId(resultSet.getInt("id"));
                temp.setIdProject(resultSet.getInt("idProject"));
                temp.setName(resultSet.getString("name"));
                temp.setDescription(resultSet.getString("description"));
                temp.setIsCompleted(resultSet.getBoolean("isCompleted"));
                temp.setNotes(resultSet.getString("notes"));
                temp.setDeadline(resultSet.getDate("deadline"));
                temp.setCreatedAt(resultSet.getDate("createdAt"));
                temp.setUpdatedAt(resultSet.getDate("updatedAt"));                
                selectedList.add(temp);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de tarefas " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }
        return selectedList;
    }
    
}
