import java.util.ArrayList;

public class UsuarioPremium extends Usuario {
    private String plano;
    private double preco;
    private ArrayList<Musica> musicasBaixadas = new ArrayList<>();

    public UsuarioPremium(String nome, String email, String plano, double preco) {
        super(nome, email);
        this.plano = plano;
        this.preco = preco;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("[HQ] Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("Baixada para ouvir offline: " + musica.getTitulo());
        }
    }

    public double getPreco() { return preco; }
    public String getPlano() { return plano; }
}