package binpackingproblem;

public class BruteForce {

    private final int vetItens[];
    private final int quantItens;
    private final int tamanhoCaixa;
    private Packing melhorPermutacao;

    public BruteForce(int[] vetItens, int tamanhoCaixa) {
        this.vetItens = vetItens;
        this.quantItens = vetItens.length;
        this.tamanhoCaixa = tamanhoCaixa;
    }

    private void gerarPermutacoes(int array[], int tamanhoArray, int index) {
        if (index == tamanhoArray - 1) {
            int permutacaoAtual[] = new int[tamanhoArray];
            for (int i = 0; i < tamanhoArray; i++) {
                permutacaoAtual[i] = array[i];
            }
            NextFit ff = new NextFit(permutacaoAtual);
            Packing caixa = ff.algoritmoNextFit(tamanhoCaixa);

            if (melhorPermutacao == null || melhorPermutacao.getQuantCaixas() > caixa.getQuantCaixas()) {
                melhorPermutacao = caixa;
            }
            if (melhorPermutacao.calcularUsoGeralTotalCaixas() == 100) {
                return;
            }
        }

        for (int i = index; i < tamanhoArray; i++) {
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
            gerarPermutacoes(array, tamanhoArray, index + 1);
            int temp2 = array[index];
            array[index] = array[i];
            array[i] = temp2;
        }
    }

    public Packing algoritmoBruteForce() {
        gerarPermutacoes(vetItens, quantItens, 0);
        return melhorPermutacao;
    }
}
