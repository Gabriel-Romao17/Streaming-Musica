import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists = new ArrayList<>();
    protected ArrayList<Musica> historicoReproducao = new ArrayList<>();

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
    }

    public abstract void reproduzirMusica(Musica musica);

    public int getContadorReproducoes() { return 0; }

    public void exibirHistorico() {
        System.out.println("\n=== HISTORICO ===");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Historico vazio.");
            return;
        }
        for (Musica m : historicoReproducao) m.exibir();
    }

    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
        System.out.println("Playlist criada!");
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome invalido");
        this.nome = nome.trim();
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email invalido");
        this.email = email.trim();
    }

    public String getNome() { return nome; }
}