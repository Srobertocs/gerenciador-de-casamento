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
        executaOpcaoMenuPrincipal();
    }

    //Métodos de execução das opcões dos menus 
    private void executaOpcaoMenuPrincipal() {
        int pegaopcao = 0;

        while (pegaopcao != 6) {
            pegaopcao = gui.menuPrincipal();

            switch (pegaopcao) {
                // 1 - Acessa o Menu de pessoas
                case 1:
                    executaOpcaoMenuPessoa();
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

    private void executaOpcaoMenuPessoa() {
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
                //2 - Consulta Pessoa  
                case 2:
                    String consultaPessoa;

                    consultaPessoa = pessoaDAO.consultaPessoa(JOptionPane.showInputDialog("Digite o nome da pessoa para consulta-la"));

                    if (consultaPessoa != null) {
                        JOptionPane.showMessageDialog(null, "RESULTADO DA CONSULTA"
                                + "\n\n" + consultaPessoa);

                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 3 - Exclui cadastro
                case 3:
                    boolean cadastroExcluido;

                    cadastroExcluido = pessoaDAO.excluiPessoa(JOptionPane.showInputDialog("Digite o nome da pessoa que deseja excluir "));

                    if (cadastroExcluido) {
                        JOptionPane.showMessageDialog(null, "Pessoa excluida com sucesso");
                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 4 - Altera o nome da pessoa 
                case 4:
                    boolean alteraNome;

                    String nomePessoa = JOptionPane.showInputDialog("Digite o nome da pessoa que deseja alterar");
                    String novoNome = JOptionPane.showInputDialog("Digite o novo nome");

                    alteraNome = pessoaDAO.alteraPessoa(nomePessoa, novoNome);

                    if (alteraNome) {
                        JOptionPane.showMessageDialog(null, "Pessoa alterada com sucesso");
                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
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
