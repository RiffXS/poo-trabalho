package iifes;

public class Aluno extends Usuario {
    /**
     * Classe com as rotinas do usuário aluno
     */

    private double saldo;

    /**
     * Construtor da classe Aluno
     * @param cpf: CPF do usuário aluno
     * @param nome: Nome do usuário aluno
     * @param senha: Senha do usuário aluno
     */
    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    /**
     * Recebe um valor e o adiciona ao saldo do usuário
     * @param valor Double com o valor a ser adicionado ao saldo
     */
    public void inserirSaldo(double valor) {
        this.saldo += valor;
    }

    /**
     * Desconta o valor da conta caso o usuário tenha crédito para isso
     * @param valor: Valor a ser retirado da conta
     * @return True: valor retirado. False: valor não retirado
     */
    public boolean retirarSaldo(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }

        return false;
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        return super.toString() + " (Saldo: R$ " + String.format("%.2f", this.saldo) + ")";
    }

    /**
     * Retorna o saldo do aluno
     * @return O saldo do aluno
     */
    public double getSaldo() {
        return saldo;
    }
}
