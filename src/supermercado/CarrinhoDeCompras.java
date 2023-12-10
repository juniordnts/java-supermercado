

package supermercado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static supermercado.EstoqueDeProdutos.estoque;

public class CarrinhoDeCompras{

    //@ spec_public 
    private Map<String, List<Produto>> produtosCarrinho;
    private /*@ spec_public @*/ double valorCompra;
    
    //@ ensures produtosCarrinho != null && valorCompra == 0.0;
    //@ ensures produtosCarrinho instanceof LinkedHashMap;
    //@ pure
    public CarrinhoDeCompras(){
        produtosCarrinho = new LinkedHashMap<String, List<Produto>>();
        valorCompra = 0;
    }
    
    //@ ensures \result != null;
    //@ ensures \result.getClass() == LinkedHashMap.class;
    //@ ensures \result.equals(produtosCarrinho);
    public Map getProdutosCarrinho(){
        return this.produtosCarrinho;
    }
    
    //@ ensures \result == valorCompra;
    //@ ensures \result == valorCompra;
    //@ ensures \result >= 0.0;
    //@ pure
    public double getValorCompra(){
        return this.valorCompra;
    }
    
    //@ requires valor >= 0.0;
    //@ assignable valorCompra;
    //@ ensures valorCompra == valor;
    public void setValorCompra(double valor){
        this.valorCompra = valor;
    }
    
    
    //@ requires produto != null;
    //@ requires 0 < produto.getValor();
    //@ requires 1 <= quantidade;
    //@ assignable produtosCarrinho;
    //@ ensures produtosCarrinho.containsKey(produto.getCodigo()) ?
    //@ produtosCarrinho.get(produto.getCodigo()).contains(produto) :
    //@ produtosCarrinho.get(produto.getCodigo()).size() == 1 &&
    //@ produtosCarrinho.get(produto.getCodigo()).get(0).getNome().equals(produto.getNome());
    public void addProduto(Produto produto, double quantidade){
        List<Produto> produtosDoCodigo;
        String codigo = produto.getCodigo();
        
        if(produtosCarrinho.containsKey(codigo)){
            produtosDoCodigo = produtosCarrinho.get(codigo);
            
            if (produtosDoCodigo.get(0).getNome().equals(produto.getNome())){
                if (produto instanceof ProdutoUnitario) {
                    while (quantidade > 0) {                
                        produtosDoCodigo.add(produto);
                        quantidade--;
                    }
                }
                else if (produto instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)produtosDoCodigo.get(0);
                    pdt.setQtdQuilos(pdt.getQtdQuilos() +  quantidade);
                    produtosDoCodigo = new LinkedList<Produto>();
                    produtosDoCodigo.add(pdt);
                }

                produtosCarrinho.put(codigo, produtosDoCodigo);
            }
            else{
                System.out.println("ATENÇÃO\tATENÇÃO\tATENÇÃO\tATENÇÃO\tATENÇÃO");
                System.out.println("Produto não foi adicionado pois o codigo '" + produto.getCodigo() + "' possui apenas produtos '"
                +produtosDoCodigo.get(0).getNome() + "' e voce esta tentando adicionar '" + produto.getNome() + "'");
            }
        }else{
            produtosDoCodigo = new LinkedList<Produto>();
            
            if (produto instanceof ProdutoUnitario) {
                while (quantidade > 0) {                
                    produtosDoCodigo.add(produto);
                    quantidade--;
                }
            } 
            else if (produto instanceof ProdutoQuilo) {
                ((ProdutoQuilo) produto).setQtdQuilos(quantidade);
                produtosDoCodigo.add(produto);
            }
            
            produtosCarrinho.put(codigo, produtosDoCodigo);
        }
    }
    
    //@ requires produtosCarrinho != null;
    //@ ensures produtosCarrinho.isEmpty();
    //@ ensures (\forall String codigo; !produtosCarrinho.containsKey(codigo));
    //@ ensures (\forall Produto produto_devolucao; 
    //@          (\exists List produtos; produtosCarrinho.values().contains(produtos) &&
    //@           produtos.contains(produto_devolucao)) ==> 
    //@           ((produto_devolucao instanceof ProdutoUnitario ||
    //@            (produto_devolucao instanceof ProdutoQuilo))));
    //@ ensure produtosCarrinho.size() == 0;
    public void devolverProdutosCarrinho(){
        if(this.produtosCarrinho.size() > 0){
            String codigo;
            int quantidade;
            Iterator it = produtosCarrinho.keySet().iterator();

            //@ loop_invariant produtosCarrinho.keySet().contains(codigo);
            //@ loop_invariant quantidade >= 0;
            //@ loop_invariant codigo != null;
            //@ loop_invariant produtosCarrinho.get(codigo) != null;
            //@ loop_invariant produtosCarrinho.get(codigo).size() >= 0;
            //@ decreases produtosCarrinho.size();
            while(it.hasNext()){
                codigo = (String) it.next();
                List produtos = produtosCarrinho.get(codigo);
                quantidade = produtos.size();
                Produto produto_devolucao = (Produto)produtos.get(0);

                if(produto_devolucao instanceof ProdutoUnitario){
                    EstoqueDeProdutos.adicionarProduto(produto_devolucao, quantidade);
                }
                if(produto_devolucao instanceof ProdutoQuilo){
                    ProdutoQuilo prodKg = EstoqueDeProdutos.ObtenhaProdutoQuiloTemporario(produto_devolucao);
                    double kilos = prodKg.getQtdQuilos();
                    EstoqueDeProdutos.adicionarProduto(prodKg, kilos);
                }
            }
            this.produtosCarrinho.clear();
            Utilitario.ImprimaMensagem("*   COMPRA CANCELADA, VOCÊ NÃO POSSUI MAIS PRODUTOS NO SEU CARRINHO!   *");
            exibirCarrinhoCliente();
        }
    }
    
    //@ ensures produtosCarrinho != null;
    //@ ensures (\forall String codigo; !produtosCarrinho.containsKey(codigo));
    public void exibirCarrinhoCliente(){
        Produto p = null;
        
        Iterator it = produtosCarrinho.keySet().iterator();
        if(produtosCarrinho.size() > 0){
            System.out.println("***** PRODUTOS NO CARRINHO *****");
            int quantidade = 0;
            double quilos = 0;
            while (it.hasNext()) {
                String codigo = (String)it.next();
                Iterator produtos = produtosCarrinho.get(codigo).iterator();
                boolean mostrarNomeProduto = true;
                List prodUnidade = (List) produtosCarrinho.get(codigo);
                while (produtos.hasNext()) {
                    p = (Produto)produtos.next();
                    quantidade++;
                    if (mostrarNomeProduto) {
                        System.out.println("Código: " + codigo);
                        System.out.println("Produto: " + p.getNome());
                        mostrarNomeProduto = false;
                    }
                }
                if (p instanceof ProdutoQuilo) {
                    ProdutoQuilo pdt = (ProdutoQuilo)p;
                    System.out.println("Quilos: " + pdt.getQtdQuilos() + "kg\n");
                }
                if (p instanceof ProdutoUnitario) {
                    System.out.println("Quantidade no carrinho = " + quantidade + "\n");
                }
                quantidade = 0;
            }
        }else{
            System.out.println("*             Você não possui produtos no carrinho              *");
        }
        Utilitario.Continuar();
    }
    
    //@ requires produtosCarrinho != null;
    //@ ensures \result == (produtosCarrinho.size() > 0);
    public boolean verificaCarrinho(){
        return this.produtosCarrinho.size() > 0 ? true : false;
    }
    
    //@ requires produtosCarrinho != null;
    //@ ensures \result >= 0;
    public double calcularPrecoCarrinho(){
        
        // Calcular o valor total da compra usando o somatorio de:
        // - calcularValorPorItem
        // - calcularValorPorPeso
        // Calcular troco do cliente se pagar em $
        double valorTotal = 0;
        Iterator itMap = produtosCarrinho.keySet().iterator();
        List<Produto> list;
        int quantidade = 0;
        double valorPeso = 0;
        double ktdKilo = 0;
        while(itMap.hasNext()){
            String codigo = (String) itMap.next();
            Iterator produtos = this.produtosCarrinho.get(codigo).iterator();
            list = (List) produtosCarrinho.get(codigo);
           
            while(produtos.hasNext()){
                Produto produtoList = (Produto) produtos.next();

                //Fazer a variavel "valorTotal" receber o valor do calculo por kilo
                if(produtoList instanceof ProdutoQuilo){
                    //Pega a quantidade de kilos e o valor do peso para que a balança possa calcular
                    ProdutoQuilo produtokg = (ProdutoQuilo) list.get(0);
                    valorPeso = produtokg.getValor();
                    ktdKilo = produtokg.getQtdQuilos();
                    valorTotal += Balanca.calcularValorPorPeso(valorPeso,ktdKilo);
                }
                //Fazer a variavel "valorTotal" receber o valor do calculo por Unidade
                else if(produtoList instanceof ProdutoUnitario){
                    //QUAL A MELHOR FORMA DE CHAMAR O MÉTODO calcularValorPorUnidade chamar da balança ou do caixa???
                     quantidade = list.size();
                     ProdutoUnitario produtounit = (ProdutoUnitario) list.get(0);
                     valorTotal += Balanca.calcularValorPorItem(produtounit.getValor(), quantidade);
                } 
           }
       }
       return valorTotal;
    }

    //@ ensures /result == this.valor    
    //@ pure
    public double calcularValorCompra(){  
        return this.getValorCompra();
    }
    
}
