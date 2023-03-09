package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.MinhaUserPosJava;

public class MinhaDao {

    private Connection connection;

    public MinhaDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(MinhaUserPosJava minhaUserPosJava) {
        try {
            String sql = "insert into cadastro_de_macacos (id, nome, email) values (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong(1, getNextId());
            insert.setString(2, minhaUserPosJava.getNome());
            insert.setString(3, minhaUserPosJava.getEmail());
            insert.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long getNextId() throws Exception {
        String sql = "select max(id) from cadastro_de_macacos";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong(1) + 1;
        } else {
            return 1L;
        }
    }

    public List<MinhaUserPosJava> editar() {
        List<MinhaUserPosJava> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, email from cadastro_de_macacos";
            PreparedStatement select = connection.prepareStatement(sql);
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                MinhaUserPosJava usuario = new MinhaUserPosJava(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
                usuarios.add(usuario);
            }
           } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
    public MinhaUserPosJava buscarPorId(long id) {
        MinhaUserPosJava usuario = null;
        try {
            String sql = "SELECT nome, email FROM cadastro_de_macacos WHERE id=?";
            PreparedStatement select = connection.prepareStatement(sql);
            select.setLong(1, id);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                usuario = new MinhaUserPosJava(
                        id,
                        rs.getString("nome"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    
    public void update(MinhaUserPosJava minhaUserPosJava) {
        try {
            String sql = "UPDATE cadastro_de_macacos SET nome=?, email=? WHERE id=?";
            PreparedStatement update = connection.prepareStatement(sql);
            update.setString(1, minhaUserPosJava.getNome());
            update.setString(2, minhaUserPosJava.getEmail());
            update.setLong(3, minhaUserPosJava.getId());
            update.execute();
            connection.commit();
            update.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
