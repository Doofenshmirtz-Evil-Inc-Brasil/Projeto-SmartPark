# Diagrama de contexto atual

```mermaid
flowchart LR
    Motorista[Motorista] --> SP[SmartPark]
    Operador[Operador] --> SP
    Administrador[Administrador] --> SP
    SP --> Sensor[Sensores]
    SP --> Pagamento[Gateway de pagamento]
    SP --> Cancela[Controle de cancelas]
    SP --> Notificacao[Notificações]
```
