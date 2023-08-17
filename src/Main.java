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
                         Suas peças são identificadas no game pela primeira letra, seguida do número do jogador a quem ela pertence, e por fim possui-se um 
                         V - "quantiaDeVida" é assim que aparecerá no seu tabuleiro cada peça, recomendo que entre na aba "conhecer os personagens" para saber
                         os artibutos de todos os personagens. Desejamos que tenha uma grande diversão e saia vitorioso nas guerras.
                         
                         ALGUMAS REGRAS E DICAS BÁSICAS
                         
                         1 - Deuses
                         
                         No jogo temos 3 Deuses da mitologia grega, Hades, Poseidon e Zeus. Eles custam mais caro por serem personagens especiais e também é
                         possível ter apenas um. Usem eles com sabdoria, eles possuem poderes especiais unicos e altamente perigosos se utilizados nas mãos 
                         erradas.
                         
                         2 - Defender
                         
                         Todos os personagens tem a opção de defender, que nada mais é do que curar aliados, o Centauro cura no eixo x, o Arqueiro no eixo y,
                         a Ninfa do Bosque cura todas as casas em volta de si mesma e todos os Deuses curam tanto no eixo x como no eixo y. Porém tome muito
                         cuidado com a cura, ela cura tanto aliados quanto inimigos, use com sabedoria.
                         
                         3 - O Tabuleiro
                         
                         O Tabuleiro é composto por uma matriz 10x10, onde ela começa a contagem pelo topo na horizontal, então sempre preste atenção para
                         informar a casa correta, um erro pode lhe custar caro.
                         
                         Bom, por enquanto essas são nossas dicas, se divirta!
                         
                         
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

        Random sorteiaJogador = new Random();
        int jogadorSorteado = sorteiaJogador.nextInt(2);
        String nomeDoQueInicia;

        if (jogadorSorteado == 0) {
            nomeDoQueInicia = jogador1.getNome();
        } else {
            nomeDoQueInicia = jogador2.getNome();
        }

        System.out.print("Será sorteado quem começará o jogo\n.\n.\n.\n" +
                "O(a) escolhido(a) foi:  " + nomeDoQueInicia + "\n");

        switch (jogadorSorteado) {
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

            switch (jogadorSorteado) {

                case 0:
                    realizarJogada(jogador1, jogador2);
                    if (verificaGanhador(jogador2)) {
                        System.out.println(("Vitória do jogador(a) " + jogador1.getNome() + "! Parabéns.\n\n\n"));
                        tabuleiro = new Tabuleiro();
                    } else {
                        realizarJogada(jogador2, jogador1);
                        if (verificaGanhador(jogador1)) {
                            System.out.println(("Vitória do jogador(a) " + jogador2.getNome() + "! Parabéns.\n\n\n"));
                            tabuleiro = new Tabuleiro();
                        }
                    }
                    break;

                case 1:
                    realizarJogada(jogador2, jogador1);
                    if (verificaGanhador(jogador1)) {
                        System.out.println(("Vitória do jogador(a) " + jogador2.getNome() + "! Parabéns.\n\n\n"));
                        tabuleiro = new Tabuleiro();
                    } else {
                        realizarJogada(jogador1, jogador2);
                        if (verificaGanhador(jogador2)) {
                            System.out.println(("Vitória do jogador(a) " + jogador1.getNome() + "! Parabéns.\n\n\n"));
                            tabuleiro = new Tabuleiro();
                        }
                    }
                    break;
            }

        } while (!verificaGanhador(jogador1) && !verificaGanhador(jogador2));
