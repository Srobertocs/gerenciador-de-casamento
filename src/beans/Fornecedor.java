/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.time.LocalDateTime;

/**
 *
 * @author SOUSA
 */
public class Fornecedor {
    private long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valorAPagar;
    private int parcelas;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificao;
    
    private static long count;
    
    
    //Construtor
    public Fornecedor(){
        Fornecedor.count += 1;
        this.id = Fornecedor.count;
    }
}
