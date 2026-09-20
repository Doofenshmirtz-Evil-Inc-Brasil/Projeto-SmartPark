package br.edu.smartpark.modelo;
public class SessaoEstacionamento {
    public String id;
    public String placaVeiculo;
    public String idVaga;
    public String horarioEntrada;
    public String horarioSaida;
    public String situacao="OPEN";
    public double valor;
    public SessaoEstacionamento(String id,String placaVeiculo,String idVaga,String horarioEntrada){
        this.id=id;this.placaVeiculo=placaVeiculo;this.idVaga=idVaga;this.horarioEntrada=horarioEntrada;
    }
}