//   Antigamente o while usava essa ideia:
//   jogador1.getMortos() < jogador1.getPersonagensEscolhidos().size() && jogador2.getMortos() < jogador2.getPersonagensEscolhidos().size()
    }

    private static boolean verificaGanhador(Jogador oponente) {
        if (oponente.getMortos() == oponente.getPersonagensEscolhidos().size()) {
            return true;
        }
        return false;
    }

    private static void realizarJogada(Jogador jogador, Jogador adversario) {

        int quantiaLances = 3;

        //Serve para localizar o Deus sendo utilizado e carregar a Barra de Especial do mesmo.
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPersonagem() instanceof Deus && posicao.getPersonagem().getPlayer() == jogador.getNumero()) {
                if (((Deus) posicao.getPersonagem()).getBarraEspecial() < ((Deus) posicao.getPersonagem()).getCargaEspecial()) {
                    ((Deus) posicao.getPersonagem()).setBarraEspecial(((Deus) posicao.getPersonagem()).getBarraEspecial() + 1);
                }
            }
        }

        do {

            System.out.println("Todas as peças que possuem o número " + jogador.getNumero() + " são suas " + jogador.getNome());
            System.out.println("Você tem " + quantiaLances + " lance(s), informe a posição da peça que deseja utilizar para o  lance?");
            printarTabuleiro();
            int indicepecaAMover = sc.nextInt() - 1;
            Personagem personagemSelecionado = tabuleiro.getPosicoes().get(indicepecaAMover).getPersonagem();

            if (personagemSelecionado == null) {
                System.out.println("Não existem peças nesse local");
            } else if (personagemSelecionado.getPlayer() == jogador.getNumero()) {

                //Serve para mostrar as opções dos Deuses, que são levemente diferentes.
                if (personagemSelecionado instanceof Deus) {
                    System.out.println("Você selecionou o " + personagemSelecionado.getNome() + ", o que deseja fazer com ele?");
                    System.out.println("""
                            0 - Usar outra peça
                            1 - Mover
                            2 - Atacar
                            3 - Defender
                            """);
                    if (((Deus) personagemSelecionado).getBarraEspecial() == ((Deus) personagemSelecionado).getCargaEspecial()) {
                        System.out.println("4 - Realizar Especial");
                    } else {
                        System.out.println("Carregando Especial " + ((Deus) personagemSelecionado).getBarraEspecial() + " / " + ((Deus) personagemSelecionado).getCargaEspecial());
                    }
                } else {
                    System.out.println("Você selecionou o " + personagemSelecionado.getNome() + ", o que deseja fazer com ele?");
                    System.out.println("""
                            0 - Usar outra peça
                            1 - Mover
                            2 - Atacar
                            3 - Defender
                            """);
                }

                int escolhaAcao = sc.nextInt();

                switch (escolhaAcao) {
                    case 0:
                        System.out.println("Entendido.\n\n");
                        break;
                    //Move peça
                    case 1:
                        System.out.println("Seu personagem tem " + personagemSelecionado.getMovimento() + " de capacidade de movimento, para que lado deseja ir?");
                        System.out.println("""
                                1- Para cima
                                2 - Para baixo
                                3 - Para a esquerda
                                4 - Para a direita
                                                            
                                Importante lembrar que essa ótica é sempre de baixo para cima, então se você está em cima no mapa e deseja avançar,
                                é necessário que escolha ir para baixo.
                                """);
                        int direcaoMovimento = sc.nextInt();

                        if (direcaoMovimento < 1 || direcaoMovimento > 4) {
                            System.out.println("Você inseriu uma opção inválida.");
                        } else {
                            System.out.println("Quantas casas? Lembre-se de que você não pode ir para uma casa que já tenha algum personagem");
                            int quantidadeMovimento = sc.nextInt();

                            if (quantidadeMovimento > personagemSelecionado.getMovimento()) {
                                System.out.println("A capacidade de movimento é inferior à quantia que você deseja andar");

                            } else if (personagemSelecionado.mover(quantidadeMovimento, tabuleiro, direcaoMovimento)) {
                                quantiaLances--;

                            } else {
                                System.out.println("Seu movimento não foi válido.");
                            }
                        }
                        break;

                    //Ataca peça
                    case 2:
                        System.out.println("Informe a peça que deseja atacar");
                        int indicePecaAAtacar = sc.nextInt() - 1;
                        Personagem personagemAtacado = tabuleiro.getPosicoes().get(indicePecaAAtacar).getPersonagem();

                        //verifica possíveis lances inválidos
                        if (personagemAtacado == null) {
                            System.out.println("Não existe nenhuma peça nessa posição");
                        } else if (personagemAtacado.getPlayer() == jogador.getNumero()) {
                            System.out.println("Não é possível atacar uma peça aliada.");
                        } else {

                            if (personagemSelecionado.atacar(personagemAtacado, tabuleiro, adversario)) {
                                quantiaLances--;

                                //Verifica se a peça atacada morreu para informar ao usuário.
                                if (tabuleiro.getPosicoes().get(indicePecaAAtacar).getPersonagem() == null) {
                                    System.out.println("Você matou o inimigo, parabéns.");
                                    if (adversario.getMortos() == adversario.getPersonagensEscolhidos().size()) {
                                        quantiaLances = 0;
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

                    // Defende peça, o que no game significa curar aliados.
                    case 3:
                        if (personagemSelecionado.defender(tabuleiro)) {
                            System.out.println("Lance realizado com sucesso, ajudou sua tropa aliada.");
                            quantiaLances--;
                        } else {
                            System.out.println("Não havia ninguém para ser curado alí, jogue novamente.");
                        }
                        break;

                    //Tem utilidade em caso de ser um Deus selecionado, serve para executar o especial.
                    case 4:
                        boolean especialUtilizado;

                        //Verifica se o personagem escolhido é um Deus
                        if (personagemSelecionado instanceof Deus) {

                            //Verifica qual Deus é, pois, caso seja hades é passado como parametro o jogador que está
                            // realizando uma ação, caso contrário o adversário.
                            if (personagemSelecionado instanceof Hades) {
                                especialUtilizado = ((Hades) personagemSelecionado).realizarEspecial(tabuleiro, jogador);
                            } else {
                                especialUtilizado = ((Deus) personagemSelecionado).realizarEspecial(tabuleiro, adversario);
                            }

                            //Verifica se tudo ocorreu corretamente e printa uma frase específica de cada Deus ao usar o especial
                            if (especialUtilizado) {
                                ((Deus) personagemSelecionado).setBarraEspecial(0);
                                System.out.println(fraseParaOEspecial(personagemSelecionado, jogador, tabuleiro));
                                quantiaLances--;

                                //Verifica se ainda tem inimigos para combater ou o jogo deve ser encerrado.
                                if (adversario.getMortos() == adversario.getPersonagensEscolhidos().size()) {
                                    quantiaLances = 0;
                                }
                            } else {
                                System.out.println("Algo aconteceu e não foi possível utilizar o especial.");
                            }

                            //Caso não seja um Deus.
                        } else {
                            System.out.println("Escolha inválida, tente novamente");
                        }
                        break;

                    default:
                        System.out.println("Escolha inválida, tente novamente");
                        break;
                }

            } else {
                System.out.println("Você selecionou uma peça do adversário.");
            }
        } while (quantiaLances > 0);
    }

    private static String fraseParaOEspecial(Personagem personagemSelecionado, Jogador jogador, Tabuleiro tabuleiro) {

        if (personagemSelecionado instanceof Hades) {
            return "Os mortos lhe ajudaram, cura concedida do submundo";
        } else if (personagemSelecionado instanceof Poseidon) {
            if (procuraDeusAdversario(tabuleiro, jogador) != null) {

                //A frase para Zeus contém uma "piadinha" pois quando poseidon atinge zeus, retira uma parte da barra de especial dele
                if (procuraDeusAdversario(tabuleiro, jogador) instanceof Zeus) {
                    return "Tsunami na área, acho que eles ficaram gripados, devido a baixa imunidade, todos ficaram sem defesa.\n" +
                            "AH, quase esqueci de falar, um certo Deus aí entrou em curto circuito.";
                } else {
                    return "Tsunami na área, acho que eles ficaram gripados, devido a baixa imunidade, todos ficaram sem defesa.";
                }
            }
        } else if (personagemSelecionado instanceof Zeus) {
            if (procuraDeusAdversario(tabuleiro, jogador) != null) {

                //A frase para Poseidon também contém uma "piadinha" pois quando zeus atinge poseidon, retira o dobro de dano
                if (procuraDeusAdversario(tabuleiro, jogador) instanceof Poseidon) {
                    return "Estão em choque? Dano de eletrocutamento em todos.\n" +
                            "O 'Aquaman' sentiu um pouco mais do impacto.";
                } else {
                    return "Estão em choque? Dano de eletrocutamento em todos.";
                }
            }
        }
        return "Algo indevido aconteceu";
    }

    private static Personagem procuraDeusAdversario(Tabuleiro tabuleiro, Jogador jogador) {

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPersonagem() instanceof Deus) {
                if (posicao.getPersonagem().getPlayer() != jogador.getNumero()) {
                    return posicao.getPersonagem();
                }
            }

        }
        return null;
    }

    private static void posicionarPecas(Jogador jogador) {

        System.out.println("Agora é a hora de posicionar peças, o tabuleiro segue sempre a linha pela horizontal, \n" +
                "então pense com sabedoria e nos informe a posição em que deseja colocar sua peça.");

        for (int i = 0; i < jogador.getPersonagensEscolhidos().size(); i++) {

            System.out.println(jogador.getNome() + ", suas casas disponíveis para posicionar suas peças são de " + jogador.getCasasInicio() + " até " + jogador.getCasasFinal());
            System.out.println("Onde você deseja posicionar seu: " + jogador.getPersonagensEscolhidos().get(i).getNome() + "?");
            printarTabuleiroVazio();
            int escolhaPosicionamentoTropa = sc.nextInt() - 1;

            if (escolhaPosicionamentoTropa < jogador.getCasasInicio() - 1 || escolhaPosicionamentoTropa > jogador.getCasasFinal() - 1) {
                System.out.println("Escolha uma casa válida.");
                i--;
            } else {
                if (tabuleiro.getPosicoes().get(escolhaPosicionamentoTropa).getPersonagem() != null) {
                    System.out.println("Tem uma peça sua aqui já, não é possível escolher essa casa.");
                    i--;
                } else {
                    tabuleiro.setPosicoes(escolhaPosicionamentoTropa, jogador.getPersonagensEscolhidos().get(i));
                }
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

        Personagem selecionarArqueiro = new Arqueiro(0);
        Personagem selecionarCentauro = new Centauro(0);
        Personagem selecionarNinfaDoBosque = new NinfaDoBosque(0);
        Deus selecionarZeus = new Zeus(0);
        Deus selecionarHades = new Hades(0);
        Deus selecionarPoseidon = new Poseidon(0);
        int quantiaDeus = 0;

        System.out.println(jogador.getNome() + ", você tem direito a jogar com um Deus de sua escolha: ");
        System.out.println("1 - Zeus, custo: " + selecionarZeus.getCusto());
        System.out.println("2 - Hades, custo: " + selecionarHades.getCusto());
        System.out.println("3 - Poseidon, custo: " + selecionarPoseidon.getCusto());
        System.out.println("4 - Não desejo usar nenhum Deus. ");
        int escolhaDeus = sc.nextInt();

        switch (escolhaDeus) {
            case 1:
                jogador.getPersonagensEscolhidos().add(new Zeus(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - selecionarZeus.getCusto());
                quantiaDeus = 1;
                break;

            case 2:
                jogador.getPersonagensEscolhidos().add(new Hades(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - selecionarHades.getCusto());
                quantiaDeus = 1;
                break;

            case 3:
                jogador.getPersonagensEscolhidos().add(new Poseidon(jogador.getNumero()));
                jogador.setElixir(jogador.getElixir() - selecionarPoseidon.getCusto());
                quantiaDeus = 1;
                break;

            case 4:
                quantiaDeus = -1;
                break;

            default:
                System.out.println("Você selecionou uma opção inválida");
                break;
        }
        do {

            System.out.println("Caro(a) " + jogador.getNome() + ", você possuí no momento " + jogador.getElixir() + " de elixir. E "+
                    jogador.getPersonagensEscolhidos().size()+" tropas escolhidas, o máximo são 20. Você tem direito a mais "+(20-jogador.getPersonagensEscolhidos().size()));
            System.out.println("Escolha o personagem que você deseja adicionar ao seu exército:");
            System.out.println("1 - Arqueiro, custo: " + selecionarArqueiro.getCusto());
            System.out.println("2 - Centauro, custo: " + selecionarCentauro.getCusto());
            System.out.println("3 - Ninfa do Bosque, custo: " + selecionarNinfaDoBosque.getCusto());
            System.out.println("4 - Estou satisfeito com o que tenho. ");
            System.out.println("0 - Não quero mais jogar");
            int escolhaPersonagem = sc.nextInt();

            switch (escolhaPersonagem) {
                case 1:
                    jogador.getPersonagensEscolhidos().add(new Arqueiro(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - selecionarArqueiro.getCusto());
                    break;

                case 2:
                    jogador.getPersonagensEscolhidos().add(new Centauro(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - selecionarCentauro.getCusto());
                    break;

                case 3:
                    jogador.getPersonagensEscolhidos().add(new NinfaDoBosque(jogador.getNumero()));
                    jogador.setElixir(jogador.getElixir() - selecionarNinfaDoBosque.getCusto());
                    break;

                case 4:
                    if (jogador.getPersonagensEscolhidos().size() == 0) {
                        System.out.println("Você se recusou a escolher personagens, então o jogo foi dado como encerrado.");
                        menuInicial();
                    }
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

            if (jogador.getPersonagensEscolhidos().size()==20){
                System.out.println("Você atingiou o máximo de tropas.");
                jogador.setElixir(0);
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
        int escolhaPersonagemgem = sc.nextInt();
        switch (escolhaPersonagemgem) {
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
                System.out.println("Além disso Zeus também possui um especial, que lhe concede o poder de causar 35 de dano à todos os \n" +
                        "inimigos, caso esse inimigo seja poseidon retira um dano adicional de mais 35. A barra de especial demora 6 \n" +
                        "jogadas para carregar. Cuidado, poseidon também tem uma arma contra Zeus, retirando 3 cargas da barra de especial.");
                break;
            case 5:
                mostrarInfo(new Poseidon(0));
                System.out.println("Além disso Poseidon também possui um especial, que lhe concede o poder de retirar a defesa de todos os \n" +
                        "inimigos, caso esse inimigo seja Zeus retira também 3 pontos de carga do especial.\n" +
                        "Cuidado, Poseidon tem uma deficiência jogando contra Zeus, fazendo com que leve o dobro de dano do especial dele.");
                break;
            case 6:
                mostrarInfo(new Hades(0));
                System.out.println("Hades tem  um especial que serve para curar seus aliados, de maneira proporcional a quantia de mortos \n" +
                        "aumentando em 6% a cada morto.");
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
