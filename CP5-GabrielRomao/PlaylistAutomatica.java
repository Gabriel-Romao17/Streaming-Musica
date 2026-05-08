import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {
    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("Playlist Automatica: " + nome);
        System.out.println("Criterio: " + criterio);
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();
        for (Musica m : todasMusicas) {
            if (m.getGenero().equalsIgnoreCase(criterio)) {
                musicas.add(m);
            }
        }
    }
}