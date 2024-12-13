/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Pessoa;
import util.Datas;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import connection.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */

public class PessoaDAO {

    public boolean adicionaPessoa(Pessoa novaPessoa) {
        String sql = "insert into pessoa (nome,nascimento,telefone,dataCriacao,dataModificacao) values (?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novaPessoa.getNome());
            stmt.setString(2, novaPessoa.getNascimento());
            stmt.setString(3, novaPessoa.getTelefone());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(novaPessoa.getDataCriacao()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(novaPessoa.getDataModificao()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pessoa> listaPessoa() {
        String sql = "select * from pessoa";

        List<Pessoa> listaPessoa = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Pessoa pessoa = new Pessoa();

                pessoa.setId(resultado.getLong("id"));
                pessoa.setNome(resultado.getString("nome"));
                pessoa.setNascimento(resultado.getString("nascimento"));
                pessoa.setTelefone(resultado.getString("telefone"));
                pessoa.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                pessoa.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                listaPessoa.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPessoa;
    }

    public boolean alteraPessoa(String nomePessoa, String novoNome) {
        String sql = "Update pessoa set nome = ?, dataModificacao = ? where nome = ? ";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setString(3, nomePessoa);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String consultaPessoa(String nomePessoa) {
        String sql = "select * from pessoa where nome = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomePessoa);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Pessoa pessoaConsulta = new Pessoa();

                    pessoaConsulta.setId(resultado.getLong("id"));
                    pessoaConsulta.setNome(resultado.getString("nome"));
                    pessoaConsulta.setNascimento(resultado.getString("nascimento"));
                    pessoaConsulta.setTelefone(resultado.getString("telefone"));
                    pessoaConsulta.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    pessoaConsulta.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return pessoaConsulta.toString();
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean excluiPessoa(String nomePessoa) {
        String sql = "delete from pessoa where nome = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomePessoa);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostraPessoa() {
        boolean vazio = true;

        List<Pessoa> listaPessoa = this.listaPessoa();

        String texto = "PESSOAS CADASTRADAS";

        for (Pessoa pessoa : listaPessoa) {
            if (pessoa != null) {
                texto += "\n" + pessoa.toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }
    
    public Pessoa buscaPessoaId(long id){
        String sql = "select * from pessoa where id = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Pessoa buscaPessoa = new Pessoa();

                    buscaPessoa.setId(resultado.getLong("id"));
                    buscaPessoa.setNome(resultado.getString("nome"));
                    buscaPessoa.setNascimento(resultado.getString("nascimento"));
                    buscaPessoa.setTelefone(resultado.getString("telefone"));
                    buscaPessoa.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaPessoa.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaPessoa;
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }     
    }
    
    public Pessoa buscaPessoaNome(String nomePessoa){
         String sql = "select * from pessoa where nome = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomePessoa);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Pessoa buscaPessoa = new Pessoa();

                    buscaPessoa.setId(resultado.getLong("id"));
                    buscaPessoa.setNome(resultado.getString("nome"));
                    buscaPessoa.setNascimento(resultado.getString("nascimento"));
                    buscaPessoa.setTelefone(resultado.getString("telefone"));
                    buscaPessoa.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaPessoa.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaPessoa;
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }     
    }
}
