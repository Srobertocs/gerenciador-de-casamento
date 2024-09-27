/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestaocasamento;

/**
 *
 * @author SOUSA
 */
public class GestaoCasamento {
    //Inicialização dos OBJETOS e DAOS
    Pessoa pessoa  = new Pessoa();
    
    PessoaDAO pessoaDAO = new PessoaDAO();
    
    public GestaoCasamento() {        
        //Iníciar código aqui

    }

    //Métodos de menu opcão 
    private int menuOpcaoInicio(){
        int opcao = 0;
        return opcao;
    }
    private int menuOpcaoPessoa(){
        int opcao = 0;
        return opcao;
    }
    
    //Main
    public static void main(String[] args) {
        new GestaoCasamento();
    }
}
