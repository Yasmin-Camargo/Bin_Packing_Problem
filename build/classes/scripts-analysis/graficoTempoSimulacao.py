import matplotlib.pyplot as plt

# Dados
algoritmos = ["9", "14", "10", "33", "45", "117", "122", "277"]
next_fit = [1393, 1900, 1181, 2905, 7138, 6635, 6486, 10585]
first_fit = [2579, 3922, 3263, 5629, 15800, 26384, 25836, 101190]
best_fit = [4108, 6130, 4620, 10643, 26961, 43671, 42431, 187285]
best_fit_decreasing = [5965, 8568, 6556, 17082, 35973, 60347, 59331, 286480]
next_fit_decreasing = [6606, 9851, 7297, 18825, 38394, 63239, 61818, 294298]
first_fit_decreasing = [8607, 11417, 8159, 21178, 46007, 81156, 81607, 406802]
modified_first_fit_decreasing = [10798, 15920, 10473, 30376, 61726, 107562, 110022, 505710]

# Convertendo valores de ns para ms
next_fit = [x / 1000000 for x in next_fit]
first_fit = [x / 1000000 for x in first_fit]
best_fit = [x / 1000000 for x in best_fit]
best_fit_decreasing = [x / 1000000 for x in best_fit_decreasing]
next_fit_decreasing = [x / 1000000 for x in next_fit_decreasing]
first_fit_decreasing = [x / 1000000 for x in first_fit_decreasing]
modified_first_fit_decreasing = [x / 1000000 for x in modified_first_fit_decreasing]

# Configurações do gráfico
plt.figure(figsize=(10, 6))
plt.title("Tempo de Simulação de Algoritmos Aproximativos")
plt.xlabel("Quantidade de itens")
plt.ylabel("Tempo de Simulação (ms)")

# Adiciona linhas de dados para cada conjunto de valores
plt.plot(algoritmos, next_fit, marker='o', label="Next Fit")
plt.plot(algoritmos, first_fit, marker='o', label="First Fit")
plt.plot(algoritmos, best_fit, marker='o', label="Best Fit")
plt.plot(algoritmos, best_fit_decreasing, marker='o', label="Best Fit Decreasing")
plt.plot(algoritmos, next_fit_decreasing, marker='o', label="Next Fit Decreasing")
plt.plot(algoritmos, first_fit_decreasing, marker='o', label="First Fit Decreasing")
plt.plot(algoritmos, modified_first_fit_decreasing, marker='o', label="Modified First Fit Decreasing")

# Adiciona legenda
plt.legend()

# Exibe o gráfico
plt.grid()
plt.tight_layout()
plt.savefig(f'graficos/Tempo de Simulação de Algoritmos Aproximativos')
plt.show()
