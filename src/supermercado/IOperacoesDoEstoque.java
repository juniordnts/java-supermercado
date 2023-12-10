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
interface IOperacoesDoEstoque {

    //@ requires produto != null && quantidade >= 0.0;
    public void adicionarProduto(Produto produto, double quantidade);

    //@ requires codigo != null && quantidade >= 1;
    public void removerProduto(String codigo, double quantidade);
    
    public void mostrarEstoque();
}
