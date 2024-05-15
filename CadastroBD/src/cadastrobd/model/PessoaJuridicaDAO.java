package cadastrobd.model;


import cadastrobd.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaJuridicaDAO {

    public ArrayList<PessoaJuridica> lista;
    public Connection connection;
    public SequenceManager sequenceManager;

    public PessoaJuridicaDAO(Connection connection, SequenceManager sequenceManager) {
        this.lista = new ArrayList<>();
        this.connection = connection;
        this.sequenceManager = sequenceManager;
    }

    public ArrayList<PessoaJuridica> readAll() {
        try {
            String SQL = "SELECT * FROM pessoajuridica";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            ArrayList<PessoaJuridica> lista = new ArrayList<>();

            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica();

                pj.id = rs.getInt("id");
                pj.nome = rs.getString("nome");
                pj.cnpj = rs.getLong("cnpj");
                pj.telefone = rs.getLong("telefone");
                pj.email = rs.getString("email");
                pj.logradouro = rs.getString("logradouro");
                pj.cidade = rs.getString("cidade");
                pj.estado = rs.getString("estado");

                lista.add(pj);
            }

            // Fechar recursos
            rs.close();
            ps.close();

            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar: " + ex.getMessage());
        }
        return null;
    }

    public PessoaJuridica getPessoa(int id) {
        for (PessoaJuridica pessoaJuridica : lista) {
            if (pessoaJuridica.id == id) {
                return pessoaJuridica;
            }
        }
        return null;
    }

    public void incluir(PessoaJuridica pessoaJuridica) throws ClassNotFoundException {
        try {
            String SQL = "INSERT INTO pessoajuridica (nome, cnpj, telefone, email, logradouro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, pessoaJuridica.nome);
            ps.setLong(2, pessoaJuridica.cnpj);
            ps.setLong(3, pessoaJuridica.telefone);
            ps.setString(4, pessoaJuridica.email);
            ps.setString(5, pessoaJuridica.logradouro);
            ps.setString(6, pessoaJuridica.cidade);
            ps.setString(7, pessoaJuridica.estado);
            ps.executeUpdate();
            lista.add(pessoaJuridica);
        } catch (SQLException ex) {
            System.out.println("Erro ao incluir " + ex.getMessage());
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) throws ClassNotFoundException {
        try {
            String SQL = "UPDATE pessoajuridica SET nome=?, cnpj=?, telefone=?, email=?, logradouro=?, cidade=?, estado=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, pessoaJuridica.nome);
            ps.setLong(2, pessoaJuridica.cnpj);
            ps.setLong(3, pessoaJuridica.telefone);
            ps.setString(4, pessoaJuridica.email);
            ps.setString(5, pessoaJuridica.logradouro);
            ps.setString(6, pessoaJuridica.cidade);
            ps.setString(7, pessoaJuridica.estado);
            ps.setInt(8, pessoaJuridica.id);
            ps.executeUpdate();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).id == pessoaJuridica.id) {
                    lista.set(i, pessoaJuridica);
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar " + ex.getMessage());
        }
    }

    public void excluir(int id) throws ClassNotFoundException {
        try {
            String SQL = "DELETE FROM pessoajuridica WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
            lista.removeIf(pj -> pj.id == id);
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir " + ex.getMessage());
        }
    }
}
