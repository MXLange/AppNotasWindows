/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author MSL
 */
public class TaskTableModel extends AbstractTableModel {

    String[] columns = {"Nome", "Descri??o", "Prazo", "Tarefa Conclu?da", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList<Task>();
    
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
       
        if(this.getValueAt(0, columnIndex) != null) {
            return this.getValueAt(0, columnIndex).getClass();
        }        
            return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
                return simpleDateFormat.format(tasks.get(rowIndex).getDeadline());
                //return tasks.get(rowIndex).getDeadline();
            case 3:
                return tasks.get(rowIndex).isItCompleted();
//            case 4:
//                return "";
//            case 5:
//                return "";
            default:
                return "Dados n?o encontrados.";
                
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }   
}
