package binpackingproblem;

public class BruteForce {

    private final int vetItens[];
    private final int quantItens;
    private int contadorPermutacoes = 0;
    private final int tamanhoCaixa;
    private int fatorialVet;
    private Packing melhorPermutacao;

    public BruteForce(int[] vetItens, int tamanhoCaixa) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
        this.tamanhoCaixa = tamanhoCaixa;
    }

    private void gerarPermutacoes(int array[], int tamanhoArray, int index) {
        if (index == tamanhoArray - 1) {
            int permutacaoAtual[] = new int[tamanhoArray];
            for(int i = 0; i < tamanhoArray; i++) {
                permutacaoAtual[i] = array[i];
            }
            for(int i=0; i< fatorialVet; i++){
            NextFit ff = new NextFit(permutacaoAtual);
            Packing caixa = ff.algoritmoNextFit(tamanhoCaixa);
            
            if(melhorPermutacao == null || melhorPermutacao.getQuantCaixas() > caixa.getQuantCaixas()) {
                melhorPermutacao = caixa;
            }
        }
            contadorPermutacoes++;
            return;
        }

        for (int i = index; i < tamanhoArray; i++) {
            int temp = array[index];
            vetItens[index] = vetItens[i];
            vetItens[i] = temp;
            gerarPermutacoes(array, tamanhoArray, index + 1);
            int temp2 = array[index];
            vetItens[index] = vetItens[i];
            vetItens[i] = temp2;
        }
    }

    private static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public Packing algoritmoBruteForce() {
        fatorialVet = calcularFatorial(quantItens);
       
        gerarPermutacoes(vetItens, quantItens, 0);
        
         return melhorPermutacao;
    }
    
}
