/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Usuario;
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

/**
 *
 * @author SOUSA
 */
public class UsuarioDAO {

    public boolean adicionaUsuario(Usuario novoUsuario, Pessoa pessoa) {
        String sql = "insert into usuario (login,senha,tipo,dataCriacao,dataModificacao, idPessoa) values (?,?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (this.confereTipoUsuario(novoUsuario.getTipo()) == false) {
                if (pessoa != null) {
                    stmt.setString(1, novoUsuario.getLogin());
                    stmt.setString(2, novoUsuario.getSenha());
                    stmt.setString(3, novoUsuario.getTipo());
                    stmt.setTimestamp(4, java.sql.Timestamp.valueOf(novoUsuario.getDataCriacao()));
                    stmt.setTimestamp(5, java.sql.Timestamp.valueOf(novoUsuario.getDataModificacao()));
                    stmt.setLong(6, pessoa.getId());

                    return stmt.executeUpdate() > 0;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean alteraSenhaUsuario(String loginUsuario, String novaSenha) {
        String sql = "Update usuario set senha = ?, dataModificacao = ? where login = ? ";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novaSenha);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setString(3, loginUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraNomeUsuario(String loginUsuario, String novoLogin) {
        String sql = "Update usuario set login = ?, dataModificacao = ? where login = ? ";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoLogin);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setString(3, loginUsuario);
            
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean excluirUsuario(String loginUsuario) {
        String sql = "delete from usuario where login = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, loginUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listaUsuario(PessoaDAO pessoaDAO) {
        String sql = "select * from usuario";

        List<Usuario> listaUsuario = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(resultado.getLong("id"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setTipo(resultado.getString("tipo"));
                usuario.setPessoa(pessoaDAO.buscaPessoaId(resultado.getInt("idPessoa")));
                usuario.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                usuario.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                listaUsuario.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }

    public String consultaUsuario(String nomeUsuario, PessoaDAO pessoaDAO) {
        String sql = "select * from usuario where login = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Usuario usuarioConsulta = new Usuario();

                    usuarioConsulta.setId(resultado.getLong("id"));
                    usuarioConsulta.setLogin(resultado.getString("login"));
                    usuarioConsulta.setSenha(resultado.getString("senha"));
                    usuarioConsulta.setTipo(resultado.getString("tipo"));
                    usuarioConsulta.setPessoa(pessoaDAO.buscaPessoaId(resultado.getInt("idPessoa")));
                    usuarioConsulta.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    usuarioConsulta.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return usuarioConsulta.toString(1);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostraUsuario(PessoaDAO pessoaDAO) {
        boolean vazio = true;

        List<Usuario> listaUsuario = this.listaUsuario(pessoaDAO);

        String texto = "PESSOAS CADASTRADAS";

        for (Usuario usuario : listaUsuario) {
            if (usuario != null) {
                texto += "\n" + usuario.toString(1);
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }
    
    public String RelatorioUsuario(PessoaDAO pessoaDAO) {
        boolean vazio = true;

        List<Usuario> listaUsuario = this.listaUsuario(pessoaDAO);

        String texto = "LISTA DE CONVIDADOS";

        for (Usuario usuario : listaUsuario) {
            if (usuario != null && usuario.getTipo().equals("Convidado")) {
                texto += "\n" + usuario.toString(2);
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public Usuario buscaNoivos(String tipo) {
        String sql = "select * from usuario where tipo = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipo);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Usuario buscaUsuario = new Usuario();

                    buscaUsuario.setId(resultado.getLong("id"));
                    buscaUsuario.setLogin(resultado.getString("login"));
                    buscaUsuario.setSenha(resultado.getString("senha"));
                    buscaUsuario.setTipo(resultado.getString("tipo"));
                    buscaUsuario.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaUsuario.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaUsuario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscaUsuarioLogin(String loginUsuario, PessoaDAO pessoaDAO) {
        String sql = "select * from usuario where login = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, loginUsuario);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Usuario buscaUsuario = new Usuario();

                    buscaUsuario.setId(resultado.getLong("id"));
                    buscaUsuario.setLogin(resultado.getString("login"));
                    buscaUsuario.setSenha(resultado.getString("senha"));
                    buscaUsuario.setTipo(resultado.getString("tipo"));
                    buscaUsuario.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaUsuario.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                    buscaUsuario.setPessoa(pessoaDAO.buscaPessoaId(resultado.getLong("idPessoa")));

                    return buscaUsuario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Usuario buscaUsuarioId(long id) {
        String sql = "select * from usuario where id = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Usuario buscaUsuario = new Usuario();

                    buscaUsuario.setId(resultado.getLong("id"));
                    buscaUsuario.setLogin(resultado.getString("login"));
                    buscaUsuario.setSenha(resultado.getString("senha"));
                    buscaUsuario.setTipo(resultado.getString("tipo"));
                    buscaUsuario.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaUsuario.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaUsuario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean confereUsuario(String nomeUsuario, String senha) {

        String sql = "select * from usuario where login = ? and senha = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean confereTipoUsuario(String tipo) {
        String sql = "select * from usuario where tipo = ?";

        if (tipo.equals("Noivo") || tipo.equals("Noiva")) {
            try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, tipo);

                try (ResultSet resultado = stmt.executeQuery()) {

                    return resultado.next();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
