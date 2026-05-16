package br.com.streaming.principal;

import br.com.streaming.modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe principal do programa
public class StreamingMusica {
    // Atributos que representam as classes  
    static ArrayList<Musica> acervo = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>(); 
    static Usuario usuarioAtual; 
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
    public static void main(String[] args) {
        inicializarAcervo();
        // Laço de repetição que executa a função de exibir menu, ler e processar a opção do usuário 
        int opcaoMenu = -1;
        int opcaoUsuario;
        do { 
            if (usuarioAtual == null) {                          
            exibirMenuPrincipal();
            opcaoMenu = lerOpcao();
            processarOpcao(opcaoMenu);
            } else {
                exibirMenuUsuario();
                opcaoUsuario = lerOpcao();
                processarOpcaoUsuario(opcaoUsuario);
            }

        } while (opcaoMenu != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    // Método que preenche o acervo com músicas aleatórias
    static void inicializarAcervo() {
        System.out.println("Carregando acervo inicial de músicas...");
        
        // Adicionando uma música de cada gênero válido para testes
        acervo.add(new Musica("Billie Jean", "Michael Jackson", 294, "Pop"));
        acervo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervo.add(new Musica("Take Five", "Dave Brubeck", 324, "Jazz"));
        acervo.add(new Musica("Strobe", "deadmau5", 637, "Eletrônica"));
        acervo.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
        acervo.add(new Musica("Sinfonia Nº 9 (Trecho)", "Ludwig van Beethoven", 600, "Clássica"));       
        acervo.add(new Musica("That's The Way It Is", "Daniel Lanois", 248, "Rock")); 
    }

    static void cadastroUsuario() {
        System.out.println("\n=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite seu nome: ");
        String nomeUsuarioAtual = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String emailUsuarioAtual = scanner.nextLine();
        System.out.println("Escolha seu tipo de conta: ");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipoUsuario = lerOpcao();

        // Verifica e adiciona o usuario no array de usuarios, tanto free quanto premium 
        if (tipoUsuario == 1) {
            usuarios.add(new UsuarioFree(nomeUsuarioAtual, emailUsuarioAtual));
        } else if (tipoUsuario == 2){
            System.out.println("\nEscolha o plano Premium:");
            System.out.println("1. Mensal (R$ 19,90)");
            System.out.println("2. Anual (R$ 199,00)");
            System.out.println("3. Familiar (R$ 29,90)");
            System.out.print("Escolha: ");
            int opPlano = lerOpcao();
            
            String planoEscolhido = "Mensal";
            if (opPlano == 2) planoEscolhido = "Anual";
            if (opPlano == 3) planoEscolhido = "Familiar";

            usuarios.add(new UsuarioPremium(nomeUsuarioAtual, emailUsuarioAtual, planoEscolhido));
            System.out.println("✅ Conta Premium criada com sucesso!");
        }
    }

    static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");

            System.out.println("1. Criar novo usuário");
            System.out.println("2. Login");
            System.out.println("3. Listar usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");  
    }

    // Imprime uma mensagem quando algum usuário cadastrado é logado 
    static void fazerlogin(int opcao) {
        usuarioAtual = usuarios.get(opcao);
        System.out.printf("Login realizado: %s ", usuarioAtual.getNome());
        // Verifica se o usuário é free ou premium e imprime o tipo
        if (usuarioAtual instanceof UsuarioFree) {
            System.out.println("(Free)");
        } else {
            System.out.println("(Premium)");
        }
    }

    // Método que exibe um menu conforme tipo do usuário logado
    static void exibirMenuUsuario() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");

        // Verifica se o usuário logado é premium ou free
        if (usuarioAtual instanceof UsuarioPremium) {
            System.out.println("1. Reproduzir música (Alta Qualidade)");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (Ilimitado)");
            System.out.println("4. Baixar música");
            System.out.println("5. Ver músicas baixadas");
        } else {
            System.out.println("1. Reproduzir música");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (máx. 3)");
            System.out.println("4. 💎 Fazer upgrade para Premium");
        }
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
    }

    static void listarUsuariosCadastrados(){
        int indice = 1;
        // Percorre o array de usuarios e pega o nome de cada um
        for (Usuario u : usuarios) {
            System.out.printf("%d. Nome: %s ", indice++ ,u.getNome()); 
            // Verifica se é premium ou free
            if (u instanceof UsuarioFree) {
                System.out.println("(Free)");
            } else {
                 System.out.printf("(Premium)%n");
            }
        }
    }

