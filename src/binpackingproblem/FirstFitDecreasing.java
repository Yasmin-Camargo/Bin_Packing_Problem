/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package binpackingproblem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author carol
 */
public class FirstFitDecreasing {
    private int vetItens[];
    private int quantItens;

    public FirstFitDecreasing(int[] vetItens) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
    }
    
    private void ordenacaoDecrescente(){
        Arrays.sort(vetItens); //Ordena em ordem crescente
        
        int limite = 0; //Iverte a ordem do vetor
        if ((quantItens % 2) == 0) { 
            limite = quantItens / 2;
        } else {
            limite = Math.floorDiv(quantItens, 2);
        }
        
        for (int i = 0, j = (quantItens - 1); i < limite; i++, j--) {
            int temp = vetItens[i];
            vetItens[i] = vetItens[j];
            vetItens[j] = temp;
        }
    }
    
    public Packing algoritmoFirstFitDecreasing(int tamanhoCaixa){
        ordenacaoDecrescente(); //Ordena em ordem decrescente
        
        FirstFit ff = new FirstFit(vetItens);
        
        return ff.algoritmoFirstFit(tamanhoCaixa);
    }
}
