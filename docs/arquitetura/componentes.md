# Componentes observáveis no estado inicial

| Parte | Responsabilidade atual | Evidência |
|---|---|---|
| Aplicação | iniciar cenário | `Main` |
| Serviço principal | coordenar reserva, entrada, saída e sincronização | `ServicoEstacionamento` |
| Modelo | representar veículo, vaga e sessão | `model/*` |
| Persistência | armazenar entidades em memória | `RepositorioEmMemoria` |
| Sensor externo | informar ocupação | `ApiLegadaSensor` |
| Pagamento externo | processar cobrança | `GatewayLegadoPagamento` |
| Cancela externa | controlar entrada/saída | `ApiLegadaCancela` |
| Notificação externa | enviar mensagens | `ApiLegadaNotificacao` |

Na Aula 05, a equipe deverá revisar essas fronteiras e produzir sua própria representação de componentes, conectores e configuração.
