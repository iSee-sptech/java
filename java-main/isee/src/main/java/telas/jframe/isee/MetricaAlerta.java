package telas.jframe.isee;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class MetricaAlerta {
    private Double ramAmarelo;
    private Double ramVermelho;
    private Double cpuAmarelo;
    private Double cpuVermelho;
    private Double discoAmarelo;
    private Double discoVermelho;
    
    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public MetricaAlerta(Double ramAmarelo, Double ramVermelho, Double cpuAmarelo, Double cpuVermelho, Double discoAmarelo, Double discoVermelho) {
        this.ramAmarelo = ramAmarelo;
        this.ramVermelho = ramVermelho;
        this.cpuAmarelo = cpuAmarelo;
        this.cpuVermelho = cpuVermelho;
        this.discoAmarelo = discoAmarelo;
        this.discoVermelho = discoVermelho;
    }

    public Double getRamAmarelo() {
        return ramAmarelo;
    }

    public Double getRamVermelho() {
        return ramVermelho;
    }

    public Double getCpuAmarelo() {
        return cpuAmarelo;
    }

    public Double getCpuVermelho() {
        return cpuVermelho;
    }

    public Double getDiscoAmarelo() {
        return discoAmarelo;
    }

    public Double getDiscoVermelho() {
        return discoVermelho;
    }
    
    
}