package br.com.streaming.util;

public class Validador {

    public static void validarTexto(String texto, String mensagemErro) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }

    public static void validarDuracao(int duracao, String mensagemErro) {
        if (duracao <= 0 || duracao >= 3600) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
    
    public static void validarIndice(int indice, int tamanhoLista, String mensagemErro) {
        if (indice < 0 || indice >= tamanhoLista) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
}
