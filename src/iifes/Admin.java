package iifes;

public class Admin extends Usuario {
    /**
     * Classe com as rotinas do usuário administrador
     */

    private String email;

    /**
     * Construtor da classe Admin
     * @param cpf: CPF do usuário administrador
     * @param nome: Nome do usuário administrador
     * @param senha: Senha do usuário administrador
     * @param email: Email do usuário administrador
     */
    public Admin(String cpf, String nome, String senha, String email) {
        super(cpf, nome, senha);
        this.email = email;
    }

    /**
     * Valida o acesso do usuário
     * @param pwd: Senha de Entrada
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
}
