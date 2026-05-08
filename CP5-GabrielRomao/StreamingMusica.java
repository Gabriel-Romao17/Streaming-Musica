import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> acervo = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAcervo();

        int opcao;
        do {
            exibirMenuInicial();
            opcao = lerOpcao();

            switch (opcao) {
                case 1: criarUsuario(); break;
                case 2: fazerLogin(); break;
                case 3: listarUsuarios(); break;
                case 4: exibirEstatisticas(); break;
                case 0: System.out.println("Saindo... Ate logo!"); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenuInicial() {
        System.out.println("\n==============================");
        System.out.println("      STREAMING DE MUSICA     ");
        System.out.println("==============================");
        System.out.println("1. Criar Novo Usuario");
        System.out.println("2. Fazer Login");
        System.out.println("3. Listar Usuarios");
        System.out.println("4. Painel de Estatisticas");
        System.out.println("0. Sair do Sistema");
        System.out.println("------------------------------");
        System.out.print("Escolha uma opcao: ");
    }

    static void criarUsuario() {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        System.out.println("\nSelecione o Tipo de Conta:");
        System.out.println("1. Plano Free (Gratis com anuncios)");
        System.out.println("2. Plano Premium (Alta qualidade)");
        int tipo = lerOpcao();

        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
            System.out.println("Conta Free criada com sucesso!");
        } else if (tipo == 2) {
            System.out.println("\n--- Escolha seu Plano Premium ---");
            System.out.println("1. Mensal   - R$ 29,99");
            System.out.println("2. Familiar - R$ 49,99");
            System.out.println("3. Anual    - R$ 89,99");
            int pOpcao = lerOpcao();
            
            String plano;
            double preco;

            switch (pOpcao) {
                case 2: plano = "Familiar"; preco = 49.99; break;
                case 3: plano = "Anual"; preco = 89.99; break;
                default: plano = "Mensal"; preco = 29.99; break;
            }
            
            usuarios.add(new UsuarioPremium(nome, email, plano, preco));
            System.out.println("Conta Premium " + plano + " criada!");
        }
    }

    static void menuUsuario() {
        int opcao;
        do {
            System.out.println("\n==============================");
            System.out.println("  CONTA: " + usuarioLogado.getNome().toUpperCase());
            System.out.println("==============================");
            System.out.println("1. Reproduzir Musica");
            System.out.println("2. Ver Meu Historico");
            System.out.println("3. Criar Nova Playlist");
            System.out.println("4. Gerar Playlist Automatica");
            
            if (usuarioLogado instanceof UsuarioPremium) {
                System.out.println("5. Baixar Musica (Offline)");
            }

            System.out.println("0. Fazer Logout");
            System.out.println("------------------------------");
            System.out.print("Sua escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1: reproduzirMusica(); break;
                case 2: usuarioLogado.exibirHistorico(); break;
                case 3: 
                    System.out.print("Nome da playlist: ");
                    usuarioLogado.criarPlaylist(scanner.nextLine());
                    break;
                case 4: gerarPlaylistAutomatica(); break;
                case 5: 
                    if (usuarioLogado instanceof UsuarioPremium) baixarMusica(); 
                    break;
            }
        } while (opcao != 0);
    }

    static void exibirEstatisticas() {
        int free = 0, premium = 0;
        double faturamento = 0;
        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree) free++;
            else if (u instanceof UsuarioPremium) {
                premium++;
                faturamento += ((UsuarioPremium) u).getPreco();
            }
        }
        System.out.println("\n========= RELATORIO =========");
        System.out.println("Usuarios Free:    " + free);
        System.out.println("Usuarios Premium: " + premium);
        System.out.printf("Faturamento:      R$ %.2f%n", faturamento);
        System.out.println("Propagandas:      " + UsuarioFree.getAnunciosExibidos());
        System.out.println("==============================");
    }

    static void inicializarAcervo() {
        acervo.add(new Musica("Flowers", "Miley Cyrus", 200, "Pop"));
        acervo.add(new Musica("Back in Black", "AC/DC", 255, "Rock"));
        acervo.add(new Musica("Titanium", "David Guetta", 245, "Eletronica"));
        acervo.add(new Musica("Blinding Lights", "The Weeknd", 200, "Pop"));
        acervo.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        acervo.add(new Musica("One More Time", "Daft Punk", 320, "Eletronica"));
        acervo.add(new Musica("Lose Yourself", "Eminem", 326, "Hip-Hop"));
        acervo.add(new Musica("Vivaldi Spring", "Max Richter", 180, "Classica"));
    }

    static void listarMusicas(ArrayList<Musica> lista) {
        System.out.println("\n--- ACERVO DISPONIVEL ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print((i + 1) + ". ");
            lista.get(i).exibir();
        }
    }

    static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine()); } 
        catch (Exception e) { return -1; }
    }

    static void listarUsuarios() {
        if (usuarios.isEmpty()) { System.out.println("Sem usuarios."); return; }
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome());
        }
    }

    static void fazerLogin() {
        listarUsuarios();
        if (usuarios.isEmpty()) return;
        System.out.print("Selecione seu usuario: ");
        int escolha = lerOpcao() - 1;
        if (escolha >= 0 && escolha < usuarios.size()) {
            usuarioLogado = usuarios.get(escolha);
            menuUsuario();
        }
    }

    static void reproduzirMusica() {
        listarMusicas(acervo);
        System.out.print("Numero da musica: ");
        int e = lerOpcao() - 1;
        if (e >= 0 && e < acervo.size()) usuarioLogado.reproduzirMusica(acervo.get(e));
    }

    static void gerarPlaylistAutomatica() {
        System.out.print("Genero (1.Pop/2.Rock/3.Eletronica): ");
        int e = lerOpcao();
        String g = (e==1)?"Pop":(e==2)?"Rock":"Eletronica";
        PlaylistAutomatica p = new PlaylistAutomatica("Mix " + g, g);
        p.atualizar(acervo);
        p.reproduzir();
    }

    static void baixarMusica() {
        listarMusicas(acervo);
        System.out.print("Baixar qual? ");
        int e = lerOpcao() - 1;
        if (e >= 0 && e < acervo.size()) ((UsuarioPremium) usuarioLogado).baixarMusica(acervo.get(e));
    }
}