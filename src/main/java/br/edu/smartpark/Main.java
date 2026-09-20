package br.edu.smartpark;

import br.edu.smartpark.modelo.VagaEstacionamento;
import br.edu.smartpark.modelo.Veiculo;
import br.edu.smartpark.servico.ServicoEstacionamento;

public class Main {

    public static void main(String[] args) {

        ServicoEstacionamento servico =
                new ServicoEstacionamento();

        servico.vagas.salvar(
                "A01",
                new VagaEstacionamento(
                        "A01",
                        "CAR"));

        servico.vagas.salvar(
                "M01",
                new VagaEstacionamento(
                        "M01",
                        "MOTORCYCLE"));

        servico.veiculos.salvar(
                "ABC1D23",
                new Veiculo(
                        "ABC1D23",
                        "CAR",
                        "Cliente Demo"));

        servico.reservar(
                "ABC1D23",
                "CAR");

        servico.registrarEntrada(
                "S1",
                "ABC1D23",
                "A01",
                "2026-08-10T19:00:00");

        servico.registrarSaida(
                "S1",
                "2026-08-10T20:35:00",
                "PIX");

        VagaEstacionamento vaga =
                servico.vagas.buscar("A01");

        System.out.println(
                "SITUACAO_FINAL="
                        + servico.sessoes
                                .buscar("S1")
                                .situacao);

        System.out.println(
                "VAGA_RESERVADA="
                        + vaga.reservada);

        System.out.println(
                "PLACA_NA_VAGA="
                        + vaga.placaVeiculo);
    }
}
