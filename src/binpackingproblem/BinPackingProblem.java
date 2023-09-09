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
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Caroline, Yasmin e Bianca
 */

public class BinPackingProblem {

    /**
     * @param args TamanhoCaixa CodAlgoritimo ArquivoEntrada
     */

    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        int tamanhoCaixa = 100, op = 0;
        int vet[], vettemp[];

        String nomeArquivo = "dataSet2_FSU_tamanho100"; /*
                                                                  * nome tamanhocaixa -> Arquivos disponíveis para teste
                                                                  * exemploGerado_18caixas_tamanho80 80
                                                                  * dataSet1_FSU_tamanho100 100
                                                                  * dataSet2_FSU_tamanho100 100
                                                                  * dataSet3_FSU_tamanho100 100
                                                                  * dataSet4_FSU_tamanho524 524
                                                                  * dataSet76_Schwerin_tamanho1000 1000
                                                                  * dataSet100_Schwerin_tamanho1000 1000
                                                                  * exemploGerado_60caixas_tamanho100 100
                                                                  */

        if (args.length == 3) {
            tamanhoCaixa = Integer.parseInt(args[0]);
            String str = args[1];

            if (str.equals("NF")) {
                op = 3;
            } else if (str.equals("FF")) {
                op = 4;
            } else if (str.equals("BF")) {
                op = 5;
            } else if (str.equals("NFD")) {
                op = 6;
            } else if (str.equals("FFD")) {
                op = 7;
            } else if (str.equals("MFFD")) {
                op = 8;
            } else if (str.equals("BFD")) {
                op = 9;
            } else if (str.equals("extra")) {
                op = 10;
            } else if (str.equals("BFA")) {
                op = 12;
            }

            nomeArquivo = args[2];
            // Função para ler arquivo para preencher vetor

        } else {
            op = menu();
        }

        String caminhoArquivo = ".//src//dataSet//" + nomeArquivo + ".txt";
        vettemp = leituraArquivo(caminhoArquivo);

