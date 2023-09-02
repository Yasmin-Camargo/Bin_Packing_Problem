package binpackingproblem;
import java.util.ArrayList;

/**
 * @author Caroline, Yasmin e Bianca
 */

public class Packing {
    private int tamMaxCaixa, numCaixas;
    ArrayList<ArrayList<Integer>> listaCaixas;
   
    public Packing(int tamMaxCaixa){
        this.tamMaxCaixa = tamMaxCaixa; 
        this.numCaixas = 0;
        this.listaCaixas = new ArrayList<>();
    }
    
    private void criarCaixa() {
        ArrayList<Integer> novaCaixa = new ArrayList<>();
        listaCaixas.add(novaCaixa);
        numCaixas++;
    }
    
    public boolean addItem(int numCaixa, int item) { //Adiciona o item em uma caixa especifica
        if ((calcularPesoTotalCaixa(numCaixa) + item) > tamMaxCaixa) {
            return false; //Tamanho Máximo da caixa excedido
        }
        ArrayList<Integer> tempCaixa = listaCaixas.get(numCaixa);
        tempCaixa.add(item);
        
        System.out.println("Item " + item + " adicionado na caixa " + (numCaixa + 1));
        return true;
    }
    
    public boolean addItem(int item) { //Adiciona o item na primeira caixa com espaço suficiente, caso contrário cria uma nova caixa
        for (int i = 0; i < numCaixas; i++) {
            if (addItem(i, item)) {
                return true;
            }
        }
        
        criarCaixa();
        if (addItem(numCaixas - 1, item)) {
            return true;
        }
        return false;
    }
    
    public ArrayList<ArrayList<Integer>> getListaCaixas(){
        return listaCaixas;
    }
    
    private int calcularPesoTotalCaixa(int indiceCaixa){
        int tamanho = 0;
        ArrayList<Integer> caixaAtual = listaCaixas.get(indiceCaixa);
        
        for (int i = 0; i < caixaAtual.size(); i++) { 
            tamanho += caixaAtual.get(i);
        }
        
        return tamanho;
    }
    
    private double[] calcularUsoGeralCadaCaixa() {
        double[] porcentagensUso = new double[numCaixas];

        for (int i = 0; i < numCaixas; i++) {
            int pesoTotalCaixa = calcularPesoTotalCaixa(i);
            porcentagensUso[i] = (double) pesoTotalCaixa / tamMaxCaixa * 100.0;
        }

        return porcentagensUso;
    }
    
    public double calcularUsoGeralTotalCaixas() {
        int pesoTotalItens = 0;

        for (int i = 0; i < numCaixas; i++) {
            pesoTotalItens += calcularPesoTotalCaixa(i);
        }

        double porcentagemUsoTotal = (double) pesoTotalItens / (tamMaxCaixa * numCaixas) * 100.0;

        return porcentagemUsoTotal;
    }
    
    @Override
    public String toString() {
        double[] porcentagensUso = calcularUsoGeralCadaCaixa();
        String print = "\n\n";
        int pesoTotal = 0;

        for (int i = 0; i < numCaixas; i++) {
            ArrayList<Integer> caixaAtual = listaCaixas.get(i);
            int pesoTotalCaixa = 0;

            print += "Caixa numero " + (i + 1) + "\n";
            print += "Itens empacotados: [";

            for (int j = 0; j < caixaAtual.size(); j++) {
                print += caixaAtual.get(j);
                pesoTotalCaixa += caixaAtual.get(j);

                if (j < caixaAtual.size() - 1) {
                    print += ", ";
                }
            }

            print += "]\n";
            print += "  Peso caixa: " + pesoTotalCaixa + "\n";
            print += "  Uso da caixa: " + porcentagensUso[i] + "%\n\n";
            pesoTotal += pesoTotalCaixa;
        }
        
        print += "\n--> Tamanho Maximo das Caixas: " + tamMaxCaixa;
        print += "\n--> Peso total dos itens: " + pesoTotal;
        print += "\n--> Numero de Caixas Utilizadas: " + numCaixas;
        print += "\n--> Uso geral das caixas: " + calcularUsoGeralTotalCaixas() + "%\n\n";
        return print;
    }
}
