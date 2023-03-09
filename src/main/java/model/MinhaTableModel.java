package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MinhaTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MinhaUserPosJava> usuarios;
    private String[] colunas = {"ID", "Nome", "E-mail"};
    private Object[][] dados;
    private List<List<Object>> linhas;

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

    public void setDados(Object[][] dados) {
        this.dados = dados;
        fireTableDataChanged();
    }
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    public void atualizar(List<MinhaUserPosJava> usuarios) {
        this.usuarios = usuarios;
        fireTableDataChanged();
    }
    public void setRowCount(int rowCount) {
        Object[][] newDados = new Object[rowCount][colunas.length];
        for (int i = 0; i < rowCount; i++) {
            if (i < dados.length) {
                System.arraycopy(dados[i], 0, newDados[i], 0, colunas.length);
            } else {
                for (int j = 0; j < colunas.length; j++) {
                    newDados[i][j] = null;
                }
            }
        }
        setDados(newDados);
    }
    public void addRow(Object[] rowData) {
        ArrayList<Object> novaLinha = new ArrayList<>(Arrays.asList(rowData));
        linhas.add(novaLinha);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }




}
