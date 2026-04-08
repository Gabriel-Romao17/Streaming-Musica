import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario() {
        this.playlists = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        setNome(nome);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public ArrayList<Playlist> getPlaylists() { return playlists; }

    public void adicionarPlaylist(Playlist p) {
        if (p != null) {
            this.playlists.add(p);
        }
    }

    public Playlist buscarPlaylist(String nomeBusca) {
        for (Playlist p : playlists) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                return p;
            }
        }
        return null;
    }
}