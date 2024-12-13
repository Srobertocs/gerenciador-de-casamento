/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Fornecedor;
import beans.Pagamento;
import view.GUI;
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
public class FornecedorDAO {

    GUI gui = new GUI();

    public boolean adicionaFornecedor(Fornecedor novoFornecedor) {
        String sql = "insert into fornecedor (nome, cnpj, telefone,valorAPagar,parcelas,status,dataCriacao,dataModificacao) values (?,?,?,?,?,?,?,?)";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            if (this.confereFornecedor(novoFornecedor.getCnpj()) == false) {
                if (novoFornecedor != null) {
                    stmt.setString(1, novoFornecedor.getNome());
                    stmt.setString(2, novoFornecedor.getCnpj());
                    stmt.setString(3, novoFornecedor.getTelefone());
                    stmt.setDouble(4, novoFornecedor.getValorAPagar());
                    stmt.setInt(5, novoFornecedor.getParcelas());
                    stmt.setString(6, novoFornecedor.getStatus());
                    stmt.setTimestamp(7, java.sql.Timestamp.valueOf(novoFornecedor.getDataCriacao()));
                    stmt.setTimestamp(8, java.sql.Timestamp.valueOf(novoFornecedor.getDataModificacao()));

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

    public List<Fornecedor> listaFornecedor() {
        String sql = "select * from fornecedor";

        List<Fornecedor> listaFornecedor = new ArrayList<>();

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();

                fornecedor.setId(resultado.getLong("id"));
                fornecedor.setCnpj(resultado.getString("cnpj"));
                fornecedor.setNome(resultado.getString("nome"));
                fornecedor.setTelefone(resultado.getString("telefone"));
                fornecedor.setValorAPagar(resultado.getDouble("valorAPagar"));
                fornecedor.setParcelas(resultado.getInt("parcelas"));
                fornecedor.setStatus(resultado.getString("status"));
                fornecedor.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                fornecedor.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                listaFornecedor.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFornecedor;
    }

    public boolean excluiFornecedor(String cnpj) {
        String sql = "delete from fornecedor where cnpj = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cnpj);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Fornecedor consultaFornecedor(String cnpj) {
        String sql = "select * from fornecedor where cnpj = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cnpj);

            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    Fornecedor fornecedor = new Fornecedor();

                    fornecedor.setId(resultado.getLong("id"));
                    fornecedor.setCnpj(resultado.getString("cnpj"));
                    fornecedor.setNome(resultado.getString("nome"));
                    fornecedor.setTelefone(resultado.getString("telefone"));
                    fornecedor.setValorAPagar(resultado.getDouble("valorAPagar"));
                    fornecedor.setParcelas(resultado.getInt("parcelas"));
                    fornecedor.setStatus(resultado.getString("status"));
                    fornecedor.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    fornecedor.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return fornecedor;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostraFornecedor() {
        boolean vazio = true;

        List<Fornecedor> listaFornecedor = this.listaFornecedor();
        String texto = "FORNECEDORES CADASTRADOS";

        for (Fornecedor fornecedor : listaFornecedor) {
            texto += "\n" + fornecedor.toString();
            vazio = false;
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public boolean confereFornecedor(String cnpj) {

        String sql = "select * from fornecedor where cnpj = ?";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cnpj);

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Fornecedor buscaFornecedorId(long id) {
        String sql = "select * from fornecedor where id = ?";
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    Fornecedor buscaFornecedor = new Fornecedor();

                    buscaFornecedor.setId(resultado.getLong("id"));
                    buscaFornecedor.setCnpj(resultado.getString("cnpj"));
                    buscaFornecedor.setNome(resultado.getString("nome"));
                    buscaFornecedor.setTelefone(resultado.getString("telefone"));
                    buscaFornecedor.setValorAPagar(resultado.getDouble("valorAPagar"));
                    buscaFornecedor.setParcelas(resultado.getInt("parcelas"));
                    buscaFornecedor.setStatus(resultado.getString("status"));
                    buscaFornecedor.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                    buscaFornecedor.setDataModificacao(resultado.getTimestamp("dataModificacao").toLocalDateTime());

                    return buscaFornecedor;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean alteraStatusPagamento(String cnpj, String novoStatus) {
        String sql = "Update fornecedor set status = ?, dataModificacao = ? where cnpj = ? ";

        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setString(3, cnpj);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void consultaPagamentoFornecedor(List<Pagamento> listaPagamentos) {

        boolean pago = false;

        List<Fornecedor> listaFornecedor = this.listaFornecedor();
        for (Fornecedor fornecedor : listaFornecedor) {
            for (Pagamento pagamento : listaPagamentos) {
                if (pagamento.getFornecedor().getId() == fornecedor.getId() && pagamento.getDescricao().equals("Pago") && pagamento.getParcelas() == fornecedor.getParcelas()) {

                    pago = this.alteraStatusPagamento(fornecedor.getCnpj(), "Pago");

                }
            }
        }
        if (pago == true) {
            gui.exibirMensagemFornecedorAlterado();
        }else {
            System.out.println("teste manezão");
        }
            
    }
}
