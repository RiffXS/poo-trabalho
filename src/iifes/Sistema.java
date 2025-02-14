package iifes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Sistema implements Salvavel {
    /**
     * Classe com as rotinas do sistema
     */

    private ArrayList<Sala>    salas;
    private ArrayList<Admin>   adms;
    private ArrayList<Aluno>   alunos;
    private ArrayList<Pedido>  pedidos;
    private ArrayList<Produto> prods;

    /**
     * Construtor da classe Sistema
     */
    public Sistema() {
        this.salas   = new ArrayList<>();
        this.adms    = new ArrayList<>();
        this.alunos  = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.prods   = new ArrayList<>();
    }

    /**********************/
    /** ADD TO ARRAYLIST **/
    /**********************/

    /**
     * Adiciona um Aluno ao ArrayList de alunos
     * @param a Objeto da classe Aluno
     */
    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    /**
     * Adiciona um Admin ao ArrayList de adms
     * @param a Objeto da classe Admin
     */
    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    /**
     * Adiciona um Produto ao ArrayList de prods
     * @param p Objeto da classe Produto
     */
    public void addProd(Produto p) {
        this.prods.add(p);
    }

    /**
     * Adiciona um Pedido ao ArrayList de pedidos
     * @param p Objeto da classe Pedido
     */
    public void addPedido(Pedido p) {
        this.pedidos.add(p);
    }

    /**
     * Adiciona uma Sala ao ArrayList de salas
     * @param s Objeto da classe Sala
     */
    public void addSala(Sala s) {
        this.salas.add(s);
    }

    /************************/
    /** GET FROM ARRAYLIST **/
    /************************/

    /**
     * Retorna o Aluno que tem o CPF dado
     * @param cpf CPF a ser procurado entre os alunos cadastrados
     * @return null: Aluno com esse CPF não existe. Objeto da classe Aluno: Aluno com esse CPF existe
     */
    public Aluno getAluno(String cpf) {
        for (Aluno a : this.alunos) {
            if (a.getCpf().equals(cpf)) {
                return a;
            }
        }

        return null;
    }

    /** Retorna o Admin que tem o CPF dado
     * @param cpf CPF a ser procurado entre os adms cadastrados
     * @return null: Admin com esse CPF não existe. Objeto da classe Admin: Admin com esse CPF existe
     */
    public Admin getAdmin(String cpf) {
        for (Admin a : this.adms) {
            if (a.getCpf().equals(cpf)) {
                return a;
            }
        }

        return null;
    }

    /** Retorna o Produto que tem o código dado
     * @param cod código a ser procurado entre os prods cadastrados
     * @return null: Produto com esse código não existe. Objeto da classe Produto: Produto com esse código existe
     */
    public Produto getProduto(String cod) {
        for (Produto p : this.prods) {
            if (p.getCodigo().equals(cod)) {
                return p;
            }
        }

        return null;
    }

    /** Retorna o Pedido que tem o código dado
     * @param cod código a ser procurado entre os pedidos cadastrados
     * @return null: Pedido com esse código não existe. Objeto da classe Pedido: Pedido com esse código existe
     */
    public Pedido getPedido(String cod) {
        for (Pedido p : this.pedidos) {
            if (p.getCodigo().equals(cod)) {
                return p;
            }
        }

        return null;
    }

    /** Retorna a Sala que tem o nome dado
     * @param nome nome a ser procurado entre as salas cadastrados
     * @return null: Sala com esse nome não existe. Objeto da classe Sala: Sala com esse nome existe
     */
    public Sala getSala(String nome) {
        for (Sala s : this.salas) {
            if (s.getBloco().regionMatches(0, nome, 0, 1) && s.getSala().regionMatches(0, nome, 1, 2) && s.getAndar().regionMatches(0, nome, 3, 1)) {
                return s;
            }
        }

        return null;
    }

    /********************/
    /** OUTROS MÉTODOS **/
    /********************/

    /**
     * Verifica se nenhum usuário administrador foi cadastrado
     * @return True: nenhum Admin cadastrado. False: Pelo menos um Admin cadastrado
     */
    public boolean sistemaVazio() {
        return this.adms.isEmpty();
    }

    /**
     * Gera o código do próximo Produto
     * @return String do código
     */
    public String gerarCodigoProduto() {
        return "PROD-" + (this.prods.size() + 1);
    }

    /**
     * Gera o código do próximo Pedido
     * @return String do código
     */
    public String gerarCodigoPedido() {
        return "PEDIDO-" + (this.pedidos.size() + 1);
    }

    /**
     * Lista todos os prods cadastrados
     */
    public void listarProdutos() {
        for (Produto p : this.prods) {
            System.out.print("  " + p + "\n");
        }
    }

    /**
     * Lista todas as salas cadastradas
     */
    public void listarSalas() {
        for (Sala s : this.salas) {
            System.out.print("  " + s.getBloco() + s.getSala() + s.getAndar() + "\n");
        }
    }

    /**
     * Retorna uma lista com todos os pedidos disponíveis para entrega
     * @param disponivel Boolean que define qual o estado de disponibilidade dos pedidos na lista
     * @return ArrayList com os pedidos disponíveis a entrega
     */
    public ArrayList<Pedido> filtrarPedidos(boolean disponivel) {
        ArrayList<Pedido> pdidos = new ArrayList<>();

        for (Pedido p : this.pedidos) {
            if (p.disponivel() == disponivel) {
                pdidos.add(p);
            }
        }

        Collections.sort(pdidos);
        return pdidos;
    }

    /**
     * Retorna uma lista com todos os pedidos do Aluno a
     * @param a Aluno que define de quem são os pedidos na lista de pedidos
     * @return ArrayList com os pedidos feitos pelo aluno
     */
    public ArrayList<Pedido> filtrarPedidos(Aluno a) {
        ArrayList<Pedido> pdidos = new ArrayList<>();

        for (Pedido p : this.pedidos) {
            if (p.getCliente().getCpf().equals(a.getCpf())) {
                pdidos.add(p);
            }
        }

        Collections.sort(pdidos);
        return pdidos;
    }

    /********************/
    /** SALVANDO DADOS **/
    /********************/

    /**
     * Salva os dados do Sistema no arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void salvar(BufferedWriter buff) throws IOException {
        for (Admin a : this.adms) {
            a.salvar(buff);
        }

        for (Aluno a : this.alunos) {
            a.salvar(buff);
        }

        buff.write("FIM");
    }

    /**
     * Carrega os dados do arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void carregar(BufferedReader buff) throws IOException {
        String linha = buff.readLine();

        if (!linha.isBlank()) {
            while (!linha.equals("FIM")) {
                if (linha.equals("ADM")) {
                    Admin a = new Admin(buff);
                    this.addAdmin(a);

                } else if (linha.equals("ALU")) {
                    Aluno a = new Aluno(buff);
                    this.addAluno(a);
                }

                linha = buff.readLine();
            }
        }
    }
}
