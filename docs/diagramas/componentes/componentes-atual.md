# Diagrama de componentes - estado inicial

```mermaid
classDiagram

    namespace Camada_Apresentacao {
        class UI_EntradaSaidaComponent {
            <<component>>
        }
    }

    namespace Camada_Negocio {
        class MovimentacaoServiceComponent {
            <<component>>
        }
        class IMovimentacaoService {
            <<interface>>
        }
    }

    namespace Camada_Dados {
        class MovimentacaoRepositoryComponent {
            <<component>>
        }
        class IMovimentacaoRepository {
            <<interface>>
        }
    }

    namespace Banco_de_Dados {
        class DatabaseComponent {
            <<database>>
        }
    }

    UI_EntradaSaidaComponent ..> IMovimentacaoService : usa
    IMovimentacaoService <|.. MovimentacaoServiceComponent : implementa
    MovimentacaoServiceComponent ..> IMovimentacaoRepository : usa
    IMovimentacaoRepository <|.. MovimentacaoRepositoryComponent : implementa
    MovimentacaoRepositoryComponent ..> DatabaseComponent : persiste
```
A estrutura evidencia a centralização inicial no serviço. Na Aula 05, a equipe deverá revisar componentes, conectores e configuração conforme o código evoluído.
