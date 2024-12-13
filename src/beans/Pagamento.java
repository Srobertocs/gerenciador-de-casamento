/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author SOUSA
 */
public class Pagamento {

    private long id;
    private LocalDate dataPagamento;
    private String descricao;
    private Fornecedor fornecedor;
    private double valor;
    private int parcelas;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    //Métodos Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    //Método ToString 
    public String toString(int aux) {

        DecimalFormat valorFormatado = new DecimalFormat("#.00");
        if (aux == 1) {
            String texto = "id: " + this.id
                    + "\nFornecedor: " + this.fornecedor.getNome()
                    + "\nParcela: " + this.parcelas
                    + "\nValor: R$" + valorFormatado.format(this.valor)
                    + "\nData de vencimento: " + this.dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yy"))
                    + "\nStatus do pagamento: " + this.descricao
                    + "\nData de criacao: " + this.dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                    + " | Data de modificacao: " + this.dataModificacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            return texto;
        }else{
            String texto ="Fornecedor: " + this.fornecedor.getNome()
                    + "\nParcela: " + this.parcelas
                    + "\nValor: R$" + valorFormatado.format(this.valor)
                    + "\nData de vencimento: " + this.dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yy"))
                    + "\nStatus do pagamento: " + this.descricao;

            return texto;   
        }   
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.dataPagamento);
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + Objects.hashCode(this.fornecedor);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 37 * hash + this.parcelas;
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
        final Pagamento other = (Pagamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.parcelas != other.parcelas) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.dataPagamento, other.dataPagamento)) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        return Objects.equals(this.dataModificacao, other.dataModificacao);
    }

}
