package iifes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Usuario implements Salvavel {
    /**
     * Classe com as rotinas do usuário
     */

    protected String cpf, nome;
    private String senha;
    /**
     * Contrutor da classe Usuario
     */
    public Usuario() {}

    /**
     * Contrutor da classe Usuario
     * @param buff Objeto do arquivo que conterá os dados
     * @throws IOException Erro ao ler conteúdo
     */
    public Usuario(BufferedReader buff) throws IOException {
        this.carregar(buff);
    }

    /**
     * Construtor da classe Usuario
     * @param cpf CPF do usuário
     * @param nome Nome do usuário
     * @param senha Senha do usuário
     */
    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Valida o acesso do usuário
     * @param pwd Senha de Entrada
     * @return True: senha correta. False: senha incorreta
     */
    public boolean validarAcesso(String pwd) {
        return pwd.equals(this.senha);
    }

    /**
     * Altera a senha do usuário
     * @param atual: Senha atual do usuário
     * @param nova: Senha nova que o usuário quer
     * @return True: senha alterada. False: senha inalterada
     */
    public boolean alterarSenha(String atual, String nova) {
        if (atual.equals(this.senha)) {
            this.senha = nova;
            return true;
        }

        return false;
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        return this.nome + " - CPF: " + this.cpf;
    }

    /**
     * Getter de CPF do usuário
     * @return CPF do usuário
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Salva os dados do Usuario no arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void salvar(BufferedWriter buff) throws IOException {
        buff.write(this.cpf + '\n');
        buff.write(this.nome + '\n');
        buff.write(this.senha + '\n');
    }

    /**
     * Carrega os dados do arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void carregar(BufferedReader buff) throws IOException{
        this.cpf   = buff.readLine();
        this.nome  = buff.readLine();
        this.senha = buff.readLine();
    }
}
