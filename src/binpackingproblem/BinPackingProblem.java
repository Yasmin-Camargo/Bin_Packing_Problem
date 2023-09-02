/*
  O objetivo do problema do empacotamento de caixas é encontrar o menor número de caixas 
  possiveis para acomodar todos os itens, respeitando as capacidades de carga
*/

package binpackingproblem;

/**
 *
 * @author Caroline, Yasmin e Bianca
 */

public class BinPackingProblem {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        int vet[] = {70, 60, 50, 33, 33, 33, 11, 7, 3};
        FirstFitDecreasing ffd = new FirstFitDecreasing(vet);
        System.out.println(ffd.algoritmoFirstFitDecreasing(100));
        
        FirstFit ff = new FirstFit(vet);
        System.out.println(ff.algoritmoFirstFit(100));
    }    
}
