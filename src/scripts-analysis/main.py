# Script em Python para criar gráficos de barras empilhadas para o problema bin-packing

import os
import matplotlib.pyplot as plt
import numpy as np

# Pasta que contém os arquivos de dados
pasta_arquivos = '../dataSet/resultados'

# Variáveis 
dados_eixoX = []  # Armazena os dados do eixo X (conteudo de cada caixa)
algoritmo = ''     # Armazena o nome do algoritmo
capacidade_maxima = 0  # Armazena a capacidade máxima
experimento = ''   # Armazena o nome do experimento
cores = ['#00E8AA', '#72FFAF']  # Lista de cores para as barras empilhadas

# Obtém a lista de nomes de arquivos na pasta
todos_arquivos = os.listdir(pasta_arquivos)

# Loop pelos arquivos na pasta
for nome_arquivo in todos_arquivos:
    arquivo = open(f"{pasta_arquivos}/{nome_arquivo}", "r")
    cont_linha = 0
    
    # Loop pelas linhas do arquivo
    for linha in arquivo:
        linha = linha.replace("\n", "").split(";")
        if cont_linha == 1:
            algoritmo = linha[0]
            capacidade_maxima = int(linha[1])
            experimento = linha[2].replace(" ", "")
        elif cont_linha > 3:
            dados_eixoX.append([int(valor) for valor in linha])  # Converte para inteiro
        cont_linha += 1

    # Gráfico de barras empilhadas
    cont_linha = 1
    for caixa in dados_eixoX:
        temp = [0]
        tempSoma = 0
        
        # Loop para calcular as alturas das barras empilhadas
        for i in range(len(caixa) - 1):
            temp.append(tempSoma + caixa[i])
            tempSoma += caixa[i]
        
        # Criação das barras empilhadas
        plt.bar(f'{cont_linha}', caixa, color=cores, bottom=temp, edgecolor='black')
        
        # Adiciona rótulos aos valores nas barras
        for i, valor in enumerate(caixa):
            plt.text(cont_linha - 1, sum(caixa[:i]), str(valor), ha='center', va='bottom')
        
        cont_linha += 1

    # Adiciona uma linha horizontal vermelha para indicar limite máximo da caixa
    plt.axhline(y=capacidade_maxima, color='red', linestyle='--', label='Linha em y=8')

    # Configurações do gráfico
    plt.title(f'{algoritmo} - Arquivo {experimento}')
    plt.ylabel('Capacidade')
    plt.xlabel('Número da caixa')
    plt.ylim(0, capacidade_maxima + 1)  # Define o intervalo do eixo Y (mínimo e máximo)
    plt.savefig(f'graficos/{algoritmo} - Arquivo {experimento}')
    plt.show()
