/*
  O objetivo do problema do empacotamento de caixas é encontrar o menor número de caixas 
  possiveis para acomodar todos os itens, respeitando as capacidades de carga
*/

package binpackingproblem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Caroline, Yasmin e Bianca
 */

public class BinPackingProblem {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int tamanhoCaixa = 524, op = 0;
        int vet[] = {442, 252, 252, 252, 252, 252, 252, 252, 127, 127, 127, 127, 127, 106, 106, 106, 106, 85, 
            84, 46, 37, 37, 12, 12, 12, 10, 10, 10, 10, 10, 10, 9, 9};     
        
        if (args.length == 3) { //TamanhoCaixa CodAlgoritimo ArquivoEntrada
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
            
            //Função para ler arquivo para preencher vetor
            
        } else {
            op = menu();
        }
        
        while (op != 0) {    
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
                    System.out.println(nf.algoritmoNextFit(tamanhoCaixa));
                    break;
                    
                case 4: //First Fit (FF)
                    System.out.println("\n\n--- First Fit ---");
                    FirstFit ff = new FirstFit(vet);
                    System.out.println(ff.algoritmoFirstFit(tamanhoCaixa));
                    break;
                
                case 5: //Next Fit Decreasing (NFD)
                    System.out.println("\n\n--- Next Fit Decreasing ---");
                    NextFitDecreasing nfd = new NextFitDecreasing(vet);
                    System.out.println(nfd.algoritmoNextFitDecreasing(tamanhoCaixa));
                    break;
                    
                case 6: //First Fit Decreasing (FFD)
                    System.out.println("\n\n--- First Fit Decreasing ---");
                    FirstFitDecreasing ffd = new FirstFitDecreasing(vet);
                    System.out.println(ffd.algoritmoFirstFitDecreasing(tamanhoCaixa));
                    break;
                 
                case 7:
                    //aplicacaoReal();
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
        System.out.println("\t 0) sair");
        opMenu = entrada.nextInt();
        if (opMenu >= 0 && opMenu <= 7){
            return opMenu;
        }
        return -1;
    }
}
    