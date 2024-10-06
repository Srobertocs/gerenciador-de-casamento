/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaocasamento;

import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */
public class PessoaDAO {

    GUI gui = new GUI();

    Pessoa[] pessoa = new Pessoa[40];

    public PessoaDAO() {

        //Pessoas armazenadas automaticamente 
        Pessoa pessoa01 = new Pessoa();
        pessoa01.setNome("silvio");
        pessoa01.setNascimento("31/10/2003");
        pessoa01.setTelefone("(34) 999634-3432");
        pessoa01.setDataCriacao(Datas.pegaDataAgora());
        pessoa01.setDataModificacao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa01);

        Pessoa pessoa02 = new Pessoa();
        pessoa02.setNome("bruna");
        pessoa02.setNascimento("30/10/2003");
        pessoa02.setTelefone("(34) 98756-6598");
        pessoa02.setDataCriacao(Datas.pegaDataAgora());
        pessoa02.setDataModificacao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa02);
        
        Pessoa pessoa03 = new Pessoa();
        pessoa03.setNome("vitor");
        pessoa03.setNascimento("04/09/2000");
        pessoa03.setTelefone("(34) 99965-0087");
        pessoa03.setDataCriacao(Datas.pegaDataAgora());
        pessoa03.setDataModificacao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa03);
        
        Pessoa pessoa04 = new Pessoa();
        pessoa04.setNome("ana julia");
        pessoa04.setNascimento("05/11/1999");
        pessoa04.setTelefone("(34) 99789-1236");
        pessoa04.setDataCriacao(Datas.pegaDataAgora());
        pessoa04.setDataModificacao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa04);
    }

    public boolean adicionaPessoa(Pessoa novaPessoa) {
        
        if(novaPessoa.getNome().equals("") || novaPessoa.getNascimento().equals("") || novaPessoa.getTelefone().equals("")){
            Pessoa.setCount();
            return false;
        }
        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null) {
                if (this.pessoa[i].getNome().equals(novaPessoa.getNome())) {
                    gui.exibirMensagemPessoaJaExistente();
                    Pessoa.setCount();
                    return false;
                }
            }
            if (this.pessoa[i] == null) {
                this.pessoa[i] = novaPessoa;
                return true;
            }
        }
        gui.exibirMensagemListaPessoaLotada();
        return false;
    }

    public String mostraPessoa() {
        
        boolean vazio = true;

        String texto = "PESSOAS CADASTRADAS";

        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null) {
                texto += "\n" + pessoa[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }

    public boolean excluiPessoa(String nomePessoa) {

        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null && this.pessoa[i].getNome().equals(nomePessoa)) {
                this.pessoa[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean alteraPessoa(String nomePessoa, String novoNome) {

        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null && this.pessoa[i].getNome().equals(nomePessoa)) {
                this.pessoa[i].setNome(novoNome);
                this.pessoa[i].setDataModificacao(Datas.pegaDataAgora());
                return true;
            }
        }
        return false;
    }

    public Pessoa consultaPessoa(String nomePessoa) {

        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null && this.pessoa[i].getNome().equals(nomePessoa)) {
                return this.pessoa[i];
            }
        }
        return null;
    }

    public Pessoa pegaPessoa(String nomePessoa) {

        boolean vazio = true;

        for (int i = 0; i < 40; i++) {

            if (this.pessoa[i] != null && this.pessoa[i].getNome().equals(nomePessoa)) {
                vazio = false;
                return pessoa[i];
            }
        }
        if (vazio == true) {
            GUI.exibirMensagemPessoaNaoEncontrada();
        }
        return null;
    }
}
