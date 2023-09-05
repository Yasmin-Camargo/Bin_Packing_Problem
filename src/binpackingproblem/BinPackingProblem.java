/*
  O objetivo do problema do empacotamento de caixas é encontrar o menor número de caixas 
  possiveis para acomodar todos os itens, respeitando as capacidades de carga
*/

package binpackingproblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Caroline, Yasmin e Bianca
 */

public class BinPackingProblem {

    /**
     * @param args TamanhoCaixa CodAlgoritimo ArquivoEntrada
     */
    
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        int tamanhoCaixa = 100, op = 0;
        int vet[];   
        String nomeArquivo = "02";
        
        if (args.length == 3) { 
            tamanhoCaixa = Integer.parseInt(args[0]);
            String str = args[1];
            
            if (str.equals("NF")) {
                op = 3;
            } else if (str.equals("FF")) {
                op = 4;
            } else if (str.equals("NFD")) {
                op = 5;
            } else if (str.equals("FFD")) {
                op = 6;
            } else if (str.equals("extra")){
                op = 7;
            }
            
            nomeArquivo = args[2];
            //Função para ler arquivo para preencher vetor
            
        } else {
            op = menu();
        }
        
        String caminhoArquivo = ".//src//dataSet//" +nomeArquivo+ ".txt";
        vet = leituraArquivo(caminhoArquivo);
         
