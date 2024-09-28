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
    public Pessoa criaPessoa() {
        Pessoa pessoa = new Pessoa();
        
        pessoa.setNome(JOptionPane.showInputDialog("Digite o nome: "));
        pessoa.setTelefone(JOptionPane.showInputDialog("Digite o numero de contato: "));
        pessoa.setNascimento(JOptionPane.showInputDialog("Digite a data de nascimento: "));
        
        return pessoa;
    }

    //Métodos dos menus e opção
    public int menuPrincipal() {
        int opcao;

        String menu = "HOME\nSEJA BEM-VINDO\n"
                + "\nselecione:"
                + "\n1- Central de pessoas"
                + "\n2-"
                + "\n3-"
                + "\n4-"
                + "\n5-"
                + "\n6- Encerrar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }

    public int menuPessoa() {
        int opcao = 0;

        String menu = "CENTRAL DE PESSOAS\n"
                + "\nselecione:"
                + "\n1- Adicionar pessoa"
                + "\n2- Consultar pessoa"
                + "\n3- Excluir pessoa"
                + "\n4- Altera pessoa"
                + "\n5- Mostra pessoas cadastradas"
                + "\n6- Voltar";

        opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
        return opcao;
    }
}
