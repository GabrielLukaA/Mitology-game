

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Tabuleiro tabuleiro = new Tabuleiro();

    public static void main(String[] args) {

        printarTabuleiro();
        boolean eu = true;
        do {
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
        } while (eu);


//        tabuleiro.constroiTabuleiro();

//        tabuleiro.constroiTabuleiro();

    }

    private static void game() {
        System.out.println("Primeiramente, nos dê seus nomes para que seja iniciado um grande jogo.");
        System.out.println("Jogador 1: ");
        Jogador jogador1 = new Jogador(sc.next(), 1, 1, 20);
        System.out.println("Jogador 2: ");
        Jogador jogador2 = new Jogador(sc.next(), 2, 81, 100);
        Random escolhePlayer = new Random();
        System.out.print("Bom, agora que conhecemos os jogadores é a hora de escolher que irá"
                + "começar o jogo, nós escolhemos na sorte que quem começará, e o escolhido foi:  \n");
        int sorteio = escolhePlayer.nextInt(2);
        String nomeDoQueInicia;
        if (sorteio == 0) {
            nomeDoQueInicia = jogador1.nome;
        } else {
            nomeDoQueInicia = jogador2.nome;
        }
        System.out.println(nomeDoQueInicia + ", você foi o escolhido para começar, tanto começar o jogo, como" +
                "pelo lado negativo, você escolherá seu esquadrão antecipadamente.");
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
        System.out.println("Agora é a hora de posicionar suas peças, o tabuleiro segue sempre a linha pela horizontal, \n" +
                "então apenas nos diga onde deseja alocar a sua peça, Ok?");
        posicionarPecas(jogador1);
        posicionarPecas(jogador2);
        do {
            if (sorteio == 0) {
                realizarJogada(jogador1, jogador2);
                if (jogador2.mortos == jogador2.personagensEscolhidos.size()) {
                    System.out.println("Vitória do jogador " + jogador1.nome + "! Parabéns.\n\n\n");
                }
                realizarJogada(jogador2, jogador1);
                if (jogador1.mortos == jogador1.personagensEscolhidos.size()) {
                    System.out.println("Vitória do jogador " + jogador2.nome + "! Parabéns.\n\n\n");
                }

            } else {
                realizarJogada(jogador2, jogador1);
                if (jogador1.mortos == jogador1.personagensEscolhidos.size()) {
                    System.out.println("Vitória do jogador " + jogador2.nome + "! Parabéns.\n\n\n");
                }
                realizarJogada(jogador1, jogador2);
                if (jogador2.mortos == jogador2.personagensEscolhidos.size()) {
                    System.out.println("Vitória do jogador " + jogador1.nome + "! Parabéns.\n\n\n");
                }

            }

        } while (jogador1.mortos < jogador1.personagensEscolhidos.size() && jogador2.mortos < jogador2.personagensEscolhidos.size());

    }

    private static int realizarJogada(Jogador jogador, Jogador adversario) {
        int mortosNoRound = 0;
        String mortosNaJogada = "";
        int nLances = 3;

        do {
            System.out.println("Todas as peças que possuem o número " + jogador.numero + " são suas " + jogador.nome);
            System.out.println("Você tem " + nLances + " lance(s), informe a posição da peça que deseja utilizar para o  lance?");

            printarTabuleiro();
            int pecaAMexer = sc.nextInt() - 1;
            Personagem personagemAcao = tabuleiro.getPosicoes().get(pecaAMexer).getPersonagem();
            if (personagemAcao == null) {
                System.out.println("Não existem peças nesse local");
            } else if (personagemAcao.player == jogador.numero) {
                System.out.println("Você selecionou o " + personagemAcao.nome + ", o que deseja fazer com ele?");
                System.out.println("""
                        1 - Mover
                        2 - Atacar
                        3 - Defender
                        """);
                int escolhaMovimento = sc.nextInt();
                switch (escolhaMovimento) {
                    case 1:
                        System.out.println("Seu personagem tem " + personagemAcao.movimento + " de capacidade de movimento, para que lado deseja ir?");
                        System.out.println("""
                                1- Para cima
                                2 - Para baixo
                                3 - Para a esquerda
                                4 - Para a direita
                                                            
                                Importante lembrar que essa ótica é sempre de baixo para cima, então se você está em cima no mapa e deseja avançar, é necessário que escolha ir para baixo.
                                """);
                        int ladoQueVai = sc.nextInt();
                        System.out.println("Quantas casas? Lembre-se de que você não pode ir para uma casa que já tenha algum personagem");
                        int quantiaAAndar = sc.nextInt();
                        if (quantiaAAndar > personagemAcao.movimento) {
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
                        } else if (personagemAtacado.player == jogador.numero) {
                            System.out.println("Não é possível atacar uma peça aliada.");
                        } else {
                            mortosNaJogada = personagemAcao.atacar(personagemAtacado, tabuleiro, adversario);
                            System.out.println("Mortos na jogada = " + mortosNaJogada);
                            if (mortosNaJogada == "não dá") {
                                System.out.println("Tem alguma peça no seu caminho, elimine ela primeiro.");
                            } else if (mortosNaJogada == "bateu") {
                                System.out.println("Golpe aplicado com sucesso");
                                nLances--;
                            } else if (mortosNaJogada == "finishGame") {
                                nLances = 0;
                            } else if (mortosNaJogada == "ataque na diagonal") {
                                System.out.println("Você tentou realizar um ataque na diagonal, ou tentou atacar uma pea que não está no seu alcance");
                            } else {
                                System.out.println("Você matou uma tropa inimiga, parabéns.");
                                nLances--;
                                mortosNoRound += Integer.parseInt(mortosNaJogada);
                                System.out.println("Mortos no round até então = " + mortosNoRound);
                            }
                        }
                        break;
                    case 3:
                        personagemAcao.defender(tabuleiro);
                        nLances--;
                        break;

                }

            } else {
                System.out.println("Você selecionou uma peça do adversário.");
            }
        } while (nLances > 0);

        return mortosNoRound;

    }

    private static void posicionarPecas(Jogador jogador) {
        for (int i = 0; i < jogador.personagensEscolhidos.size(); i++) {
            System.out.println(jogador.nome + ", suas casas disponíveis para posicionar suas peças são de " + jogador.casasInicio + " até " + jogador.casasFinal);
            System.out.println("Onde você deseja posicionar seu: " + jogador.personagensEscolhidos.get(i).nome + "?");
            for (int j = 0; j < 100; j++) {
                if ((j + 1) % 10 == 0) {
                    if (j < 10) {
                        System.out.print(" 0" + (j + 1) + " |");
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
            int escolhaPosicao = sc.nextInt() - 1;
            if (escolhaPosicao < jogador.casasInicio - 1 || escolhaPosicao > jogador.casasFinal - 1) {
                System.out.println("Escolha uma casa válida.");
                i--;
            } else {
                tabuleiro.setPosicoes(escolhaPosicao, jogador.personagensEscolhidos.get(i));
                if (i == jogador.personagensEscolhidos.size() - 1) {
                    printarTabuleiro();
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

        System.out.println(jogador.nome + ", você tem direito a jogar com um Deus de sua escolha: ");
        System.out.println("1 - Zeus, custo: " + zeusSelecao.custo);
        System.out.println("2 - Hades, custo: " + hadesSelecao.custo);
        System.out.println("3 - Poseidon, custo: " + poseidonSelecao.custo);
        System.out.println("4 - Não desejo usar nenhum Deus. ");
        int opcaoDeus = sc.nextInt();
        switch (opcaoDeus) {
            case 1:
                jogador.personagensEscolhidos.add(new Zeus(jogador.numero));
                jogador.elixir -= zeusSelecao.custo;
                deuses = 1;
                break;
            case 2:
                jogador.personagensEscolhidos.add(new Hades(jogador.numero));
                jogador.elixir -= hadesSelecao.custo;
                deuses = 1;
                break;
            case 3:
                jogador.personagensEscolhidos.add(new Poseidon(jogador.numero));
                jogador.elixir -= poseidonSelecao.custo;
                deuses = 1;
                break;
            case 4:
                deuses = -1;
                break;
        }
        do {
            System.out.println("Caro(a) " + jogador.nome + ", você possuí no momento " + jogador.elixir + " de elixir.");
            System.out.println("Escolha o personagem que você deseja adicionar ao seu exército:");
            System.out.println("1 - Arqueiro, custo: " + arqueiroSelecao.custo);
            System.out.println("2 - Centauro, custo: " + centauroSelecao.custo);
            System.out.println("3 - Ninfa do Bosque, custo: " + ninfaDoBosqueSelecao.custo);
            System.out.println("4 - Estou satisfeito com o que tenho. ");
            System.out.println("0 - Não quero mais jogar");
            int opcaoPersona = sc.nextInt();
            switch (opcaoPersona) {
                case 1:
                    jogador.personagensEscolhidos.add(new Arqueiro(jogador.numero));
                    jogador.elixir -= arqueiroSelecao.custo;
                    break;
                case 2:
                    jogador.personagensEscolhidos.add(new Centauro(jogador.numero));
                    jogador.elixir -= centauroSelecao.custo;
                    break;
                case 3:
                    jogador.personagensEscolhidos.add(new NinfaDoBosque(jogador.numero));
                    jogador.elixir -= ninfaDoBosqueSelecao.custo;
                    break;
                case 4:
                    jogador.elixir = 0;
                    break;
                case 0:
                    System.out.println("Jogo encerrado.");
                    System.exit(0);
                    break;


            }
        } while (jogador.elixir >= 2);

        System.out.println("Sua escolha de personagens foi realizada. Boa sorte " + jogador.nome + "!\n\n");
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
        System.out.println("Sobre o " + personagem.nome + " podemos lhe informar que ele possuí:\n" +
                personagem.vida + " de vida.\n" +
                personagem.alcance + " de alcance de ataque.\n"
                + personagem.ataque + " de ataque .\n" +
                personagem.movimento + " de capacidade de movimento.\n" +
                personagem.defesa + " de defesa." +
                "\n\nO " + personagem.nome + " custa para ser colocado ao seu lado no campo de batalha " + personagem.custo + " de elixir.\n\n"
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
