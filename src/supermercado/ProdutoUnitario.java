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
public class ProdutoUnitario extends Produto {

    //@ requires codigo != null && !codigo.isEmpty();
    //@ requires nome != null && !nome.isEmpty();
    //@ requires valor >= 0.0;
    public ProdutoUnitario(String codigo, String nome, double valor) {
        super(codigo, nome, valor);
    }

    /*@ public normal_behavior
      @ requires qtd >= 0;
      @ assignable \nothing;
      @ ensures \result >= 0;
    @*/
    @Override
    public double calcularValor(double qtd) {
        return (int)qtd * this.getValor();
    }
    
}
