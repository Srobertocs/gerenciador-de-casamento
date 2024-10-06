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
    UsuarioDAO usuarioDAO = new UsuarioDAO(pessoaDAO);

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
                // 1 - Acessa o Menu da lista de presentes
                case 1:
                    executaOpcaoMenuPresente();
                    break;
                // 2 - Acessa o Menu de pessoas
                case 2:
                    executaOpcaoMenuPessoa();
                    break;
                // 3 - Acessa o Menu de usuarios
                case 3:
                    executaOpcaoMenuUsuario();
                    break;
                case 6:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
                    break;
            }
        }
    }

    private void executaOpcaoMenuUsuario() {
        int pegaOpcao = 0;

        while (pegaOpcao != 6) {
            pegaOpcao = gui.menuUsuario();

            switch (pegaOpcao) {
                // 1 - Adicionar usuário
                case 1:
                    boolean confirmacao;

                    Usuario usuario = gui.criaUsuario();
                    Pessoa pessoa = pessoaDAO.pegaPessoa(usuario.getLogin());

                    confirmacao = usuarioDAO.adicionaUsuario(usuario, pessoa);

                    if (confirmacao) {
                        gui.exibirMensagemUsuarioAdicionado();
                    } else {
                        gui.exibirMensagemUsuarioNaoAdicionado();
                    }
                    break;
                // 2 - Consultar usuario 
                case 2:
                    Usuario usuarioConsulta;

                    usuarioConsulta = usuarioDAO.consultaUsuario(gui.recebeNomeUsuario());

                    if (usuarioConsulta != null) {
                        gui.exibirUsuarios(usuarioConsulta.toString());
                    } else {
                        GUI.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;

                case 3:
                    boolean excluirUsuario;

                    excluirUsuario = usuarioDAO.excluirUsuario(usuarioDAO.consultaUsuario(gui.recebeNomeUsuario()));

                    if (excluirUsuario) {
                        gui.exibirMensagemUsuarioExcluido();
                    } else {
                        GUI.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // Altera a senha do usuario
                case 4:
                    boolean alteraSenha;

                    alteraSenha = usuarioDAO.alteraSenhaUsuario(usuarioDAO.consultaUsuario(gui.recebeNomePessoa()), gui.recebeNovaSenhaUsuario());

                    if (alteraSenha) {
                        gui.exibirMensagemUsuarioAlterado();
                    } else {
                        GUI.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // 5 - Mostra Usuarios cadastrados
                case 5:

                    if (usuarioDAO.mostraUsuario() == null) {
                        GUI.exibirMensagemUsuarioNaoEncontrado();
                    } else {
                        gui.exibirUsuarios(usuarioDAO.mostraUsuario());
                    }
                    break;
                // 6 - Voltar 
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
                    Pessoa pessoaConsulta;

                    pessoaConsulta = pessoaDAO.consultaPessoa(gui.recebeNomePessoa());

                    if (pessoaConsulta != null) {
                        gui.exibirPessoas(pessoaConsulta.toString());
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

                    alteraNome = pessoaDAO.alteraPessoa(gui.recebeNomePessoa(), gui.recebeNovoNomePessoa());

                    if (alteraNome) {
                        gui.exibirMensagemPessoaAlterada();
                    } else {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 5 - Mostrar pessoas  
                case 5:
                    if (pessoaDAO.mostraPessoa() == null) {
                        GUI.exibirMensagemPessoaNaoEncontrada();
                    } else {
                        gui.exibirPessoas(pessoaDAO.mostraPessoa());
                    }
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
                    gui.exibirPresentes(presenteDAO.mostraListaPresentes());
                    break;
                //Reserva comprador de cada presente 
                case 2:
                    boolean confirmacao = false;

                    Pessoa pessoa = new Pessoa();
                    pessoa = pessoaDAO.pegaPessoa(gui.recebeNomePessoa());

                    if (pessoa != null) {
                        long id = Integer.parseInt(gui.recebeIdPresente());

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
