import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {

        do {
            menuInicial();
        } while (true);

    }

    private static void menuInicial() {
        System.out.println("""
                Bem vindo a sua tela inicial, o que deseja realizar?
                                    
                1 - Começar um jogo
                2 - Conhecer os personagens
                3 - Entender o jogo
                4 - Encerrar aplicação
                """);
        int opcaoInicio = sc.nextInt();
        switch (opcaoInicio) {
            case 1:
                game();
                break;
            case 2:
                infoPersonagens();
                break;
            case 3:
                System.out.println("""
                         O jogo consiste básicamente em uma guerra estratégica, onde seu objetivo é eliminar todas as tropas inimigas para sair com a vitória.
                         Suas peças são identificadas no game pela primeira letra, seguida do número do jogador a quem ela pertence, e por fim possui-se um V - "quantiaDeVida"
                         é assim que aparecerá no seu tabuleiro cada peça, recomendo que entre na aba "conhecer os personagens" para saber os artibutos de todos os personagens.
                         Desejamos que tenha uma grande diversão e saia vitorioso nas guerras.
                        """);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Opção selecionada é inválida");
                break;
        }

    }

    private static void game() {

        System.out.println("Primeiramente, nos dê seus nomes para que seja iniciado um grande jogo.");
        System.out.println("Jogador 1: ");
        Jogador jogador1 = new Jogador(sc.next(), 1, 1, 20);
        System.out.println("Jogador 2: ");
        Jogador jogador2 = new Jogador(sc.next(), 2, 81, 100);

        Random escolhePlayer = new Random();
        int sorteio = escolhePlayer.nextInt(2);
        String nomeDoQueInicia;

        if (sorteio == 0) {
            nomeDoQueInicia = jogador1.getNome();
        } else {
            nomeDoQueInicia = jogador2.getNome();
        }

        System.out.print("Será sorteado quem começará o jogo\n.\n.\n.\n" +
                "O(a) escolhido(a) foi:  " + nomeDoQueInicia + "\n");

        switch (sorteio) {
            case 0:
                escolhePersonagens(jogador1);
                escolhePersonagens(jogador2);
                break;
            case 1:
                escolhePersonagens(jogador2);
                escolhePersonagens(jogador1);
                break;
        }


        posicionarPecas(jogador1);
        posicionarPecas(jogador2);


        do {

            switch (sorteio) {

                case 0:
                    realizarJogada(jogador1, jogador2);
                    if (verificaGanhador(jogador2)) {
                        System.out.println(("Vitória do jogador " + jogador1.getNome() + "! Parabéns.\n\n\n"));
                        tabuleiro = new Tabuleiro();
                    } else {
                        realizarJogada(jogador2, jogador1);
                        if (verificaGanhador(jogador1)) {
                            System.out.println(("Vitória do jogador " + jogador2.getNome() + "! Parabéns.\n\n\n"));
                            tabuleiro = new Tabuleiro();
                        }
                    }
                    break;

                case 1:
                    realizarJogada(jogador2, jogador1);
                    if (verificaGanhador(jogador1)) {
                        System.out.println(("Vitória do jogador " + jogador2.getNome() + "! Parabéns.\n\n\n"));
                        tabuleiro = new Tabuleiro();
                    } else {
                        realizarJogada(jogador1, jogador2);
                        if (verificaGanhador(jogador2)) {
                            System.out.println(("Vitória do jogador " + jogador1.getNome() + "! Parabéns.\n\n\n"));
                            tabuleiro = new Tabuleiro();
                        }
                    }
                    break;
            }

        } while (jogador1.getMortos() < jogador1.getPersonagensEscolhidos().size() && jogador2.getMortos() < jogador2.getPersonagensEscolhidos().size());
    }

    private static boolean verificaGanhador(Jogador oponente) {
        if (oponente.getMortos() == oponente.getPersonagensEscolhidos().size()) {
            return true;
        }
        return false;
    }

    private static int realizarJogada(Jogador jogador, Jogador adversario) {
        int mortosNoRound = 0;
        String mortosNaJogada = "";
        int nLances = 3;

        do {

            System.out.println("Todas as peças que possuem o número " + jogador.getNumero() + " são suas " + jogador.getNome());
            System.out.println("Você tem " + nLances + " lance(s), informe a posição da peça que deseja utilizar para o  lance?");
            printarTabuleiro();

            int pecaAMexer = sc.nextInt() - 1;
            Personagem personagemAcao = tabuleiro.getPosicoes().get(pecaAMexer).getPersonagem();

            if (personagemAcao == null) {
                System.out.println("Não existem peças nesse local");
            } else if (personagemAcao.getPlayer() == jogador.getNumero()) {

                System.out.println("Você selecionou o " + personagemAcao.getNome() + ", o que deseja fazer com ele?");
                System.out.println("""
                        1 - Mover
                        2 - Atacar
                        3 - Defender
                        """);
                int escolhaMovimento = sc.nextInt();

                switch (escolhaMovimento) {

                    case 1:
                        System.out.println("Seu personagem tem " + personagemAcao.getMovimento() + " de capacidade de movimento, para que lado deseja ir?");
                        System.out.println("""
                                1- Para cima
                                2 - Para baixo
                                3 - Para a esquerda
                                4 - Para a direita
                                                            
                                Importante lembrar que essa ótica é sempre de baixo para cima, então se você está em cima no mapa e deseja avançar,
                                é necessário que escolha ir para baixo.
                                """);
                        int ladoQueVai = sc.nextInt();

                        System.out.println("Quantas casas? Lembre-se de que você não pode ir para uma casa que já tenha algum personagem");
                        int quantiaAAndar = sc.nextInt();

                        if (quantiaAAndar > personagemAcao.getMovimento()) {
                            System.out.println("A capacidade de movimento é inferior à quantia que você deseja andar");

                        } else if (personagemAcao.mover(quantiaAAndar, tabuleiro, ladoQueVai)) {
                            nLances--;

                        } else {
                            System.out.println("Seu movimento não foi válido.");
                        }
                        break;

                    case 2:
                        System.out.println("Informe a peça que deseja atacar");
                        int pecaAAtacar = sc.nextInt() - 1;
                        Personagem personagemAtacado = tabuleiro.getPosicoes().get(pecaAAtacar).getPersonagem();
                        if (personagemAtacado == null) {
                            System.out.println("Não existe nenhuma peça nessa posição");
                        } else if (personagemAtacado.getPlayer() == jogador.getNumero()) {
                            System.out.println("Não é possível atacar uma peça aliada.");
                        } else {
                            if (personagemAcao.atacar(personagemAtacado, tabuleiro, adversario)) {
                                nLances--;
                                if (tabuleiro.getPosicoes().get(pecaAAtacar).getPersonagem() == null) {
                                    System.out.println("Você matou o inimigo, parabéns.");
                                    if (adversario.getMortos() == adversario.getPersonagensEscolhidos().size()) {
                                        nLances = 0;
                                    }
                                } else {
                                    System.out.println("Tropa inimiga atingida com sucesso");
                                }
                            } else {
                                System.out.println("Você não conseguiu realizar o ataque, lembre-se de que não pode haver mais tropas no caminho do seu alvo \n" +
                                        "e o ataque deve ser no eixo x ou no eixo y.");
                            }
                        }
                        break;
                    case 3:
                       if (personagemAcao.defender(tabuleiro)){
                           System.out.println("Lance realizado com sucesso, ajudou sua tropa aliada.");
                           nLances--;
                       } else {
                           System.out.println("Não havia ninguém para ser curado alí, jogue novamente.");
                       }
                        break;

                }

            } else {
                System.out.println("Você selecionou uma peça do adversário.");
            }
        } while (nLances > 0);

        return mortosNoRound;

    }

    private static void posicionarPecas(Jogador jogador) {
        System.out.println("Agora é a hora de posicionar peças, o tabuleiro segue sempre a linha pela horizontal, \n" +
                "então pense com sabedoria e nos informe a posição em que deseja colocar sua peça.");

        for (int i = 0; i < jogador.getPersonagensEscolhidos().size(); i++) {

            System.out.println(jogador.getNome() + ", suas casas disponíveis para posicionar suas peças são de " + jogador.getCasasInicio() + " até " + jogador.getCasasFinal());
            System.out.println("Onde você deseja posicionar seu: " + jogador.getPersonagensEscolhidos().get(i).getNome() + "?");
            printarTabuleiroVazio();

            int escolhaPosicao = sc.nextInt() - 1;
            if (escolhaPosicao < jogador.getCasasInicio() - 1 || escolhaPosicao > jogador.getCasasFinal() - 1) {
                System.out.println("Escolha uma casa válida.");
                i--;
            } else {
                tabuleiro.setPosicoes(escolhaPosicao, jogador.getPersonagensEscolhidos().get(i));
                if (i == jogador.getPersonagensEscolhidos().size() - 1) {
                    printarTabuleiro();
                }
            }
        }
    }

    private static void printarTabuleiroVazio() {
        for (int j = 0; j < 100; j++) {
            if ((j + 1) % 10 == 0) {
                if (j < 10) {
                    System.out.println(" 0" + (j + 1) + " |");
                } else {
                    System.out.println(" " + (j + 1) + " |");
                }
            } else if ((j % 10 == 0)) {
                if (j < 10) {
                    System.out.print("| 0" + (j + 1) + " |");
                } else {
                    System.out.print("| " + (j + 1) + " |");
                }
            } else {
                if (j < 10) {
                    System.out.print(" 0" + (j + 1) + " |");
                } else {
                    System.out.print(" " + (j + 1) + " |");
                }
            }
        }
    }

    private static void escolhePersonagens(Jogador jogador) {
        Personagem arqueiroSelecao = new Arqueiro(0);
        Personagem centauroSelecao = new Centauro(0);
        Personagem ninfaDoBosqueSelecao = new NinfaDoBosque(0);
        Deus zeusSelecao = new Zeus(0);
        Deus hadesSelecao = new Hades(0);
        Deus poseidonSelecao = new Poseidon(0);
        int deuses = 0;

        System.out.println(jogador.getNome() + ", você tem direito a jogar com um Deus de sua escolha: ");
        System.out.println("1 - Zeus, custo: " + zeusSelecao.getCusto());
        System.out.println("2 - Hades, custo: " + hadesSelecao.getCusto());
        System.out.println("3 - Poseidon, custo: " + poseidonSelecao.getCusto());
        System.out.println("4 - Não desejo usar nenhum Deus. ");
        int opcaoDeus = sc.nextInt();
        switch (opcaoDeus) {
            case 1:
                jogador.getPersonagensEscolhidos().add(new Zeus(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - zeusSelecao.getCusto());
                deuses = 1;
                break;
            case 2:
                jogador.getPersonagensEscolhidos().add(new Hades(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - hadesSelecao.getCusto());
                deuses = 1;
                break;
            case 3:
                jogador.getPersonagensEscolhidos().add(new Poseidon(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - poseidonSelecao.getCusto());
                deuses = 1;
                break;
            case 4:
                deuses = -1;
                break;
            default:
                System.out.println("Você selecionou uma opção inválida");
                break;
        }
        do {
            System.out.println("Caro(a) " + jogador.getNome() + ", você possuí no momento " + jogador.getElixir() + " de elixir.");
            System.out.println("Escolha o personagem que você deseja adicionar ao seu exército:");
            System.out.println("1 - Arqueiro, custo: " + arqueiroSelecao.getCusto());
            System.out.println("2 - Centauro, custo: " + centauroSelecao.getCusto());
            System.out.println("3 - Ninfa do Bosque, custo: " + ninfaDoBosqueSelecao.getCusto());
            System.out.println("4 - Estou satisfeito com o que tenho. ");
            System.out.println("0 - Não quero mais jogar");
            int opcaoPersona = sc.nextInt();
            switch (opcaoPersona) {
                case 1:
                    jogador.getPersonagensEscolhidos().add(new Arqueiro(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - arqueiroSelecao.getCusto());
                    break;
                case 2:
                    jogador.getPersonagensEscolhidos().add(new Centauro(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - centauroSelecao.getCusto());
                    break;
                case 3:
                    jogador.getPersonagensEscolhidos().add(new NinfaDoBosque(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - ninfaDoBosqueSelecao.getCusto());
                    break;
                case 4:
                    jogador.setElixir(0);
                    break;
                case 0:
                    System.out.println("Jogo encerrado.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Você selecionou uma opção inválida.");
                    break;

            }
        } while (jogador.getElixir() >= 2);

        System.out.println("Sua escolha de personagens foi realizada. Boa sorte " + jogador.getNome() + "!\n\n");
    }

    private static void infoPersonagens() {
        System.out.println("""
                Informe qual personagem você deseja conhecer melhor: 
                1 - Arqueiro
                2 - Centauro
                3 - Ninfa do bosque
                4 - Zeus
                5 - Poseidon
                6 - Hades
                7 - Retornar ao Menu Inicial              
                """);
        int opcaoPersonagem = sc.nextInt();
        switch (opcaoPersonagem) {
            case 1:
                mostrarInfo(new Arqueiro(0));
                break;
            case 2:
                mostrarInfo(new Centauro(0));
                break;
            case 3:
                mostrarInfo(new NinfaDoBosque(0));
                break;
            case 4:
                mostrarInfo(new Zeus(0));
                break;
            case 5:
                mostrarInfo(new Poseidon(0));
                break;
            case 6:
                mostrarInfo(new Hades(0));
                break;
            case 7:
                break;
            default:
                System.out.println("Você escolheu uma opção inválida, tente novamente");
                break;

        }
    }

    private static void mostrarInfo(Personagem personagem) {
        System.out.println("Sobre o " + personagem.getNome() + " podemos lhe informar que ele possuí:\n" +
                personagem.getVidaMax() + " de vida.\n" +
                personagem.getAlcance() + " de alcance de ataque.\n"
                + personagem.getAtaque() + " de ataque .\n" +
                personagem.getMovimento() + " de capacidade de movimento.\n" +
                personagem.getDefesa() + " de defesa." +
                "\n\nO " + personagem.getNome() + " custa para ser colocado ao seu lado no campo de batalha " + personagem.getCusto() + " de elixir.\n\n"
        );
    }

    public static void printarTabuleiro() {
        tabuleiro.getPosicoes().size();
        for (int i = 0; i < 100; i++) {
            if ((i + 1) % 10 == 0) {
                System.out.println(" " + tabuleiro.getPosicoes().get(i) + " |");
            } else if ((i % 10 == 0)) {
                System.out.print("| " + tabuleiro.getPosicoes().get(i) + " |");
            } else {
                System.out.print(" " + tabuleiro.getPosicoes().get(i) + " |");
            }

        }

    }
}
