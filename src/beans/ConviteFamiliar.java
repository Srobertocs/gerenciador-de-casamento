/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author SOUSA
 */
public class ConviteFamiliar {

    private long id;
    private String nomeFamilia;
    private String acesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    //Métodos Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setNomeFamilia(String nomeFamilia) {
        this.nomeFamilia = nomeFamilia;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    //Métodos Getters 
   public long getId(){
       return id;
   }
    public String getNomeFamilia() {
        return nomeFamilia;
    }

    public String getAcesso() {
        return acesso;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    //Método de ToString 
    @Override
    public String toString() {
        String texto = "Id: " + this.id
                + "\nNome Familia: " + this.nomeFamilia
                + "\nAcesso: " + this.acesso;
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.nomeFamilia);
        hash = 89 * hash + Objects.hashCode(this.acesso);
        hash = 89 * hash + Objects.hashCode(this.dataCriacao);
        hash = 89 * hash + Objects.hashCode(this.dataModificacao);
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
        final ConviteFamiliar other = (ConviteFamiliar) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomeFamilia, other.nomeFamilia)) {
            return false;
        }
        if (!Objects.equals(this.acesso, other.acesso)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }

}
