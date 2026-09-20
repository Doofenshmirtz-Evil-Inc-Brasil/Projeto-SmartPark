package br.edu.smartpark.modelo;
public class VagaEstacionamento {
    public String id;
    public String tipo;
    public boolean ocupada;
    public boolean reservada;
    public String placaVeiculo;
    public VagaEstacionamento(String id,String tipo){
        this.id=id;this.tipo=tipo;
    }
}
