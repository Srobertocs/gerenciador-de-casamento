/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    private LocalDateTime dataModificacao;

    //Métodos Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDateTime dataModificao) {
        this.dataModificacao = dataModificao;
    }

    //Métodos Getters
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public int getParcelas() {
        return parcelas;
    }

    public String getStatus() {
        return status;
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

        DecimalFormat valorFormatado = new DecimalFormat("#.00");

        String texto = "\nCodigo: " + this.id
                + " | Nome do fornecedor: " + this.nome
                + " | CNPJ: " + this.cnpj
                + " | Contato: " + this.telefone
                + " | Valor: R$" + valorFormatado.format(this.valorAPagar)
                + " | Parcelas: " + this.parcelas + " x"
                + "  | Status: " + this.status
                + "  | Data de criacao: " + this.dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                + "  | Data de Modificacao: " + this.dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return texto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.cnpj);
        hash = 67 * hash + Objects.hashCode(this.telefone);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.valorAPagar) ^ (Double.doubleToLongBits(this.valorAPagar) >>> 32));
        hash = 67 * hash + this.parcelas;
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.dataCriacao);
        hash = 67 * hash + Objects.hashCode(this.dataModificacao);
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
        final Fornecedor other = (Fornecedor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorAPagar) != Double.doubleToLongBits(other.valorAPagar)) {
            return false;
        }
        if (this.parcelas != other.parcelas) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }
}
