import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> acervo = new ArrayList<>(); 
    static Usuario usuarioAtual; 
    static Scanner scanner = new Scanner(System.in);
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public static void main(String[] args) {
        inicializarAcervo();
        int opcao;
        fazerLogin();
        do {   
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);

        } while (opcao != 0);

        System.out.println("\nAté logo!");
        scanner.close();
    }

    static void inicializarAcervo() {
        System.out.println("Carregando novo acervo de músicas...");
        
        acervo.add(new Musica("Flowers", "Miley Cyrus", 200, "Pop"));
        acervo.add(new Musica("Back in Black", "AC/DC", 255, "Rock"));
        acervo.add(new Musica("My Favorite Things", "John Coltrane", 821, "Jazz"));
        acervo.add(new Musica("Titanium", "David Guetta", 245, "Eletrônica"));
        acervo.add(new Musica("HUMBLE.", "Kendrick Lamar", 177, "Hip-Hop"));
        acervo.add(new Musica("As Quatro Estações: Verão", "Vivaldi", 175, "Clássica"));
        acervo.add(new Musica("Blinding Lights", "The Weeknd", 200, "Pop"));
        acervo.add(new Musica("Everlong", "Foo Fighters", 250, "Rock"));
    }

    static void fazerLogin() {
        System.out.println("\n=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite o seu nome: ");
        String nomeUsuarioAtual = scanner.nextLine();
        System.out.print("Digite o seu email: ");
        String emailUsuarioAtual = scanner.nextLine();
        System.out.println("Escolha seu tipo de conta: ");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipoUsuario = lerOpcao();

        if (tipoUsuario == 1) {
            usuarioAtual = new UsuarioFree(nomeUsuarioAtual, emailUsuarioAtual);
        } else if (tipoUsuario == 2){
            System.out.println("\nEscolha o plano Premium:");
            System.out.println("1. Mensal (R$ 29,90)");
            System.out.println("2. Anual (R$ 99,00)");
            System.out.println("3. Familiar (R$ 49,90)");
            System.out.print("Escolha: ");
            int opPlano = lerOpcao();
            
            String planoEscolhido = "Mensal";
            if (opPlano == 2) planoEscolhido = "Anual";
            if (opPlano == 3) planoEscolhido = "Familiar";

            usuarioAtual = new UsuarioPremium(nomeUsuarioAtual, emailUsuarioAtual, planoEscolhido);
            System.out.println("Conta Premium criada com sucesso!");
        }
    }

    static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
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
            System.out.println("4. Fazer upgrade para Premium");
        }
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
        if (usuarioAtual instanceof UsuarioPremium) {
            switch (opcao) {
                case 1: {
                    listarMusicas(acervo, "MÚSICAS");
                    System.out.print("Selecione a música desejada: ");
                    int musicaDesejada = lerOpcao() - 1;
                    if (musicaDesejada < acervo.size() && musicaDesejada >= 0) {
                        Musica m = acervo.get(musicaDesejada);
                        usuarioAtual.reproduzirMusica(m); 
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                }              
                case 2: usuarioAtual.exibirHistorico(); break;
                case 3:
                    System.out.print("Escolha o nome da playlist: ");
                    String nomePlaylist = scanner.nextLine();
                    usuarioAtual.criarPlaylist(nomePlaylist);
                    break;
                case 4:
                    listarMusicas(acervo, "MÚSICAS");
                    System.out.print("Selecione a música desejada: ");
                    int musicaDesejada = lerOpcao() - 1;
                    if (musicaDesejada < acervo.size() && musicaDesejada >= 0) {
                        Musica m = acervo.get(musicaDesejada);
                        ((UsuarioPremium)usuarioAtual).baixarMusica(m); 
                    }
                    break;
                case 5: ((UsuarioPremium)usuarioAtual).listarMusicasBaixadas(); break;
                case 0: break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } else {
            switch(opcao) {
                case 1: 
                    listarMusicas(acervo, "MÚSICAS");
                    System.out.print("Selecione a música desejada: ");
                    int musicaDesejada = lerOpcao() - 1;
                    if (musicaDesejada < acervo.size() && musicaDesejada >= 0) {
                        Musica m = acervo.get(musicaDesejada);
                        usuarioAtual.reproduzirMusica(m); 
                    } else {
                        System.out.println("Opção inválida");
                    }              
                    break;
                case 2: usuarioAtual.exibirHistorico(); break;
                case 3:
                    System.out.print("Escolha o nome da playlist: ");
                    String nomePlaylist = scanner.nextLine();
                    usuarioAtual.criarPlaylist(nomePlaylist);
                    break;
                case 4: fazerLogin();  break;
                case 0: break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } 
    }

    static void listarMusicas(ArrayList<Musica> lista, String tituloCabecalho) {
        System.out.println("\n=== " + tituloCabecalho + " ===");
        if (lista.isEmpty()) {
            System.out.println("Nenhuma música encontrada.");
            return;
        }
        for (int indice = 0; indice < lista.size(); indice++) {
             System.out.printf("%d. Nome: %s | Artista: %s | Duração: %d segundos | Gênero: %s%n"
                , (indice + 1), lista.get(indice).getTitulo(), lista.get(indice).getArtista(), 
                lista.get(indice).getDuracao(), lista.get(indice).getGenero()
            );
        }
        System.out.println("Total: " + lista.size() + " música(s)");
    }
}