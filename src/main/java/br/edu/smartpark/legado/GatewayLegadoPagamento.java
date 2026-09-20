package br.edu.smartpark.legado;
public class GatewayLegadoPagamento {
    public String pagar(String placa,double valor,String metodo){
        return valor >= 0 ? "00;APPROVED;"+metodo : "99;ERROR";
    }
}
