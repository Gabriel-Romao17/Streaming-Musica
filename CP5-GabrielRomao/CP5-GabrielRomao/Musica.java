public class Musica {
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    private static final String[] GENEROS_VALIDOS = {
        "Pop", "Rock", "Jazz", "Eletronica", "Hip-Hop", "Classica"
    };

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracaoSegundos);
        setGenero(genero);
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("Titulo invalido");
        this.titulo = titulo.trim();
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) throw new IllegalArgumentException("Artista invalido");
        this.artista = artista.trim();
    }

    public void setDuracao(int duracaoSegundos) {
        if (duracaoSegundos <= 0 || duracaoSegundos >= 3600) throw new IllegalArgumentException("Duracao invalida");
        this.duracaoSegundos = duracaoSegundos;
    }

    public void setGenero(String genero) {
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero)) {
                this.genero = g;
                return;
            }
        }
        throw new IllegalArgumentException("Genero invalido");
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getGenero() { return genero; }

    public void exibir() {
        System.out.printf("Titulo: %-15s | Artista: %-15s | Duracao: %3ds | Genero: %s%n", 
            titulo, artista, duracaoSegundos, genero);
    }
}