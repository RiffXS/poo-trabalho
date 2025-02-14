package iifes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Admin extends Usuario {
    /**
     * Classe com as rotinas do usuário administrador
     */

    private String email;

    /**
     * Contrutor da classe Admin
     * @param buff Objeto do arquivo que conterá os dados
     * @throws IOException Erro ao ler conteúdo
     */
    public Admin(BufferedReader buff) throws IOException {
        super();
        this.carregar(buff);
    }

    /**
     * Construtor da classe Admin
     * @param cpf CPF do usuário administrador
     * @param nome Nome do usuário administrador
     * @param senha Senha do usuário administrador
     * @param email Email do usuário administrador
     */
    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    /**
     * Valida o acesso do usuário
     * @param pwd Senha de Entrada
     * @return True: senha correta. False: senha incorreta
     */
    public boolean validarAcesso(String pwd) {
        return super.validarAcesso(pwd);
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        return super.toString() + " (ADMIN)";
    }

    /**
     * Salva os dados do Admin no arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void salvar(BufferedWriter buff) throws IOException {
        buff.write("ADM\n");
        super.salvar(buff);
        buff.write(this.email + '\n');
    }

    /**
     * Carrega os dados do arquivo buff
     * @param buff Objeto do arquivo que conterá os dados
     */
    public void carregar(BufferedReader buff) throws IOException {
        super.carregar(buff);
        this.email = buff.readLine();
    }
}
