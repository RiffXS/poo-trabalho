package iifes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface Salvavel {
    public abstract void salvar(BufferedWriter buff) throws IOException;

    public abstract void carregar(BufferedReader buff) throws IOException;
}
