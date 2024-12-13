/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author SOUSA
 */
public class ConviteIndividual {

    private long id;
    private Pessoa pessoa;
    private ConviteFamiliar Familia;
    private String parentesco;
    private String confirmacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    //Métodos Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setFamilia(ConviteFamiliar Familia) {
        this.Familia = Familia;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    //Métodos Getters
    public long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public ConviteFamiliar getFamilia() {
        return Familia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    //Método ToString 
    public String toString(int aux) {
        
        if(aux == 1){
             String texto = "Id: " + this.id
                + "\nNome: " + this.pessoa.getNome()
                + "\nFamilia: " + this.Familia.getNomeFamilia()
                + "\nParentesco: " + this.parentesco
                + "\nPresenca: " + this.confirmacao
                + "\nData de Modificacao: " + this.dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + "\nData de Criacao: " + this.dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return texto;
        
        }else {
            String texto ="Nome: " + this.pessoa.getNome()
                + "\nFamilia: " + this.Familia.getNomeFamilia()
                + "\nParentesco: " + this.parentesco
                + "\nPresenca: " + this.confirmacao;
        return texto;   
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.pessoa);
        hash = 97 * hash + Objects.hashCode(this.Familia);
        hash = 97 * hash + Objects.hashCode(this.parentesco);
        hash = 97 * hash + Objects.hashCode(this.confirmacao);
        hash = 97 * hash + Objects.hashCode(this.dataCriacao);
        hash = 97 * hash + Objects.hashCode(this.dataModificacao);
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
        final ConviteIndividual other = (ConviteIndividual) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.parentesco, other.parentesco)) {
            return false;
        }
        if (!Objects.equals(this.confirmacao, other.confirmacao)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.Familia, other.Familia)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }

}
