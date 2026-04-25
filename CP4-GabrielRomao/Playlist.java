import java.util.ArrayList;

public class Playlist {

    private String nome;
    private ArrayList<Musica> musicas;

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo!");
        } else {
            String nomeCorreto = nome.trim();
            if (nomeCorreto.isEmpty()) {
                throw new IllegalArgumentException("O nome da playlist não deve ser vazio!");
            } else {
                this.nome = nomeCorreto;
            }
        }
    }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("A música não deve ser nula!");
        } else {
            musicas.add(musica);
        }
    }

    public void removerMusica(int indice) {
        if (indice >= musicas.size() || indice < 0) {
            throw new IllegalArgumentException("O índice não deve ser negativo ou maior que o tamanho da lista de músicas");
        } else {       
                musicas.remove(indice);         
        }
    }

    public void listarMusicas() {
        for (int indice = 0; indice < musicas.size(); indice++) {
            Musica m = musicas.get(indice);         
            System.out.printf("%d. Nome: %s | Artista: %s | Duração: %d segundos | Gênero: %s%n"
                , (indice + 1), m.getTitulo(), m.getArtista(), m.getDuracao(), m.getGenero()
            );
        }
    }

    public int getDuracaoTotal() {
        int duracaoTotal = 0;
        for (int indice = 0; indice < musicas.size(); indice++) {
            Musica m = musicas.get(indice);
            duracaoTotal += m.getDuracao();
        }
        return duracaoTotal;
    }

    public int getQuantidadeMusicas() { return musicas.size(); }

    public Playlist() {
        this("Desconhecido");
    }

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }
}