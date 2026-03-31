import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> acervo = new ArrayList<>();
    static Usuario usuarioAtual = new Usuario();
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public static void main(String[] args) {
        usuarioAtual.nome = "Aluno";
        usuarioAtual.playlists = new ArrayList<>();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas(acervo, "MÚSICAS CADASTRADAS NO ACERVO");
                break;
            case 3:
                menuBuscarMusica();
                break;
            case 4:
                criarPlaylist();
                break;
            case 5:
                gerenciarPlaylists();
                break;
            case 6:
                exibirEstatisticas();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Erro: O título não pode ser vazio!");
            return;
        }

        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();
        if (artista.isEmpty()) {
            System.out.println("Erro: O artista não pode ser vazio!");
            return;
        }

        System.out.print("Duração (segundos): ");
        int duracao = lerOpcao();
        if (duracao <= 0) {
            System.out.println("Erro: A duração deve ser maior que 0!");
            return;
        }

        System.out.print("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): ");
        String genero = scanner.nextLine().trim();
        String generoFormatado = validarGenero(genero);
        if (generoFormatado == null) {
            System.out.println("Erro: Gênero inválido!");
            return;
        }

        Musica novaMusica = new Musica();
        novaMusica.titulo = titulo;
        novaMusica.artista = artista;
        novaMusica.duracao = duracao;
        novaMusica.genero = generoFormatado;
        
        acervo.add(novaMusica);

        System.out.println("Música cadastrada com sucesso!");
    }

    static String validarGenero(String g) {
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            if (GENEROS_VALIDOS[i].equalsIgnoreCase(g)) {
                return GENEROS_VALIDOS[i];
            }
        }
        return null;
    }

    static void listarMusicas(ArrayList<Musica> lista, String tituloCabecalho) {
        System.out.println("\n=== " + tituloCabecalho + " ===");

        if (lista.isEmpty()) {
            System.out.println("Nenhuma música encontrada.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Musica m = lista.get(i);
            System.out.println((i + 1) + ". Título: " + m.titulo +
                    " | Artista: " + m.artista +
                    " | Duração: " + formatarDuracao(m.duracao) +
                    " | Gênero: " + m.genero);
        }
        System.out.println("Total: " + lista.size() + " música(s)");
    }

    static void menuBuscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        System.out.println("1. Por Título");
        System.out.println("2. Por Artista");
        System.out.println("3. Por Gênero");
        System.out.print("Escolha: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                buscar("titulo");
                break;
            case 2:
                buscar("artista");
                break;
            case 3:
                buscar("genero");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    static void buscar(String tipo) {
        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine().toLowerCase();
        
        ArrayList<Musica> resultados = new ArrayList<>();
        
        for (int i = 0; i < acervo.size(); i++) {
            Musica m = acervo.get(i);
            boolean match = false;
            
            if (tipo.equals("titulo") && m.titulo.toLowerCase().contains(termo)) {
                match = true;
            } else if (tipo.equals("artista") && m.artista.toLowerCase().contains(termo)) {
                match = true;
            } else if (tipo.equals("genero") && m.genero.toLowerCase().contains(termo)) {
                match = true;
            }
            
            if (match) {
                resultados.add(m);
            }
        }
        
        listarMusicas(resultados, "RESULTADOS DA BUSCA");
    }

    static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine().trim();
        
        if (nome.isEmpty()) {
            System.out.println("Erro: O nome não pode ser vazio!");
            return;
        }
        
        Playlist novaPlaylist = new Playlist();
        novaPlaylist.nome = nome;
        novaPlaylist.musicas = new ArrayList<>();
        
        usuarioAtual.playlists.add(novaPlaylist);
        System.out.println("Playlist '" + nome + "' criada com sucesso para o usuário " + usuarioAtual.nome + "!");
    }

    static void gerenciarPlaylists() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            
            opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    listarPlaylists();
                    break;
                case 2:
                    adicionarMusicaPlaylist();
                    break;
                case 3:
                    removerMusicaPlaylist();
                    break;
                case 4:
                    exibirDetalhesPlaylist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        } while (opcao != 0);
    }

    static void listarPlaylists() {
        System.out.println("\n--- MINHAS PLAYLISTS ---");
        ArrayList<Playlist> playlists = usuarioAtual.playlists;
        if (playlists.isEmpty()) {
            System.out.println("Você ainda não tem playlists.");
            return;
        }
        
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).nome + 
                               " (" + playlists.get(i).musicas.size() + " músicas)");
        }
    }

    static Playlist selecionarPlaylist() {
        listarPlaylists();
        ArrayList<Playlist> playlists = usuarioAtual.playlists;
        if (playlists.isEmpty()) {
            return null;
        }
        
        System.out.print("Escolha o número da playlist: ");
        int indice = lerOpcao() - 1;
        
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        } else {
            System.out.println("Playlist não encontrada.");
            return null;
        }
    }

    static void adicionarMusicaPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        listarMusicas(acervo, "ACERVO DISPONÍVEL");
        if (acervo.isEmpty()) return;
        
        System.out.print("Escolha o número da música para adicionar: ");
        int indiceMusica = lerOpcao() - 1;
        
        if (indiceMusica >= 0 && indiceMusica < acervo.size()) {
            p.musicas.add(acervo.get(indiceMusica));
            System.out.println("Música adicionada à playlist '" + p.nome + "'!");
        } else {
            System.out.println("Música não encontrada.");
        }
    }

    static void removerMusicaPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        listarMusicas(p.musicas, "MÚSICAS DA PLAYLIST " + p.nome.toUpperCase());
        if (p.musicas.isEmpty()) return;
        
        System.out.print("Escolha o número da música para remover: ");
        int indiceMusica = lerOpcao() - 1;
        
        if (indiceMusica >= 0 && indiceMusica < p.musicas.size()) {
            p.musicas.remove(indiceMusica);
            System.out.println("Música removida com sucesso!");
        } else {
            System.out.println("Música não encontrada na playlist.");
        }
    }

    static void exibirDetalhesPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        listarMusicas(p.musicas, "DETALHES DA PLAYLIST: " + p.nome.toUpperCase());
    }

    static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        int total = acervo.size();
        System.out.println("Total de músicas no acervo: " + total);
        System.out.println("Total de playlists criadas: " + usuarioAtual.playlists.size());

        if (total == 0) {
            return;
        }

        int somaDuracao = 0;
        for (int i = 0; i < acervo.size(); i++) {
            somaDuracao += acervo.get(i).duracao;
        }
        
        System.out.println("Duração total do acervo: " + formatarDuracao(somaDuracao));
        System.out.println("Duração média: " + formatarDuracao(somaDuracao / total));

        int[] contadores = new int[GENEROS_VALIDOS.length];

        for (int i = 0; i < acervo.size(); i++) {
            String generoDaMusica = acervo.get(i).genero;
            
            for (int j = 0; j < GENEROS_VALIDOS.length; j++) {
                if (generoDaMusica.equalsIgnoreCase(GENEROS_VALIDOS[j])) {
                    contadores[j]++;
                    break;
                }
            }
        }

        int maximo = 0;
        String generoMaisCadastrado = "";
        
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] > maximo) {
                maximo = contadores[i];
                generoMaisCadastrado = GENEROS_VALIDOS[i];
            }
        }

        if (maximo > 0) {
            System.out.println("Gênero mais cadastrado: " + generoMaisCadastrado + " (" + maximo + " músicas)");
        }
    }

    static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }
}