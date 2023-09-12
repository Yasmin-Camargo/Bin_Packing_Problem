![Apresentação - Bin Packing Problem](https://github.com/Caroline-Camargo/Bin_Packing_Problem/assets/88253809/73d5b7c1-3642-4ac7-9369-6bd9d34330e8)
Trabalho de Algoritmos e Estruturas de Dados III desenvolvido junto com [@Yasmin-Camargo](https://github.com/Yasmin-Camargo) e [@BiancaBDullius](https://github.com/BiancaBDullius) que possui como objetivo realizar a Implementação do problema do empacotamento de caixas, utilizando um algoritmo exato e algoritmos aproximativos

# :package: Problema de Empacotamento de Caixas

## :mag_right: Visão Geral

O **Problema de Empacotamento de Caixas** é um desafio de otimização combinatorial que envolve a alocação eficiente de itens de diferentes tamanhos em caixas de capacidade limitada. O objetivo é minimizar o número de caixas utilizadas para acomodar todos os itens, garantindo que nenhuma caixa exceda sua capacidade máxima.

Este projeto aborda o problema em quatro etapas principais: implementação de um algoritmo exato, desenvolvimento de algoritmos aproximativos, análise de resultados e uma aplicação prática do problema.

## :dart: Algoritmo Exato

Nesta etapa, foi desenvolvido um algoritmo que busca a solução exata para o problema do empacotamento bin. O algoritmo é baseado em força bruta, gerando todas as permutações possíveis dos itens de entrada. No entanto, sua complexidade de tempo é O(n!), tornando-o impraticável para conjuntos grandes de itens.

## :game_die: Algoritmos Aproximativos

Os algoritmos aproximativos são implementados para encontrar soluções que se aproximam da ótima em tempo polinomial. Foram desenvolvidos algoritmos online e offline para abordar o problema do empacotamento bin. Os algoritmos implementados incluem:

### :vibration_mode: Heurísticas Online

- **Next Fit (NF):** Coloca cada item na primeira caixa disponível, abrindo uma nova caixa se necessário. Fator de aproximação: 2 aproximado.

- **Best Fit (BF):** Coloca o item na caixa que sobra menos espaço ou abre uma nova caixa. Fator de aproximação: 1.7 aproximado.

- **First Fit (FF):** Coloca o item na primeira caixa em que cabe ou abre uma nova caixa. Fator de aproximação: 1.7 aproximado.


### :mobile_phone_off: Heurística Offline

- **Next Fit Decreasing (NFD):** Ordena os itens do maior para o menor e aplica o algoritmo Next Fit. Fator de aproximação: 1.691 aproximado.

- **Best Fit Decreasing (BFD):** Ordena os itens do maior para o menor e aplica o algoritmo Best Fit. Fator de aproximação: 1.222 aproximado.

- **First Fit Decreasing (FFD):** Ordena os itens do maior para o menor e aplica o algoritmo First Fit. Fator de aproximação: 1.222 aproximado.

- **Modified First Fit Decreasing (MFFD):** Divide os itens em classes de tamanho, coloca itens grandes primeiro e segue uma estratégia específica para cada classe. Fator de aproximação: 1.183 aproximado.

## :desktop_computer:	Implementação

Os algoritmos foram implementados em Java, e o código-fonte está disponível nesse repositório do GitHub. O programa permite que o usuário escolha algoritmos, altere o tamanho da caixa e execute o empacotamento. Ele também fornece funcionalidades adicionais, como geração de estatísticas e exportação de resultados para CSV. Há também um exemplo prático de uma aplicação real do problema do empacotamento, que consiste em otimizar da melhor maneira possível o tempo levado para assistir uma lista de filmes. 

Para fins de demonstração foi gerado uma lista de filmes da empresa marvel e a configuração escolhida foi a disponibilidade de 6 horas por dia para realizar essa tarefa. O resultado dessa simulação pode ser encontrado na pasta src/dataset/resultados/. Vale ressaltar, que o código permite que esse arquivo seja modificado e outros filmes possam ser adicionados ou removidos da lista, permitindo que seja aplicado para outras listas de filmes.

Como objetivo de automatizar a geração dos resultados, foi desenvolvido scripts em Python utilizando a biblioteca de geração de gráficos matplotlib para criar gráficos de barras empilhadas para representar visualmente a distribuição de itens em caixas para o problema de bin packing. Um modelo do gráfico gerado é possível ser visualizado abaixo:
![Modified_First_Fit_Decreasing](https://github.com/Caroline-Camargo/Bin_Packing_Problem/assets/88253809/9fde409b-b41a-42d1-bfb8-a56b24236845)

## :play_or_pause_button: Executando o Programa

Na pasta raiz do projeto (Bin_Packing_Problem) você pode executar o programa usando o seguinte comando no terminal, podendo passar o tamanho da caixa, código do algoritmo e o arquivo de entrada, conforme exemplo a seguir:

```bash
# Execução normal
$ java -jar ./dist/BinPackingProblem.jar

# Passando parâmetros
$ java -jar ./dist/BinPackingProblem.jar TamanhoCaixa CodAlgoritimo ArquivoEntrada

# Exemplo
$ java -jar ./dist/BinPackingProblem.jar 100 FFD dataSet1_FSU_tamanho100
