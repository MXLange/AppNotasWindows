/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author MSL
 */
public class ProjectController {
    
    
    
    public void save(Project project) {
        
        String sql = "INSERT INTO projects (name, description, createdAt, UpdatedAt) VALUES (?, ?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception e) {
             throw new RuntimeException("Erro ao salvar projeto. " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }     
    }
    
    public void update(Project project) {

        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, UpdatedAt = ? WHERE id = ?;";

        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int id) {
        
        String sql = "DELETE FROM projects WHERE id = ?;";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
           conn = ConnectionFactory.getConnection();
           statement = conn.prepareStatement(sql);
           statement.setInt(1, id);
           statement.execute();
           
        } catch (Exception e) {
             throw new RuntimeException("Erro ao deletar elemento" + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
        
        
    }
    
    public List<Project> getAll() {
        
        String sql = "SELECT * FROM projects;";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Project> listOfProjects = new ArrayList<Project>();
        
        try {
            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                
                Project temp = new Project();
                
                temp.setId(resultSet.getInt("id"));
                temp.setName(resultSet.getString("name"));
                temp.setDescription(resultSet.getString("description"));
                temp.setCreatedAt(resultSet.getDate("createdAt"));
                temp.setUpdatedAt(resultSet.getDate("UpdatedAt"));
                
                listOfProjects.add(temp);
            }
            
            
        } catch (Exception e) {
        } finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }
        
        return listOfProjects;
    }
    
}
