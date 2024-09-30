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
                // 1 - Acessa o Menu de pessoas
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
                // 1 - Adicionar pessoas
                case 1:
                    boolean confirmacao;

                    confirmacao = pessoaDAO.adicionaPessoa(gui.criaPessoa());

                    if (confirmacao == true) {
                        JOptionPane.showMessageDialog(null, "Pessoa cadastrada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pessoa não cadastrada. A lista de pessoas está lotada");
                    }
                    break;
                // 3 - Excluir cadastro
                case 3:
                    boolean cadastroExcluido;

                    cadastroExcluido = pessoaDAO.excluirPessoa(JOptionPane.showInputDialog("Digite o nome da pessoa que deseja excluir "));  
                    
                    if(cadastroExcluido){
                        JOptionPane.showMessageDialog(null, "Cadastro excluido com sucesso");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "O Cadastro não foi encontrado. \nPossiveis motivos: "
                                + "\n1 - Nome digitado incorretamente "
                                + "\n2 - Cadastro inexistente"
                                + "\n3- Nenhum cadastro encontrado");
                    }
                    break;
                // 5 - Mostrar casdastro 
                case 5:
                    pessoaDAO.mostraPessoa();
                    break;
                // 6 - Volta para o menu principal
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
