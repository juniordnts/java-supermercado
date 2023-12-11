
package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Venda {
    //@ spec_public 
    private Cliente cliente;
    //@ spec_public 
    private Caixa caixa;
    //@ spec_public 
    private EnumTipoDePagamento formaDePAgamento;
    //@ spec_public 
    private double valorVenda, troco;
    
    // @ requires caixa != null;
    // @ requires cliente != null;
    // @ ensures getCaixa() == caixa;
    // @ ensures getCliente() == cliente;
    public Venda(Caixa caixa, Cliente cliente){
        this.cliente = cliente;
        this.caixa = caixa;
    }
    
    // @ ensures \result != null;
    // @ ensures \result == formaDePAgamento;
    public EnumTipoDePagamento getFormaDePagamento() {
        return formaDePAgamento;
    }

    // @ requires formaDePAgamento != null;
    // @ ensures getFormaDePagamento() == formaDePAgamento;
    public void setFormaDePagamento(EnumTipoDePagamento formaDePAgamento) {
        this.formaDePAgamento = formaDePAgamento;
    }
    
    // @ ensures \result != null;
    // @ ensures \result == caixa;
    public Caixa getCaixa(){
        return this.caixa;
    }
    
    // @ ensures \result >= 0.0;
    // @ ensures \result == valorVenda;
    public double getValorVenda(){
        return this.valorVenda;
    }
    
    // @ ensures getValorVenda() == cliente.getCarrinho().calcularPrecoCarrinho();
    public void vender(){
        this.valorVenda = cliente.getCarrinho().calcularPrecoCarrinho();        
    }
    
    // @ ensures \result != null;
    public String dadosVenda(){
        String dados =  "\n"
                        + "*    Operador do Caixa: "+ caixa.getOperadorCaixa().getNome()+" *\n"
                        + "*    Forma de Pagamento: "+ this.formaDePAgamento+"\n"
                        + "*    Valor da venda: "+ String.format( "%.2f", this.valorVenda)+" \n"
                        + "*    Troco: "+ String.format( "%.2f", this.troco);
        return dados;
    }
    
    // @ ensures \result == true || \result == false;
    public boolean formaDePagamento(){
        Scanner scan = new Scanner(System.in);
        double dinheiro;
        vender();
        Utilitario.ImprimaMensagem("*           O VALOR DA SUA COMPRA É: R$ "+ String.format( "%.2f", getValorVenda()) +"            *");
        System.out.println("***********************************************************");
        System.out.println("*   ESCOLHA SUA FORMA DE PAGAMENTO!  \n ( 1 ) DINHEIRO  \n ( 2 ) CARTÃO                                           *");
        System.out.println("***********************************************************");
        int formaPagamento = scan.nextInt();
       
        if((int)EnumTipoDePagamento.CARTAO.ordinal() == formaPagamento-1){
            System.out.println("Compra realizada com sucesso!");
            this.formaDePAgamento = getFormaDePagamento().CARTAO;
            return true;
        }
        else if((int)EnumTipoDePagamento.DINHEIRO.ordinal() == formaPagamento-1){
            do{
                System.out.println("Entre com o valor em dinheiro: ");
                dinheiro = scan.nextDouble();

                if(dinheiro > getValorVenda()){
                    this.troco = dinheiro - getValorVenda();
                    Utilitario.ImprimaMensagem("Troco de: R$"+  String.format( "%.2f", this.troco) );
                    this.formaDePAgamento = getFormaDePagamento().DINHEIRO;
                    return true;
                }else{
                    System.out.println("Valor insuficiente!!!");
                }
            }while(dinheiro < getValorVenda());
        }
       return false;  
    }
}
