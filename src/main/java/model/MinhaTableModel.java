package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MinhaTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MinhaUserPosJava> usuarios;
    private String[] colunas = {"ID", "Nome", "E-mail"};

    public MinhaTableModel(List<MinhaUserPosJava> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MinhaUserPosJava usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getNome();
            case 2:
                return usuario.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    public void atualizar(List<MinhaUserPosJava> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
    }


}
