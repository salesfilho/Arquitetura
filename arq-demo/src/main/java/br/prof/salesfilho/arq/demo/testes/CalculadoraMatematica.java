/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.demo.testes;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author salesfilho
 */
public class CalculadoraMatematica {

    public static void main(String[] args) {

        CalculadoraMatematica calculadoraMatematica = new CalculadoraMatematica();

        //
        Integer[] resultado = new Integer[21];

        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                resultado[i] = i * (-1);
            } else {
                resultado[i] = i;
            }
        }
        Collections.sort(Arrays.asList(resultado));

        resultado = calculadoraMatematica.sequenciaAmostraUnitariaDiscreta(resultado);

        for (int j = 0; j < resultado.length; j++) {
            System.out.println("N[" + j + "]" + resultado[j]);
        }

    }

    public double somatorioXelevadoY(int limiteInferior, int limiteSuperior, double x, double y) {
        double resultado = 0;
        //funcao y=x^2
        //somatorio comecando em limite inferior até o limite superior de x^2
        for (int i = limiteInferior; i <= limiteSuperior; i++) {
            resultado += Math.pow(x, y);
        }
        return resultado;
    }

    public Integer[] sequenciaDegauUnitarioDiscreta(int limiteSuperior, double x, double y) {
        Integer[] resultado = new Integer[Math.abs(limiteSuperior)];
        int n = 0;
        //funcao y=x^2
        //somatorio comecando em limite inferior até o limite superior de x^2
        for (int k = 0; k <= limiteSuperior; k++) {
            resultado[k] = resultado[n - k];
        }
        return resultado;
    }

    /*
     Sequencia amostra unitaria
     0 - Se n =! 0
     1 - caso contrário
     */
    public Integer[] sequenciaAmostraUnitariaDiscreta(Integer[] vetorN) {
        Integer[] resultado = new Integer[vetorN.length];
        for (int n = 0; n < vetorN.length; n++) {
            if (n != 0) {
                resultado[n] = 0;
            } else {
                resultado[n] = 1;
            }
        }
        return resultado;
    }

}
