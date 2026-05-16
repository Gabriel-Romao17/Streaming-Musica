package br.com.streaming.modelo;

public final class Musica extends ItemReproducao {

    private String artista;
    private int duracaoSegundos;
    private String genero;

    // Construtor principal
    public Musica(String nome, String artista, int duracao, String genero) {
        super(nome);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public Musica() {
        super("Desconhecido");
        this.artista = "Desconhecido";
        this.duracaoSegundos = 1;
        this.genero = "Pop";
    }

    public Musica(String nome, String artista) {
        this(nome, artista, 1, "Pop");
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista não deve ser nulo ou vazio");      
        }
        this.artista = artista.trim();
    }

    public int getDuracao() { return duracaoSegundos; }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao >= 3600 ) {
            throw new IllegalArgumentException("Musica com duração inválida, deve ser maior que 0 e menor que 3600 segundos");
        }
        this.duracaoSegundos = duracao;
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        String[] generosMusicais = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
        boolean generoEncontrado = false;

        if (genero == null) throw new IllegalArgumentException("Genero não pode ser nulo");
        
        for(String g : generosMusicais) {
            if (g.equalsIgnoreCase(genero)) {
                this.genero = genero.toLowerCase();
                generoEncontrado = true;
                break;
            }
        }
        if (!generoEncontrado) throw new IllegalArgumentException("Genero inválido");
    }

    public void exibir() {
        System.out.printf("Título: %s | Artista: %s | Duração: %d | Gênero: %s%n", getNome(), getArtista(), getDuracao(), getGenero());
    }

    // --- MÉTODOS DA INTERFACE REPRODUZIVEL ---

    @Override
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo música: " + getNome() + " - " + getArtista());
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Pausando música: " + getNome());
    }

    @Override
    public void parar() {
        System.out.println("⏹ Parando música: " + getNome());
    }

    @Override
    public int getDuracaoTotal() {
        return this.duracaoSegundos;
    }
}
