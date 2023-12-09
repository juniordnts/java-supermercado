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
public abstract class Produto {	
    private /*@ spec_public @*/ String nome;
    private /*@ spec_public @*/ String codigo;
    private /*@ spec_public @*/ double valor;
    
    //@ requires codigo != null && !codigo.isEmpty();
    //@ requires nome != null && !nome.isEmpty();
    //@ requires valor >= 0.0;
    //@ ensures this.valor == valor;
    //@ pure
    public Produto(String codigo, String nome, double valor) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
    }


    //@ requires nome != null && !nome.isEmpty();
    //@ assignable this.nome;
    //@ ensures this.nome.equals(nome);
    public void setNome(String nome) {
        this.nome = nome;
    }

    //@ requires codigo != null && !codigo.isEmpty();
    //@ assignable this.codigo;
    //@ ensures this.codigo.equals(codigo);
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    //@ requires valor >= 0.0;
    //@ assigns this.valor;
    //@ ensures this.valor == valor;
    public void setValor(double valor) {
    	this.valor = valor;
    }    
    
    //@ ensures \result == this.nome;
    public String getNome() {
    	return nome;
    }
    
    //@ ensures \result == this.codigo;
    public String getCodigo() {
    	return codigo;
    }

    //@ ensures \result == this.valor;    
    public double getValor() {
    	return valor;
    }

    //@ requires qtd >= 0.0;
    //@ ensures \result >= 0.0;
    public abstract double calcularValor(double qtd);
    
}
