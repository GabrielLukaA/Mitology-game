

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
                    System.out.println("O jogo...");
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
        Jogador jogador1 = new Jogador(sc.next(), 1, 0, 20);
        System.out.println("Jogador 2: ");
        Jogador jogador2 = new Jogador(sc.next(), 2, 80, 99);
        Random escolhePlayer = new Random();
        System.out.print("Bom, agora que conhecemos os jogadores é a hora de escolher que irá"
                + "começar o jogo, nós escolhemos na sorte que quem começará, e o escolhido foi:  ");
        int sorteio = escolhePlayer.nextInt(2);
        System.out.println(sorteio);
        String nomeDoQueInicia;
        if (sorteio == 0) {
            nomeDoQueInicia = jogador1.nome;
        } else {
            nomeDoQueInicia = jogador2.nome;
        }
        System.out.println(nomeDoQueInicia + "Você foi o escolhido para começar, tanto começar o jogo, como" +
                "pelo lado negativo, você escolherá seu esquadrão antecipadamente");
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
                jogador1.mortos += realizarJogada(jogador1);
                jogador2.mortos += realizarJogada(jogador2);
            } else {
                jogador1.mortos += realizarJogada(jogador2);
                jogador2.mortos += realizarJogada(jogador1);
            }

        } while (jogador1.mortos < jogador1.personagensEscolhidos.size() && jogador2.mortos < jogador2.personagensEscolhidos.size());
        System.out.println("Personagens player 1:" + jogador1.personagensEscolhidos.size());
        System.out.println("Personagens player 2:" + jogador2.personagensEscolhidos.size());
        if (jogador1.mortos == jogador1.personagensEscolhidos.size()) {
            System.out.println("Vitória do jogador " + jogador2.nome + "! Parabéns.");
        } else {
            System.out.println("Vitória do jogador " + jogador1.nome + "! Parabéns.");
        }


    }

    private static int realizarJogada(Jogador jogador) {
        int mortosNoRound = 0;
        int nLances = 3;

        do {

            System.out.println("Você tem " + nLances + " lances, informe a posição da peça que deseja utilizar para o seu primeiro lance?");
            printarTabuleiro();
            int pecaAMexer = sc.nextInt() - 1;
            Personagem personagemAcao = tabuleiro.getPosicoes().get(pecaAMexer).getPersonagem();
            if (personagemAcao.player == jogador.numero) {
                System.out.println("Você selecionou o " + personagemAcao.nome + "o que deseja fazer com ele?");
                System.out.println("""
                        1 - Mover
                        2 - Atacar
                        3 - Defender
                        """);
                int escolhaMovimento = sc.nextInt();
                switch (escolhaMovimento) {
                    case 1:
                        System.out.println("Seu personagem tem " + personagemAcao.movimento + " de movimento, para que lado deseja ir?");
                        System.out.println("""
                                1- Para cima
                                2 - Para baixo
                                3 - Para a esquerda
                                4 - Para a direita
                                                            
                                Importante lembrar que essa ótica é sempre de baixo para cima, então se você está em cima no mapa e deseja\n
                                 avançar, é necessário que escolha ir para baixo.
                                """);
                        int ladoQueVai = sc.nextInt();
                        System.out.println("Quantas casas? Lembre-se de que você não pode ir para uma casa que já tenha algum personagem");
                        int quantiaAAndader = sc.nextInt();
                        if (personagemAcao.mover(quantiaAAndader, tabuleiro, ladoQueVai)) {
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
                            mortosNoRound = personagemAcao.atacar(personagemAtacado, tabuleiro);
                            nLances--;
                        }
                        break;
                    case 3:
                        personagemAcao.defender(tabuleiro);
                        nLances--;
                        break;

                }

            } else {
                System.out.println("Você selecionou uma peça do adversário ou um local sem peças.");
            }
        } while (nLances > 0);

        return mortosNoRound;

    }

    private static void posicionarPecas(Jogador jogador) {
        for (int i = 0; i < jogador.personagensEscolhidos.size(); i++) {
            System.out.println("Suas casas disponíveis para posicionar suas peças são de " + jogador.casasInicio + " até " + jogador.casasFinal);
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
            if (escolhaPosicao < jogador.casasInicio || i > jogador.casasFinal) {
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

        System.out.println("Você tem direito a jogar com um Deus de sua escolha: ");
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
            System.out.println("Você possuí no momento " + jogador.elixir);
            System.out.println("Escolha o personagem que você deseja adicionar ao seu exército");
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
                    break;


            }
        } while (jogador.elixir >= 2);

        System.out.println("Sua escolha de personagens foi realizada");
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
                Arqueiro arqueiroInfo = new Arqueiro(0);
                System.out.println("Sobre o arqueiro podemos lhe informar que ele possuí:\n" +
                        arqueiroInfo.vida + " de vida.\n" +
                        arqueiroInfo.alcance + " de alcance de ataque, sendo até 2 para o dano primário e até 4 para" +
                        "o dano secundário."
                        + arqueiroInfo.ataque + " de ataque em uma curta distancia" +
                        ", tendo esse dano reduzido em 40% quando em uma maior distancia, ou seja"
                        + (arqueiroInfo.ataque / 100 * 60) + " de dano secundário.\n" +
                        arqueiroInfo.movimento + " de capacidade de movimento.\n" +
                        arqueiroInfo.defesa + " de defesa." +
                        "\n\nO Arqueiro custa para ser colocado ao seu lado no campo de batalha " + arqueiroInfo.custo + " de elixir."
                );
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                System.out.println("Você escolheu uma opção inválida, tente novamente");
                break;

        }
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
