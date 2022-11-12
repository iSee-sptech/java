package telas.jframe.isee;



import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;

public class funcao {

    Sistema funcaoSistema = new Sistema();
    DiscoGrupo funcaoDiscoGrupo = new DiscoGrupo();
    Memoria funcaoMemoria = new Memoria();
    Processador funcaoProcessador = new Processador();
    Looca funcaoLooca = new Looca();

    public String getOP() {
        return funcaoSistema.getSistemaOperacional();
    }

    ;

    
    public String getFabricante() {
        return funcaoSistema.getFabricante();
    }

    ;
 
    public Integer getArquitetura() {
        return funcaoSistema.getArquitetura();
    }

    ;
  
  
    public Long getTempoDeAtividade() {
        return funcaoSistema.getTempoDeAtividade();
    }

    ;

   
    // tb Maquina
    public Long getTamanhoTotal() {
        return funcaoDiscoGrupo.getTamanhoTotal();
    }

    public Long getTotal() {
        return funcaoMemoria.getTotal();
    }

    public Long getFrequencia() {
        return funcaoProcessador.getFrequencia();
    }

    // tb Historico
    public Long getTempoDeTransferencia() {
        return funcaoLooca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
    }

    public Long getDisponivel() {
        return funcaoMemoria.getDisponivel();
    }

    public Double getUso() {
        return funcaoProcessador.getUso();
    }
    
    public Long getEmUso(){
        return funcaoMemoria.getEmUso();
        
    }
    

}
