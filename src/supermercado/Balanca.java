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
public class Balanca{
    
    //@ requires pesoDoProduto > 0;
    //@ requires quantidade > 0;
    //@ ensures \result == pesoDoProduto * quantidade;
    //@ ensures \result >= 0.0;
    //@ pure
    public static double calcularValorPorPeso(double pesoDoProduto, double quantidade){
        return pesoDoProduto*quantidade;
    }
    
    //@ requires valorDoProduto > 0;
    //@ requires quantidade > 0;
    //@ ensures \result == valorDoProduto * quantidade;
    //@ ensures \result >= 0.0;
    //@ pure
    public static double calcularValorPorItem(double valorDoProduto, int quantidade){
        return valorDoProduto*quantidade;
    }
}
