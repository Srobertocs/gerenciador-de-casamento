/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import DAO.UsuarioDAO;
import DAO.PresenteDAO;
import DAO.PessoaDAO;
import DAO.MuralRecadosDAO;
import view.GUI;
import beans.Usuario;
import beans.Pessoa;
import beans.Presente;
import beans.Recados;
import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */
public class GestaoCasamento {

    //Inicialização dos OBJETOS e DAOS
    Usuario usuarioLogado = null;

    PessoaDAO pessoaDAO = new PessoaDAO();
    PresenteDAO presenteDAO = new PresenteDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO(pessoaDAO);
    MuralRecadosDAO muralRecadosDAO = new MuralRecadosDAO(usuarioDAO);

    GUI gui = new GUI();

    public GestaoCasamento() {
        executaOpcaoMenuPrincipalNaoLogado();
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
                //Login de Usuário
                case 1:
                    boolean confirmacao;

                    String nomeUsuario = gui.recebeNomeUsuario();

                    confirmacao = usuarioDAO.buscaUsuario(nomeUsuario, gui.recebeSenhaUsuario());

                    if (confirmacao) {
                        usuarioLogado = usuarioDAO.consultaUsuario(nomeUsuario);
                        executaOpcaoMenuPrincipalLogado();
                        pegaOpcao = 3;

                    } else {

                        gui.exibirMensagemErroLogin();
                    }
                    break;
                //Cadastro de usuario
                case 2:

                    Usuario usuario = gui.criaUsuario();
                    Pessoa pessoa = pessoaDAO.pegaPessoa(usuario.getLogin());

                    if (usuarioDAO.adicionaUsuario(usuario, pessoa)) {
                        gui.exibirMensagemUsuarioAdicionado();
                    } else {
                        gui.exibirMensagemUsuarioNaoAdicionado();
                    }
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
                // 4 - Acessar o Menu de mural de recados
                case 4:
                    executaOpcaoMenuMuralRecados();
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
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                //Excluir Usuario
                case 3:
                    boolean excluirUsuario;

                    excluirUsuario = usuarioDAO.excluirUsuario(usuarioDAO.consultaUsuario(gui.recebeNomeUsuario()));

                    if (excluirUsuario) {
                        gui.exibirMensagemUsuarioExcluido();
                    } else {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // Altera a senha do usuario
                case 4:
                    boolean alteraSenha;

                    alteraSenha = usuarioDAO.alteraSenhaUsuario(usuarioDAO.consultaUsuario(gui.recebeNomeUsuario()), gui.recebeNovaSenhaUsuario());

                    if (alteraSenha) {
                        gui.exibirMensagemUsuarioAlterado();
                    } else {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // 5 - Mostra Usuarios cadastrados
                case 5:

                    if (usuarioDAO.mostraUsuario() == null) {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    } else {
                        gui.exibirUsuarios(usuarioDAO.mostraUsuario());
                    }
                    break;
                // 6 - Deslogar 
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
                        gui.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 3 - Exclui cadastro
                case 3:
                    boolean cadastroExcluido;

                    cadastroExcluido = pessoaDAO.excluiPessoa(gui.recebeNomePessoa());

                    if (cadastroExcluido) {
                        gui.exibirMensagemPessoaExcluida();
                    } else {
                        gui.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 4 - Altera o nome da pessoa 
                case 4:
                    boolean alteraNome;

                    alteraNome = pessoaDAO.alteraPessoa(gui.recebeNomePessoa(), gui.recebeNovoNomePessoa());

                    if (alteraNome) {
                        gui.exibirMensagemPessoaAlterada();
                    } else {
                        gui.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 5 - Mostrar pessoas  
                case 5:
                    if (pessoaDAO.mostraPessoa() == null) {
                        gui.exibirMensagemPessoaNaoEncontrada();
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

    private void executaOpcaoMenuMuralRecados() {

        int pegaopcao = 0;

        while (pegaopcao != 6) {
            pegaopcao = gui.menuMuralRecados();

            switch (pegaopcao) {
                // 1 - Postar recado
                case 1:
                    boolean postado;

                    postado = muralRecadosDAO.postarMuralRecados(gui.criaRecado(), usuarioLogado);

                    if (postado) {
                        gui.exibirMensagemRecadoPostado();
                    } else {
                        gui.exibirMensagemRecadoNaoPostado();
                    }
                    break;
                // 2 - Mostrar mural de recados
                case 2:
                    if (muralRecadosDAO.mostrarMuralRecados() == null) {
                        gui.exibirMensagemNaoExisteMuralRecados();

                    } else {
                        gui.exibirMuralRecados(muralRecadosDAO.mostrarMuralRecados());
                    }
                    break;
                // 3 - Consultar recados 
                case 3:

                    String textoConsulta;

                    textoConsulta = muralRecadosDAO.consultarMuralRecados(usuarioDAO.consultaUsuario(gui.recebeNomeUsuario()));

                    if (textoConsulta == null) {
                        gui.exibirMensagemRecadoConsultaInvalida();
                    } else {
                        gui.exibirMuralRecados(textoConsulta);
                    }
                    break;
                // 4 - Editar comentario
                case 4:
                    long codigoRecadoEdicao;
                    boolean recadoEditado = false;

                    codigoRecadoEdicao = muralRecadosDAO.validaCodigoRecado(gui.recebeCodigoRecado());

                    if (codigoRecadoEdicao == 0) {
                        gui.exibirMensagemCodigoNaoEncontrado();
                    } else {
                        if (muralRecadosDAO.validaUsuarioRecado(usuarioLogado, codigoRecadoEdicao)) {
                            recadoEditado = muralRecadosDAO.editarComentarioMuralRecados(codigoRecadoEdicao, gui.recebeNovoRecado());
                        } else {
                            gui.exibirMensagemUsuarioRecadoInvalido();
                        }
                    }
                    if (recadoEditado == true) {
                        gui.exibirMensagemRecadoEditado();
                    } else {
                        gui.exibirMensagemRecadoNaoEditado();
                    }
                    break;
                // 5 - Excluir comentário
                case 5:
                    long codigoRecadoExclusao;
                    boolean recadoExcluido = false;

                    codigoRecadoExclusao = muralRecadosDAO.validaCodigoRecado(gui.recebeCodigoRecado());

                    if (codigoRecadoExclusao == 0) {
                        gui.exibirMensagemCodigoNaoEncontrado();
                    }else{
                        if(muralRecadosDAO.validaUsuarioRecado(usuarioLogado, codigoRecadoExclusao)){
                            recadoExcluido = muralRecadosDAO.excluirRecado(codigoRecadoExclusao, usuarioLogado);
                        }else{
                            gui.exibirMensagemUsuarioRecadoInvalido();
                        }
                    }
                    
                    if(recadoExcluido == true){
                        gui.exibirMensagemRecadoExcluido();
                    }else{
                        gui.exibirMensagemRecadoNaoExcluido();
                    }
                    break;

                case 6:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }

        }
    }
    
    private void executaOpcaoMenuFornecedor(){
        
    }

    //Main
    public static void main(String[] args) {
        new GestaoCasamento();
    }
}
