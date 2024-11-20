/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import view.GUI;
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

    GUI gui = new GUI();

    Usuario[] usuario = new Usuario[40];

    public boolean validaTipo(String tipo, String novoTipo) {

        if (tipo.equals("Noivo") & tipo.equals(novoTipo)) {
            gui.exibirMensagemNoivoJaCadastrado();
            return false;
        } else if (tipo.equals("Noiva") & tipo.equals(novoTipo)) {
            gui.exibirMensagemNoivaJaCadastrada();
            return false;
        }
        return true;
    }

    public String mostraUsuario() {

        boolean vazio = true;

        String texto = "USUARIOS CADASTRADOS";

        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] != null) {
                texto += "\n" + usuario[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;

        } else {
            return texto;
        }
    }

    public Usuario consultaUsuario(String nomeLogin) {

        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] != null && this.usuario[i].getLogin().equals(nomeLogin)) {
                return usuario[i];
            }
        }
        return null;
    }

    public boolean alteraSenhaUsuario(Usuario usuario, String novaSenha) {

        if (usuario == null) {
            return false;
        } else {

            for (int i = 0; i < 40; i++) {

                if (this.usuario[i] != null) {
                    if (this.usuario[i].getSenha().equals(usuario.getSenha()) && this.usuario[i].getLogin().equals(usuario.getLogin())) {
                        this.usuario[i].setSenha(novaSenha);
                        this.usuario[i].setDataModificacao(Datas.pegaDataAgora());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean excluirUsuario(Usuario usuario) {

        if (usuario == null) {
            return false;
        }
        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] == usuario) {
                this.usuario[i] = null;
                return true;
            }
        }
        return true;
    }

    //Banco de dados
    public boolean adicionaUsuario(Usuario novoUsuario, Pessoa pessoa) {
        String sql = "insert into usuario (login,senha,tipo,dataCriacao,dataModificacao, idPessoa) values (?,?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
