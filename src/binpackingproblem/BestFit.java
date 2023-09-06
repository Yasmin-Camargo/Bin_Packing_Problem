package binpackingproblem;

/**
 * @author @author Caroline, Yasmin e Bianca
 */

public class BestFit {
    private int vetItens[];
    private int quantItens;

    public BestFit(int[] vetItens) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
    }
    
    private int caixaMenorEspacoVazio(Packing minhasCaixas, int tamanhoMaxCaixa, int item){
        int quantCaixas = minhasCaixas.getListaCaixas().size();
        if (quantCaixas == 0) {
            return -1;
        }
        
        int menorEspaco = 999999999, indice = -1;
        for (int i = 0; i < quantCaixas; i++) {
            int tempEspacoSobrando = tamanhoMaxCaixa - minhasCaixas.calcularPesoTotalCaixa(i);
            if (item <= tempEspacoSobrando && tempEspacoSobrando < menorEspaco) {
                menorEspaco = tempEspacoSobrando;
                indice = i;
            }
        }
        return indice;
    }
    
    
    public Packing algoritmoBestFit(int tamanhoMaxCaixa){
        Packing minhasCaixas = new Packing(tamanhoMaxCaixa);
        
        for (int i = 0; i < quantItens; i++) { //Adicionando itens
            int indiceCaixa = caixaMenorEspacoVazio(minhasCaixas, tamanhoMaxCaixa, vetItens[i]);
            if (indiceCaixa == -1) { //Criar caixa
                minhasCaixas.addItem(vetItens[i]);
            } else{
                minhasCaixas.addItem(indiceCaixa, vetItens[i]);
            }
        }
        return minhasCaixas;
    }
}
