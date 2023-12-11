
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Caixa {
    //@ spec_public 
    private ArrayList<Venda> vendas;
    //@ spec_public 
    private Funcionario operadorCaixa;
    //@ spec_public 
    private int numeroDoCaixa;
    //@ spec_public 
    private Balanca balanca;
    
    // @ requires numero >= 0;
    // @ requires operador != null;
    // @ ensures numeroDoCaixa == numero;
    // @ ensures operadorCaixa == operador;
    // @ ensures vendas != null;
    public Caixa(int numero, OperadorDeCaixa operador){
        this.numeroDoCaixa = numero;
        this.operadorCaixa = operador;
        this.balanca = new Balanca();
        vendas = new ArrayList<>();
    }    

    // @ requires numero >= 0;
    // @ ensures numeroDoCaixa == numero;
    // @ ensures vendas != null;
    public Caixa(int numero){
        this.numeroDoCaixa = numero;
        this.balanca = new Balanca();
        vendas = new ArrayList<>();
    } 
    
    //@ ensures (\forall int i; 0 <= i && i < vendas.size(); vendas.get(i) instanceof Venda);
    //@ ensures (\forall int i; 0 <= i && i < vendas.size(); ((Venda)vendas.get(i)).dadosVenda() != null);
    public void relatorioCaixa(){
        if (vendas.size() > 0) {
            Iterator it = vendas.iterator();
            while(it.hasNext()){
                Venda venda = (Venda) it.next();
                Utilitario.ImprimaMensagem(venda.dadosVenda());
            }
        }else{
            Utilitario.ImprimaMensagem("*           Não há vendas registradas nesse caixa              *");
        }
    }
    
    // @ ensures \result != null;
    // @ ensures \result.equals(vendas);
    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    // @ requires vendas != null;
    // @ ensures this.vendas == vendas;
    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }
    
    // @ ensures \result >= 0;
    public int getNumeroDoCaixa() {
        return numeroDoCaixa;
    }

    // @ ensures \result >= 0;
    // @ ensures this.numeroDoCaixa == numeroDoCaixa;
    public void setNumeroDoCaixa(int numeroDoCaixa) {
        this.numeroDoCaixa = numeroDoCaixa;
    }

    public Funcionario getOperadorCaixa() {
        return operadorCaixa;
    }

    // @ requires operadorCaixa != null;
    // @ ensures this.operadorCaixa == operadorCaixa;
    public void setOperadorCaixa(Funcionario  operadorCaixa) {
        this.operadorCaixa = operadorCaixa;
    }
    
    public void cancelarVenda(){
        
    }

    private double calcularValorPorItem(double valorDaUnidadeProduto, int quantidade){
        return balanca.calcularValorPorItem(valorDaUnidadeProduto, quantidade);
    }
    
    private double calcularValorPorPeso(double valorDoPeso, double quantidade){
        return balanca.calcularValorPorPeso(valorDoPeso, quantidade);
    }
    
    @Override
    public String toString() {
         return "Caixa " + numeroDoCaixa;        
    }
    
    public Venda iniciarVenda(Cliente cliente){
        Venda venda = new Venda(this, cliente);
        venda.vender();
        boolean pagamento = venda.formaDePagamento();
        if(pagamento){
             vendas.add(venda);
        }else{
            Utilitario.ImprimaMensagem("*  !!!! FALHA AO COMPRAR !!!!  *");
            return null;
        }
       
        return venda;
    }
}
