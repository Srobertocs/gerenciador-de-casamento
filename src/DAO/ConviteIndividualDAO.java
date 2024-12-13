/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.ConviteFamiliar;
import beans.ConviteIndividual;
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
public class ConviteIndividualDAO {

    public boolean adicionaConviteIndividual(ConviteIndividual novoConviteIndividual) {
        String sql = "insert into conviteIndividual (confirmacao, parentesco, dataCriacao,dataModificacao, idPessoa, idFamilia) values (?,?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (this.confereConviteIndividual(novoConviteIndividual) == false) {
                if (novoConviteIndividual != null) {
                    stmt.setString(1, novoConviteIndividual.getConfirmacao());
                    stmt.setString(2, novoConviteIndividual.getParentesco());
                    stmt.setTimestamp(3, java.sql.Timestamp.valueOf(novoConviteIndividual.getDataCriacao()));
                    stmt.setTimestamp(4, java.sql.Timestamp.valueOf(novoConviteIndividual.getDataModificacao()));
                    stmt.setLong(5, novoConviteIndividual.getPessoa().getId());
                    stmt.setLong(6, novoConviteIndividual.getFamilia().getId());

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

    public boolean confereConviteIndividual(ConviteIndividual conviteIndividual) {

        String sql = "select * from conviteIndividual where idPessoa = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, conviteIndividual.getPessoa().getId());

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ConviteIndividual> listaConviteIdividual(PessoaDAO pessoaDAO, ConviteFamiliarDAO conviteFamiliarDAO) {
        String sql = "select * from conviteIndividual";

        List<ConviteIndividual> listaConviteIndividual = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                ConviteIndividual conviteIndividual = new ConviteIndividual();

                conviteIndividual.setId(resultado.getLong("id"));
                conviteIndividual.setConfirmacao(resultado.getString("confirmacao"));
                conviteIndividual.setParentesco(resultado.getString("parentesco"));
                conviteIndividual.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                conviteIndividual.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                conviteIndividual.setPessoa(pessoaDAO.buscaPessoaId(resultado.getLong("idPessoa")));
                conviteIndividual.setFamilia(conviteFamiliarDAO.buscaConviteFamiliarId(resultado.getLong("idFamilia")));

                listaConviteIndividual.add(conviteIndividual);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConviteIndividual;
    }

    public String mostraConviteIndividual(PessoaDAO pessoaDAO, ConviteFamiliarDAO conviteFamiliarDAO) {
        boolean vazio = true;

        List<ConviteIndividual> listaConviteIndividual = this.listaConviteIdividual(pessoaDAO, conviteFamiliarDAO);

        String texto = "CONVITES CADASTRADOS";

        for (ConviteIndividual conviteIndividual : listaConviteIndividual) {
            texto += "\n\n" + conviteIndividual.toString(1);
            vazio = false;

        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public boolean alteraConfirmacao(ConviteFamiliar conviteFamiliar) {

        String sql = "Update conviteIndividual set confirmacao = ?, dataModificacao = ? where idFamilia = ? ";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (conviteFamiliar != null) {
                stmt.setString(1, "Confirmado");
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
                stmt.setLong(3, conviteFamiliar.getId());

                return stmt.executeUpdate() > 0;

            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String RelatorioConviteIndividual(PessoaDAO pessoaDAO, ConviteFamiliarDAO conviteFamiliarDAO) {
        boolean vazio = true;

        List<ConviteIndividual> listaConviteIndividual = this.listaConviteIdividual(pessoaDAO, conviteFamiliarDAO);

        String texto = "CONVIDADOS CONFIRMADOS";

        for (ConviteIndividual conviteIndividual : listaConviteIndividual) {
            if(conviteIndividual.getConfirmacao().equals("Confirmado")){
                texto += "\n\n" + conviteIndividual.toString(2);
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
