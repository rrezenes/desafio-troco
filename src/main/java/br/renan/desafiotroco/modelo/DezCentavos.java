/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.renan.desafiotroco.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Renan
 */
public class DezCentavos implements Moeda {

    private final BigDecimal valor = BigDecimal.valueOf(0.10);
    private final String nome = "Dez Centavos";

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public BigDecimal getValor() {
        return valor;
    }

}
