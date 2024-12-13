/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import DAO.ConviteFamiliarDAO;
import DAO.ConviteIndividualDAO;
import DAO.FornecedorDAO;
import DAO.UsuarioDAO;
import DAO.PresenteDAO;
import DAO.PessoaDAO;
import DAO.MuralRecadosDAO;
import DAO.PagamentoDAO;
import view.GUI;
import beans.Usuario;
import beans.Pessoa;
import beans.Presente;
import beans.Recados;
import beans.Pagamento;
import beans.Fornecedor;
import javax.swing.JOptionPane;
import util.Datas;
import java.sql.Connection;
import connection.ConnectionFactory;
import pdf.GeraPdf;

/**
 *
 * @author SOUSA
 */
public class GestaoCasamento {

    //Inicialização dos OBJETOS e DAOS
    Usuario usuarioLogado = null;
    Usuario noivo = null;
    Usuario noiva = null;

    PessoaDAO pessoaDAO = new PessoaDAO();
    FornecedorDAO fornecedorDAO = new FornecedorDAO();
    PresenteDAO presenteDAO = new PresenteDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    MuralRecadosDAO muralRecadosDAO = new MuralRecadosDAO();
    PagamentoDAO pagamentoDAO = new PagamentoDAO();
    ConviteFamiliarDAO conviteFamiliarDAO = new ConviteFamiliarDAO();
    ConviteIndividualDAO conviteIndividualDAO = new ConviteIndividualDAO();
    
    GeraPdf gerarPdf = new GeraPdf();
    GUI gui = new GUI();

    public GestaoCasamento() {
        //Criação dos OBJETOS                                           
        noivo = usuarioDAO.buscaNoivos("Noivo");
        noiva = usuarioDAO.buscaNoivos("Noiva");

        //Chamada de método
        executaOpcaoMenuPrincipalNaoLogado();
    }

