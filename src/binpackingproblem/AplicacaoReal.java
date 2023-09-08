package binpackingproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Caroline, Yasmin e Bianca
 */

public class AplicacaoReal {
    /* Exemplo prático de bin packing problem: 
        Quero assistir todos filmes da marvel no menor número de dias possíveis(não estou preucupada com a ordem cronologica), 
        sendo que eu não gosto de parar um filme pela metade e tenho disponível 6 horas por dia para fazer isso. 
        Qual é a melhor forma de assistir?
    */
    int limiteMinutos; //6 Horas por dia
    String caminho_arquivo;

    public AplicacaoReal() {
        limiteMinutos = 60 * 6; //6 Horas por dia
        caminho_arquivo = ".//src//dataSet//filmesMarvel.csv";
    }
    
    public AplicacaoReal(int tempo, String nomeArquivo) {
        limiteMinutos = tempo; //6 Horas por dia
        caminho_arquivo = ".//src//dataSet//"+ nomeArquivo +".csv";
    }
        
    public void executar() throws IOException {
        // Abre o arquivo
        BufferedReader arquivo = null;
        try {
            arquivo = new BufferedReader(new FileReader(caminho_arquivo));   
        } catch (IOException e) {
            System.out.println("ERRO ao abrir arquivo");
            System.exit(0);
        }
        
        int contLinhas = 0; //Conta a quantidade de linhas
        while (arquivo.readLine() != null) {            
            contLinhas++;
        }
                
        arquivo.close();
        
        try {
            arquivo = new BufferedReader(new FileReader(caminho_arquivo));   
        } catch (IOException e) {
            System.out.println("ERRO ao abrir arquivo");
            System.exit(0);
        }

        String[] nomesFilmes = new String[contLinhas - 1]; //Desconta o cabeçalho
        int[] duracaoFilmes = new int[contLinhas - 1];
        
        String line = arquivo.readLine(); //Lê a o cabeçalho
        
        // Preencher o vetor com os números lidos
        for (int i = 0; i < contLinhas - 1; i++) {
            line = arquivo.readLine();
            String[] partes = line.split(", ");
            nomesFilmes[i] = partes[0];
            duracaoFilmes[i] = Integer.parseInt(partes[1]);
        }
        
        int filmes[] = duracaoFilmes.clone();
        Packing tempCaixa = null;
        String nomeAlgoritmo = "";
        
        int op = menu();
        switch (op) {
            case 1: //Next Fit (NF)
                System.out.println("\n--- Next Fit ---");
                nomeAlgoritmo = "NextFit";
                NextFit nf = new NextFit(filmes);
                tempCaixa = nf.algoritmoNextFit(limiteMinutos);
                break;

            case 2: //First Fit (FF)
                System.out.println("\n\n--- First Fit ---");
                nomeAlgoritmo = "FirstFit";
                FirstFit ff = new FirstFit(filmes);
                tempCaixa = ff.algoritmoFirstFit(limiteMinutos);
                break;

            case 3: //Best Fit (BF)
                System.out.println("\n\n--- Best Fit ---");
                nomeAlgoritmo = "BestFit";
                BestFit bf = new BestFit(filmes);
                tempCaixa = bf.algoritmoBestFit(limiteMinutos);
                break;

            case 4: //Next Fit Decreasing (NFD)
                System.out.println("\n\n--- Next Fit Decreasing ---");
                nomeAlgoritmo = "NextFitDecreasing";
                NextFitDecreasing nfd = new NextFitDecreasing(filmes);
                tempCaixa = nfd.algoritmoNextFitDecreasing(limiteMinutos);
                break;

            case 5: //First Fit Decreasing (FFD)
                System.out.println("\n\n--- First Fit Decreasing ---");
                nomeAlgoritmo = "FirstFitDecreasing";
                FirstFitDecreasing ffd = new FirstFitDecreasing(filmes);
                tempCaixa = ffd.algoritmoFirstFitDecreasing(limiteMinutos);
                break;

            case 6: //Modified First Fit Decreasing (MFFD)
                System.out.println("\n\n--- Modified First Fit Decreasing ---");
                nomeAlgoritmo = "ModifiedFirstFitDecreasing";
                ModifiedFirstFitDecreasing mffd = new ModifiedFirstFitDecreasing(filmes);
                tempCaixa = mffd.algoritmoModifiedFirstFitDecreasing(limiteMinutos);
                break;

            case 7: //Best Fit Decreasing (BFD)
                System.out.println("\n\n--- Modified First Fit Decreasing ---");
                nomeAlgoritmo = "ModifiedFirstFitDecreasing";
                BestFitDecreasing bfd = new BestFitDecreasing(filmes);
                tempCaixa = bfd.algoritmoBestFitDecreasing(limiteMinutos);
                break;

            default:
                System.out.println("\n\n DIGITE UM NUMERO VALIDO!!!\n");
                break;
        }
        
        ArrayList<ArrayList<Integer>> ordemAssistir = tempCaixa.getListaCaixas();
        
        for (int i = 0; i < ordemAssistir.size(); i++) {
            System.out.println("\n\n--- Dia " + (i + 1) + " ---");
            ArrayList<Integer> vetTemp = ordemAssistir.get(i);
            for (int j = 0; j < vetTemp.size(); j++) {
                int temp = encontraFilme(vetTemp.get(j), duracaoFilmes);
                System.out.println(nomesFilmes[temp] + " (" + vetTemp.get(j) + " minutos)");
            }
        }
        
        BinPackingProblem.executaPython(nomeAlgoritmo + "_filmesMarvel");
        tempCaixa.exportarDados(nomeAlgoritmo, "filmesMarvel");
    }
    
    public int encontraFilme(int num, int[] assistidos){
        for (int i = 0; i < assistidos.length; i++) {
            if (assistidos[i] == num) {
                assistidos[i] = -1; //Já olhou
                return i;
            }
        }
        return 0;
    }
    
    public int menu(){
        Scanner entrada = new Scanner(System.in);
        int opMenu;
        System.out.println("\n\n\tESCOLHA O ALGORITMO");
        System.out.println("\t 1 - Next Fit (NF)");
        System.out.println("\t 2 - First Fit (FF)");
        System.out.println("\t 3 - Best Fit (BF)");
        System.out.println("\t 4 - Next Fit Decreasing (NFD)");
        System.out.println("\t 5 - First Fit Decreasing (FFD)");
        System.out.println("\t 6 - Modified First Fit Decreasing (MFFD)");
        System.out.println("\t 7 - Best Fit Decreasing(BFD)");
        opMenu = entrada.nextInt();
        if (opMenu >= 0 && opMenu <= 11){
            return opMenu;
        }
        return -1;
    }
}

