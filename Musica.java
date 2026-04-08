public class Musica {
    private String titulo;
    private String artista;
    private int duracao; // em segundos
    private String genero;

    public Musica() {}

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        }
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artista = artista.trim();
        }
    }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) {
        // Ajustado para o intervalo solicitado nas imagens (1 a 60 min)
        if (duracao >= 60 && duracao <= 3600) {
            this.duracao = duracao;
        }
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) {
        String[] validos = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
        for (String v : validos) {
            if (v.equalsIgnoreCase(genero)) {
                this.genero = v;
                break;
            }
        }
    }

    public void exibirDados() {
        int min = duracao / 60;
        int seg = duracao % 60;
        System.out.printf("%s - %s [%s] (%d:%02d)\n", titulo, artista, genero, min, seg);
    }
}