        String nomeGrafico = "";
        while (op != 0) {
            vet = vettemp.clone();
            Packing caixa;
            switch (op) {
                case 0: // Encerra programa
                    System.out.println("\n\n Saindo ...\n");
                    break;

                case 1: // Exibir Lista de Itens
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

                case 2: // Alterar tamanho da Caixa
                    System.out.println("Por favor, digite o novo tamanho maximo da caixa: ");
                    int tempTamanhoCaixa = entrada.nextInt();
                    if (tempTamanhoCaixa > 0) {
                        tamanhoCaixa = tempTamanhoCaixa;
                        System.out.println("Tamanho atualizado: " + tamanhoCaixa);
                    } else {
                        System.out.println("Tamanho da caixa deve ser um numero positivo");
                    }
                    break;

                case 3: // Next Fit (NF)
                    System.out.println("\n--- Next Fit ---");
                    nomeGrafico = "Next_Fit";
                    NextFit nf = new NextFit(vet);
                    caixa = nf.algoritmoNextFit(tamanhoCaixa);
                    caixa.exportarDados("Next_Fit", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 4: // First Fit (FF)
                    System.out.println("\n\n--- First Fit ---");
                    nomeGrafico = "First_Fit";
                    FirstFit ff = new FirstFit(vet);
                    caixa = ff.algoritmoFirstFit(tamanhoCaixa);
                    caixa.exportarDados("First_Fit", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 5: // Best Fit (BF)
                    System.out.println("\n\n--- Best Fit ---");
                    nomeGrafico = "Best_Fit";
                    BestFit bf = new BestFit(vet);
                    caixa = bf.algoritmoBestFit(tamanhoCaixa);
                    caixa.exportarDados("Best_Fit", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 6: // Next Fit Decreasing (NFD)
                    System.out.println("\n\n--- Next Fit Decreasing ---");
                    nomeGrafico = "Next_Fit_Decreasing";
                    NextFitDecreasing nfd = new NextFitDecreasing(vet);
                    caixa = nfd.algoritmoNextFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("Next_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 7: // First Fit Decreasing (FFD)
                    System.out.println("\n\n--- First Fit Decreasing ---");
                    nomeGrafico = "First_Fit_Decreasing";
                    FirstFitDecreasing ffd = new FirstFitDecreasing(vet);
                    caixa = ffd.algoritmoFirstFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("First_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 8: // Modified First Fit Decreasing (MFFD)
                    System.out.println("\n\n--- Modified First Fit Decreasing ---");
                    nomeGrafico = "Modified_First_Fit_Decreasing";
                    ModifiedFirstFitDecreasing mffd = new ModifiedFirstFitDecreasing(vet);
                    caixa = mffd.algoritmoModifiedFirstFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("Modified_First_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 9: // Best Fit Decreasing (BFD)
                    System.out.println("\n\n--- Best Fit Decreasing ---");
                    nomeGrafico = "Best_Fit_Decreasing";
                    BestFitDecreasing bfd = new BestFitDecreasing(vet);
                    caixa = bfd.algoritmoBestFitDecreasing(tamanhoCaixa);
                    caixa.exportarDados("Best_Fit_Decreasing", nomeArquivo);
                    System.out.println(caixa);
                    break;

                case 10:
                    AplicacaoReal simulacao = new AplicacaoReal();
                    simulacao.executar();
                    break;

                case 11:
                    gerarMediaTempoExecucao(10000, tamanhoCaixa, vet, nomeArquivo);
                    break;

                case 12:
                    System.out.println("\n\n--- Brute Force ---");
                    nomeGrafico = "Brute_Force";
                    long tempoInicial = System.nanoTime();
                    BruteForce bfa = new BruteForce(vet, tamanhoCaixa);
                    caixa = bfa.algoritmoBruteForce();
                    long tempoFinal = System.nanoTime();
                    long tempoDecorrido = (long) (tempoFinal - tempoInicial);
                    System.out.println("\nTempo Execução Brute Force");
                    System.out.println("Nanossegundos: " + tempoDecorrido);
                    caixa.exportarDados("Brute_Force", nomeArquivo);
                    System.out.println(caixa);
                    break;

                default:
                    System.out.println("\n\n DIGITE UM NUMERO VALIDO!!!\n");
                    break;
            }

            nomeGrafico += "_" + nomeArquivo;
            if (op > 2 && op < 10 || op ==12) {
                executaPython(nomeGrafico);
            }
            op = menu();
        }
    }

    public static int menu() {
        Scanner entrada = new Scanner(System.in);
        int opMenu;
        System.out.println("\n\n\t===== Implementacao do Problema do empacotamento (Bin Packing Problem) =====");
        System.out.println("\t 1) Exibir Lista de Itens");
        System.out.println("\t 2) Alterar tamanho da Caixa");
        System.out.println("\t 3) Next Fit (NF)");
        System.out.println("\t 4) First Fit (FF)");
        System.out.println("\t 5) Best Fit (BF)");
        System.out.println("\t 6) Next Fit Decreasing (NFD)");
        System.out.println("\t 7) First Fit Decreasing (FFD)");
        System.out.println("\t 8) Modified First Fit Decreasing (MFFD)");
        System.out.println("\t 9) Best Fit Decreasing(BFD)");
        System.out.println("\t 10) Extra: Aplicacao real");
        System.out.println("\t 11) Gerar media tempo execucao");
        System.out.println("\t 12) Brute Force");
        System.out.println("\t 0) sair");
        opMenu = entrada.nextInt();
        if (opMenu >= 0 && opMenu <= 12) {
            return opMenu;
        }
        return -1;
    }

    public static int[] leituraArquivo(String caminho_arquivo) throws IOException {
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

    public static void executaPython(String nomeGrafico) throws IOException {
        try {
            String comando = "python3 src//scripts-analysis//geradorGraficoBarrasEmplilhadas.py " + nomeGrafico;
            System.out.println(comando);
            ProcessBuilder builder = new ProcessBuilder(comando.split(" "));

            // Redireciona a saída de erro padrão do processo Python para o Java
            builder.redirectErrorStream(true);

            Process processo = builder.start();

            // Cria um leitor de entrada para capturar a saída (incluindo a saída de erro)
            // do processo Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;

            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // Exibe a saída do processo Python no console Java
            }

            int resultado = processo.waitFor();
            if (resultado == 0) {
                System.out.println("Script Python executado com sucesso.");
            } else {
                System.out.println("Erro ao executar o script Python. Codigo de saida: " + resultado);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void gerarMediaTempoExecucao(int repeticoes, int tamanhoCaixa, int vet[], String nome) {
        String caminhoCompleto = ".//src//dataSet//tempoExecucaoSimulacoes//tempoExecucao_" + nome + ".csv";
        long tempoTotal = 0;
        try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoCompleto))) {

            arquivo.write("Algoritmo; tempo\n");
            // arquivo.write(nomeAlgoritmo + "; " + tamMaxCaixa + "; " + nomeArquivo +
            // "\n\nCaixas:\n");

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
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

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
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                BestFit bf = new BestFit(vet);
                bf.algoritmoBestFit(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);

                String temp = "" + tempoDecorrido;
                arquivo.write("BestFit;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Best Fit");
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                BestFitDecreasing bfd = new BestFitDecreasing(vet);
                bfd.algoritmoBestFitDecreasing(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);

                String temp = "" + tempoDecorrido;
                arquivo.write("BestFitDecreasing;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Best Fit Decreasing");
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

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
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

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
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

            for (int i = 0; i < repeticoes; i++) {
                long tempoInicial = System.nanoTime();
                ModifiedFirstFitDecreasing mffd = new ModifiedFirstFitDecreasing(vet);
                mffd.algoritmoModifiedFirstFitDecreasing(tamanhoCaixa);
                long tempoFinal = System.nanoTime();
                long tempoDecorrido = (long) (tempoFinal - tempoInicial);

                String temp = "" + tempoDecorrido;
                arquivo.write("ModifiedFirstFitDecreasing;" + temp);
                arquivo.write("\n");
                tempoTotal += (long) tempoDecorrido;
            }
            System.out.println("\nTempo Medio Modified First Fit Decreasing");
            System.out.println("Nanossegundos: " + (tempoTotal / repeticoes));

        } catch (IOException e) {
            System.out.println("ERRO ao criar arquivo");
        }
    }
}