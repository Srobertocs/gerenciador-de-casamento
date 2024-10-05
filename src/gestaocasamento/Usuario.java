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
public class Usuario {
    private long id;
    private String login;
    private String senha;
    private String tipo;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    private static long count;
    
   public Usuario(){
       Usuario.count += 1;
       this.id = Usuario.count;
   }
   
   //Métodos Setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    @Override
    public String toString() {
        String texto = "\nID: " + this.id
                + " | Login: " + this.login
                + " | Senha: " + this.senha
                + " | ID Pessoa vinculada : " + this.pessoa.getId()
                + " | Nome Pessoa vinculada: " + this.pessoa.getNome()
                + " | Tipo: " + this.tipo
                + "\nData de criacao: " + this.dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + "  Data de modificao: " + this.dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.login);
        hash = 71 * hash + Objects.hashCode(this.senha);
        hash = 71 * hash + Objects.hashCode(this.tipo);
        hash = 71 * hash + Objects.hashCode(this.pessoa);
        hash = 71 * hash + Objects.hashCode(this.dataCriacao);
        hash = 71 * hash + Objects.hashCode(this.dataModificacao);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }
    
    
    
   
   
    
}
