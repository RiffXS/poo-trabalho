package iifes;

public class Produto {
    /**
     * Classe com as rotinas do produto
     */

    private String codigo, nome;
    private int qtd;
    private double valor;

    /**
     * Construtor da classe Produto
     * @param codigo Código do Produto
     * @param nome Nome do Produto
     * @param qtd Quantidade do Produto em estoque
     * @param valor Valor do Produto
     */
    public Produto(String codigo, String nome, int qtd, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.qtd = qtd;
        this.valor = valor;
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        return this.codigo + ": " + this.nome;
    }

    /**
     * Retira uma qtd de produtos do estoque
     * @param qtd Inteiro a ser removido da quantidade do produto em estoque
     */
    public void retirarDeEstoque(int qtd) {
        this.qtd -= qtd;
    }

    /**
     * Retorna o código do produto
     * @return O código do produto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna a quantidade do produto em estoque
     * @return A quantidade do produto em estoque
     */
    public int getQuantidade() {
        return qtd;
    }

    /**
     * Retorna o valor do produto
     * @return O valor do produto
     */
    public double getValor() {
        return this.valor;
    }
}