    //Métodos de execução das opcões dos menus 
    private void executaOpcaoMenuPrincipalNaoLogado() {
        int pegaOpcao = 0;

        while (pegaOpcao != 3) {
            pegaOpcao = gui.menuPrincipalNaoLogado(noivo.getLogin(), noiva.getLogin());

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

                    String loginUsuario = gui.recebeNomeUsuario();

                    confirmacao = usuarioDAO.confereUsuario(loginUsuario, gui.recebeSenhaUsuario());

                    if (confirmacao) {
                        usuarioLogado = usuarioDAO.buscaUsuarioLogin(loginUsuario, pessoaDAO); // Pega o usuario logado
                        
                        if(usuarioLogado.getTipo().equals("Convidado")){
                            executaOpcaoMenuPrincipalLogadoNormal();
                        }else{
                            executaOpcaoMenuPrincipalLogadoADM();
                        }
                        pegaOpcao = 3;

                    } else {

                        gui.exibirMensagemErroLogin();
                    }
                    break;
                //Cadastro de usuario
                case 2:

                    Usuario usuario = gui.criaUsuario();
                    Pessoa pessoa = pessoaDAO.buscaPessoaNome(usuario.getLogin());

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

    private void executaOpcaoMenuPrincipalLogadoADM() {
        int pegaOpcao = 0;

        while (pegaOpcao != 9) {
            pegaOpcao = gui.menuPrincipalLogado(noivo.getLogin(), noiva.getLogin(), usuarioLogado.getLogin());

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
                // 5 - Acessar o Menu de fornecedores 
                case 5:
                    executaOpcaoMenuFornecedor();
                    break;
                // 6 - Acessa o Menu de pagamentos
                case 6:
                    executaOpcaoMenuPagamento();
                    break;
                // 7 - Acessar Menu de Convite
                case 7:
                    executaOpcaoMenuConvite();
                    break;
                // 8 - Sair
                case 8:
                    executaOpcaoMenuRelatorios();
                    break;
                // 9 - Sair
                case 9:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
                    break;
            }
        }
    }
    
     private void executaOpcaoMenuPrincipalLogadoNormal() {
        int pegaOpcao = 0;

        while (pegaOpcao != 4) {
            pegaOpcao = gui.menuPrincipalLogadoNormal(noivo.getLogin(), noiva.getLogin(), usuarioLogado.getLogin());

            switch (pegaOpcao) {
                // 1 - Acessa o Menu da lista de presentes
                case 1:
                    executaOpcaoMenuPresente();
                    break;
                // 2 - Acessa o Menu de Mural de Recados
                case 2:
                      executaOpcaoMenuMuralRecados();
                    break;
                // 3 - Acessa o Menu de Convite
                case 3:
                    executaOpcaoMenuConvite();
                    break;
                    
                case 4:
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

                    confirmacao = usuarioDAO.adicionaUsuario(usuario, pessoaDAO.buscaPessoaNome(usuario.getLogin()));

                    if (confirmacao) {
                        gui.exibirMensagemUsuarioAdicionado();
                    } else {
                        gui.exibirMensagemUsuarioNaoAdicionado();
                    }
                    break;
                // 2 - Consultar usuario 
                case 2:
                    String textoUsuarioConsulta;

                    textoUsuarioConsulta = usuarioDAO.consultaUsuario(gui.recebeNomeUsuario(), pessoaDAO);

                    if (textoUsuarioConsulta != null) {
                        gui.exibirUsuarios(textoUsuarioConsulta);
                    } else {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                //Excluir Usuario
                case 3:
                    boolean excluirUsuario;

                    excluirUsuario = usuarioDAO.excluirUsuario(gui.recebeNomeUsuario());

                    if (excluirUsuario) {
                        gui.exibirMensagemUsuarioExcluido();
                    } else {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // Altera a senha do usuario
                case 4:
                    boolean alteraSenha;

                    alteraSenha = usuarioDAO.alteraSenhaUsuario(gui.recebeNomeUsuario(), gui.recebeNovaSenhaUsuario());

                    if (alteraSenha) {
                        gui.exibirMensagemUsuarioAlterado();
                    } else {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    }
                    break;
                // 5 - Mostra Usuarios cadastrados
                case 5:

                    if (usuarioDAO.mostraUsuario(pessoaDAO) == null) {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    } else {
                        gui.exibirUsuarios(usuarioDAO.mostraUsuario(pessoaDAO));
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

                    boolean confirmacao = pessoaDAO.adicionaPessoa(gui.criaPessoa());

                    if (confirmacao == true) {
                        gui.exibirMensagemPessoaAdicionada();
                    } else {
                        gui.exibirMensagemPessoaNaoAdicionada();
                    }
                    break;
                //2 - Consulta Pessoa  
                case 2:
                    String textoPessoaConsulta;

                    textoPessoaConsulta = pessoaDAO.consultaPessoa(gui.recebeNomePessoa());

                    if (textoPessoaConsulta != null) {
                        gui.exibirPessoas(textoPessoaConsulta);
                    } else {
                        gui.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 3 - Exclui cadastro
                case 3:
                    boolean pessoaExcluida;

                    pessoaExcluida = pessoaDAO.excluiPessoa(gui.recebeNomePessoa());

                    if (pessoaExcluida) {
                        gui.exibirMensagemPessoaExcluida();
                    } else {
                        gui.exibirMensagemPessoaNaoEncontrada();
                    }
                    break;
                // 4 - Altera o nome da pessoa 
                case 4:
                    boolean alteraNome;
                    
                    String nomeAtual = gui.recebeNomePessoa();
                    String novoNome = gui.recebeNovoNomePessoa();

                    alteraNome = pessoaDAO.alteraPessoa(nomeAtual, novoNome);

                    if (alteraNome) {
                        usuarioDAO.alteraNomeUsuario(nomeAtual, novoNome);
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

        while (pegaOpcao != 4) {

            pegaOpcao = gui.menuPresente();
            switch (pegaOpcao) {
                //Mostra a lista de presentes 
                case 1:
                    if (presenteDAO.mostraListaPresente(pessoaDAO) == null) {
                        gui.exibirMensagemListaPresenteVazia();
                    } else {
                        gui.exibirPresentes(presenteDAO.mostraListaPresente(pessoaDAO));
                    }
                    break;
                //Reserva comprador de cada presente 
                case 2:
                    boolean confirmacao = false;

                    Pessoa pessoa = new Pessoa();
                    pessoa = pessoaDAO.buscaPessoaNome(gui.recebeNomePessoa());

                    if (pessoa != null) {
                        long id = gui.recebeIdPresente();

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
                //Adiciona presente nas listas
                case 3:
                    boolean usuarioAdicionado;

                    if (usuarioLogado == null) {
                        gui.exibirMensagemUsuarioNaoEncontrado();
                    } else {
                        usuarioAdicionado = presenteDAO.adicionaPresente(gui.criaPresente(), usuarioLogado.getTipo(), usuarioDAO);

                        if (usuarioAdicionado) {
                            gui.exibirMensagemPresenteAdicionado();
                        } else {
                            gui.exibirMensagemPresenteNaoAdicionado();
                        }
                    }
                    break;
                case 4:
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

                    postado = muralRecadosDAO.postarMuralRecados(usuarioLogado, gui.criaRecado());

                    if (postado) {
                        gui.exibirMensagemRecadoPostado();
                    } else {
                        gui.exibirMensagemRecadoNaoPostado();
                    }
                    break;
                // 2 - Mostrar mural de recados
                case 2:
                    if (muralRecadosDAO.mostrarMuralRecados(usuarioDAO) == null) {
                        gui.exibirMensagemNaoExisteMuralRecados();

                    } else {
                        gui.exibirMuralRecados(muralRecadosDAO.mostrarMuralRecados(usuarioDAO));
                    }
                    break;
                // 3 - Consultar recados 
                case 3:

                    String textoConsulta;

                    textoConsulta = muralRecadosDAO.consultarMuralRecados(usuarioDAO.buscaUsuarioLogin(gui.recebeNomeUsuario(), pessoaDAO), usuarioDAO);

                    if (textoConsulta == null) {
                        gui.exibirMensagemRecadoConsultaInvalida();
                    } else {
                        gui.exibirMuralRecados(textoConsulta);
                    }
                    break;
                // 4 - Editar comentario
                case 4:

                    if (muralRecadosDAO.editarComentarioMuralRecados(gui.recebeCodigoRecado(), usuarioLogado, gui.recebeNovoRecado())) {
                        gui.exibirMensagemRecadoEditado();
                    } else {
                        gui.exibirMensagemRecadoNaoEditado();
                    }
                    break;
                // 5 - Excluir comentário
                case 5:

                    if (muralRecadosDAO.excluirRecado(gui.recebeCodigoRecado(), usuarioLogado)) {
                        gui.exibirMensagemRecadoExcluido();
                    } else {
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

    private void executaOpcaoMenuFornecedor() {
        int pegaopcao = 0;

        while (pegaopcao != 6) {
            pegaopcao = gui.menuFornecedor();

            switch (pegaopcao) {
                // 1 - Adicionar fornecedor
                case 1:
                    boolean fornecedorAdicionado;

                    fornecedorAdicionado = fornecedorDAO.adicionaFornecedor(gui.criaFornecedor());

                    if (fornecedorAdicionado) {
                        gui.exibirMensagemFornecedorAdicionado();
                    } else {
                        gui.exibirMensagemFornecedorNaoAdicionado();
                    }
                    break;
                // 2 - Mostra a lista de fornecedores
                case 2:
                    if (fornecedorDAO.mostraFornecedor() == null) {
                        gui.exibirMensagemFornecedorNaoEncontrado();
                    } else {
                        gui.exibirFornecedor(fornecedorDAO.mostraFornecedor());
                    }
                    break;
                // 3 - Consulta fornecedor
                case 3:
                    Fornecedor fornecedorConsulta;

                    fornecedorConsulta = fornecedorDAO.consultaFornecedor(gui.recebeCnpjFornecedor());

                    if (fornecedorConsulta != null) {
                        gui.exibirFornecedor(fornecedorConsulta.toString());
                    } else {
                        gui.exibirMensagemFornecedorNaoEncontrado();
                    }
                    break;
                // 4 - Excluir fornecedor 
                case 4:
                    boolean fornecedorExcluido;

                    fornecedorExcluido = fornecedorDAO.excluiFornecedor(gui.recebeCnpjFornecedor());

                    if (fornecedorExcluido) {
                        gui.exibirMensagemFornecedorExcluido();
                    } else {
                        gui.exibirMensagemFornecedorNaoEncontrado();
                    }
                    break;
                // 5 - Alterar forma de pagamento
                case 5:
                    boolean statusAlterado;

                    statusAlterado = fornecedorDAO.alteraStatusPagamento(gui.recebeCnpjFornecedor(), gui.recebeNovoStatusFornecedor());

                    if (statusAlterado) {
                        gui.exibirMensagemFornecedorAlterado();
                    } else {
                        gui.exibirMensagemFornecedorNaoEncontrado();
                    }
                    break;
                // 6 - voltar 
                case 6:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuPagamento() {
        int pegaopcao = 0;

        while (pegaopcao != 7) {
            pegaopcao = gui.menuPagamento();

            switch (pegaopcao) {
                // 1 - Lançamento dos pagamentos
                case 1:
                    boolean pagamentosLancados;
                    String textoFornecedor;

                    textoFornecedor = fornecedorDAO.mostraFornecedor();

                    pagamentosLancados = pagamentoDAO.lancamentoPagamentos(fornecedorDAO.buscaFornecedorId(gui.recebeIdFornecedor(textoFornecedor)), Datas.convercaoData(gui.recebeDataPrimeiroPagamento()));

                    if (pagamentosLancados == true) {
                        gui.exibirMensagemPagamentoLancado();
                    } else {
                        gui.exibirMensagemPagamentoNaoLancado();
                    }
                    break;
                // 2 - Visualizar os pagamentos   
                case 2:
                    if (pagamentoDAO.mostraPagamentosLancados(fornecedorDAO) == null) {
                        gui.exibirMensagemPagamentoNaoEncontrado();
                    } else {
                        gui.exibirPagamento(pagamentoDAO.mostraPagamentosLancados(fornecedorDAO));
                    }
                    break;

                // 3 - Visualizar pagamentos do dia
                case 3:
                    if (pagamentoDAO.mostraPagamentosDoDia(fornecedorDAO) == null) {
                        gui.exibirMensagemPagamentoNaoEncontrado();
                    } else {
                        gui.exibirPagamento(pagamentoDAO.mostraPagamentosDoDia(fornecedorDAO));
                    }
                    break;

                // 4 - Consultar pagamentos
                case 4:
                    String pagamentoConsultado = pagamentoDAO.consultaVencimentoDosDias(Datas.convercaoData(gui.recebeDataPesquisa()), fornecedorDAO);
                    if (pagamentoConsultado == null) {
                        gui.exibirMensagemPagamentoNaoEncontrado();
                    } else {
                        gui.exibirPagamentoConsultados(pagamentoConsultado);
                    }
                    break;

                // 5 - Pagamento das contas do dia  
                case 5:
                    boolean pagamentosPagos;

                    String textoPagamento = pagamentoDAO.mostraPagamentosDoDia(fornecedorDAO);

                    if (textoPagamento == null) {
                        gui.exibirMensagemPagamentoNaoEncontrado();
                    } else {
                        pagamentosPagos = pagamentoDAO.pagamentosPagos(gui.recebeIdPagamento(textoPagamento));

                        if (pagamentosPagos == true) {
                            gui.exibirMensagemPagamentoPago();
                            fornecedorDAO.consultaPagamentoFornecedor(pagamentoDAO.listaPagamento(fornecedorDAO));
                        } else {
                            gui.exibirMensagemPagamentoNaoPago();
                        }
                    }
                    break;

                // 6 - Pagamento Automatico
                case 6:
                    boolean pagamentosAutPagos;

                    pagamentosAutPagos = pagamentoDAO.pagamentoPagosAuto(gui.recebeDataPagamentoAuto());

                    if (pagamentosAutPagos == true) {
                        gui.exibirMensagemPagamentoPago();
                        fornecedorDAO.consultaPagamentoFornecedor(pagamentoDAO.listaPagamento(fornecedorDAO));
                    } else {
                        gui.exibirMensagemPagamentoNaoPago();
                    }
                    break;
                //6 - Voltar 
                case 7:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuConvite() {

        int pegaOpcao = 0;

        while (pegaOpcao != 3) {

            pegaOpcao = gui.menuConvite();
            switch (pegaOpcao) {
                //Mostra a lista de presentes 
                case 1:
                    executaOpcaoMenuConviteFamilia();
                    break;
                //Reserva comprador de cada presente 
                case 2:
                    executaOpcaoMenuConviteIndividual();
                    break;
                //Volta
                case 3:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuConviteFamilia() {

        int pegaOpcao = 0;

        while (pegaOpcao != 4) {

            pegaOpcao = gui.menuConviteFamilia();
            switch (pegaOpcao) {
                //Adiciona convite familiar 
                case 1:
                    boolean conviteFamiliaAdicionado = false;

                    conviteFamiliaAdicionado = conviteFamiliarDAO.adicionaConviteFamilia(gui.criaConviteFamilia(noivo, noiva));
                    if (conviteFamiliaAdicionado == true) {
                        gui.exibirMensagemFamiliaCadastrada();
                    } else {
                        gui.exibirMensagemFamiliaNaoCadastrada();
                    }
                    break;
                //Mostra convite familiar
                case 2:
                    if (conviteFamiliarDAO.mostraConviteFamiliar() == null) {
                        gui.exibirMensagemFamiliaNaoEncontrada();
                    } else {
                        gui.exibirFamilia(conviteFamiliarDAO.mostraConviteFamiliar());
                    }
                    break;
                //Confirmar presença
                case 3:
                    boolean alterado = false;
                    
                    alterado = conviteIndividualDAO.alteraConfirmacao(conviteFamiliarDAO.confirmaConviteFamiliar(usuarioLogado, conviteIndividualDAO.listaConviteIdividual(pessoaDAO, conviteFamiliarDAO)));
                    
                    if(alterado == true){
                        gui.exibirMensagemFamiliaConfirmada();
                    }else{
                        gui.exibirMensagemFamiliaNaoConfirmada();
                    }
                    break;
                //Voltar 
                case 4: 
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }

    private void executaOpcaoMenuConviteIndividual() {

        int pegaOpcao = 0;

        while (pegaOpcao != 3) {

            pegaOpcao = gui.menuConviteIndividual();
            switch (pegaOpcao) {
                //Adiciona convite Individual
                case 1:

                    boolean conviteIndividualAdicionado = false;

                    conviteIndividualAdicionado = conviteIndividualDAO.adicionaConviteIndividual(gui.criaConviteIndividual(pessoaDAO, conviteFamiliarDAO));
                    if (conviteIndividualAdicionado == true) {
                        gui.exibirMensagemConviteIndividualCadastrado();
                    } else {
                        gui.exibirMensagemConviteIndividualNaoCadastrado();
                    }
                    break;
                //Mostra convite Individual
                case 2:
                    if (conviteIndividualDAO.mostraConviteIndividual(pessoaDAO, conviteFamiliarDAO) == null) {
                        gui.exibirMensagemConviteIndividualNaoEncontrado();
                    } else {
                        gui.exibirConviteIndividual(conviteIndividualDAO.mostraConviteIndividual(pessoaDAO, conviteFamiliarDAO));
                    }
                    break;
                case 3:
                    break;
                default:
                    gui.exibirMensagemOpcaoInexistente();
            }
        }
    }
    
    private void executaOpcaoMenuRelatorios() {

        int pegaOpcao = 0;

        while (pegaOpcao != 5) {

            pegaOpcao = gui.menuRelatorio();
            switch (pegaOpcao) {
                //Relatorio de recados 
                case 1:
                    gerarPdf.gerarPdf("Recados", muralRecadosDAO.RelatorioMuralRecados(usuarioDAO));
                    break;
                //Relatorio Lista de Convidados 
                case 2:
                    gerarPdf.gerarPdf("Convidados", usuarioDAO.RelatorioUsuario(pessoaDAO));
                    break;
                //Relatorio Pagamentos efetuados
                case 3:
                    gerarPdf.gerarPdf("Pagamento", pagamentoDAO.RelatorioPagamentosPagos(fornecedorDAO));
                    break;
                //Relatorio Convidados confirmados
                case 4: 
                    gerarPdf.gerarPdf("Convidados_confirmados", conviteIndividualDAO.RelatorioConviteIndividual(pessoaDAO, conviteFamiliarDAO));
                    break;
                //Voltar
                case 5:
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
