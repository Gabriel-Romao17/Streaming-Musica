public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private static int anunciosExibidos = 0;
    private int contadorReproducoes = 0;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        if (contadorReproducoes >= 30) {
            System.out.println("Limite de reproducoes atingido!");
            return;
        }

        contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) {
            anunciosExibidos++;
            System.out.println("\n[ANUNCIO] Assine Premium para remover propagandas!\n");
        }

        System.out.println("Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("Limite de playlists atingido!");
            return;
        }
        super.criarPlaylist(nome);
    }

    @Override
    public int getContadorReproducoes() {
        return contadorReproducoes;
    }

    public static int getAnunciosExibidos() {
        return anunciosExibidos;
    }
}