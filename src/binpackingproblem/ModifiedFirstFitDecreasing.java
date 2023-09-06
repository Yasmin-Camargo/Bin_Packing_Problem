package binpackingproblem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Caroline, Yasmin e Bianca
 */

public class ModifiedFirstFitDecreasing {
    private int vetItens[];
    private int quantItens;

    public ModifiedFirstFitDecreasing(int[] vetItens) {
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
    
    private String verificaTamanho(int tamanhoItem, int tamanhoCaixa){
        double proporcao = tamanhoItem/tamanhoCaixa;
        
        if (proporcao > (1/2)) {
            return "grande";
        } else if (proporcao > (1/3)) {
            return "medio";
        } else if (proporcao > (1/6)) {
            return "pequeno";
        } else {
            return "minusculo";
        }
    }
    
    public Packing algoritmoModifiedFirstFitDecreasing(int tamanhoMaximoCaixa){
        ordenacaoDecrescente(); //Ordena em ordem decrescente
        
        Packing minhasCaixas = new Packing(tamanhoMaximoCaixa);
        ArrayList<Integer> itensMedios = new ArrayList<>();
        ArrayList<Integer> itensRestantes = new ArrayList<>();
        int numCaixa = 0;
        
        for (int i = 0; i < quantItens; i++) { //Passo 1: Distribuir uma caixa para cada item grande (classificação)
            if (verificaTamanho(vetItens[i], tamanhoMaximoCaixa).equals("grande")) {
                minhasCaixas.addItem(vetItens[i]);
                numCaixa++;
            } else if (verificaTamanho(vetItens[i], tamanhoMaximoCaixa).equals("medio")){
                itensMedios.add(vetItens[i]);
            } else {
                itensRestantes.add(vetItens[i]);
            }
        }
        
        for (int i = 0; i < numCaixa; i++) { //Passo 2
            if (itensMedios.size() <= 0) { //Precisa ter algum item no vetor de itens medios
                break;
            }
            int menorItemMedio = itensMedios.get(itensMedios.size() -1); //Pega o último elemento da lista de itens médios (menor tamanho)
            if ((tamanhoMaximoCaixa - minhasCaixas.calcularPesoTotalCaixa(i)) >= menorItemMedio) { //Espaço sobrando precisa ser >= ao tamanho do menor item médio
                for (int j = 0; j < itensMedios.size(); j++) {
                    if (minhasCaixas.addItem(i, itensMedios.get(j))) { //Coloca na caixa o maior item de tamanho médio
                        itensMedios.remove(j);
                        break;
                    }
                }
            }
        }
        
        for (int i = numCaixa - 1; i >= 0; i--) { //Passo 3: percorre da direita para esquerda
            if (numCaixa == 0) { //Precisa existir alguma caixa (itens grandes)
                break;
            }
            
            if (minhasCaixas.getQuantItensCaixa(i) < 2 && itensRestantes.size() > 1) { //Caixa que não possui item médio (só tem 1 item grande)
                int doisMenoresItensRestantes = itensRestantes.get(itensRestantes.size() - 1) + itensRestantes.get(itensRestantes.size() - 2); ////Pega ps 2 últimos elementos da lista de itens restantes (menor tamanho)
                if ((tamanhoMaximoCaixa - minhasCaixas.calcularPesoTotalCaixa(i)) >= doisMenoresItensRestantes) { //Espaço sobrando precisa ser >= ao tamanho dos 2 menores itens 
                    minhasCaixas.addItem(i, itensRestantes.remove(itensRestantes.size() - 1)); //Adiciona na caixa o menor item
                    for (int j = 0; j < itensRestantes.size(); j++) { 
                        if (minhasCaixas.addItem(i, itensRestantes.get(j))) { //Coloca na caixa o maior item 
                            itensRestantes.remove(j);
                            break;
                        }
                    }
                }
            }
        }
        
        itensMedios.addAll(itensRestantes); //Agora itens restantes foram concatenados nos itens médios 
        for (int i = 0; i < numCaixa; i++) { //Passo 4
            int menorItemMedio = itensMedios.get(itensMedios.size() -1); //Pega o último elemento da lista de itens médios (menor tamanho)
            if ((tamanhoMaximoCaixa - minhasCaixas.calcularPesoTotalCaixa(i)) >= menorItemMedio) { //Espaço sobrando precisa ser >= ao tamanho do menor item médio
                for (int j = 0; j < itensMedios.size(); j++) {
                    if (minhasCaixas.addItem(i, itensMedios.get(j))) { //Coloca na caixa o maior item de tamanho médio
                        itensMedios.remove(j);
                        break;
                    }
                }
            }
        }
        
        int vetFinal[] = new int[itensMedios.size()];
        for (int i = 0; i < itensMedios.size(); i++) {
            vetFinal[i] = itensMedios.get(i);
        }
        
        FirstFitDecreasing ffd = new FirstFitDecreasing(vetFinal);
        return ffd.algoritmoFirstFitDecreasing(tamanhoMaximoCaixa, minhasCaixas);
    }
}
