# Sistemas externos

| Sistema | Responsabilidade | Evidência |
|---|---|---|
| Sensor de vaga | informar estado físico da vaga | `ApiLegadaSensor` |
| Gateway de pagamento | processar cobrança | `GatewayLegadoPagamento` |
| Controlador de cancela | abrir entrada/saída | `ApiLegadaCancela` |
| Notificação | enviar mensagem ao usuário/operação | `ApiLegadaNotificacao` |

No estado inicial, essas integrações são chamadas diretamente pelo serviço principal. Esse acoplamento é proposital e deverá ser analisado nas aulas correspondentes.
