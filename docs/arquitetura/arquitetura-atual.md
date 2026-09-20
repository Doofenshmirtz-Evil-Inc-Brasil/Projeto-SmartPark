# Arquitetura atual - estado inicial

O SmartPark inicia com uma estrutura simples e propositalmente concentrada.

## Organização

- `Main`: monta e executa um cenário mínimo.
- `model`: representa veículo, vaga e sessão.
- `repository`: armazenamento em memória.
- `ServicoEstacionamento`: concentra reserva, entrada, saída, cálculo, integração e sincronização.
- `legacy`: simula sensores, pagamento, cancela e notificação.

## Problemas intencionais

1. `ServicoEstacionamento` possui responsabilidades demais.
2. As integrações externas são instanciadas diretamente.
3. A reserva ignora vaga ocupada/reservada e estado do sensor.
4. A entrada abre a cancela mesmo diante de conflito.
5. Não há validação explícita de sessão duplicada.
6. A duração da permanência é fixa em vez de calculada.
7. A saída abre a cancela mesmo quando o pagamento falha.
8. Reserva e placa permanecem associadas após a saída.
9. O retorno textual de APIs legadas é interpretado dentro do serviço.
10. Não há tratamento adequado de indisponibilidade das integrações.

Esses problemas devem ser evoluídos somente conforme as atividades das aulas.
