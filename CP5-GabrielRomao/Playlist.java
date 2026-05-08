import java.util.ArrayList;

public class Playlist {
    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        setNome(nome);
        musicas = new ArrayList<>();
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        this.nome = nome;
    }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            musicas.add(musica);
        }
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma musica na playlist.");
            return;
        }
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public void reproduzir() {
        System.out.println("Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("Tocando: " + m.getTitulo());
        }
    }
}