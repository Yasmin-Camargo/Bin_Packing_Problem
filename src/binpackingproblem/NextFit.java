package binpackingproblem;

/**
 * @author Caroline, Yasmin e Bianca
 */

public class NextFit {
    private int vetItens[];
    private int quantItens;

    public NextFit(int[] vetItens) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
    }
    
    public Packing algoritmoNextFit(int tamanhoCaixa){
        printVetorItens();
        Packing minhasCaixas = new Packing(tamanhoCaixa);
        
        for (int i = 0; i < quantItens; i++) { //Adicionando itens
            minhasCaixas.addItemUltimaCaixa(vetItens[i]);
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
