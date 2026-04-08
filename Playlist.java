import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
        this();
        setNome(nome);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        }
    }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica m) {
        if (m != null) {
            this.musicas.add(m);
        }
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            this.musicas.remove(indice);
        }
    }

    public void exibirPlaylist() {
        System.out.println("\n--- PLAYLIST: " + nome + " ---");
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.print((i + 1) + ". ");
                musicas.get(i).exibirDados();
            }
        }
    }
}