package iifes;

import java.util.ArrayList;

public class Pedido implements Comparable<Pedido> {
    /**
     * Classe com as rotinas do pedido
     */

    private String cod;
    private Aluno cliente, entregador;
    private Sala s;
    private ArrayList<Item> carrinho;
    private boolean entregue;


    /**
     * Contrutor da classe Pedido
     * @param cod Código do Pedido
     * @param c Aluno que fez o Pedido
     * @param e Aluno que entrega o Pedido
     * @param s Sala que Pedido será entregue
     */
    public Pedido(String cod, Aluno c, Aluno e, Sala s, ArrayList<Item> carrinho) {
        this.cod = cod;
        this.cliente = c;
        this.entregador = e;
        this.s = s;
        this.carrinho = carrinho;

        this.entregue = false;
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        String msg = "Código do Pedido: " + this.cod +
                "\nProdutos:\n";

        for (Item item : this.carrinho) {
            msg += "  " + item + "\n";
        }

        if (this.entregue) msg += "Status: Entregue";
        else msg += "Status: Em aberto";

        msg += "\nValor total: R$ " + String.format("%.2f", this.valorTotal()) + "\n";

        return msg;
    }

    /**
     * Define o entregador do Pedido
     * @param a Objeto da classe Aluno
     */
    public void atribuirEntregador(Aluno a) {
        this.entregador = a;
    }

    /**
     * Retorna se o Pedido já foi entregue
     * @return True: pedido não entregue. False: pedido já entregue
     */
    public boolean disponivel() {
        return !this.entregue;
    }

    /**
     * Retorna o valor total do Pedido
     * @return O double com a soma do valor
     */
    public double valorTotal() {
        double total = 0;

        for (Item item : this.carrinho) {
            total += item.valorTotal();
        }

        return total + 1;
    }

    /**
     * Marca o estado de entregue para verdadeiro
     */
    public void marcarComoEntregue() {
        this.entregador.inserirSaldo(1 * 0.8);
        this.entregue = true;
    }

    /**
     * Confirma o pedido, remove os itens do estoque e remove do saldo do cliente
     */
    public void confirmar() {
        for (int i = 0; i < this.carrinho.size(); i++) {
            Item it = this.carrinho.get(i);
            it.getProduto().retirarDeEstoque(it.getQtd());
        }

        this.cliente.retirarSaldo(this.valorTotal());
    }

    /**
     * Retorna o código do pedido
     * @return O código do pedido
     */
    public String getCodigo() {
        return cod;
    }

    /**
     * Retorna o aluno cliente do pedido
     * @return O aluno cliente do pedido
     */
    public Aluno getCliente() {
        return cliente;
    }

    @Override public int compareTo(Pedido comparesTo) {
        int compareSize = comparesTo.carrinho.size();

        int size = this.carrinho.size();
        if (size != compareSize) {
            return compareSize - size;
        }

        return (int) Math.ceil(comparesTo.valorTotal() - this.valorTotal());
    }
}
