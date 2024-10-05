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
    PresenteDAO presenteDAO = new PresenteDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    GUI gui = new GUI();

    public GestaoCasamento() {
        //executaOpcaoMenuPrincipalNaoLogado();
        executaOpcaoMenuPrincipalLogado();
    }

    //Métodos de execução das opcões dos menus 
    private void executaOpcaoMenuPrincipalNaoLogado() {
        int pegaOpcao = 0;

        while (pegaOpcao != 3) {

            pegaOpcao = gui.menuPrincipalNaoLogado();

            switch (pegaOpcao) {
                case 1:
                    executaOpcaoMenuPresente();
                    break;
                case 2:
                    executaOpcaoMenuLogin();
                    break;
                case 3:
                    gui.exibirMesangemProgramaEncerrado();
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuLogin() {
        int pegaOpcao = 0;

        while (pegaOpcao != 3) {
            pegaOpcao = gui.menuLogin();

            switch (pegaOpcao) {
                case 1:
                    executaOpcaoMenuPrincipalLogado();
                    pegaOpcao = 3;
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuPrincipalLogado() {
        int pegaOpcao = 0;

        while (pegaOpcao != 6) {
            pegaOpcao = gui.menuPrincipalLogado();

            switch (pegaOpcao) {
                // 1 - Acessa o Menu de pessoas
                case 1:
                    executaOpcaoMenuPessoa();
                    break;
                // 2 - Acessa o Menu da lista de presentes
                case 2:
                    executaOpcaoMenuPresente();
                    break;
                case 6:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
                    break;
            }
        }
    }

    private void executaOpcaoMenuPessoa() {

        int pegaOpcao = 0;

        while (pegaOpcao != 6) {

            pegaOpcao = gui.menuPessoa();
            switch (pegaOpcao) {
                // 1 - Adicionar pessoas
                case 1:
                    boolean confirmacao;

                    confirmacao = pessoaDAO.adicionaPessoa(gui.criaPessoa());

                    if (confirmacao == true) {
                        gui.exibirMensagemPessoaAdicionada();
                    } else {
                        gui.exibirMensagemPessoaNaoAdicionada();
                    }
                    break;
                //2 - Consulta Pessoa  
                case 2:
                    String consultaPessoa;

                    consultaPessoa = pessoaDAO.consultaPessoa(gui.recebeNomePessoa());

                    if (consultaPessoa != null) {
                        gui.exibirPessoas(consultaPessoa);
                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 3 - Exclui cadastro
                case 3:
                    boolean cadastroExcluido;

                    cadastroExcluido = pessoaDAO.excluiPessoa(gui.recebeNomePessoa());

                    if (cadastroExcluido) {
                        gui.exibirMensagemPessoaExcluida();
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
                        gui.exibirMensagemPessoaAlterada();
                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 5 - Mostrar casdastro 
                case 5:
                    gui.exibirPessoas(pessoaDAO.mostraPessoa());
                    break;
                // 6 - Volta para o menu principal
                case 6:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
                    break;
            }
        }
    }

    private void executaOpcaoMenuPresente() {

        int pegaOpcao = 0;

        while (pegaOpcao != 3) {

            pegaOpcao = gui.menuPresente();
            switch (pegaOpcao) {
                //Mostra a lista de presentes 
                case 1:
                    gui.exibiPresente(presenteDAO.mostraListaPresentes());
                    break;
                //Reserva comprador de cada presente 
                case 2:
                    boolean confirmacao = false;

                    Pessoa pessoa = new Pessoa();
                    pessoa = pessoaDAO.pegaPessoa(gui.recebeNomePessoa());

                    if (pessoa != null) {
                        long id = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo referente ao presente que deseja comprar"));

                        if (presenteDAO.validaIdPresente(id)) {
                            confirmacao = presenteDAO.reservaCompradorPresentes(pessoa, id);
                        } else {
                            gui.exibirMensagemIdPresenteDigitadoIncorreto();
                        }
                    }
                    if (confirmacao) {
                        gui.exibirMensagemReservaPresenteFeitaComSucesso();
                    }
                    break;
                //Retorna para o menu principal
                case 3:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    //Main
    public static void main(String[] args) {
        new GestaoCasamento();
    }
}
