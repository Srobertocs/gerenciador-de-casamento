/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author SOUSA
 */
public class Datas {

    public static LocalDateTime pegaDataAgora() {

        LocalDateTime data = LocalDateTime.now();
        return data;
    }

    public static LocalDate pegaDataAgoraSemHoras() {

        LocalDate data = LocalDate.now();
        return data;
    }

    public static LocalDate incrementaDataVencimento(LocalDate dataAtual) {
        LocalDate dataLancamento;

        dataLancamento = dataAtual.plusDays(30);

        return dataLancamento;
    }

    public static LocalDate dataVencimentoAuto(int dias) {
        LocalDate dataVencimento;

        dataVencimento = LocalDate.now().plusDays(dias);

        return dataVencimento;
    }

    public static LocalDate convercaoData(String textoData) {

        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate data = LocalDate.parse(textoData, formatacao);

        return data;
    }
}
