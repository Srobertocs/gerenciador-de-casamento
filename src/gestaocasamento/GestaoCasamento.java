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
                // 2 - Acessa o Menu da lista de presentes
                case 2:
                    executaOpcaoMenuPresente();
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

    private void executaOpcaoMenuPresente() {

        int pegaOpcao = 0;

        while (pegaOpcao != 3) {
            
            pegaOpcao = gui.menuPresente();
            switch (pegaOpcao) {
               //Mostra a lista de presentes 
                case 1:     
                    JOptionPane.showMessageDialog(null, presenteDAO.mostraListaPresentes());
                    break;
                //Reserva comprador de cada presente 
                case 2:
                    boolean confirmacao = false;
                    //Cria uma pessoa para receber através do nome o cadastro de uma pessoa já existente 
                    Pessoa pessoa = new Pessoa();
                    pessoa = pessoaDAO.pegaPessoa(JOptionPane.showInputDialog("Digite o seu nome para fazer a reserva do presente"));

                    //condição para saber se existe a pessoa 
                   if(pessoa != null){
                       //pega o id do presente
                       long id  = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo referente ao presente que deseja comprar"));
                       //Valida o id do presente
                       if(presenteDAO.validaID(id)){
                           //Vinculo a pessoa ao presente vinculado ao id recebido depois de validado
                           confirmacao = presenteDAO.reservaCompradorPresentes(pessoa, id);
                       }else{
                           JOptionPane.showMessageDialog(null, "Codigo digitado incorreto");
                       }                       
                   }
                   if (confirmacao){
                       JOptionPane.showMessageDialog(null, "Reserva feita com sucesso");
                   }         
                    break;              
                //Retorna para o menu principal
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida. Digite novamente!");
            }
        }
    }

    //Main
    public static void main(String[] args) {
        new GestaoCasamento();
    }
}
