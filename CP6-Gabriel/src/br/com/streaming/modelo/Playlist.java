package br.com.streaming.modelo;

import java.util.ArrayList;

public class Playlist extends ItemReproducao {

    protected ArrayList<Musica> musicas;
    protected String descricao;

    public Playlist() {
        super("Desconhecido");
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
        super(nome);
        this.musicas = new ArrayList<>();
    }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("A música não deve ser nula!");
        }
        musicas.add(musica);
    }

    public void removerMusica(int indice) {
        if (indice >= musicas.size() || indice < 0) {
            throw new IllegalArgumentException("O índice não deve ser negativo ou maior que o tamanho da lista de músicas");
        }
        musicas.remove(indice);         
    }

    public void listarMusicas() {
        for (int indice = 0; indice < musicas.size(); indice++) {
            Musica m = musicas.get(indice);         
            // Usando getNome() em vez de getTitulo()
            System.out.printf("%d. Nome: %s | Artista: %s | Duração: %d segundos | Gênero: %s%n"
                , (indice + 1), m.getNome(), m.getArtista(), m.getDuracao(), m.getGenero()
            );
        }
    }

    public int getQuantidadeMusicas() { return musicas.size(); }

    // --- MÉTODOS DA INTERFACE REPRODUZIVEL ---

    @Override
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo playlist: " + getNome());
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getNome());
        }
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Pausando playlist: " + getNome());
    }

    @Override
    public void parar() {
        System.out.println("⏹ Parando playlist: " + getNome());
    }

    @Override
    public int getDuracaoTotal() {
        int duracaoTotal = 0;
        for (Musica m : musicas) {
            duracaoTotal += m.getDuracaoTotal();
        }
        return duracaoTotal;
    }
}
