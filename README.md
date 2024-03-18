# ADA LocateCar - Locadora de veículos

Este projeto implementa um sistema de aluguel de carros em Java. As principais funcionalidades incluem:

    * Alugar um carro por placa e documento do cliente (CPF ou CNPJ)
    * Devolver um carro pela placa e registrar a data de devolução
    * Calcular o custo do aluguel com base no tipo de carro e na duração do aluguel
    * Cadastro, busca e atualização de clientes e veículos

### O código segue vários princípios importantes de programação orientada a objetos:

### Princípios SOLID

    * SRP (Single Responsibility Principle)
    
        Classe Veiculo:
            Responsabilidade única: Armazenar e fornecer informações sobre o veículo.
    
        Classe Pessoa:
            Responsa'bilidade única: Armazenar e fornecer informações sobre a pessoa.

    * Genéricos
    
        Classe CalculadoraDesconto:
            Usa genéricos para calcular o desconto para qualquer tipo de veículo.
            Permite maior flexibilidade e reutilização de código.
    
      * Comparable
    
          Classe Veiculo:
              Implementa a interface Comparable para permitir a ordenação por placa.
              Permite a ordenação eficiente da lista de veículos.

### Facilidade de implementação

    Interfaces e Genéricos foram relativamente fáceis de implementar. 

    Collections como HashMap eram familiares e fáceis de usar para gerenciar dados de veículo e pessoa.

    A interface Comparable também foi simples de implementar, permitindo a ordenação de veículos.

### Dificuldades encontradas

    Os princípios SOLID precisou de tempo para implementação.
