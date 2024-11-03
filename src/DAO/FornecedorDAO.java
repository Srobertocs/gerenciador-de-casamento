/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Fornecedor;
import util.Datas;
import view.GUI;

/**
 *
 * @author SOUSA
 */
public class FornecedorDAO {

    GUI gui = new GUI();

    Fornecedor[] fornecedores = new Fornecedor[10];

    public FornecedorDAO() {

        Fornecedor fornecedor01 = new Fornecedor();
        fornecedor01.setNome("Gosto de festa");
        fornecedor01.setCnpj("10.123.258/0001-89");
        fornecedor01.setTelefone("(34) 3314-9865");
        fornecedor01.setValorAPagar(10000.0);
        fornecedor01.setParcelas(2);
        fornecedor01.setStatus("em pagamento");
        fornecedor01.setDataCriacao(Datas.pegaDataAgora());
        fornecedor01.setDataModificacao(Datas.pegaDataAgora());
        adicionaFornecedor(fornecedor01);

        Fornecedor fornecedor02 = new Fornecedor();
        fornecedor02.setNome("Cerimonial Oliveira");
        fornecedor02.setCnpj("12.789.258/0001-84");
        fornecedor02.setTelefone("(34) 3365-7855");
        fornecedor02.setValorAPagar(25000.0);
        fornecedor02.setParcelas(10);
        fornecedor02.setStatus("em pagamento");
        fornecedor02.setDataCriacao(Datas.pegaDataAgora());
        fornecedor02.setDataModificacao(Datas.pegaDataAgora());
        adicionaFornecedor(fornecedor02);

        Fornecedor fornecedor03 = new Fornecedor();
        fornecedor03.setNome("Gastronomia Buffet Lima");
        fornecedor03.setCnpj("25.456.259/0001-87");
        fornecedor03.setTelefone("(34) 3156-9852");
        fornecedor03.setValorAPagar(12000.0);
        fornecedor03.setParcelas(1);
        fornecedor03.setStatus("em pagamento");
        fornecedor03.setDataCriacao(Datas.pegaDataAgora());
        fornecedor03.setDataModificacao(Datas.pegaDataAgora());
        adicionaFornecedor(fornecedor03);

    }

    public boolean adicionaFornecedor(Fornecedor novoFornecedor) {

        if (novoFornecedor.getNome().equals("") || novoFornecedor.getCnpj().equals("") || novoFornecedor.getTelefone().equals("") || novoFornecedor.getParcelas() == 0 || novoFornecedor.getStatus().equals("")
                || novoFornecedor.getValorAPagar() == 0.0) {
            Fornecedor.setCount();
            return false;
        }

        for (int i = 0; i < 10; i++) {
            if (this.fornecedores[i] != null) {
                if (this.fornecedores[i].getCnpj().equals(novoFornecedor.getCnpj())) {
                    gui.exibirMensagemFornecedorExistente();
                    Fornecedor.setCount();
                    return false;
                }
            } else {
                this.fornecedores[i] = novoFornecedor;
                return true;
            }
        }
        return false;
    }

    public String mostraFornecedor() {

        boolean vazio = true;

        String texto = "FORNECEDORES CADASTRADOS";

        for (int i = 0; i < 10; i++) {

            if (this.fornecedores[i] != null) {
                texto += "\n" + fornecedores[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public Fornecedor consultaFornecedor(String cnpj) {

        for (int i = 0; i < 10; i++) {
            if (this.fornecedores[i] != null && this.fornecedores[i].getCnpj().equals(cnpj)){
                return this.fornecedores[i];
            }
        }
        return null;
    }
    
    public Fornecedor pegaFornecedor(long id){
        
        for (int i = 0; i < 10; i++) {
            if (this.fornecedores[i] != null && this.fornecedores[i].getId() == id){
                return this.fornecedores[i];
            }
        }
        return null;
    }

    public boolean excluiFornecedor(String cnpj) {

        for (int i = 0; i < 10; i++) {
            if (this.fornecedores[i] != null && this.fornecedores[i].getCnpj().equals(cnpj)) {
                this.fornecedores[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean alteraStatusPagamento(String cnpj, String novoStatus) {

        for (int i = 0; i < 10; i++) {
            if (this.fornecedores[i] != null && this.fornecedores[i].getCnpj().equals(cnpj)) {
                this.fornecedores[i].setStatus(novoStatus);
                this.fornecedores[i].setDataModificacao(Datas.pegaDataAgora());
                return true;
            }
        }
        return false;
    }
}
