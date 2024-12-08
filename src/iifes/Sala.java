package iifes;

public class Sala {
    /**
     * Classe com as rotinas da sala
     */

    private String bloco, sala, andar;

    /**
     * Construtor da classe Sala
     * @param bloco Bloco da sala (ex: 9)
     * @param sala Número da sala (ex: 04)
     * @param andar Andar da sala (ex: S)
     */
    public Sala(String bloco, String sala, String andar) {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    /**
     * Define como um objeto de classe se comporta ao ser transformado em String
     * @return String com os dados da classe
     */
    public String toString() {
        return this.bloco + this.sala + this.andar;
    }

    /**
     * Retorna o bloco da sala
     * @return O bloco da sala
     */
    public String getBloco() {
        return bloco;
    }

    /**
     * Retorna o número da sala
     * @return O número da sala
     */
    public String getSala() {
        return sala;
    }

    /**
     * Retorna o andar da sala
     * @return O andar da sala
     */
    public String getAndar() {
        return andar;
    }
}
