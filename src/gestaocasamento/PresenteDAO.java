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
public class PresenteDAO {

    GUI gui = new GUI();

    Presente[] presente = new Presente[5];

    public PresenteDAO() {
        //Armazena os presentes automaticamente
        Presente presente01 = new Presente();
        presente01.setComprador(null);
        presente01.setNome("JOGO DE PANELAS");
        presente01.setTipo("Utensilio de cozinha");
        presente01.setDataCriacao(Datas.pegaDataAgora());
        presente01.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPresentes(presente01);

        Presente presente02 = new Presente();
        presente02.setComprador(null);
        presente02.setNome("TELEVISAO");
        presente02.setTipo("Eletrodomestico");
        presente02.setDataCriacao(Datas.pegaDataAgora());
        presente02.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPresentes(presente02);

        Presente presente03 = new Presente();
        presente03.setComprador(null);
        presente03.setNome("GELADEIRA");
        presente03.setTipo("Eletrodomestico");
        presente03.setDataCriacao(Datas.pegaDataAgora());
        presente03.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPresentes(presente03);

        Presente presente04 = new Presente();
        presente04.setComprador(null);
        presente04.setNome("MICROONDAS");
        presente04.setTipo("Eletrodomestico");
        presente04.setDataCriacao(Datas.pegaDataAgora());
        presente04.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPresentes(presente04);

        Presente presente05 = new Presente();
        presente05.setComprador(null);
        presente05.setNome("CAMA KING SIZE");
        presente05.setTipo("Movel");
        presente05.setDataCriacao(Datas.pegaDataAgora());
        presente05.setDataModificao(Datas.pegaDataAgora());
        this.adicionaPresentes(presente05);
    }

    public void adicionaPresentes(Presente novoPresente) {
        for (int i = 0; i < 5; i++) {
            if (this.presente[i] == null) {
                this.presente[i] = novoPresente;
                break;
            }
        }
    }

    public String mostraListaPresentes() {
        String texto = "LISTA DE PRESENTES\n";
        for (int i = 0; i < 5; i++) {
            if (this.presente[i] != null) {
                texto += "\n" + this.presente[i].toString();
            }
        }
        return texto;
    }

    public boolean validaIdPresente(long id) {
        for (int i = 0; i < 5; i++) {
            if (this.presente[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean reservaCompradorPresentes(Pessoa pessoa, long id) {

        for (int i = 0; i < 5; i++) {
            //Localiza o presente 
            if (this.presente[i] != null & this.presente[i].getId() == id) {
                //Verifica se não possui comprador
                if (this.presente[i].getComprador() == null) {
                    this.presente[i].setComprador(pessoa);
                    this.presente[i].setDataModificao(Datas.pegaDataAgora());
                    return true;
                } else {
                    gui.exibiMensagemPresenteJaEscolhido();
                }
            }
        }
        return false;
    }
}
