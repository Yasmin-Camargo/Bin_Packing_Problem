package binpackingproblem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        if (numCaixa < 0 || (calcularPesoTotalCaixa(numCaixa) + item) > tamMaxCaixa) {
            return false; //Tamanho Máximo da caixa excedido
        }
        ArrayList<Integer> tempCaixa = listaCaixas.get(numCaixa);
        tempCaixa.add(item);
        
        //System.out.println("Item " + item + " adicionado na caixa " + (numCaixa + 1));
        return true;
    }
    
    public int getQuantCaixas(){
        return numCaixas;
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
    
    public boolean addItemUltimaCaixa(int item) { //Adiciona o item na última caixa, caso não tenha espaço suficiente cria uma nova caixa
        if (addItem(numCaixas -1, item)) {
            return true;
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
    
    public int getQuantItensCaixa(int i){
        ArrayList<Integer> caixaAtual = listaCaixas.get(i);
        return caixaAtual.size();
    }
    
    public int calcularPesoTotalCaixa(int indiceCaixa){
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
    
    public void exportarDados(String nomeAlgoritmo, String nomeArquivo){
        String caminhoCompleto = ".//src//dataSet//resultados//resultado_" +nomeAlgoritmo+ "_" +nomeArquivo+ ".csv";
        try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            
            arquivo.write("Algoritmo; Capacidade Máxima; Nome do Arquivo\n");
            arquivo.write(nomeAlgoritmo + ";" + tamMaxCaixa + ";" + nomeArquivo + "\n\nCaixas:\n");

            for (int i = 0; i < listaCaixas.size(); i++) {
                ArrayList<Integer> caixaAtual = listaCaixas.get(i);
                for (int j = 0; j < caixaAtual.size(); j++) {
                    arquivo.write(caixaAtual.get(j).toString());
                    if (j < caixaAtual.size() - 1) {
                        arquivo.write(";");
                    }
                }
                arquivo.write("\n");
            }

            //System.out.println("\nArquivo CSV criado com sucesso: " + caminhoCompleto);
        } catch (IOException e) {
            System.out.println("ERRO ao criar arquivo");
        }
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
