# Resgate Espacial <img src="https://img.shields.io/badge/C%2B%2B-00599C?style=for-the-badge&logo=c%2B%2B&logoColor=white">

### 📌 Descrição

Este repositório contém a implementação de um algoritmo para simular o resgate de astronautas presos em diferentes módulos de uma estação espacial danificada. O objetivo do projeto é aplicar conceitos de **orientação a objetos** e algoritmos de **busca do menor caminho**, utilizando **listas dinâmicas** para armazenar informações dos astronautas resgatados.

Um **robô de resgate** percorre a estação espacial, resgatando astronautas e levando-os ao módulo de segurança. A estação é representada por uma matriz 2D, onde cada célula pode conter diferentes tipos de módulos:

- `S`: Módulo de segurança (ponto de partida e chegada do robô).
- `A`: Módulo com um astronauta (deve ser visitado antes de retornar ao `S`).
- `#`: Módulo inacessível (obstáculo).
- `~`: Módulo vazio (vácuo espacial, não pode ser atravessado).
- `F`: Módulo em chamas (perigo) – **não pode acessar espaços adjacentes**.
- `.`: Módulo seguro e transitável.

Além de realizar o resgate, o robô mantém uma lista dinâmica com os dados dos astronautas resgatados.

O programa deve processar múltiplos arquivos de entrada e gerar relatórios detalhados com as estatísticas da operação de resgate, além de exibir no terminal o caminho feito a cada astronauta.

### ▶ Como Executar

1. Clone este repositório:
   ```sh
   git clone https://github.com/PeedroHG/ResgateEspacial.git
   cd ResgateEspacial
   ```

2. Compile e rode o código:
   ```sh
   make run
   ```

### 🔗 Versão Anterior: Fuga Humana

Este projeto é uma evolução do trabalho anterior, **Fuga Humana**, que implementava um algoritmo de menor caminho em **C** com um contexto mais simples. Caso tenha interesse, você pode conferir a primeira versão aqui:

➡ **[Fuga Humana (Implementação em C)](https://github.com/PeedroHG/FugaHumana)**

### 📌 Próximos Passos

- Verificar a eficiência do menor caminho.
- Analisar o Problema do Caixeiro-Viajante.