        while (op != 0) { 
            Packing caixa;
            switch (op) {
                case 0: //Encerra programa
                    System.out.println("\n\n Saindo ...\n");
                    break;
                    
                case 1: //Exibir Lista de Itens
                    System.out.println("\nItens:");
                    System.out.print("[");
                    for (int i = 0; i < vet.length; i++) {
                        System.out.print(vet[i]);
                        if ((i + 1) != vet.length) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("]");
                    break;
                    
                case 2: //Alterar tamanho da Caixa
                    System.out.println("Por favor, digite o novo tamanho maximo da caixa: ");
                    int tempTamanhoCaixa = entrada.nextInt();
                    if (tempTamanhoCaixa > 0 ) {
                        tamanhoCaixa = tempTamanhoCaixa;
                        System.out.println("Tamanho atualizado: " + tamanhoCaixa);
                    } else {
                        System.out.println("Tamanho da caixa deve ser um numero positivo");
                    }
                    break;
                    
                case 3: //Next Fit (NF)
                    System.out.println("\n--- Next Fit ---");
                    NextFit nf = new NextFit(vet);
                    caixa = nf.algoritmoNextFit(tamanhoCaixa);
                    caixa.exportarDados("Next_Fit", nomeArquivo);
                    System.out.println(caixa);
                    break;
                    
                case 4: //First Fit (FF)
                    System.out.println("\n\n--- First Fit ---");
                    FirstFit ff = new FirstFit(vet);
                    caixa = ff.algoritmoFirstFit(tamanhoCaixa);
                    caixa.exportarDados("First_Fit", nomeArquivo);
                    System.out.println(caixa);
                    break;
                
                case 5: //Next Fit Decreasing (NFD)
                    System.out.println("\n\n--- Next Fit Decreasing ---");
                    NextFitDecreasing nfd = new NextFitDecreasing(vet);
                    caixa = nfd.algoritmoNextFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("Next_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;
                    
                case 6: //First Fit Decreasing (FFD)
                    System.out.println("\n\n--- First Fit Decreasing ---");
                    FirstFitDecreasing ffd = new FirstFitDecreasing(vet);
                    caixa = ffd.algoritmoFirstFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("First_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;
                 
                case 7:
                    aplicacaoReal();
                    break;
                    
                case 8:
                    gerarMediaTempoExecucao(1000, tamanhoCaixa, vet, nomeArquivo);
                    break;
                    
                default:
                    System.out.println("\n\n DIGITE UM NUMERO VALIDO!!!\n");
                    break;
            }
            op = menu();
        }
    } 
    
    public static int menu(){
        Scanner entrada = new Scanner(System.in);
        int opMenu;
        System.out.println("\n\n\t===== Implementacao do Problema do empacotamento (Bin Packing Problem) =====");
        System.out.println("\t 1) Exibir Lista de Itens");
        System.out.println("\t 2) Alterar tamanho da Caixa");
        System.out.println("\t 3) Next Fit (NF)");
        System.out.println("\t 4) First Fit (FF)");
        System.out.println("\t 5) Next Fit Decreasing (NFD)");
        System.out.println("\t 6) First Fit Decreasing (FFD)");
        System.out.println("\t 7) Extra: Aplicacao real");
        System.out.println("\t 8) Gerar media tempo execucao");
        System.out.println("\t 0) sair");
        opMenu = entrada.nextInt();
        if (opMenu >= 0 && opMenu <= 8){
            return opMenu;
        }
        return -1;
    }
    
    public static int[] leituraArquivo(String caminho_arquivo) throws IOException{
        // Abre o arquivo
        BufferedReader arquivo = null;
        try {
            arquivo = new BufferedReader(new FileReader(caminho_arquivo));   
        } catch (IOException e) {
            System.out.println("ERRO ao abrir arquivo");
            System.exit(0);
        }
        
        int contLinhas = 0;
        while (arquivo.readLine() != null) {            
            contLinhas++;
        }
        
        int vet[] = new int[contLinhas];
        
        arquivo.close();
        try {
            arquivo = new BufferedReader(new FileReader(caminho_arquivo));   
        } catch (IOException e) {
            System.out.println("ERRO ao abrir arquivo");
            System.exit(0);
        }

        // Preencher o vetor com os números lidos
        for (int i = 0; i < contLinhas; i++) {
            String line = arquivo.readLine();
            vet[i] = Integer.parseInt(line.trim());
        }
        
        return vet;
    }
    
    public static void gerarMediaTempoExecucao(int repeticoes, int tamanhoCaixa, int vet[], String nome){
        String caminhoCompleto = ".//src//dataSet//tempoExecucao_"+ nome +".csv";
        long tempoTotal = 0;
        try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            
            arquivo.write("Algoritmo; tempo\n");
            //arquivo.write(nomeAlgoritmo + "; " + tamMaxCaixa + "; " + nomeArquivo + "\n\nCaixas:\n");

            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                NextFit nf = new NextFit(vet);
                nf.algoritmoNextFit(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);
                
                String temp = "" + tempoDecorrido;
                arquivo.write("NextFit;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Medio Next Fit");
            System.out.println("Nanossegundos: " + (tempoTotal/repeticoes));
            System.out.println("Milissegundos: " + (tempoTotal/repeticoes) / 1_000_000);
            System.out.println("Segundos: " + (tempoTotal/repeticoes) / 1_000_000_000);
            
            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                FirstFit ff = new FirstFit(vet);
                ff.algoritmoFirstFit(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);
                
                String temp = "" + tempoDecorrido;
                arquivo.write("FirstFit;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Medio First Fit");
            System.out.println("Nanossegundos: " + (tempoTotal/repeticoes));
            System.out.println("Milissegundos: " + (tempoTotal/repeticoes) / 1_000_000);
            System.out.println("Segundos: " + (tempoTotal/repeticoes) / 1_000_000_000);
            
            
            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                NextFitDecreasing nfd = new NextFitDecreasing(vet);
                nfd.algoritmoNextFitDecreasing(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);
                
                String temp = "" + tempoDecorrido;
                arquivo.write("NextFitDecreasing;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Medio Next Fit Decreasing");
            System.out.println("Nanossegundos: " + (tempoTotal/repeticoes));
            System.out.println("Milissegundos: " + (tempoTotal/repeticoes) / 1_000_000);
            System.out.println("Segundos: " + (tempoTotal/repeticoes) / 1_000_000_000);
            
            
            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                FirstFitDecreasing ffd = new FirstFitDecreasing(vet);
                ffd.algoritmoFirstFitDecreasing(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);
                
                String temp = "" + tempoDecorrido;
                arquivo.write("FirstFitDecreasing;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Medio First Fit Decreasing");
            System.out.println("Nanossegundos: " + (tempoTotal/repeticoes));
            System.out.println("Milissegundos: " + (tempoTotal/repeticoes) / 1_000_000);
            System.out.println("Segundos: " + (tempoTotal/repeticoes) / 1_000_000_000);
            
        } catch (IOException e) {
            System.out.println("ERRO ao criar arquivo");
        }
    }
    
    public static void aplicacaoReal() throws IOException{
        /* Exemplo prático de bin packing problem: 
        Quero assistir todos filmes da marvel no menor número de dias possíveis(não estou preucupada com a ordem cronologica), 
        sendo que eu não gosto de parar um filme pela metade e tenho disponível 6 horas por dia para fazer isso. 
        Qual é a melhor forma de assistir?
        */
        
        int limiteMinutos = 60 * 6; //6 Horas por dia
        String caminho_arquivo = ".//src//dataSet//filmesMarvel.csv";
        
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
        FirstFitDecreasing ffd = new FirstFitDecreasing(filmes);
        Packing tempCaixa = ffd.algoritmoFirstFitDecreasing(limiteMinutos);
        ArrayList<ArrayList<Integer>>  ordemAssistir = tempCaixa.getListaCaixas();
        
        System.out.println("\n\n\n");
        for (int i = 0; i < ordemAssistir.size(); i++) {
            System.out.println("\n\n--- Dia " + i + " ---");
            ArrayList<Integer> vetTemp = ordemAssistir.get(i);
            for (int j = 0; j < vetTemp.size(); j++) {
                System.out.println(nomesFilmes[encontraFilme(vetTemp.get(j), duracaoFilmes)]);
            }
        }
        tempCaixa.exportarDados("filmesMarvel", "filmesMarvel");
    }
    
    public static int encontraFilme(int num, int[] assistidos){
        for (int i = 0; i < assistidos.length; i++) {
            if (assistidos[i] == num) {
                assistidos[i] = -1; //Já olhou
                return i;
            }
        }
        return 0;
    }
}    