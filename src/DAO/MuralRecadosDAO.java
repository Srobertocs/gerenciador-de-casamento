/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Usuario;
import beans.Recados;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Datas;

/**
 *
 * @author SOUSA
 */
public class MuralRecadosDAO {

    public boolean postarMuralRecados(Usuario usuario, Recados novoRecado) {
        String sql = "insert into recados (recado,dataCriacao,dataModificacao, idUsuario) values (?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (novoRecado != null) {
                stmt.setString(1, novoRecado.getRecado());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(novoRecado.getDataCriacao()));
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(novoRecado.getDataModificacao()));
                stmt.setLong(4, usuario.getId());

                return stmt.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String consultarMuralRecados(Usuario usuario, UsuarioDAO usuarioDAO) {
        String sql = "select * from recados where idUsuario = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, usuario.getId());

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Recados recadoConsulta = new Recados();

                    recadoConsulta.setId(resultado.getLong("id"));
                    recadoConsulta.setRecado(resultado.getString("recado"));
                    recadoConsulta.setUsuario(usuarioDAO.buscaUsuarioId(resultado.getLong("idUsuario")));
                    recadoConsulta.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    recadoConsulta.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return recadoConsulta.toString(1);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validaCodigoUsuario(long codigoRecado, Usuario usuarioLogado) {
        String sql = "select * from recados where id = ? and IdUsuario = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, codigoRecado);
            stmt.setLong(2, usuarioLogado.getId());

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editarComentarioMuralRecados(long codigoRecado, Usuario usuarioLogado, String novoRecado) {

        if (validaCodigoUsuario(codigoRecado, usuarioLogado)) {

            String sql = "Update recados set recado = ?, dataModificacao = ? where id = ? ";

            try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setString(1, novoRecado);
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
                stmt.setLong(3, codigoRecado);

                return stmt.executeUpdate() > 0;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

    public boolean excluirRecado(long codigoRecado, Usuario usuarioLogado) {

        if (validaCodigoUsuario(codigoRecado, usuarioLogado)) {

            String sql = "delete from recados where id = ? ";

            try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setLong(1, codigoRecado);

                return stmt.executeUpdate() > 0;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }

    public List<Recados> listaRecado(UsuarioDAO usuarioDAO) {
        String sql = "select * from recados";

        List<Recados> listaRecado = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Recados recado = new Recados();

                recado.setId(resultado.getLong("id"));
                recado.setRecado(resultado.getString("recado"));
                recado.setUsuario(usuarioDAO.buscaUsuarioId(resultado.getInt("idUsuario")));
                recado.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                recado.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                listaRecado.add(recado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaRecado;
    }

    public String mostrarMuralRecados(UsuarioDAO usuarioDAO) {
        boolean vazio = true;

        String texto = "MURAL DE RECADOS";

        List<Recados> listaRecado = this.listaRecado(usuarioDAO);

        for (Recados recados : listaRecado) {
            if (recados != null) {
                texto += "\n\n" + recados.toString(1);
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }
    
    public String RelatorioMuralRecados(UsuarioDAO usuarioDAO) {
        boolean vazio = true;

        String texto = "RELATORIO DE RECADOS\n";

        List<Recados> listaRecado = this.listaRecado(usuarioDAO);

        for (Recados recados : listaRecado) {
            if (recados != null) {
                texto += "\n" + recados.toString(2);
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }
}
