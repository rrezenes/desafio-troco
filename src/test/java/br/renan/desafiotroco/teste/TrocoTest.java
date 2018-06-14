/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.renan.desafiotroco.teste;

import br.renan.desafiotroco.modelo.Caixa;
import br.renan.desafiotroco.modelo.Caixa;
import br.renan.desafiotroco.modelo.Cedula;
import br.renan.desafiotroco.modelo.Cedula;
import br.renan.desafiotroco.modelo.CemReais;
import br.renan.desafiotroco.modelo.CemReais;
import br.renan.desafiotroco.modelo.CincoCentavos;
import br.renan.desafiotroco.modelo.CincoCentavos;
import br.renan.desafiotroco.modelo.CincoReais;
import br.renan.desafiotroco.modelo.CincoReais;
import br.renan.desafiotroco.modelo.CinquentaCentavos;
import br.renan.desafiotroco.modelo.CinquentaCentavos;
import br.renan.desafiotroco.modelo.CinquentaReais;
import br.renan.desafiotroco.modelo.CinquentaReais;
import br.renan.desafiotroco.modelo.DezCentavos;
import br.renan.desafiotroco.modelo.DezCentavos;
import br.renan.desafiotroco.modelo.DezReais;
import br.renan.desafiotroco.modelo.DezReais;
import br.renan.desafiotroco.modelo.Moeda;
import br.renan.desafiotroco.modelo.Moeda;
import br.renan.desafiotroco.modelo.UmCentavo;
import br.renan.desafiotroco.modelo.UmCentavo;
import br.renan.desafiotroco.modelo.UmReal;
import br.renan.desafiotroco.modelo.UmReal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Renan
 */
public class TrocoTest {

    ArrayList<Moeda> moedas;
    ArrayList<Cedula> cedulas;
    Caixa caixa;
    BigDecimal valorTotalASerPago;
    BigDecimal valorEfetivamentePago;

    public TrocoTest() {
    }

    @Before
    public void setUp() {
        moedas = new ArrayList<>();
        cedulas = new ArrayList<>();

        moedas.add(new CinquentaCentavos());
        moedas.add(new DezCentavos());
        moedas.add(new CincoCentavos());
        moedas.add(new UmCentavo());

        cedulas.add(new CemReais());
        cedulas.add(new CinquentaReais());
        cedulas.add(new DezReais());
        cedulas.add(new CincoReais());
        cedulas.add(new UmReal());

        caixa = new Caixa(moedas, cedulas);

        valorTotalASerPago = BigDecimal.valueOf(13.33);
        valorEfetivamentePago = BigDecimal.valueOf(100);

        caixa.setValorPedido(valorTotalASerPago);
        caixa.setValorPago(valorEfetivamentePago);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCalcularQuantidadeDeCedulasEMoedasDoTroco() {

        System.out.println("calcularQuantidadeDeCedulasEMoedas");
        HashMap<String, Integer> contagemTroco = caixa.calcularQuantidadeCedulasEMoedas();

        System.out.println("contagemTroco: " + contagemTroco);

        LinkedHashMap<String, Integer> trocoEsperado = new LinkedHashMap<>();

        trocoEsperado.put(new CinquentaReais().getNome(), 1);
        trocoEsperado.put(new DezReais().getNome(), 3);
        trocoEsperado.put(new CincoReais().getNome(), 1);
        trocoEsperado.put(new UmReal().getNome(), 1);
        trocoEsperado.put(new CinquentaCentavos().getNome(), 1);
        trocoEsperado.put(new DezCentavos().getNome(), 1);
        trocoEsperado.put(new CincoCentavos().getNome(), 1);
        trocoEsperado.put(new UmCentavo().getNome(), 2);

        System.out.println("trocoEsperado: " + trocoEsperado);

        assertEquals(contagemTroco, trocoEsperado);
    }

    @Test
    public void testCalcularTroco() {
        System.out.println("\ncalcularTroco");

        caixa.cacularTroco();
        BigDecimal trocoEsperado = BigDecimal.valueOf(86.67);

        System.out.println("Troco " + caixa.getTroco());

        System.out.println("trocoEsperado: " + trocoEsperado);

        assertEquals(caixa.getTroco(), trocoEsperado);
    }
}
