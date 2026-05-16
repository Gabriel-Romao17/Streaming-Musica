package br.com.streaming.modelo;

// Classe que representa o usuário tipo free, com as funções padrões dos usuários, mas com limitações
public final class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    private int limiteReproducoes;
    
    // Construtor parametrizado
    public UsuarioFree(String nome, String email) {
        super(nome, email); // Chama construtor da superclasse
        this.contadorReproducoes = 0;
        this.limiteReproducoes = 30;
    }
    
    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        if (contadorReproducoes > this.limiteReproducoes) {
            System.out.println("Você atingiu o limite de reproduções, assine o premium para músicas ilimitadas!");
            return;
        }
        
        // A cada 3 músicas, exibir anúncio
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        
        super.reproduzirMusica(musica); // Chama método da superclasse
    }
    
    @Override
    public void criarPlaylist(String nome) {
        // Verifica se a quantidade de playlists é maior que o permitido
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            System.out.println("💎 Assine Premium para playlists ilimitadas!");
            return;
        }
        // Caso não, a playlist é criada
        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist criada!");
    }
    
    public void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }

    @Override
    public void exibirDetalhesConta() {
        System.out.println("--- DETALHES DA CONTA ---");
        System.out.println("Tipo: Gratuita (Free)");
        System.out.println("Limite de Playlists: " + MAX_PLAYLISTS);
    }
}