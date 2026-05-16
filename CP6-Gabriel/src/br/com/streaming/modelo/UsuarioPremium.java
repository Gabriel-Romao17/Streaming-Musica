package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import java.util.ArrayList;

public final class UsuarioPremium extends Usuario implements Baixavel {
    private String plano; 
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }
    
    @Override
    public void reproduzirMusica(Musica musica) {

        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getNome());
        historicoReproducao.add(musica);
    }
    
    // --- MÉTODOS OBRIGATÓRIOS DA INTERFACE BAIXAVEL ---
    
    @Override
    public void baixar(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getNome());
        } else {
            System.out.println("ℹ️ Música já está baixada!");
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        if (musicasBaixadas.contains(musica)) {
            musicasBaixadas.remove(musica);
            System.out.println("🗑️ Download removido: " + musica.getNome());
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
    }

    // Mantemos o listar para a interface do utilizador
    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        
        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }

    @Override
    public void exibirDetalhesConta() {
        System.out.println("--- DETALHES DA CONTA ---");
        System.out.println("Tipo: Premium");
        System.out.println("Plano contratado: " + plano);
    }

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
    public ArrayList<Musica> getMusicasBaixadas() { return musicasBaixadas; }
    public void setMusicasBaixadas(ArrayList<Musica> musicasBaixadas) { this.musicasBaixadas = musicasBaixadas; }
}
