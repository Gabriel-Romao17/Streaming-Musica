import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> acervo = new ArrayList<>();
    static Usuario usuario = new Usuario();

    public static void main(String[] args) {
        usuario.nome = "Gabriel"; 
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar todas as músicas");
            System.out.println("3. Buscar música");
            System.out.println("4. Criar playlist");
            System.out.println("5. Gerenciar playlists");
            System.out.println("6. Exibir estatísticas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarMusica();
                case 2 -> listarAcervo();
                case 3 -> buscarMusica();
                case 4 -> criarPlaylist();
                case 5 -> gerenciarPlaylists();
                case 6 -> exibirEstatisticas();
            }
        } while (opcao != 0);
    }

    static void cadastrarMusica() {
        Musica m = new Musica();
        System.out.print("Título: "); m.setTitulo(scanner.nextLine());
        System.out.print("Artista: "); m.artista = scanner.nextLine();
        System.out.print("Duração (seg): "); m.duracaoSegundos = Integer.parseInt(scanner.nextLine());
        System.out.print("Gênero: "); m.genero = scanner.nextLine();
        acervo.add(m);
        System.out.println("✅ Música cadastrada!");
    }

    static void listarAcervo() {
        if (acervo.isEmpty()) { System.out.println("Acervo vazio."); return; }
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print("ID [" + i + "] ");
            acervo.get(i).exibir();
        }
    }

    static void buscarMusica() {
        System.out.print("Digite o termo de busca: ");
        String busca = scanner.nextLine();
        for (Musica m : acervo) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) m.exibir();
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        usuario.criarPlaylist(scanner.nextLine());
        System.out.println("✅ Playlist criada!");
    }

    static void gerenciarPlaylists() {
        if (usuario.playlists.isEmpty()) { System.out.println("Crie uma playlist primeiro."); return; }
        usuario.listarPlaylists();
        System.out.print("Escolha o ID da playlist: ");
        Playlist p = usuario.getPlaylist(Integer.parseInt(scanner.nextLine()));

        System.out.println("\n1. Adicionar música | 2. Remover música | 3. Listar | 4. Detalhes | 0. Voltar");
        int sub = Integer.parseInt(scanner.nextLine());

        if (sub == 1) {
            listarAcervo();
            System.out.print("ID da música: ");
            p.adicionarMusica(acervo.get(Integer.parseInt(scanner.nextLine())));
        } else if (sub == 2) {
            p.listarMusicas();
            System.out.print("Índice para remover: ");
            p.removerMusica(Integer.parseInt(scanner.nextLine()));
        } else if (sub == 3) {
            p.listarMusicas();
        } else if (sub == 4) {
            System.out.println("Qtd: " + p.getQuantidadeMusicas() + " | Total: " + p.getDuracaoTotal() + "s");
        }
    }

    static void exibirEstatisticas() {
        System.out.println("Total de músicas no sistema: " + acervo.size());
        System.out.println("Total de playlists: " + usuario.playlists.size());
    }
}