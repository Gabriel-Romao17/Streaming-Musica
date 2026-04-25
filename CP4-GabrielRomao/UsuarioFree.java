public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    private final int limiteReproducoes = 30;
    
    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }
    
    @Override
    public void reproduzirMusica(Musica musica) {
        if (contadorReproducoes >= limiteReproducoes) {
            System.out.println("Você atingiu o limite de reproduções, assine o premium para músicas ilimitadas!");
            return;
        }

        contadorReproducoes++;
        
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        
        super.reproduzirMusica(musica);
    }
    
    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("Limite de playlists atingido!");
            System.out.println("Assine Premium para playlists ilimitadas!");
            return;
        }
        super.criarPlaylist(nome);
    }
    
    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }
}