/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Fornecedor;
import beans.Pagamento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import util.Datas;

/**
 *
 * @author SOUSA
 */
public class PagamentoDAO {

    Pagamento[] pagamentos = new Pagamento[50];

    public Pagamento criaPagamento(int parcela, Fornecedor fornecedor, double valor, LocalDate dataVenc) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setDataCriacao(Datas.pegaDataAgora());
        novoPagamento.setDataModificacao(Datas.pegaDataAgora());
        novoPagamento.setDescricao("Pendente");
        novoPagamento.setFornecedor(fornecedor);
        novoPagamento.setParcelas(parcela);
        novoPagamento.setValor(valor);
        novoPagamento.setDataPagamento(dataVenc);

        return novoPagamento;
    }

    public boolean lancamentoPagamentos(Fornecedor fornecedor, String textoData) {

        double valorPorParcela = calculaValorParcelas(fornecedor.getParcelas(), fornecedor.getValorAPagar());

        int parcelas = 1;
        LocalDate dataAtual = Datas.convercaoData(textoData);

        if (consultaLancamentoExistente(fornecedor) == true) {
            return false;
        } else {
            for (int i = 0; i < 50; i++) {
                if (this.pagamentos[i] == null) {
                    if (parcelas <= fornecedor.getParcelas()){
                        this.pagamentos[i] = criaPagamento(parcelas, fornecedor, valorPorParcela, dataAtual);
                        dataAtual = Datas.incrementaDataVencimento(dataAtual);
                        parcelas++;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public double calculaValorParcelas(int parcelas, double valor) {
        return valor / parcelas;
    }

    /*public String consultaStatusPagamento(Fornecedor fornecedor) {
        return null;
    }
     */
    public boolean consultaLancamentoExistente(Fornecedor fornecedor) {

        for (int i = 0; i < 50; i++) {
            if (this.pagamentos[i] != null && this.pagamentos[i].getFornecedor().getCnpj().equals(fornecedor.getCnpj())) {
                return true;
            }
        }
        return false;
    }

    public String mostraPagamentosLancados() {

        boolean vazio = true;

        String texto = "PAGAMENTOS  LANCADOS";

        for (int i = 0; i < 50; i++) {

            if (this.pagamentos[i] != null) {
                texto += "\n\n" + this.pagamentos[i].toString();
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
