
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
public class MetricasAlerta {
    private Integer ramAmarelo;
    private Integer ramVermelho;
    private Double cpuAmarelo;
    private Double cpuVermelho;
    private Integer discoAmarelo;
    private Integer discoVermelho;
    
    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public MetricasAlerta(Integer ramAmarelo, Integer ramVermelho, Double cpuAmarelo, Double cpuVermelho, Integer discoAmarelo, Integer discoVermelho) {
        this.ramAmarelo = ramAmarelo;
        this.ramVermelho = ramVermelho;
        this.cpuAmarelo = cpuAmarelo;
        this.cpuVermelho = cpuVermelho;
        this.discoAmarelo = discoAmarelo;
        this.discoVermelho = discoVermelho;
    }

    public Integer getRamAmarelo() {
        return ramAmarelo;
    }

    public Integer getRamVermelho() {
        return ramVermelho;
    }

    public Double getCpuAmarelo() {
        return cpuAmarelo;
    }

    public Double getCpuVermelho() {
        return cpuVermelho;
    }

    public Integer getDiscoAmarelo() {
        return discoAmarelo;
    }

    public Integer getDiscoVermelho() {
        return discoVermelho;
    }
    
    
}
