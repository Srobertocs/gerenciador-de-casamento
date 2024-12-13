/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import util.Datas;
import beans.ConviteFamiliar;
import beans.ConviteIndividual;
import beans.Usuario;
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
public class ConviteFamiliarDAO {

    public boolean adicionaConviteFamilia(ConviteFamiliar novoConviteFamiliar) {
        String sql = "insert into conviteFamilia (nomeFamilia, acesso, dataCriacao,dataModificacao) values (?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (this.confereConviteFamilia(novoConviteFamiliar) == false) {
                if (novoConviteFamiliar != null) {
                    stmt.setString(1, novoConviteFamiliar.getNomeFamilia());
                    stmt.setString(2, novoConviteFamiliar.getAcesso());
                    stmt.setTimestamp(3, java.sql.Timestamp.valueOf(novoConviteFamiliar.getDataCriacao()));
                    stmt.setTimestamp(4, java.sql.Timestamp.valueOf(novoConviteFamiliar.getDataModificacao()));

                    return stmt.executeUpdate() > 0;
                } else {
                    return false;
                }
            }else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean confereConviteFamilia(ConviteFamiliar conviteFamiliar) {

        String sql = "select * from conviteFamilia where acesso = ? or nomeFamilia = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, conviteFamiliar.getAcesso());
            stmt.setString(2, conviteFamiliar.getNomeFamilia());

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ConviteFamiliar> listaConviteFamiliar() {
        String sql = "select * from ConviteFamilia";

        List<ConviteFamiliar> listaConviteFamiliar = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                ConviteFamiliar conviteFamiliar = new ConviteFamiliar();

                conviteFamiliar.setId(resultado.getLong("id"));
                conviteFamiliar.setNomeFamilia(resultado.getString("nomeFamilia"));
                conviteFamiliar.setAcesso(resultado.getString("acesso"));
                conviteFamiliar.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                conviteFamiliar.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                listaConviteFamiliar.add(conviteFamiliar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConviteFamiliar;
    }

    public String mostraConviteFamiliar() {
        boolean vazio = true;

        List<ConviteFamiliar> listaConviteFamiliar = this.listaConviteFamiliar();
        String texto = "FAMILIAS CADASTRADAS";

        for (ConviteFamiliar conviteFamiliar : listaConviteFamiliar) {
            texto += "\n\n" + conviteFamiliar.toString();
            vazio = false;
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public ConviteFamiliar confirmaConviteFamiliar(Usuario usuarioLogado, List<ConviteIndividual> listaConviteIndividual) {

        List<ConviteFamiliar> listaConviteFamiliar = this.listaConviteFamiliar();

        for (ConviteFamiliar conviteFamiliar : listaConviteFamiliar) {
            for (ConviteIndividual conviteIndividual : listaConviteIndividual) {
                if (usuarioLogado.getPessoa().getId() == conviteIndividual.getPessoa().getId()) {
                    if (conviteIndividual.getParentesco().equals("Pai")) {
                        return conviteFamiliar;
                    }
                }
            }
        }
        return null;
    }

    public ConviteFamiliar buscaConviteFamiliarId(long id) {
        String sql = "select * from conviteFamilia where id = ?";
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    ConviteFamiliar buscaFamilia = new ConviteFamiliar();

                    buscaFamilia.setId(resultado.getLong("id"));
                    buscaFamilia.setNomeFamilia(resultado.getString("nomeFamilia"));
                    buscaFamilia.setAcesso(resultado.getString("acesso"));
                    buscaFamilia.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaFamilia.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaFamilia;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
