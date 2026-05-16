package br.com.streaming.util;

public class FormatadorTempo {
    
    // Método estático: pode ser chamado sem precisar dar 'new' na classe
    public static String formatarSegundos(int totalSegundos) {
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }
}
