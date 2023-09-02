/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package binpackingproblem;

/**
 *
 * @author Caroline, Yasmin e Bianca
 */
public class FirstFit {
    private int vetItens[];
    private int quantItens;

    public FirstFit(int[] vetItens) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
    }
    
    public Packing algoritmoFirstFit(int tamanhoCaixa){
        printVetorItens();
        Packing minhasCaixas = new Packing(tamanhoCaixa);
        
        for (int i = 0; i < quantItens; i++) { //Adicionando itens
            minhasCaixas.addItem(vetItens[i]);
        }
        
        return minhasCaixas;
    }
    
    public void printVetorItens(){
        System.out.print("\nItens: ");
        for (int item : vetItens) {
            System.out.print(item + "   ");
        }
        System.out.print("\n\n");
    }
}
