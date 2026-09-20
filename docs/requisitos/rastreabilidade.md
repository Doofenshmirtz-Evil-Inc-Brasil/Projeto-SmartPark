# Rastreabilidade inicial

| Requisito | Evidência inicial | Modelo |
|---|---|---|
| Manter veículos | `veiculos` | classes |
| Manter vagas | `vagas` | classes |
| Reservar vaga | `reservar` | classes/componentes |
| Registrar entrada | `registrarEntrada` | sequência/componentes |
| Processar pagamento | `registrarSaida` / `GatewayLegadoPagamento` | sequência/componentes |
| Controlar cancela | `ApiLegadaCancela` | sequência/componentes |
| Sincronizar vaga | `sincronizarVaga` / `ApiLegadaSensor` | componentes |
| Notificar usuário | `ApiLegadaNotificacao` | componentes |

Atualize a rastreabilidade conforme requisitos, código, modelos e decisões forem evoluídos.
