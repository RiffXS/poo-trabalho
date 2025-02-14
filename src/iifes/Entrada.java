package iifes;

import java.io.*;
import java.util.*;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilário Seibel Júnior, Rafael Barros Leão Borges e Breno Amâncio Affonso
     */

    public Scanner input;
    private Boolean first = true;

    /**
     * Construtor da classe Entrada
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALIZAÇÃO DO ARQUIVO!!
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada
     * @param msg Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    public String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        boolean continua = true;

        String linha = "";

        while (continua) {
            try {
                System.out.print(msg);

                linha = this.input.nextLine();

                // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada
                while (linha.charAt(0) == '#') linha = this.input.nextLine();

                continua = false;

            } catch (NoSuchElementException | IllegalStateException | StringIndexOutOfBoundsException e) {
                System.out.print("ERRO! Algo deu errado ao ler a linha.\n");
            }
        }

        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    public int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        boolean continua = true;

        int result = -1;
        while (continua) {
            try {
                String linha = this.lerLinha(msg);

                result = Integer.parseInt(linha);
                continua = false;

            } catch (NumberFormatException e) {
                System.out.print("ERRO! Número inválido.\n");
            }
        }

        return result;
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    public double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        boolean continua = true;

        double result = -1.00;
        while (continua) {
            try {
                String linha = this.lerLinha(msg);
                result = Double.parseDouble(linha);
                continua = false;

            } catch (NullPointerException | NumberFormatException e) {
                System.out.print("ERRO! Número inválido.\n");

            }
        }

        return result;
    }

    /*********************/
    /** MENU DO SISTEMA **/
    /*********************/

    /**
     * Exibe o menu principal até que o usuário opte por sair do programa
     * @param s Objeto da classe Sistema
     */
    public void menu(Sistema s) {
        if (this.first) {
            this.carregarDados(s);

            this.first = false;
        }

        if (s.sistemaVazio()) {
            System.out.print("** Inicializando o sistema **\n");
            this.cadAdmin(s);
        }

        String msg = """
                
                *********************
                Escolha uma opção:
                  1) Login
                  0) Sair
                
                """;

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) login(s);
            else System.out.print("Opção inválida. Tente novamente:\n");

            op = this.lerInteiro(msg);
        }

        this.salvarDados(s);
    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Admin
     */
    public void menu(Sistema s, Admin a) {
        String msg = """
                
                *********************
                Escolha uma opção:
                  1) Cadastrar novo administrador
                  2) Cadastrar aluno
                  3) Cadastrar produto
                  4) Cadastrar sala
                  0) Logout
                
                """;

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) cadAdmin(s);
            if (op == 2) cadAluno(s);
            if (op == 3) cadProduto(s);
            if (op == 4) cadSala(s);
            if (op < 0 || op > 4) System.out.print("Opção inválida. Tente novamente:\n");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Aluno
     */
    public void menu(Sistema s, Aluno a) {
        String msg = """
                
                *********************
                Escolha uma opção:
                  1) Fazer pedido
                  2) Fazer entrega
                  3) Meus pedidos
                  4) Inserir crédito
                  0) Logout
                
                """;

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) {
                if (s.listaSalaVazia()) System.out.print("Nenhuma sala cadastrada. Incapaz de realizar pedido.\n");
                else if (s.listaProdutoVazia()) System.out.print("Nenhum produto cadastrado. Incapaz de realizar pedido.\n");
                else fazerPedido(s, a);
            }
            if (op == 2) entregarPedido(s, a);
            if (op == 3) listarPedidos(s, a);
            if (op == 4) inserirCredito(s, a);
            if (op < 0 || op > 4) System.out.print("Opção inválida. Tente novamente:\n");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Faz o login do usuário
     * @param s Objeto da classe Sistema
     */
    public void login(Sistema s) {
        System.out.print("\nBem vindo! Digite seus dados de login:\n");
        String cpf = this.lerLinha("CPF: ");
        String senha = this.lerLinha("Senha: ");

        Admin adm = s.getAdmin(cpf);

        if (adm != null) {
            if (adm.validarAcesso(senha)) this.menu(s, adm);
            else System.out.print("Senha inválida.\n");

        } else {
            Aluno a = s.getAluno(cpf);

            if (a != null) {
                if (a.validarAcesso(senha)) this.menu(s, a);
                else System.out.print("Senha inválida.\n");

            } else {
                System.out.print("Usuário inexistente.\n");
            }
        }
    }

    /***************/
    /** CADASTROS **/
    /***************/

    /**
     * Lê os dados de um novo administrador e o cadastra no sistema
     * @param s Objeto da classe Sistema
     */
    public void cadAdmin(Sistema s) {
        System.out.print("\n** Cadastrando um novo administrador **\n");
        String cpf = this.lerLinha("Digite o CPF: ");

        while (s.getAdmin(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro CPF: ");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        String email = this.lerLinha("Digite o email: ");

        Admin a = new Admin(cpf, nome, senha, email);
        s.addAdmin(a);

        System.out.print("Usuário " + a + " criado com sucesso.\n");

        this.menu(s, a);
    }

    /**
     * Lê os dados de um novo aluno e o cadastra no sistema
     * @param s Objeto da classe Sistema
     */
    public void cadAluno(Sistema s) {
        System.out.print("\n** Cadastrando um novo aluno **\n");

        String cpf = this.lerLinha("Digite o CPF: ");

        while (s.getAluno(cpf) != null) {
            cpf = this.lerLinha("Usuário já existente. Escolha outro CPF.");
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");
        Aluno a = new Aluno(cpf, nome, senha);

        s.addAluno(a);

        System.out.print("Usuário " + a + " criado com sucesso.\n");
    }

    /**
     * Lê os dados de um novo produto e o cadastra no sistema
     * @param s Objeto da classe Sistema
     */
    public void cadProduto(Sistema s) {
        System.out.print("\n** Cadastrando um novo produto **\n");

        String nome = this.lerLinha("Digite o nome do produto: ");
        int qtd = this.lerInteiro("Digite a quantidade em estoque: ");
        double valor = this.lerDouble("Digite o valor unitário do produto: ");

        Produto p = new Produto(s.gerarCodigoProduto(), nome, qtd, valor);

        s.addProd(p);

        System.out.print("Produto " + p + " criado com sucesso.\n");
    }

    /**
     * Lê os dados de uma nova sala e o cadastra no sistema
     * @param s Objeto da classe Sistema
     */
    public void cadSala(Sistema s) {
        System.out.print("\n** Cadastrando uma nova sala **\n");

        String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
        String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): ");
        String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");

        Sala sla = new Sala(bloco, sala, andar);

        s.addSala(sla);

        System.out.print("Sala " + sla + " criada com sucesso.\n");
    }

    /**********************/
    /** MÉTODOS DO ALUNO **/
    /**********************/

    /**
     * Permite um aluno a fazer um pedido
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Aluno
     */
    public void fazerPedido(Sistema s, Aluno a) {
        System.out.print("\n** Fazendo um pedido **\n");

        Sala sla = this.lerSala(s);

        ArrayList<Item> itens = new ArrayList<>();
        String msg = """
                
                *********************
                Escolha uma opção:
                  1) Inserir produto no carrinho
                  2) Fechar pedido
                
                """;

        int op = this.lerInteiro(msg);

        while (op != 2) {
            if (op == 1) itens.add(this.lerItem(s));
            if (op < 1 || op > 2) System.out.print("Opção inválida. Tente novamente:\n");

            op = this.lerInteiro(msg);
        }

        Collections.sort(itens);

        if (!itens.isEmpty()) {
            Pedido p = new Pedido(s.gerarCodigoPedido(), a, null, sla, itens);

            if (a.getSaldo() >= p.valorTotal()) {
                p.confirmar();
                s.addPedido(p);

            } else {
                System.out.print("Pedido não pode ser realizado. Saldo insuficiente.\n");
            }
        }
    }

    /**
     * Faz a leitura da String do código de uma sala e retorna a Sala
     * @param s Objeto da classe Sistema
     * @return Objeto da classe Sala
     */
    private Sala lerSala(Sistema s) {
        System.out.print("Salas disponíveis:\n");
        s.listarSalas();

        String sala = this.lerLinha("Digite a sala: ");

        while (s.getSala(sala) == null) {
            sala = this.lerLinha("Sala não existente. Tente novamente:\n");
        }

        return s.getSala(sala);
    }

    /**
     * Disponibiliza os produtos cadastrados, e faz a leitura da escolha
     * @param s Objeto da classe Sistema
     * @return Objeto da classe Item
     */
    private Item lerItem(Sistema s) {
        System.out.print("\nProdutos disponíveis:\n");
        s.listarProdutos();

        String prod = this.lerLinha("Digite o código do produto: ");

        while (s.getProduto(prod) == null) {
            prod = this.lerLinha("Produto não existente. Tente novamente:\n");
        }
        Produto p = s.getProduto(prod);

        int qtd = this.lerInteiro("Digite a quantidade de " + p + " no pedido: ");
        while (qtd > p.getQuantidade()) {
            qtd = this.lerInteiro("Quantidade inválida. Máximo de " + p.getQuantidade() + "\nDisponíveis em estoque para " + p + " Tente novamente:\n");
        }

        return new Item(p, qtd);
    }

    /**
     * Permite um aluno a fazer uma entrega
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Aluno
     */
    public void entregarPedido(Sistema s, Aluno a) {
        System.out.print("\n** Escolhendo um pedido para entregar **\n");

        ArrayList<Pedido> pdidos = s.filtrarPedidos(true);

        for (Pedido p : pdidos) {
            System.out.print(p + "\n");
        }

        String cod = this.lerLinha("Digite o código do pedido: ");
        while (s.getPedido(cod) == null) {
            cod = this.lerLinha("Código inválido. Tente novamente:\n");
        }

        Pedido p = s.getPedido(cod);

        p.atribuirEntregador(a);
        p.marcarComoEntregue();
    }

    /**
     * Lista todos os pedidos a partir do aluno que os fez
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Aluno
     */
    public void listarPedidos(Sistema s, Aluno a) {
        System.out.print("\n** Pedido de " + a + " **\n");

        for (Pedido p : s.filtrarPedidos(a)) {
            System.out.print(p + "\n");
        }
    }

    /**
     * Permite a um aluno a inserir crédito no seu saldo
     * @param s Objeto da classe Sistema
     * @param a Objeto da classe Aluno
     */
    public void inserirCredito(Sistema s, Aluno a) {
        System.out.print("\n** Inserindo saldo **\n");

        double valor = this.lerDouble("Digite o valor a ser adicionado no saldo: ");

        a.inserirSaldo(valor);
    }

    /***********************/
    /** LEITURA E ESCRITA **/
    /**      DE DADOS     **/
    /***********************/

    /**
     * Salva os dados do Sistema no arquivo dados.txt
     * @param s Objeto da classe Sitema
     */
    public void salvarDados(Sistema s) {
        try {
            FileWriter file = new FileWriter("dados.txt");
            BufferedWriter buff = new BufferedWriter(file);

            s.salvar(buff);

            buff.close();
        } catch (IOException e) {
            System.out.print("ERRO! Falha ao salvar arquivo.\n");
        }
    }

    /**
     * Carrega os dados do arquivo dados.txt no Sistema dado
     * @param s Objeto da classe Sistema
     */
    public void carregarDados(Sistema s) {
        try {
            FileReader file = new FileReader("dados.txt");
            BufferedReader buff = new BufferedReader(file);

            s.carregar(buff);

            buff.close();

        } catch (FileNotFoundException e) {
            System.out.print("Arquivo não encontrado.\n\n");
        } catch (IOException e) {
            System.out.print("ERRO! Falha ao ler o arquivo.\n");
        }
    }
}
