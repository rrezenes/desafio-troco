/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.renan.desafiotroco.modelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 *
 * @author Renan
 */
public class Caixa {

    private ArrayList<Moeda> moedas;
    private ArrayList<Cedula> cedulas;
    private BigDecimal troco = BigDecimal.ZERO;
    private BigDecimal valorPedido, valorPago;

    public Caixa() {
    }

    public Caixa(ArrayList<Moeda> moedas, ArrayList<Cedula> cedulas) {
        this.moedas = moedas;
        this.cedulas = cedulas;
    }

    public ArrayList<Moeda> getMoedas() {
        return moedas;
    }

    public void setMoedas(ArrayList<Moeda> moedas) {
        this.moedas = moedas;
    }

    public ArrayList<Cedula> getCedulas() {
        return cedulas;
    }

    public void setCedulas(ArrayList<Cedula> cedulas) {
        this.cedulas = cedulas;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public void addMoeda(Moeda moeda) {
        this.moedas.add(moeda);
    }

    public void addCedula(Cedula cedula) {
        this.cedulas.add(cedula);
    }

    public HashMap<String, Integer> calcularQuantidadeCedulasEMoedas() {

        this.cacularTroco();

        BigDecimal aux = BigDecimal.valueOf(0.0);
        BigDecimal ultimoEncontrado = BigDecimal.valueOf(0);

        LinkedHashMap<String, Integer> contagemTroco = new LinkedHashMap<>();

        while (aux.compareTo(troco) < 0
                && (cedulas.get(cedulas.size() - 1).getValor().add(aux)).compareTo(troco) <= 0) {
            for (Cedula cedula : cedulas) {
                if (cedula.getValor().compareTo(troco) <= 0) {
                    if ((cedula.getValor().add(aux)).compareTo(troco) <= 0) {

                        if (ultimoEncontrado.compareTo(cedula.getValor()) == 0) {
                            int qtd = contagemTroco.get(cedula.getNome()) + 1;
                            contagemTroco.computeIfPresent(cedula.getNome(), (k, v) -> qtd);
                        } else {
                            contagemTroco.put(cedula.getNome(), 1);
                        }

                        ultimoEncontrado = cedula.getValor();
                        aux = aux.add(cedula.getValor());
                        break;
                    }
                }
            }
        }

        while (aux.compareTo(troco) < 0
                && (moedas.get(moedas.size() - 1).getValor().add(aux)).compareTo(troco) <= 0) {
            for (Moeda moeda : moedas) {
                if (moeda.getValor().compareTo(troco) <= 0) {
                    if ((moeda.getValor().add(aux)).compareTo(troco) <= 0) {

                        if (ultimoEncontrado.compareTo(moeda.getValor()) == 0) {
                            int qtd = contagemTroco.get(moeda.getNome()) + 1;
                            contagemTroco.computeIfPresent(moeda.getNome(), (k, v) -> qtd);
                        } else {
                            contagemTroco.put(moeda.getNome(), 1);
                        }

                        ultimoEncontrado = moeda.getValor();
                        aux = aux.add(moeda.getValor());
                        break;
                    }
                }
            }
        }

        return contagemTroco;
    }

    public void cacularTroco() {
        try {
            troco = this.valorPago.subtract(this.valorPedido);
        } catch (NullPointerException npe) {
            System.out.println("Impossivel calcular troco: " + npe.getLocalizedMessage());
            troco = BigDecimal.ZERO;
        }
    }

    public String formatarValor(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
}
