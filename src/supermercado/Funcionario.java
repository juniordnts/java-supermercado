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
public class Funcionario implements IOperacoesDoEstoque{
    /*@
      @ public invariant nome != null;
      @ public invariant userName != null;
      @ public invariant senha != null;
    @*/
    private /*@ spec_public @*/ String nome;
    private /*@ spec_public @*/ String userName;
    private /*@ spec_public @*/ String senha;


    //@ requires nome != null && !nome.isEmpty();
    //@ requires userName != null && !userName.isEmpty();
    //@ requires senha != null && !senha.isEmpty();
    //@ ensures this.nome.equals(nome) && this.userName.equals(userName) && this.senha.equals(senha);
    //@ pure
    public Funcionario(String nome, String userName, String senha) {
        this.nome = nome;
        this.userName = userName;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public void adicionarProduto(Produto produto, double quantidade) {
        //this.estoqueDeProdutos.adicionarProduto(produto, quantidade);
        EstoqueDeProdutos.adicionarProduto(produto, quantidade);
    }

    @Override
    public void removerProduto(String codigo, double quantidade) {
        EstoqueDeProdutos.removerProduto(codigo, quantidade);
    }

    @Override
    public void mostrarEstoque() {
        EstoqueDeProdutos.mostrarEstoque(1);
    }
}
