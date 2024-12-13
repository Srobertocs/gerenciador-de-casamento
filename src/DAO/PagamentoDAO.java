/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Fornecedor;
import beans.Pagamento;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.Datas;

/**
 *
 * @author SOUSA
 */
public class PagamentoDAO {

    public boolean lancamentoPagamentos(Fornecedor fornecedor, LocalDate dataAtual) {

        double valorPorParcela;

        if (fornecedor == null || confereLancamentoExistente(fornecedor) == true) {
            return false;
        } else {
            valorPorParcela = calculaValorParcelas(fornecedor.getParcelas(), fornecedor.getValorAPagar());
            for (int parcelas = 1; parcelas <= fornecedor.getParcelas(); parcelas++) {
                this.lancaPagamento(criaPagamento(parcelas, fornecedor, valorPorParcela, dataAtual));
                dataAtual = Datas.incrementaDataVencimento(dataAtual);
            }
            return true;
        }
    }

    public boolean pagamentosPagos(long codigo) {

        String sql = "Update pagamento set descricao = ?, dataModificacao = ? where id = ? and dataPagamento = ? and descricao = ? ";
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "Pago");
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setLong(3, codigo);
            stmt.setDate(4, java.sql.Date.valueOf(Datas.pegaDataAgoraSemHoras()));
            stmt.setString(5, "Em aberto");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pagamento> listaPagamento(FornecedorDAO fornecedorDAO) {
        String sql = "select * from pagamento";

        List<Pagamento> listaPagamento = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setId(resultado.getLong("id"));
                pagamento.setDescricao(resultado.getString("descricao"));
                pagamento.setValor(resultado.getDouble("valor"));
                pagamento.setParcelas(resultado.getInt("parcela"));
                pagamento.setDataPagamento(resultado.getDate("dataPagamento").toLocalDate());
                pagamento.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                pagamento.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                pagamento.setFornecedor(fornecedorDAO.buscaFornecedorId(resultado.getLong("idFornecedor")));

                listaPagamento.add(pagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPagamento;
    }

    public String mostraPagamentosLancados(FornecedorDAO fornecedorDAO) {

        List<Pagamento> listaPagamento = this.listaPagamento(fornecedorDAO);
        boolean vazio = true;

        String texto = "PAGAMENTOS  LANCADOS";

        for (Pagamento pagamento : listaPagamento) {

            texto += "\n\n" + pagamento.toString(1);
            vazio = false;
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public String mostraPagamentosDoDia(FornecedorDAO fornecedorDAO) {
        boolean vazio = true;

        String sql = "Select * from pagamento where dataPagamento = ?";

        String texto = "PAGAMENTOS COM VENCIMENTO PARA HOJE";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(Datas.pegaDataAgoraSemHoras()));

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    Pagamento pagamentoDoDia = new Pagamento();

                    pagamentoDoDia.setId(resultado.getLong("id"));
                    pagamentoDoDia.setDescricao(resultado.getString("descricao"));
                    pagamentoDoDia.setValor(resultado.getDouble("valor"));
                    pagamentoDoDia.setParcelas(resultado.getInt("parcela"));
                    pagamentoDoDia.setDataPagamento(resultado.getDate("dataPagamento").toLocalDate());
                    pagamentoDoDia.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    pagamentoDoDia.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                    pagamentoDoDia.setFornecedor(fornecedorDAO.buscaFornecedorId(resultado.getLong("idFornecedor")));

                    texto += "\n\n" + pagamentoDoDia.toString(1);

                    vazio = false;
                }
                if (vazio == true) {
                    return null;
                } else {
                    return texto;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pagamento criaPagamento(int parcela, Fornecedor fornecedor, double valor, LocalDate dataVenc) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setDataCriacao(Datas.pegaDataAgora());
        novoPagamento.setDataModificacao(Datas.pegaDataAgora());
        novoPagamento.setDescricao("Em aberto");
        novoPagamento.setFornecedor(fornecedor);
        novoPagamento.setParcelas(parcela);
        novoPagamento.setValor(valor);
        novoPagamento.setDataPagamento(dataVenc);

        return novoPagamento;
    }

    public boolean lancaPagamento(Pagamento novoPagamento) {
        String sql = "insert into pagamento (descricao,valor,dataCriacao,parcela,dataPagamento,dataModificacao, idFornecedor) values (?,?,?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (novoPagamento != null) {
                stmt.setString(1, novoPagamento.getDescricao());
                stmt.setDouble(2, novoPagamento.getValor());
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(novoPagamento.getDataCriacao()));
                stmt.setInt(4, novoPagamento.getParcelas());
                stmt.setDate(5, java.sql.Date.valueOf(novoPagamento.getDataPagamento()));
                stmt.setTimestamp(6, java.sql.Timestamp.valueOf(novoPagamento.getDataModificacao()));
                stmt.setLong(7, novoPagamento.getFornecedor().getId());

                return stmt.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double calculaValorParcelas(int parcelas, double valor) {
        return valor / parcelas;
    }

    public boolean confereLancamentoExistente(Fornecedor fornecedor) {

        String sql = "select * from pagamento where idFornecedor = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, fornecedor.getId());

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String consultaVencimentoDosDias(LocalDate dataSelecionada, FornecedorDAO fornecedorDAO) {
        boolean vazio = true;
        String sql = "Select * from pagamento where dataPagamento = ?";

        String texto = "PAGAMENTOS COM VENCIMENTO PARA A DATA SELECIONADA";;

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(dataSelecionada));

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    Pagamento pagamentoDoDia = new Pagamento();

                    pagamentoDoDia.setId(resultado.getLong("id"));
                    pagamentoDoDia.setDescricao(resultado.getString("descricao"));
                    pagamentoDoDia.setValor(resultado.getDouble("valor"));
                    pagamentoDoDia.setParcelas(resultado.getInt("parcela"));
                    pagamentoDoDia.setDataPagamento(resultado.getDate("dataPagamento").toLocalDate());
                    pagamentoDoDia.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    pagamentoDoDia.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                    pagamentoDoDia.setFornecedor(fornecedorDAO.buscaFornecedorId(resultado.getLong("idFornecedor")));

                    texto += "\n\n" + pagamentoDoDia.toString(1);

                    vazio = false;
                }
                if (vazio == true) {
                    return null;
                } else {
                    return texto;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean pagamentoPagosAuto(int dias) {

        LocalDate dataVencimentoAuto = Datas.dataVencimentoAuto(dias);

        boolean pago = false;

        String sql = "Update pagamento set descricao = ?, dataModificacao = ?  where dataPagamento <= ? and descricao = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "Pago");
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setDate(3, java.sql.Date.valueOf(dataVencimentoAuto));
            stmt.setString(4, "Em aberto");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String RelatorioPagamentosPagos(FornecedorDAO fornecedorDAO) {
        
        DecimalFormat valorFormatado = new DecimalFormat("#.00");

        List<Pagamento> listaPagamento = this.listaPagamento(fornecedorDAO);
        boolean vazio = true;
        
        int totalPago = 0;

        String texto = "RELATORIO DE PAGAMENTO";

        for (Pagamento pagamento : listaPagamento) {
            
            if(pagamento.getDescricao().equals("Pago")){
                texto += "\n\n" + pagamento.toString(2);
                vazio = false;
                
                totalPago += pagamento.getValor();  
            }
        }
        if (vazio == true) {
            return null;
        } else {
            texto += "\n\n\n VALOR TOTAL PAGO = R$"+ valorFormatado.format(totalPago);
            return texto;
        }
    }
}
