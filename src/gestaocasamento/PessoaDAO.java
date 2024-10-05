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
        pessoa01.setNome("Silvio");
        pessoa01.setNascimento("31/10/2003");
        pessoa01.setTelefone("(34) 999634-3432");
        pessoa01.setDataCriacao(Datas.pegaDataAgora());
        pessoa01.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa01);

        Pessoa pessoa02 = new Pessoa();
        pessoa02.setNome("Bruna");
        pessoa02.setNascimento("30/10/2003");
        pessoa02.setTelefone("(34) 98756-6598");
        pessoa02.setDataCriacao(Datas.pegaDataAgora());
        pessoa02.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPessoa(pessoa02);
    }

    public boolean adicionaPessoa(Pessoa novaPessoa) {
        for (int i = 0; i < 40; i++) {
            if (this.pessoa[i] != null) {
                if( this.pessoa[i].getNome().equals(novaPessoa.getNome())){
                     gui.exibirMensagemPessoaJaExistente();
                     return false;
                }      
            } else if (this.pessoa[i] == null) {
                this.pessoa[i] = novaPessoa;
                return true;
            }
        }
        return false;
    }

    public String mostraPessoa() {
        boolean vazio = true;

        String texto = "PESSOAS CADASTRADAS\n";

        for (int i = 0; i < 40; i++) {
            if (this.pessoa[i] != null) {
                texto += "\n" + pessoa[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            GUI.exibirMensagemPessoaNaoEncontrada();
        } else {
           return texto;
        }
        return null;
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
                this.pessoa[i].setDataModificao(Datas.pegaDataAgora());
                return true;
            }
        }
        return false;
    }

    public String consultaPessoa(String nomePessoa) {
        String pessoa;
        
        for (int i = 0; i < 40; i++) {
            if (this.pessoa[i] != null && this.pessoa[i].getNome().equals(nomePessoa)) {
                pessoa = "RESULTADO DA CONSULTA"
                        + "\n\n" + this.pessoa[i].toString();
                return pessoa;
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
