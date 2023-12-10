/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

/**
 *
 * @author Richiely Batista, Filipe Maciel
 */
public class ProdutoQuilo extends Produto{

    //@ public invariant qtdQuilos >= 0.0;
    private /*@ spec_public @*/ double qtdQuilos;
    
    /*@
      @ requires codigo != null && nome != null && valor >= 0.0 && qtdQuilos >= 0.0;
      @ ensures this.qtdQuilos == qtdQuilos;
    @*/
    public ProdutoQuilo(String codigo, String nome, double valor, double qtdQuilos) {
        super(codigo, nome, valor);
        this.qtdQuilos = qtdQuilos;
    }

    //@ ensures \result >= 0.0;
    //@ ensures \result == this.qtdQuilos;
    public double getQtdQuilos() {
        return qtdQuilos;
    }

    /*@
      @ requires qtdQuilos >= 0.0;
      @ ensures this.qtdQuilos == (qtdQuilos < 0.0 ? 0.0 : qtdQuilos);
    @*/
    public void setQtdQuilos(double qtdQuilos) {
        if (qtdQuilos < 0) {
            this.qtdQuilos = 0;
        }
        else{
            this.qtdQuilos = qtdQuilos;
        }
    }
    

    //@ requires quilos >= 0.0;
    //@ ensures \result >= 0.0;
    @Override
    public double calcularValor(double quilos){
        return quilos * this.getValor();
    }
    
}
