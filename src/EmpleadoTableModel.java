/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yorsh
 */
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmpleadoTableModel extends AbstractTableModel {
    private List<String[]> data;
    private String[] columnNames = {"Código", "Nombre", "Edad", "Peso", "Talla", "IMC", "¿Bornout?", "Causa"};

    public EmpleadoTableModel(List<String[]> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

