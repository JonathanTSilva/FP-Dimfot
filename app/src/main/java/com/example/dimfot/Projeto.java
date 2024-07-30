package com.example.dimfot;

public class Projeto {
    private String nomeProjeto, dataInicio, dataTermino, nomeCliente, endereco, bairro, cep, cidade,
            pais, latitude, longitude, responsavel, custoEq, consumoMes, consumoDia, hspMes,
            hspDia, rendimento, potPlaca, potTotPlaca, qtdPlaca, potGerada, potInvMin, potInvMax;

    Projeto (String nomeProjeto, String dataInicio, String dataTermino, String nomeCliente,
             String endereco, String bairro, String cep, String cidade, String pais, String latitude,
             String longitude, String responsavel, String custoEq, String consumoMes, String consumoDia,
             String hspMes, String hspDia, String rendimento, String potPlaca, String potTotPlaca,
             String qtdPlaca, String potGerada, String potInvMin, String potInvMax){
        this.nomeProjeto = nomeProjeto;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.pais = pais;
        this.latitude = latitude;
        this.longitude = longitude;
        this.responsavel = responsavel;
        this.custoEq = custoEq;
        this.consumoMes = consumoMes;
        this.consumoDia = consumoDia;
        this.hspMes = hspMes;
        this.hspDia = hspDia;
        this.rendimento = rendimento;
        this.potPlaca = potPlaca;
        this.potTotPlaca = potTotPlaca;
        this.qtdPlaca = qtdPlaca;
        this.potGerada = potGerada;
        this.potInvMin = potInvMin;
        this.potInvMax = potInvMax;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCustoEq() { return custoEq; }

    public void setCustoEq(String custoEq) { this.custoEq = custoEq; }

    public String getConsumoMes() { return consumoMes; }

    public void setConsumoMes(String consumoMes) { this.consumoMes = consumoMes; }

    public String getConsumoDia() { return consumoDia; }

    public void setConsumoDia(String conusumoDia) { this.consumoDia = conusumoDia; }

    public String getHspMes() { return hspMes; }

    public void setHspMes(String hspMes) { this.hspMes = hspMes; }

    public String getHspDia() { return hspDia; }

    public void setHspDia(String hspDia) { this.hspDia = hspDia; }

    public String getRendimento() { return rendimento; }

    public void setRendimento(String rendimento) { this.rendimento = rendimento; }

    public String getPotPlaca() { return potPlaca; }

    public void setPotPlaca(String potPlaca) { this.potPlaca = potPlaca; }

    public String getPotTotPlaca() { return potTotPlaca; }

    public void setPotTotPlaca(String potTotPlaca) { this.potTotPlaca = potTotPlaca; }

    public String getQtdPlaca() { return qtdPlaca; }

    public void setQtdPlaca(String qtdPlaca) { this.qtdPlaca = qtdPlaca; }

    public String getPotGerada() { return potGerada; }

    public void setPotGerada(String potGerada) { this.potGerada = potGerada; }

    public String getPotInvMin() { return potInvMin; }

    public void setPotInvMin(String potInvMin) { this.potInvMin = potInvMin; }

    public String getPotInvMax() { return potInvMax; }

    public void setPotInvMax(String potInvMax) { this.potInvMax = potInvMax; }
}
