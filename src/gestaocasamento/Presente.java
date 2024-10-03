/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaocasamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author SOUSA
 */
public class Presente {

    private long id;
    private String nome;
    private String tipo;
    private Pessoa comprador;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificao;

    private static long count;

    //Construtor 
    public Presente() {
        Presente.count += 1;
        this.id = Presente.count;
    }

    //Métodos Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setComprador(Pessoa comprador) {
        this.comprador = comprador;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificao(LocalDateTime dataModificao) {
        this.dataModificao = dataModificao;
    }

    //Métodos Getters
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Pessoa getComprador() {
        return comprador;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificao() {
        return dataModificao;
    }

    //Método ToString
    @Override
    public String toString() {
        String texto;

        if (this.comprador == null) {
            texto = "\n Codigo: " + this.id
                    + "\n Nome: " + this.nome
                    + "\n Classificacao: " + this.tipo
                    + "\n Comprador: " + this.comprador
                    + "\n\n Data de modificacao: " + this.dataModificao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        } else {
            texto = "\n Codigo: " + this.id
                    + "\n Nome: " + this.nome
                    + "\n Classificacao: " + this.tipo
                    + "\n Comprador: " + this.comprador.getNome()
                    + "\n\n Data de modificacao: " + this.dataModificao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.tipo);
        hash = 79 * hash + Objects.hashCode(this.comprador);
        hash = 79 * hash + Objects.hashCode(this.dataCriacao);
        hash = 79 * hash + Objects.hashCode(this.dataModificao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Presente other = (Presente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.comprador, other.comprador)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificao, other.dataModificao);
    }

}
