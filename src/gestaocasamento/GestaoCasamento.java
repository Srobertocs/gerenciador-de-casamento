/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestaocasamento;

import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */
public class GestaoCasamento {

    //Inicialização dos OBJETOS e DAOS
    PessoaDAO pessoaDAO = new PessoaDAO();
    GUI gui = new GUI();

    public GestaoCasamento() {
        executaOpcaoInicio();
    }

    //Métodos de execução das opcões dos menus 
    private void executaOpcaoInicio() {
        int pegaopcao = 0;

        while (pegaopcao != 6) {
            pegaopcao = gui.menuPrincipal();

            switch (pegaopcao) {
                case 1:
                    executaOpcaoPessoa();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Programa encerrado");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida. Digite novamente");
                    break;
            }
        }
    }

    private void executaOpcaoPessoa() {
        int pegaopcao = 0;

        while (pegaopcao != 6) {
            pegaopcao = gui.menuPessoa();

            switch (pegaopcao) {
                case 1:
                    boolean confirmacao;
                    
                    confirmacao = pessoaDAO.adicionapessoa(gui.criaPessoa());
                    
                    if(confirmacao == true){
                        JOptionPane.showMessageDialog(null, "Pessoa cadastrada");
                    }else {
                         JOptionPane.showMessageDialog(null, "Pessoa não cadastrada");      
                    }
                    break;  
                    
                case 5:
                    pessoaDAO.mostraPessoa();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida. Digite novamente!");
                    break;
            }
        }
    }

    //Main
    public static void main(String[] args) {
        new GestaoCasamento();
    }
}
