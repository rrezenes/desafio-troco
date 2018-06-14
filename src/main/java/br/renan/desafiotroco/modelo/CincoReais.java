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
public class CincoReais implements Cedula {

    private final BigDecimal valor = BigDecimal.valueOf(5);
    private final String nome = "Cinco Reais";

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public BigDecimal getValor() {
        return valor;
    }
}
