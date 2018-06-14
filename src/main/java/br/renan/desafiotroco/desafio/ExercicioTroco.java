/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.renan.desafiotroco.desafio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import br.renan.desafiotroco.modelo.Caixa;
import br.renan.desafiotroco.modelo.Cedula;
import br.renan.desafiotroco.modelo.CemReais;
import br.renan.desafiotroco.modelo.CincoCentavos;
import br.renan.desafiotroco.modelo.CincoReais;
import br.renan.desafiotroco.modelo.CinquentaCentavos;
import br.renan.desafiotroco.modelo.CinquentaReais;
import br.renan.desafiotroco.modelo.DezCentavos;
import br.renan.desafiotroco.modelo.DezReais;
import br.renan.desafiotroco.modelo.Moeda;
import br.renan.desafiotroco.modelo.UmCentavo;
import br.renan.desafiotroco.modelo.UmReal;

/**
 *
 * @author Renan
 */
public class ExercicioTroco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Moeda> moedas = new ArrayList<>();
        ArrayList<Cedula> cedulas = new ArrayList<>();

        moedas.add(new CinquentaCentavos());
        moedas.add(new DezCentavos());
        moedas.add(new CincoCentavos());
        moedas.add(new UmCentavo());

        cedulas.add(new CemReais());
        cedulas.add(new CinquentaReais());
        cedulas.add(new DezReais());
        cedulas.add(new CincoReais());
        cedulas.add(new UmReal());

        Caixa caixa = new Caixa(moedas, cedulas);

        System.out.println("---------------------");
        System.out.println("Calculadora de trocos!");
        System.out.println("---------------------");

        Scanner reader = new Scanner(System.in);

        System.out.println("Insira o valor total do pedido: ");
        BigDecimal valorPedido = reader.nextBigDecimal();

        System.out.println("Insira o valor pago: ");
        BigDecimal valorEmDinheiro = reader.nextBigDecimal();

        System.out.println("---------------------\n");
        reader.close();

        caixa.setValorPedido(valorPedido);
        caixa.setValorPago(valorEmDinheiro);

        HashMap<String, Integer> contagemTroco = caixa.calcularQuantidadeCedulasEMoedas();

        if (caixa.getTroco().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Quantidade de Cédulas e Moedas: ");
        } else if (caixa.getTroco().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Não há necessidade de troco");
        } else {
            System.out.println("Falta dinheiro no pagamento"); 
           
        }

        contagemTroco.forEach((nomeMoeda, quantidade) -> {
            System.out.println(quantidade + "x: " + nomeMoeda);
        });

    }

}
