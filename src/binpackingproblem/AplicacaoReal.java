/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package binpackingproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yasmi
 */
public class AplicacaoReal {
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
