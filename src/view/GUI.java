/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import DAO.ConviteFamiliarDAO;
import DAO.PessoaDAO;
import beans.ConviteFamiliar;
import beans.ConviteIndividual;
import beans.Usuario;
import beans.Recados;
import beans.Pessoa;
import beans.Pagamento;
import beans.Fornecedor;
import beans.Presente;
import util.Datas;
import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */
public class GUI {

    //Métodos para popular objetos
    //Objeto Pessoa
    public Pessoa criaPessoa() {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(JOptionPane.showInputDialog("Digite o nome: "));
        pessoa.setTelefone(JOptionPane.showInputDialog("Digite o numero de contato: "));
        pessoa.setNascimento(JOptionPane.showInputDialog("Digite a data de nascimento: "));
        pessoa.setDataCriacao(Datas.pegaDataAgora());
        pessoa.setDataModificacao(Datas.pegaDataAgora());

        return pessoa;
    }

    public String recebeNomePessoa() {
        return JOptionPane.showInputDialog("Digite o nome da pessoa:");
    }

    public String recebeNovoNomePessoa() {
        return JOptionPane.showInputDialog("Digite o novo nome:");
    }

    //Objeto Presente
    public int recebeIdPresente() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo referente ao presente que deseja comprar"));
    }

    public Presente criaPresente() {
        Presente presente = new Presente();

        presente.setNome(JOptionPane.showInputDialog("Digite o nome do presente: "));
        presente.setTipo(JOptionPane.showInputDialog("Digite o tipo do presente: "));
        presente.setDataCriacao(Datas.pegaDataAgora());
        presente.setDataModificao(Datas.pegaDataAgora());

        return presente;
    }

    //Objeto usuário
    public Usuario criaUsuario() {
        Usuario usuario = new Usuario();

        usuario.setLogin(JOptionPane.showInputDialog("Digite o nome da pessoa para ser o login:"));
        usuario.setSenha(JOptionPane.showInputDialog("Digite a sequencia que sera a sua senha:"));
        usuario.setTipo(JOptionPane.showInputDialog("Digite a categoria da pessoa conforme mostrado abaixo: "
                + "\n| Noivo"
                + " | Noiva"
                + " | Convidado |"));
        usuario.setPessoa(null);
        usuario.setDataCriacao(Datas.pegaDataAgora());
        usuario.setDataModificacao(Datas.pegaDataAgora());

        return usuario;
    }

    public String recebeNomeUsuario() {
        return JOptionPane.showInputDialog("Digite o login do usuario:");
    }

    public String recebeSenhaUsuario() {
        return JOptionPane.showInputDialog("Digite a senha do usuario: ");
    }

    public String recebeNovaSenhaUsuario() {
        return JOptionPane.showInputDialog("Digite a nova senha do usuario:");
    }

    //Objeto Mural de Recados
    public Recados criaRecado() {
        Recados recado = new Recados();

        recado.setRecado(JOptionPane.showInputDialog("Digite o recado: "));
        recado.setUsuario(null);
        recado.setDataCriacao(Datas.pegaDataAgora());
        recado.setDataModificacao(Datas.pegaDataAgora());

        return recado;
    }

    public long recebeCodigoRecado() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do recado: "));
    }

    public String recebeNovoRecado() {
        return JOptionPane.showInputDialog("Digite a alteracao do recado: ");
    }

    //Objeto Fornecedor
    public Fornecedor criaFornecedor() {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome(JOptionPane.showInputDialog("Digite o nome do fornecedor: "));
        fornecedor.setCnpj(JOptionPane.showInputDialog("Digite o cnpj: "));
        fornecedor.setTelefone(JOptionPane.showInputDialog("Digite telefone de contato: "));
        fornecedor.setValorAPagar(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor: ")));
        fornecedor.setParcelas(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de parcelas: ")));
        fornecedor.setStatus(JOptionPane.showInputDialog("Digite em que status se encontra o fornecedor :"
                + "\n** pago **"
                + "\n** em pagamento **"));
        fornecedor.setDataCriacao(Datas.pegaDataAgora());
        fornecedor.setDataModificacao(Datas.pegaDataAgora());

        return fornecedor;
    }

    public String recebeCnpjFornecedor() {
        return JOptionPane.showInputDialog("Digite o cnpj do fornecedor");
    }

    public String recebeNovoStatusFornecedor() {
        return JOptionPane.showInputDialog("Digite o novo status atual do fornecedor:"
                + "\n** pago **"
                + "\n** em pagamento **");
    }

    //Objeto Pagamento
    public int recebeIdFornecedor(String texto) {
        return Integer.parseInt(JOptionPane.showInputDialog(texto
                + "\n\nDigite o codigo do fornecedor para fazer o lancamento dos pagamentos"));
    }

    public int recebeIdPagamento(String texto) {
        return Integer.parseInt(JOptionPane.showInputDialog(texto
                + "\n\nDigite o codigo do pagamento que deseja pagar"));
    }

    public String recebeDataPrimeiroPagamento() {
        return JOptionPane.showInputDialog("Digite a data do primeiro vencimento: ");
    }

    public int recebeDataPagamentoAuto() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite o intervalo de dias que deseja efetuar o pagamento automatico: "));
    }

    public String recebeDataPesquisa() {
        return JOptionPane.showInputDialog("Digite a data que deseja consultar se existe pagamentos: ");
    }

    //Objeto Convite Familiar
    public ConviteFamiliar criaConviteFamilia(Usuario noivo, Usuario noiva) {
        ConviteFamiliar convite = new ConviteFamiliar();

        convite.setNomeFamilia(JOptionPane.showInputDialog("Digite o nome da familia: "));
        convite.setAcesso(JOptionPane.showInputDialog(" O Acesso deverá ter o nome do noivo, da noiva, o dia, o mes, o ano e quatro letras"
                + "\n Por exemplo: " + noivo.getLogin() + noiva.getLogin() + "11122024abcd"
                + "\n Crie seu acesso: "));
        convite.setDataCriacao(Datas.pegaDataAgora());
        convite.setDataModificacao(Datas.pegaDataAgora());

        return convite;
    }

    public ConviteIndividual criaConviteIndividual(PessoaDAO pessoaDAO, ConviteFamiliarDAO conviteFamiliarDAO) {
        ConviteIndividual convite = new ConviteIndividual();

        convite.setFamilia(conviteFamiliarDAO.buscaConviteFamiliarId(Integer.parseInt(JOptionPane.showInputDialog(conviteFamiliarDAO.mostraConviteFamiliar() + "\n\nDigite o id da Familia"))));
        while(convite.getPessoa() == null){
            convite.setPessoa(pessoaDAO.buscaPessoaNome(JOptionPane.showInputDialog(pessoaDAO.mostraPessoa() + "\n\nDigite o nome da pessoa")));
            if(convite.getPessoa() == null){
                JOptionPane.showMessageDialog(null, "OHH MANEZAO. Voce precisa digitar o nome da pessoa!");
            }
        }
        convite.setParentesco(JOptionPane.showInputDialog("Digite o parentesco da pessoa"));
        convite.setConfirmacao("Nao confirmado");
        convite.setDataCriacao(Datas.pegaDataAgora());
        convite.setDataModificacao(Datas.pegaDataAgora());

        return convite;
    }

    //Métodos dos menus de opção
    public int menuPrincipalNaoLogado(String noivo, String noiva) {
        int opcao;

        String menu = "HOME"
                + "\nCerimonial de " + noivo + " & " + noiva
                + "\nSeja Bem Vindo"
                + "\n\nStatus: Deslogado"
                + "\n\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Entrar no programa"
                + "\n3- Encerrar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPrincipalLogado(String noivo, String noiva, String usuarioLogado) {
        int opcao;

        String menu = "HOME"
                + "\nCerimonial de " + noivo + " & " + noiva
                + "\n\nStatus: Logado"
                + "\nUsuario Logado: " + usuarioLogado
                + "\n\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Menu de pessoas "
                + "\n3- Menu de usuarios"
                + "\n4- Mural de recados"
                + "\n5- Menu de fornecedores"
                + "\n6- Pagamentos "
                + "\n7- Convites"
                + "\n8- Relatorios"
                + "\n9- Deslogar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }
    
    public int menuPrincipalLogadoNormal(String noivo, String noiva, String usuarioLogado) {
        int opcao;

        String menu = "HOME"
                + "\nCerimonial de " + noivo + " & " + noiva
                + "\n\nStatus: Logado"
                + "\nUsuario Logado: " + usuarioLogado
                + "\n\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Mural de recados"
                + "\n3- Convites"
                + "\n4- Deslogar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuConvite() {
        int opcao;

        String menu = "MENU CONVITE"
                + "\n\nselecione:"
                + "\n1- Convite Familiar"
                + "\n2- Convite Individual"
                + "\n3- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }
    
    public int menuRelatorio() {
        int opcao;

        String menu = "RELATORIOS"
                + "\n\nselecione:"
                + "\n1- Relatorio de Recados enviados"
                + "\n2- Relatorio de Convidados"
                + "\n3- Relatorio dos Pagamentos Pagos"
                + "\n4- Relatorio de Convidados Confirmados"
                + "\n5- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuConviteFamilia() {
        int opcao;

        String menu = "MENU CONVITE FAMILIA"
                + "\n\nselecione:"
                + "\n1- Cadastrar Familiar"
                + "\n2- Mostrar Familias cadastradas"
                + "\n3- Confirmar presença da familia"
                + "\n4- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuConviteIndividual() {
        int opcao;

        String menu = "MENU CONVITE INDIVIDUAL"
                + "\n\nselecione:"
                + "\n1- Cadastrar Convite"
                + "\n2- Mostrar Convites Cadastrados"
                + "\n3- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPessoa() {
        int opcao;

        String menu = "MENU DE PESSOAS\n"
                + "\nselecione:"
                + "\n1- Adicionar pessoa"
                + "\n2- Consultar pessoa"
                + "\n3- Excluir pessoa"
                + "\n4- Alterar o nome da pessoa"
                + "\n5- Mostra pessoas cadastradas"
                + "\n6- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuFornecedor() {
        int opcao;

        String menu = "MENU DE FORNECEDORES\n"
                + "\nselecione:"
                + "\n1- Cadastro de fornecedores"
                + "\n2- Mostrar lista de fornecedores "
                + "\n3- Consulta fornecedor"
                + "\n4- Excluir fornecedor"
                + "\n5- Alterar status do fornecedor"
                + "\n6- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPagamento() {
        int opcao;

        String menu = "PAGAMENTOS\n"
                + "\nselecione:"
                + "\n1- Lançamento dos pagamentos"
                + "\n2- Visualizar todos os pagamentos "
                + "\n3- Visualizar pagamentos do dia"
                + "\n4- Consultar pagamento"
                + "\n5- Pagar Contas do dia"
                + "\n6- Pagamento Automatico"
                + "\n7- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuUsuario() {
        int opcao;

        String menu = "MENU DE USUARIOS\n"
                + "\nselecione:"
                + "\n1- Adicionar usuario"
                + "\n2- Consultar usuario"
                + "\n3- Excluir usuario"
                + "\n4- Alterar senha do usuario"
                + "\n5- Mostrar usuarios cadastrados"
                + "\n6- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPresente() {
        int opcao;

        String menu = "LISTA DE PRESENTES\n"
                + "\nselecione:"
                + "\n1- Visualizar lista de presentes"
                + "\n2- Reservar presente"
                + "\n3- Adicionar presente"
                + "\n4 - Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuLogin() {
        int opcao;

        String menu = "ENTRAR NO PROGRAMA\n"
                + "\nselecione:"
                + "\n1- Login"
                + "\n2- Cadastro"
                + "\n3- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuMuralRecados() {
        int opcao;

        String menu = "MURAL DE RECADOS\n"
                + "\nselecione:"
                + "\n1- Escrever recado"
                + "\n2- Exibir mural de recado"
                + "\n3- Consultar recados"
                + "\n4- Editar recado"
                + "\n5- Excluir recado"
                + "\n6- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    //Métodos de exibição de mensagens
    // GestaoCasamento
    public void exibirMensagemOpcaoInexistente() {
        JOptionPane.showMessageDialog(null, "Opcao invalida. Digite novamente!");
    }

    public void exibirMesangemProgramaEncerrado() {
        JOptionPane.showMessageDialog(null, "Programa encerrado");
    }

    public void exibirMensagemErroLogin() {
        JOptionPane.showMessageDialog(null, "Acesso negado"
                + "\nPossiveis motivos: "
                + "\n1- Dados de login e senha incorretos"
                + "\n2- Nenhum cadastro encontrado");
    }

    //Objeto pessoa
    public void exibirMensagemPessoaNaoEncontrada() {
        JOptionPane.showMessageDialog(null, "A pessoa não foi encontrada. "
                + "\nPossiveis motivos: "
                + "\n1 - Nome digitado incorretamente "
                + "\n2 - Pessoa inexistente"
                + "\n3 - Nenhuma pessoa cadastrada");
    }

    public void exibirPessoas(String pessoa) {
        JOptionPane.showMessageDialog(null, pessoa);
    }

    public void exibirMensagemPessoaJaExistente() {
        JOptionPane.showMessageDialog(null, "Pessoa ja existente");
    }

    public void exibirMensagemListaPessoaLotada() {
        JOptionPane.showMessageDialog(null, "A Lista de Pessoas esta lotada");
    }

    public void exibirMensagemPessoaExcluida() {
        JOptionPane.showMessageDialog(null, "Pessoa excluida com sucesso");
    }

    public void exibirMensagemPessoaAlterada() {
        JOptionPane.showMessageDialog(null, "Pessoa alterada com sucesso");
    }

    public void exibirMensagemPessoaAdicionada() {
        JOptionPane.showMessageDialog(null, "Pessoa cadastrada");
    }

    public void exibirMensagemPessoaNaoAdicionada() {
        JOptionPane.showMessageDialog(null, "Pessoa não cadastrada.");
    }

    //Objeto Presente 
    public void exibirPresentes(String presentes) {
        JOptionPane.showMessageDialog(null, presentes);
    }

    public void exibirMensagemListaPresenteVazia() {
        JOptionPane.showMessageDialog(null, "A lista de presente esta vazia");
    }

    public void exibirMensagemPresenteJaEscolhido() {
        JOptionPane.showMessageDialog(null, "Presente já reservado");
    }

    public void exibirMensagemPresenteAdicionado() {
        JOptionPane.showMessageDialog(null, "presente adicionado");
    }

    public void exibirMensagemPresenteNaoAdicionado() {
        JOptionPane.showMessageDialog(null, "presente nao adicionado"
                + "\n Possiveis motivos: "
                + "\n1 - So os noivos tem autorizacao para adicionar um novo presente");
    }

    public void exibirMensagemIdPresenteDigitadoIncorreto() {
        JOptionPane.showMessageDialog(null, "Codigo digitado incorreto");
    }

    public void exibirMensagemReservaPresenteFeitaComSucesso() {
        JOptionPane.showMessageDialog(null, "Reserva feita com sucesso");
    }

    //Objeto Usuario
    public void exibirMensagemUsuarioNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "O Usuario não foi encontrado"
                + "\nPossiveis motivos: "
                + "\n1 - Nome digitado incorretamente "
                + "\n2 - Usuario inexistente"
                + "\n3 - Nenhum usuario cadastrado");
    }

    public void exibirMensagemUsuarioExistente() {
        JOptionPane.showMessageDialog(null, "Login ja existente");
    }

    public void exibirMensagemUsuarioExcluido() {
        JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
    }

    public void exibirMensagemListaUsuarioLotada() {
        JOptionPane.showMessageDialog(null, "A Lista de Usuarios esta lotada");
    }

    public void exibirMensagemUsuarioAlterado() {
        JOptionPane.showMessageDialog(null, "Senha do usuario alterada com sucesso");
    }

    public void exibirMensagemUsuarioAdicionado() {
        JOptionPane.showMessageDialog(null, "Usuario cadastrado");
    }

    public void exibirMensagemUsuarioNaoAdicionado() {
        JOptionPane.showMessageDialog(null, "Nao foi possivel cadastrar usuario");
    }

    public void exibirUsuarios(String usuarios) {
        JOptionPane.showMessageDialog(null, usuarios);
    }

    public void exibirMensagemNoivoJaCadastrado() {
        JOptionPane.showMessageDialog(null, "Noivo já cadastrado");
    }

    public void exibirMensagemNoivaJaCadastrada() {
        JOptionPane.showMessageDialog(null, "Noiva já cadastrada");
    }

    //Objeto Mural de Recados
    public void exibirMensagemRecadoPostado() {
        JOptionPane.showMessageDialog(null, "Recado postado com sucesso");
    }

    public void exibirMensagemRecadoNaoPostado() {
        JOptionPane.showMessageDialog(null, "Recado nao postado");
    }

    public void exibirMuralRecados(String muralRecados) {
        JOptionPane.showMessageDialog(null, muralRecados);
    }

    public void exibirMensagemNaoExisteMuralRecados() {
        JOptionPane.showMessageDialog(null, "Nao existe nenhum recado no mural");
    }

    public void exibirMensagemRecadoConsultaInvalida() {
        JOptionPane.showMessageDialog(null, "Consulta invalida: possiveis motivos"
                + "\n1 - Remetente do recado nao cadastrado"
                + "\n2 - Remetente não escreveu nenhum comentario"
                + "\n3 - Mural de recados está vazio");
    }

    public void exibirMensagemRecadoNaoEditado() {
        JOptionPane.showMessageDialog(null, "Recado nao editado\npossiveis motivos:"
                + "\n1 - Codigo digitado incorretamente;"
                + "\n2 - Nao eh possivel editar um recado que voce nao escreveu.");
    }

    public void exibirMensagemRecadoEditado() {
        JOptionPane.showMessageDialog(null, "Recado editado com sucesso");
    }

    public void exibirMensagemRecadoExcluido() {
        JOptionPane.showMessageDialog(null, "Recado excluido com sucesso");
    }

    public void exibirMensagemRecadoNaoExcluido() {
        JOptionPane.showMessageDialog(null, "Recado nao excluido\npossiveis motivos:"
                + "\n1 - Codigo digitado incorretamente;"
                + "\n2 - Nao eh possivel excluir um recado que voce nao escreveu.");
    }

    //Objeto Fornecedor
    public void exibirFornecedor(String fornecedor) {
        JOptionPane.showMessageDialog(null, fornecedor);
    }

    public void exibirMensagemFornecedorExistente() {
        JOptionPane.showMessageDialog(null, "Fornecedor já adicionado");
    }

    public void exibirMensagemFornecedorAdicionado() {
        JOptionPane.showMessageDialog(null, "Fornecedor cadastradado com sucesso");
    }

    public void exibirMensagemFornecedorNaoAdicionado() {
        JOptionPane.showMessageDialog(null, "Fornecedor nao cadastrado");
    }

    public void exibirMensagemFornecedorNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "Fornecedor não foi encontrado. "
                + "\nPossiveis motivos: "
                + "\n1 - CNPJ digitado incorretamente "
                + "\n2 - Fornecedor inexistente"
                + "\n3 - Nenhuma fornecedor cadastrado");
    }

    public void exibirMensagemFornecedorExcluido() {
        JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
    }

    public void exibirMensagemFornecedorAlterado() {
        JOptionPane.showMessageDialog(null, "Status do fornecedor alterado com sucesso");
    }

    //Objeto Pagamento
    public void exibirMensagemPagamentoLancado() {
        JOptionPane.showMessageDialog(null, "Pagamentos lancados com sucesso");
    }

    public void exibirMensagemPagamentoPago() {
        JOptionPane.showMessageDialog(null, "Pagamentos pago com sucesso");
    }

    public void exibirMensagemPagamentoNaoLancado() {
        JOptionPane.showMessageDialog(null, "Pagamentos nao lancados"
                + "\nprincipais motivos:"
                + "\n1 - Codigo do fornecedor nao existe"
                + "\n2 - Pagamentos do fornecedor ja lancados");
    }

    public void exibirMensagemPagamentoNaoPago() {
        JOptionPane.showMessageDialog(null, "Pagamento nao pago"
                + "\nprincipais motivos:"
                + "\n1 - O pagamento ja esta pago"
                + "\n2 - Codigo digitado nao encotrado"
                + "\n3- Nao existe pagamento para hoje"
                + "\n4- Codigo digitado referente ao pagamento de outro dia");
    }

    public void exibirMensagemPagamentoNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "Nenhum Pagamento encontrado");
    }

    public void exibirPagamento(String pagamento) {
        System.out.println(pagamento);
    }

    public void exibirPagamentoConsultados(String pagamento) {
        JOptionPane.showMessageDialog(null, pagamento);
    }

    //Objeto ConviteFamiliar
    public void exibirMensagemFamiliaNaoCadastrada() {
        JOptionPane.showMessageDialog(null, "Familia nao cadastrada"
                + "\nprincipais motivos:"
                + "\n1 - Familia ja cadastrada"
                + "\n2 - Alguma informacao incorreta digitada");
    }

    public void exibirMensagemFamiliaCadastrada() {
        JOptionPane.showMessageDialog(null, "Familia cadastrada com sucesso");
    }

    public void exibirMensagemFamiliaConfirmada() {
        JOptionPane.showMessageDialog(null, "Familia confirmada para o casamento");
    }
    
    public void exibirMensagemFamiliaNaoConfirmada() {
        JOptionPane.showMessageDialog(null, "Familia não confirmada "
                + "\nPossiveis motivos: "
                + "\n1 - Usuario nao eh o titular da familia"
                + "\n2 - Familia ja aceitou o convite");
    }
    
    public void exibirMensagemFamiliaNaoEncontrada() {
        JOptionPane.showMessageDialog(null, "Familia não foi encontrada. "
                + "\nPossiveis motivos: "
                + "\n1 - Id digitado incorreto"
                + "\n2 - Familia nao cadastrada"
                + "\n3 - Nenhuma familia encontrada");
    }

    public void exibirFamilia(String conviteFamiliar) {
        JOptionPane.showMessageDialog(null, conviteFamiliar);
    }

    //Objeto ConviteIndividual
    public void exibirMensagemConviteIndividualCadastrado() {
        JOptionPane.showMessageDialog(null, "Convite cadastrado com sucesso");
    }

    public void exibirMensagemConviteIndividualNaoCadastrado() {
        JOptionPane.showMessageDialog(null, "Convite nao cadastrado"
                + "\nprincipais motivos:"
                + "\n1 - Convite ja cadastrado"
                + "\n2 - Alguma informacao incorreta digitada");
    }

    public void exibirMensagemConviteIndividualNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "Convite não foi encontrado"
                + "\nPossiveis motivos: "
                + "\n1 - Id digitado incorreto"
                + "\n2 - Convite nao cadastrada"
                + "\n3 - Nenhuma Convite encontrado");
    }

    public void exibirConviteIndividual(String conviteIndividual) {
        JOptionPane.showMessageDialog(null, conviteIndividual);
    }

}
