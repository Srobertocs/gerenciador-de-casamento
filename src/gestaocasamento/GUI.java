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
    public Usuario criaUsuario(){
        Usuario usuario = new Usuario();
        
        usuario.setLogin(JOptionPane.showInputDialog("Digite o nome do usuario como login:"));
        usuario.setSenha(JOptionPane.showInputDialog("Digite a senha:"));
        usuario.setTipo(JOptionPane.showInputDialog("Digite o tipo de usuario: "
                + "\nNoivo"
                + "\nNoiva"
                + "\nConvidado"));
        usuario.setPessoa(null);
        usuario.setDataCriacao(Datas.pegaDataAgora());
        usuario.setDataModificacao(Datas.pegaDataAgora());
        
        return usuario;
    } 
    
    //Métodos dos menus de opção
    
    public int menuPrincipalNaoLogado() {
        int opcao;

        String menu = "HOME\nSEJA BEM-VINDO\n\nNAO LOGADO\n"
                + "\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Entrar no programa"
                + "\n3- Encerrar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPrincipalLogado() {
        int opcao;

        String menu = "HOME\nSEJA BEM-VINDO\n\nLOGADO\n"
                + "\nselecione:"
                + "\n1- Lista de presentes"
                + "\n2- Gerenciamento de pessoas "
                + "\n3- Gerenciamento de usuarios"
                + "\n4-"
                + "\n5-"
                + "\n6- Sair";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPessoa() {
        int opcao;

        String menu = "GERENCIAMENTO DE PESSOAS\n"
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
    
    public int menuUsuario() {
        int opcao;

        String menu = "GERENCIAMENTO DE USUARIOS\n"
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
                + "\n3 - Voltar";

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

    //Objeto pessoa
    public static void exibirMensagemPessoaNaoEncontrada() {
        JOptionPane.showMessageDialog(null, "A pessoa não foi encontrada. "
                + "\nPossiveis motivos: "
                + "\n1 - Nome digitado incorretamente "
                + "\n2 - Pessoa inexistente"
                + "\n3 - Nenhuma pessoa cadastrada");
    }
    
    public void exibirPessoas(String pessoa){
         JOptionPane.showMessageDialog(null, pessoa);
    }

    public void exibirMensagemPessoaJaExistente() {
        JOptionPane.showMessageDialog(null, "Pessoa ja existente");
    }
    
    public void exibirMensagemListaPessoaLotada(){
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
    public void exibirPresentes(String presentes){
        JOptionPane.showMessageDialog(null,presentes);
    }
    
    public void exibirMensagemPresenteJaEscolhido() {
        JOptionPane.showMessageDialog(null, "Presente já escolhido por outra pessoa");
    }

    public void exibirMensagemIdPresenteDigitadoIncorreto() {
        JOptionPane.showMessageDialog(null, "Codigo digitado incorreto");
    }

    public void exibirMensagemReservaPresenteFeitaComSucesso() {
        JOptionPane.showMessageDialog(null, "Reserva feita com sucesso");
    }
    
    //Objeto Usuario
    public static void exibirMensagemUsuarioNaoEncontrado() {
        JOptionPane.showMessageDialog(null, "O Usuario não foi encontrado"
                + "\nPossiveis motivos: "
                + "\n1 - Nome digitado incorretamente "
                + "\n2 - Usuario inexistente"
                + "\n3 - Nenhum usuario cadastrado");
    }
    
    public static void exibirMensagemUsuarioExistente(){
        JOptionPane.showMessageDialog(null, "Login ja existente");
    }
    
    public void exibirMensagemListaUsuarioLotada(){
        JOptionPane.showMessageDialog(null, "A Lista de Usuarios esta lotada");  
    }
    
       public void exibirMensagemUsuarioAdicionado() {
        JOptionPane.showMessageDialog(null, "Usuario cadastrado");
    }
       
       public void exibirMensagemUsuarioNaoAdicionado() {
        JOptionPane.showMessageDialog(null, "Usuario não cadastrado.");
    }
       
       public void exibirUsuarios(String usuarios){
        JOptionPane.showMessageDialog(null, usuarios);
    }
       public void exibirNoivoJaCadastrado(){
           JOptionPane.showMessageDialog(null, "Noivo já cadastrado");
       }
       public void exibirNoivaJaCadastrada(){
           JOptionPane.showMessageDialog(null, "Noiva já cadastrada");
       }
}

