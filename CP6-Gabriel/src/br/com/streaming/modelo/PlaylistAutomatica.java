package br.com.streaming.modelo;

import java.util.ArrayList;
import java.util.Random;

// Subclasse de Playlist, responsável por automatizar a criação de playlists conforme critério do usuário
public final class PlaylistAutomatica extends Playlist{
    // Atributo único da subclasse PlaylistAutomatica
    private String criterio;

    //Construtor Parametrizado
    public PlaylistAutomatica(String nome,String criterio) {
        super(nome);
        setCriterio(criterio);
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir();
    }

    // Função que atualiza a playlist de forma automatica conforme criterio do usuario
    public void atualizar(ArrayList<Musica> todasMusicas, ArrayList<Musica> historicoUsuario) {
        // Limpa o array de musicas
        musicas.clear();
        
        // Verifica o criterio do usuário
        if (criterio.equals("top")) {
            // Arrays auxiliares para funcionar como uma tabela de contagem
            ArrayList<Musica> musicasUnicas = new ArrayList<>();
            ArrayList<Integer> contagens = new ArrayList<>();

            // Percorre o histórico e conta as ocorrências
            for (Musica m : historicoUsuario) {
                int indice = musicasUnicas.indexOf(m);
                
                if (indice == -1) {
                    // Música apareceu pela primeira vez
                    musicasUnicas.add(m);
                    contagens.add(1);
                } else {
                    // Música já existe, então o contador é incrementado
                    int contagemAtual = contagens.get(indice);
                    contagens.set(indice, contagemAtual + 1);
                }
            }

            // Encontra a música com o maior número na lista de contagens
            int maxReproducoes = 0;
            Musica maisTocada = null;
            
            for (int i = 0; i < musicasUnicas.size(); i++) {
                if (contagens.get(i) > maxReproducoes) {
                    maxReproducoes = contagens.get(i);
                    maisTocada = musicasUnicas.get(i);
                }
            }
            
            // Adiciona a música mais tocada na playlist automática
            if (maisTocada != null) {
                musicas.add(maisTocada);
            }
            
        } else if (criterio.equals("recomendadas")) {
            // Verifica se tem músicas no acervo para evitar erros
            if (!todasMusicas.isEmpty()) {              
                Random sorteador = new java.util.Random();
                
                // Sorteia 5 músicas para a playlist recomendada, por exemplo
                for (int i = 0; i < 10; i++) {
                    
                    // Sorteia um número entre 0 e o limite máximo do acervo
                    int indiceSorteado = sorteador.nextInt(todasMusicas.size());
                    
                    // Pega a música correspondente a esse número
                    Musica recomendada = todasMusicas.get(indiceSorteado);
                    
                    // Adiciona a música na playlist
                    musicas.add(recomendada);
                }
            }
        }
    }
}
