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

    Pessoa[] pessoa = new Pessoa[40];

    public boolean adicionapessoa(Pessoa novaPessoa) {
        for (int i = 0; i < 40; i++) {
            if (this.pessoa[i] == null) {
                this.pessoa[i] = novaPessoa;
                return true;
            }
        }
        return false;
    }

    public void mostraPessoa() {
        boolean vazio = true;
        String texto = "PESSOAS CADASTRADAS\n";

        for (int i = 0; i < 40; i++) {
            if (this.pessoa[i] != null) {
                texto +="\n" + pessoa[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            JOptionPane.showMessageDialog(null, "Nenhum cadastro encontrado");
        }else{
            JOptionPane.showMessageDialog(null, texto);
        }
    }
}
