import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    static ArrayList<Musica> acervo = new ArrayList<>();
    static Usuario usuarioAtual = new Usuario("Aluno");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        System.out.println("\nAté logo!");
    }

    static void exibirMenu() {
        System.out.println("\n=== STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Lista de músicas (Acervo)");
        System.out.println("3. Criar playlist");
        System.out.println("4. Gerenciar playlists");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarMusica();
            case 2 -> listarAcervo();
            case 3 -> criarPlaylist();
            case 4 -> gerenciarPlaylists();
        }
    }

    static void cadastrarMusica() {
        System.out.println("\n--- CADASTRO ---");
        System.out.print("Título: "); String t = scanner.nextLine();
        System.out.print("Artista: "); String a = scanner.nextLine();
        System.out.print("Duração (60-3600 seg): "); int d = lerOpcao();
        System.out.print("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): "); String g = scanner.nextLine();

        Musica m = new Musica(t, a, d, g);

        if (m.getTitulo() != null && m.getGenero() != null && m.getDuracao() >= 60) {
            acervo.add(m);
            System.out.println("Música cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Dados inválidos ou duração fora do limite.");
        }
    }

    static void listarAcervo() {
        System.out.println("\n--- ACERVO GERAL ---");
        if (acervo.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (int i = 0; i < acervo.size(); i++) {
            System.out.print((i + 1) + ". ");
            acervo.get(i).exibirDados();
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        if (usuarioAtual.buscarPlaylist(nome) == null) {
            Playlist p = new Playlist(nome);
            if (p.getNome() != null) {
                usuarioAtual.adicionarPlaylist(p);
                System.out.println("Playlist '" + nome + "' criada!");
            } else {
                System.out.println("Erro: Nome inválido.");
            }
        } else {
            System.out.println("Erro: Já existe uma playlist com esse nome.");
        }
    }

    static void gerenciarPlaylists() {
        ArrayList<Playlist> pl = usuarioAtual.getPlaylists();
        if (pl.isEmpty()) {
            System.out.println("Nenhuma playlist encontrada.");
            return;
        }

        System.out.println("\n--- SUAS PLAYLISTS ---");
        for (int i = 0; i < pl.size(); i++) {
            System.out.println((i + 1) + ". " + pl.get(i).getNome());
        }

        System.out.print("Escolha uma playlist pelo número (ou 0 para voltar): ");
        int sel = lerOpcao() - 1;

        if (sel >= 0 && sel < pl.size()) {
            Playlist selecionada = pl.get(sel);
            selecionada.exibirPlaylist();

            System.out.println("\n1. Adicionar música | 2. Remover música | 0. Voltar");
            int subOpcao = lerOpcao();

            if (subOpcao == 1) {
                listarAcervo();
                System.out.print("Escolha a música do acervo: ");
                int mSel = lerOpcao() - 1;
                if (mSel >= 0 && mSel < acervo.size()) {
                    selecionada.adicionarMusica(acervo.get(mSel));
                    System.out.println("Música adicionada!");
                }
            } else if (subOpcao == 2) {
                System.out.print("Digite o número da música para remover: ");
                int mRem = lerOpcao() - 1;
                selecionada.removerMusica(mRem);
                System.out.println("Música removida!");
            }
        }
    }
}