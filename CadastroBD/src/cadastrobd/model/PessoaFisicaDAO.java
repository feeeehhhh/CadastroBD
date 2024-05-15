package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

public class PessoaFisicaDAO {
    private ConectorBD conectorBD;
    private ArrayList<PessoaFisica> lista;

    public PessoaFisicaDAO(ConectorBD conectorBD) {
        this.conectorBD = conectorBD;
        this.lista = new ArrayList<>();
    }

    public ArrayList<PessoaFisica> readAll() throws ClassNotFoundException {
        try {
            String SQL = "SELECT * FROM pessoafisica";
            PreparedStatement ps = conectorBD.getDataSource().getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            ArrayList<PessoaFisica> lista = new ArrayList<>();

            while (rs.next()) {
                PessoaFisica pef = new PessoaFisica();

                pef.id = rs.getInt("id");
                pef.nome = rs.getString("nome");
                pef.cpf = rs.getLong("cpf");
                pef.telefone = rs.getLong("telefone");
                pef.email = rs.getString("email");
                pef.logradouro = rs.getString("logradouro");
                pef.cidade = rs.getString("cidade");
                pef.estado = rs.getString("estado");
                

                lista.add(pef);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar " + ex.getMessage());
        }
        return null;
    }

    public void incluir(PessoaFisica pessoaFisica) throws ClassNotFoundException {
        try {
            String SQL = "INSERT INTO pessoafisica (nome, cpf, telefone, email, logradouro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conectorBD.getDataSource().getConnection().prepareStatement(SQL);
            ps.setString(1, pessoaFisica.nome);
            ps.setLong(2, pessoaFisica.cpf);
            ps.setLong(3, pessoaFisica.telefone);
            ps.setString(4, pessoaFisica.email);
            ps.setString(5, pessoaFisica.logradouro);
            ps.setString(6, pessoaFisica.cidade);
            ps.setString(7, pessoaFisica.estado);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao incluir " + ex.getMessage());
        }
    }
    public PessoaFisica getPessoa(int id) {
        for (PessoaFisica pessoaFisica : lista) {
            if (pessoaFisica.id == id) {
                return pessoaFisica;
            }
        }
        return null;
    }

    public void alterar(PessoaFisica pessoaFisica) throws ClassNotFoundException {
        try {
            String SQL = "UPDATE pessoafisica SET nome=?, cpf=?, telefone=?, email=?, logradouro=?, cidade=?, estado=? WHERE id=?";
            PreparedStatement ps = conectorBD.getDataSource().getConnection().prepareStatement(SQL);
            ps.setString(1, pessoaFisica.nome);
            ps.setLong(2, pessoaFisica.cpf);
            ps.setLong(3, pessoaFisica.telefone);
            ps.setString(4, pessoaFisica.email);
            ps.setString(5, pessoaFisica.logradouro);
            ps.setString(6, pessoaFisica.cidade);
            ps.setString(7, pessoaFisica.estado);
            ps.setInt(8, pessoaFisica.id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar " + ex.getMessage());
        }
    }

    public void excluir(int id) throws ClassNotFoundException {
        try {
            String SQL = "DELETE FROM pessoafisica WHERE id=?";
            PreparedStatement ps = conectorBD.getDataSource().getConnection().prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir " + ex.getMessage());
        }
    }
}
