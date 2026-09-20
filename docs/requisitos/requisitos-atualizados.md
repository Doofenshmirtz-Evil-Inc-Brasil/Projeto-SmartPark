# Requisitos atualizados — SmartPark

## Funcionais
- RF01 — cadastrar e consultar vagas e veículos.
- RF02 — reservar uma vaga compatível com o tipo de veículo.
- RF03 — registrar entrada e saída de veículos.
- RF04 — consultar o estado do sensor associado à vaga.
- RF05 — calcular o valor da sessão de estacionamento.
- RF06 — processar pagamento da sessão.
- RF07 — controlar a abertura de cancelas.
- RF08 — notificar eventos relevantes do estacionamento.

## Pontos que precisam de critérios de aceitação
- impedir reserva de vaga ocupada ou já reservada;
- definir o comportamento quando sensor e estado interno divergirem;
- calcular duração usando os horários reais da sessão;
- definir se a cancela de saída pode abrir após falha de pagamento;
- limpar corretamente reserva e placa ao liberar a vaga.

Esses pontos devem ser tratados pelas atividades sem alterar o arquivo `requisitos-iniciais.md`.