    static int lerOpcao() {
        // Tenta transformar a opção do usuário em integer e retorna -1 caso haja erro de formato
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Processa a opção do usuário no menu principal
    static void processarOpcao(int opcao) {
        if (usuarioAtual == null) {                 
            switch (opcao) {
                case 1:
                    cadastroUsuario();
                    break;
                case 2:
                    System.out.println("Usuários cadastrados: ");
                    listarUsuariosCadastrados();
                    System.out.print("Escolha o usuário: ");
                    int opcaoUsuario = lerOpcao();
                    fazerlogin(opcaoUsuario - 1);
                    break;
                case 3:
                    listarUsuariosCadastrados();
                    break;
                case 0: break;
                // Opção "secreta" com funções desenvolvidas nos checkpoints anteriores
                case 999:
                    int opcaoAdmin;
                    do {
                        exibirMenuAdmin();
                        opcaoAdmin = lerOpcao();
                        processarOpcaoAdmin(opcaoAdmin);
                    } while (opcaoAdmin != 0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    // Processa a opção digitada no menu de usuários
    static void processarOpcaoUsuario(int opcao) {
        switch (opcao) {
            // Lista as músicas, pede que o usuários as selecione
            case 1: {
                listarMusicas(acervo, "MÚSICAS");
                System.out.print("Selecione a música desejada: ");
                int musicaDesejada = lerOpcao() - 1;
                // Verifica se a musica desejada é um indice que está dentro do limite do array e se é maior que zero
                if (musicaDesejada < acervo.size() && musicaDesejada >= 0) {
                    Musica m = acervo.get(musicaDesejada);
                    usuarioAtual.reproduzirMusica(m); 
                    break; 
                } else {
                    System.out.println("Opção inválida");
                    break;
                }
            }              
            case 2: usuarioAtual.exibirHistorico(); break;
            case 3:
                System.out.print("Escolha o nome da playlist: ");
                String nomePlaylist = scanner.nextLine();
                usuarioAtual.criarPlaylist(nomePlaylist);
                break;
            case 4:
                if (usuarioAtual instanceof UsuarioPremium) {
                    listarMusicas(acervo, "MÚSICAS");
                    System.out.print("Selecione a música desejada: ");
                    int musicaDesejada = lerOpcao() - 1;
                    Musica m = acervo.get(musicaDesejada);
                    ((UsuarioPremium)usuarioAtual).baixar(m); // Alterado aqui!
                    break;
                }
                // Caso contrário, apenas imprime uma mensage de erro
                } else {
                    System.out.println("Função exclusiva para usuários premium!");
                }
                case 5:
                    // Só chama o método listarMusicasBaixadas se o usuário logado for premium
                    if (usuarioAtual instanceof UsuarioPremium) {
                    ((UsuarioPremium)usuarioAtual).listarMusicasBaixadas(); 
                    break;
                    } else {
                        System.out.println("Função exclusiva para usuários premium!");
                    }
                case 0: usuarioAtual = null; break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
        } 
    }
    
    static void listarMusicas(ArrayList<Musica> lista, String tituloCabecalho) {
        System.out.println("\n=== " + tituloCabecalho + " ===");

        if (lista.isEmpty()) {
            System.out.println("Nenhuma música encontrada.");
            return;
        }

        // Percorre a lista e imprime as informações de cada música
        for (int indice = 0; indice < lista.size(); indice++) {
             System.out.printf("%d. Nome: %s | Artista: %s | Duração: %d segundos | Gênero: %s%n"
                , (indice + 1), lista.get(indice).getNome(), lista.get(indice).getArtista(), 
                lista.get(indice).getDuracao(), lista.get(indice).getGenero()
            );
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

    static void criarPlaylistManual() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        
        // Tenta executar a função criarPlaylist da classe Usuario
        try {
            usuarioAtual.criarPlaylist(nome);
            System.out.println("Playlist '" + nome + "' criada com sucesso!");
        } catch (IllegalArgumentException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    static void criarPlaylistAutomatica() {
        System.out.println("=== PLAYLISTS AUTOMÁTICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.println("3. Adicionadas Recentemente");
        System.out.print("Escolha: ");
        int escolha = lerOpcao();

        System.out.print("Digite o nome desejado para a playlist: ");
        String nomePlaylist = scanner.nextLine();
        
        // Cria a playlist automatica conforme critério e adiciona no array de playlists do usuário
        if (escolha == 1) {
            System.out.println("🤖 Gerando playlist \"Top 10 Mais Tocadas\"...");
            PlaylistAutomatica playlistTop = new PlaylistAutomatica(nomePlaylist,"top");
            playlistTop.atualizar(acervo, usuarioAtual.getHistoricoReproducao());
            usuarioAtual.getPlaylists().add(playlistTop);
            System.out.println("✅ Playlist criada com 10 músicas!");
        } else if (escolha == 2) {
            System.out.println("🤖 Gerando playlist \"Recomendadas para Você\"...");
            PlaylistAutomatica playlistRecomendada = new PlaylistAutomatica(nomePlaylist,"recomendadas");
            playlistRecomendada.atualizar(acervo, usuarioAtual.getHistoricoReproducao());
            usuarioAtual.getPlaylists().add(playlistRecomendada);
            System.out.println("✅ Playlist criada!");
        } else {
            System.out.println("🤖 Gerando playlist \"Adicionadas Recentemente\"...");
            PlaylistAutomatica playlistRecente = new PlaylistAutomatica(nomePlaylist,"Recente");
            playlistRecente.atualizar(acervo, usuarioAtual.getHistoricoReproducao());
            usuarioAtual.getPlaylists().add(playlistRecente);
            System.out.println("✅ Playlist criada!");
        }
    }

    static void menuPlaylists() {
        System.out.println("\n=== GERENCIAR PLAYLISTS ===");
        System.out.println("1. Criar playlist");
        System.out.println("3. Listar minhas playlists");
        System.out.println("4. Adicionar música a uma playlist");
        System.out.println("5. Remover música de uma playlist");
        System.out.println("6. Exibir detalhes de uma playlist");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    static void gerenciarPlaylists() {
        int opcao;
        do {
            menuPlaylists();
            opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    System.out.println("1. Playlist Manual");
                    System.out.println("2. Playlist Automática");
                    int opcaoCriarPlaylist = lerOpcao();
                    if (opcaoCriarPlaylist == 1) { criarPlaylistManual(); break; } 
                    else {  break; }                 

                case 2: 
                    System.out.println("\n--- MINHAS PLAYLISTS ---");
                    // Tenta percorrer uma lista com as playlists do usuario e imprime suas informações
                    try {
                        ArrayList<Playlist> listas = usuarioAtual.getPlaylists();
                        for (int i = 0; i < listas.size(); i++) {
                            System.out.printf("%d. %s%n", i + 1, listas.get(i).getNome());
                        }                   
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;                   
                case 3: adicionarMusicaPlaylist(); break;
                case 4: removerMusicaPlaylist(); break;
                case 5: exibirDetalhesPlaylist(); break;
                case 0: break;
                default: System.out.println("Opção Inválida");
            }
        } while (opcao != 0);
    }

    static Playlist selecionarPlaylist() {
        System.out.println("\n--- MINHAS PLAYLISTS ---");
        try {
            ArrayList<Playlist> listas = usuarioAtual.getPlaylists();
            for (int i = 0; i < listas.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, listas.get(i).getNome());
            }
            System.out.print("Escolha o número da playlist: ");
            int escolha = lerOpcao() - 1;
            if (escolha >= 0 && escolha < listas.size()) {
                return listas.get(escolha);
            } else {
                System.out.println("Opção inválida.");
                return null;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            // Tenta adicionar a música na playlist desejada utilizando a lista acervo 
            try {
                p.adicionarMusica(acervo.get(indiceMusica));
                System.out.println("Música adicionada à playlist '" + p.getNome() + "'!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Música não encontrada.");
        }
    }

    static void removerMusicaPlaylist() {
        Playlist p = selecionarPlaylist();
        if (p == null) return;
        
        System.out.println("\n=== MÚSICAS DA PLAYLIST: " + p.getNome() + " ===");
        p.listarMusicas();
        
        System.out.print("Escolha o número da música para remover: ");
        int indiceMusica = lerOpcao() - 1;
        
        try {
            p.removerMusica(indiceMusica);
            System.out.println("Música removida com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void exibirDetalhesPlaylist() {
        Playlist p = selecionarPlaylist();
        // Se a playlist for nula, retorna ao menu
        if (p == null) return;
        
        System.out.println("\n=== DETALHES DA PLAYLIST: " + p.getNome().toUpperCase() + " ===");
        p.listarMusicas();
        
        int totalSegundos = p.getDuracaoTotal();
        int min = totalSegundos / 60;
        int seg = totalSegundos % 60;
        System.out.println("\nDuração total: " + String.format("%d:%02d", min, seg));
        System.out.println("Quantidade de músicas: " + p.getQuantidadeMusicas());
    }

    static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        int totalUsuarios = 0;
        int usuariosFree = 0;
        int usuariosPremium = 0;
        int reproducoesTotais = 0;
        int reproducoesFree = 0;
        int reproducoesPremium = 0;
        int anunciosExibidos = 0;

        // Percorre o array de usuarios
        for (Usuario u : usuarios) {
            // A cada elemento percorrido, acumula na variavel
            totalUsuarios++;
            // Verifica se o usuário é Free
            if (u instanceof UsuarioFree) {
                // Adiciona o usuário free ao contador
                usuariosFree++;
                // Casting para acessar as opções da superclasse Playlist         
                UsuarioFree free = (UsuarioFree) u;
                // Acessa o histórico de reprodução do usuário
                reproducoesFree += free.getHistoricoReproducao().size();
                // A cada 3 reproduções, 1 anúncio é exibido, sendo assim, é só dividir as reproduções por 3,
                // acumulando assim no contador
                anunciosExibidos += free.getHistoricoReproducao().size() / 3;    
            } else {
                // Mesmo do anterior, sem a parte dos anúncios
                usuariosPremium++;
                UsuarioPremium premium = (UsuarioPremium) u;
                reproducoesPremium += premium.getHistoricoReproducao().size();
            }
        }

        // Soma as reproduções de cada tipo de usuário
        reproducoesTotais = reproducoesFree + reproducoesPremium;

        // Imprime as estatísticas calculadas acima
        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.printf("- Free: %d usuários%n", usuariosFree);
        System.out.printf("- Premium: %d usuários%n", usuariosPremium);  
        
        System.out.println("Reproduções totais: " + reproducoesTotais);
        System.out.printf("- Free: %d reproduções%n", reproducoesFree);
        System.out.printf("- Premium: %d reproduções%n", reproducoesPremium);
        
        System.out.println("Anúncios exibidos: " + anunciosExibidos);
    }

    //Painel admin
    static void exibirMenuAdmin() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA (ADMIN) ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Editar música (NOVO)"); // Requisito do CP3
        System.out.println("5. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    static void cadastrarMusica() {
        try {
            System.out.println("\n--- CADASTRAR MÚSICA ---");

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Artista: ");
            String artista = scanner.nextLine().trim();

            System.out.print("Duração (segundos): ");
            int duracao = lerOpcao();

            System.out.print("Gênero (Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica): ");
            String genero = scanner.nextLine().trim();

            Musica novaMusica = new Musica(titulo, artista, duracao, genero);
            acervo.add(novaMusica);

            System.out.println("Música cadastrada com sucesso!");

        } catch (IllegalArgumentException erro) {
            System.out.println("\nErro ao cadastrar: " + erro.getMessage());
            System.out.println("Por favor, tente cadastrar novamente.");
        }
    } 

    static void editarMusica() {
        System.out.println("\n--- EDITAR MÚSICA ---");
        // Valida se a lista acervo está vazia
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }

        listarMusicas(acervo, "ESCOLHA A MÚSICA PARA EDITAR");
        System.out.print("Digite o número da música: ");
        int indice = lerOpcao() - 1;

        // Valida se o índice é menor que zero ou maior ou igual ao tamanho do array
        if (indice < 0 || indice >= acervo.size()) {
            System.out.println("Música não encontrada!");
            return;
        }

        // Busco a música desejada pelo índice
        Musica m = acervo.get(indice);
        
        System.out.println("Editando: " + m.getNome() + " - " + m.getArtista());
        System.out.println("1. Editar Título");
        System.out.println("2. Editar Artista");
        System.out.println("3. Editar Duração");
        System.out.println("4. Editar Gênero");
        System.out.print("O que deseja editar? ");
        int op = lerOpcao();

        try {
            switch(op) {
                case 1:
                    System.out.print("Novo título: ");
                    m.setTitulo(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Novo artista: ");
                    m.setArtista(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nova duração (segundos): ");
                    m.setDuracao(lerOpcao());
                    break;
                case 4:
                    System.out.print("Novo gênero: ");
                    m.setGenero(scanner.nextLine());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            System.out.println("Música atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
    }

    static void buscar(String tipo) {
        System.out.print("Digite o termo de busca: ");
        String termo = scanner.nextLine().toLowerCase();
        
        ArrayList<Musica> resultados = new ArrayList<>();
        
        // Percorre a lista de músicas e verifica se o termo digitado pelo usários se encontra nas informações da música
        for (int i = 0; i < acervo.size(); i++) {
            Musica m = acervo.get(i);

            // Flag de validação
            boolean achou = false;
            
            if (tipo.equals("titulo") && m.getNome().toLowerCase().contains(termo)) {
                achou = true;
            } else if (tipo.equals("artista") && m.getArtista().toLowerCase().contains(termo)) {
                achou = true;
            } else if (tipo.equals("genero") && m.getGenero().toLowerCase().contains(termo)) {
                achou = true;
            }
            
            if (achou) {
                resultados.add(m);
            }
        }
        
        listarMusicas(resultados, "RESULTADOS DA BUSCA");
    }

    static void processarOpcaoAdmin(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(acervo, "MÚSICAS CADASTRADAS NO ACERVO"); break;
            case 3: menuBuscarMusica(); break;
            case 4: editarMusica(); break;
            case 5: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("Opção Inválida");
        }
    }
}

