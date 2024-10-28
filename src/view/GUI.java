/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import beans.Usuario;
import beans.Recados;
import beans.Pessoa;
import beans.Fornecedor;
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
    public String recebeIdPresente() {
        return JOptionPane.showInputDialog("Digite o codigo referente ao presente que deseja comprar");
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

    //Métodos dos menus de opção
    public int menuPrincipalNaoLogado() {
        int opcao;

        String menu = "HOME\nSeja Bem Vindo\n\nStatus: Deslogado"
                + "\n\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Entrar no programa"
                + "\n3- Encerrar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPrincipalLogado() {
        int opcao;

        String menu = "HOME\nStatus: Logado"
                + "\n\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Painel de pessoas "
                + "\n3- Painel de usuarios"
                + "\n4- Mural de recados"
                + "\n5- Painel de fornecedores"
                + "\n6- Deslogar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPessoa() {
        int opcao;

        String menu = "PAINEL DE PESSOAS\n"
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

        String menu = "PAINEL DE FORNECEDOR\n"
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

    public int menuUsuario() {
        int opcao;

        String menu = "PAINEL DE USUARIOS\n"
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
                + "\n3- Voltar";

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

    public void exibirMensagemPresenteJaEscolhido() {
        JOptionPane.showMessageDialog(null, "Presente já reservado");
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
        JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
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
        JOptionPane.showMessageDialog(null, "Recado nao editado");
    }

    public void exibirMensagemCodigoNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "Nenhum recado com esse codigo foi encontrado");
    }

    public void exibirMensagemRecadoEditado() {
        JOptionPane.showMessageDialog(null, "Recado editado com sucesso");
    }

    public void exibirMensagemRecadoExcluido() {
        JOptionPane.showMessageDialog(null, "Recado excluido com sucesso");
    }

    public void exibirMensagemRecadoNaoExcluido() {
        JOptionPane.showMessageDialog(null, "Recado nao excluido");
    }

    public void exibirMensagemUsuarioRecadoInvalido() {
        JOptionPane.showMessageDialog(null, "O recado que esta tentando acessar nao foi escrito por voce.\n"
                + "Selecione para executar a opcao que selecionou algum comentario que voce mesmo escreveu");
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
}
