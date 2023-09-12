# Primeiramente importamos as libs
import numpy as np
import matplotlib.pyplot as plt
# Gráfico sobre notas de 3 alunos nas provas do semestre
label = ["Data Set Simples", "Data Set 1", "Data Set 3"]
next_fit = [66.66, 75, 74.75]
first_fit = [66.66, 75, 74.75]
best_fit = [100, 75, 74.75]
best_fit_decreasing = [66.66, 75, 74.75]
next_fit_decreasing = [66.66, 75, 74.75]
first_fit_decreasing = [100, 75, 74.75]
modified_first_fit_decreasing = [100, 75, 74.75]
brute_force = [100, 75, 99.66]
 

# Definindo a largura das barras
barWidth = 0.05

# Aumentando o gráfico
plt.figure(figsize=(10,6))

# Definindo a posição das barras
r1 = np.arange(len(next_fit))
r2 = [x + barWidth for x in r1]
r3 = [x + barWidth for x in r2]
r4 = [x + barWidth for x in r3]
r5 = [x + barWidth for x in r4]
r6 = [x + barWidth for x in r5]
r7 = [x + barWidth for x in r6]
r8 = [x + barWidth for x in r7]
 
# Criando as barras
plt.bar(r1, next_fit, color='#78DE71', width=barWidth, label='Next Fit')
plt.bar(r2, first_fit, color='#34BF9F', width=barWidth, label='First Fit')
plt.bar(r3, best_fit, color='#049C2A', width=barWidth, label='Best Fit')
plt.bar(r4, best_fit_decreasing, color='#3A438A', width=barWidth, label='Best Fit Decreasing')
plt.bar(r5, next_fit_decreasing, color='#9763FF', width=barWidth, label='First Fit Decreasing')
plt.bar(r6, first_fit_decreasing, color='#471D5E', width=barWidth, label='First Fit Decreasing')
plt.bar(r7, modified_first_fit_decreasing, color='#2B0CAA', width=barWidth, label='Modified First Fit Decreasing')
plt.bar(r8, brute_force, color='#31E5EF', width=barWidth, label='Brute Force')
 
# Adiciando legendas as barras
plt.xlabel('Dataset')
plt.xticks([r + barWidth for r in range(len(next_fit))], label)
plt.ylabel('Uso Geral Caixas Na Melhor Solução')
plt.title('Uso Geral das Caixas Por Algoritmo Na Melhor Solução')
 
# Criando a legenda e exibindo o gráfico
plt.legend()

plt.savefig(f'src/Uso Geral das Caixas Por Algoritmo Na Melhor Solução')
plt.show()