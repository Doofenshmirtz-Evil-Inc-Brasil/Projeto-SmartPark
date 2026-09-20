package br.edu.smartpark.servico;

import br.edu.smartpark.legado.ApiLegadaCancela;
import br.edu.smartpark.legado.ApiLegadaNotificacao;
import br.edu.smartpark.legado.GatewayLegadoPagamento;
import br.edu.smartpark.legado.ApiLegadaSensor;
import br.edu.smartpark.modelo.SessaoEstacionamento;
import br.edu.smartpark.modelo.VagaEstacionamento;
import br.edu.smartpark.modelo.Veiculo;
import br.edu.smartpark.repositorio.RepositorioEmMemoria;

public class ServicoEstacionamento {

    public final RepositorioEmMemoria<VagaEstacionamento> vagas =
            new RepositorioEmMemoria<>();

    public final RepositorioEmMemoria<Veiculo> veiculos =
            new RepositorioEmMemoria<>();

    public final RepositorioEmMemoria<SessaoEstacionamento> sessoes =
            new RepositorioEmMemoria<>();

    /*
     * Dependências diretas mantidas propositalmente.
     * Elas servem para análise de responsabilidades,
     * arquitetura e comunicação entre sistemas.
     */
    private final ApiLegadaSensor sensor =
            new ApiLegadaSensor();

    private final GatewayLegadoPagamento pagamento =
            new GatewayLegadoPagamento();

    private final ApiLegadaCancela cancela =
            new ApiLegadaCancela();

    private final ApiLegadaNotificacao notificacao =
            new ApiLegadaNotificacao();

    public VagaEstacionamento reservar(
            String placa,
            String tipo) {

        for (VagaEstacionamento vaga : vagas.todos()) {

            /*
             * Problema intencional:
             * ignora se a vaga já está ocupada,
             * reservada ou em conflito com o sensor.
             */
            if (tipo.equals(vaga.tipo)) {
                vaga.reservada = true;
                vaga.placaVeiculo = placa;
                return vaga;
            }
        }

        return null;
    }

    public SessaoEstacionamento registrarEntrada(
            String idSessao,
            String placa,
            String idVaga,
            String horarioEntrada) {

        Veiculo veiculo = veiculos.buscar(placa);
        VagaEstacionamento vaga = vagas.buscar(idVaga);

        if (veiculo == null || vaga == null) {
            return null;
        }

        /*
         * Problema intencional:
         * o retorno textual do sensor é interpretado
         * diretamente dentro do serviço.
         */
        String estadoSensor = sensor.ler(idVaga);

        System.out.println(
                "SENSOR=" + estadoSensor);

        /*
         * Problema intencional:
         * a cancela abre mesmo se houver conflito
         * entre reserva, ocupação e leitura do sensor.
         */
        cancela.abrir("ENTRY");

        vaga.ocupada = true;
        vaga.placaVeiculo = placa;

        SessaoEstacionamento sessao =
                new SessaoEstacionamento(
                        idSessao,
                        placa,
                        idVaga,
                        horarioEntrada);

        /*
         * Problema intencional:
         * não há validação explícita de sessão duplicada.
         */
        sessoes.salvar(idSessao, sessao);

        return sessao;
    }

    public void registrarSaida(
            String idSessao,
            String horarioSaida,
            String metodoPagamento) {

        SessaoEstacionamento sessao =
                sessoes.buscar(idSessao);

        if (sessao == null) {
            return;
        }

        Veiculo veiculo =
                veiculos.buscar(sessao.placaVeiculo);

        VagaEstacionamento vaga =
                vagas.buscar(sessao.idVaga);

        sessao.horarioSaida = horarioSaida;

        /*
         * Problema intencional:
         * a duração real não é calculada a partir
         * dos horários de entrada e saída.
         */
        long minutos = 95;

        sessao.valor =
                calcularValor(
                        veiculo == null
                                ? "CAR"
                                : veiculo.tipo,
                        minutos);

        /*
         * Problema intencional:
         * pagamento externo é chamado diretamente
         * e o protocolo textual é interpretado aqui.
         */
        String resultado =
                pagamento.pagar(
                        sessao.placaVeiculo,
                        sessao.valor,
                        metodoPagamento);

        sessao.situacao =
                resultado.startsWith("00;")
                        ? "PAID"
                        : "PAYMENT_ERROR";

        /*
         * Problema intencional:
         * a cancela de saída abre mesmo quando
         * o pagamento falha.
         */
        cancela.abrir("EXIT");

        if (vaga != null) {
            vaga.ocupada = false;

            /*
             * Problema intencional:
             * reserva e placa permanecem na vaga
             * depois da saída.
             */
        }

        /*
         * Problema intencional:
         * a notificação usa a placa como destinatário,
         * sem associação a canal de contato do usuário.
         */
        notificacao.enviar(
                sessao.placaVeiculo,
                "Saída registrada. Valor="
                        + sessao.valor
                        + " situacao="
                        + sessao.situacao);
    }

    public void sincronizarVaga(String idVaga) {

        VagaEstacionamento vaga = vagas.buscar(idVaga);

        if (vaga == null) {
            return;
        }

        String estado = sensor.ler(idVaga);

        /*
         * Problema intencional:
         * ausência de validação de contrato e
         * tratamento de falha do sensor.
         */
        vaga.ocupada =
                estado.contains("|OCCUPIED|");
    }

    private double calcularValor(
            String tipoVeiculo,
            long minutos) {

        /*
         * Regra simples mantida no serviço para permitir
         * análise de responsabilidade e qualidade.
         */
        double tarifaPorHora =
                "MOTORCYCLE".equals(tipoVeiculo)
                        ? 4.0
                        : 8.0;

        double horas =
                Math.ceil(minutos / 60.0);

        return horas * tarifaPorHora;
    }
}
