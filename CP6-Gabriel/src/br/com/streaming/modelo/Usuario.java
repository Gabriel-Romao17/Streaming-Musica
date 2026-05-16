package br.com.streaming.modelo;

import java.util.ArrayList;

// Classe que representa o usuário e gerencia playlists
public abstract class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;
    

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }
    
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }
    
    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        for (Musica m : historicoReproducao) {
            m.exibir();
        }
    }
    
    public void criarPlaylist(String nome) {
        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist criada!");
    }

    public abstract void exibirDetalhesConta(); 

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Musica> getHistoricoReproducao() {
        return historicoReproducao;
    }

    public void setHistoricoReproducao(ArrayList<Musica> historicoReproducao) {
        this.historicoReproducao = historicoReproducao;
    }
}