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
public class Recados {
    private long id;
    private Usuario usuario;
    private String recado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    private static long count;
    
    public Recados(){
        Recados.count += 1;     
        this.id = Recados.count;
    }
    
    //Métodos Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecado(String recado) {
        this.recado = recado;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public String getRecado() {
        return recado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }
    
    //Método ToString
    @Override
    public String toString() {
        String texto = "Enviado por: " + this.usuario.getLogin()
                + "\nRecado: " + this.recado
                + "\n\nData de postagem: " + this.dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + "\nData de modificacao: " + this.dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.usuario);
        hash = 37 * hash + Objects.hashCode(this.recado);
        hash = 37 * hash + Objects.hashCode(this.dataCriacao);
        hash = 37 * hash + Objects.hashCode(this.dataModificacao);
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
        final Recados other = (Recados) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.recado, other.recado)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }
    
}
    
    
    
