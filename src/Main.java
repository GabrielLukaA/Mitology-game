

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        Tabuleiro tabuleiro = new Tabuleiro();
        Arqueiro arqueiro = new Arqueiro();
        Arqueiro arqueiroOponente = new Arqueiro();
        arqueiro.atacar(arqueiroOponente);
        tabuleiro.adicionaTabuleiro(1, 1, arqueiro);
        tabuleiro.adicionaTabuleiro(1, 0, arqueiroOponente);

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


        tabuleiro.constroiTabuleiro();
        System.out.println("Informe para que lado deseja mover seu personagem: ");
        int ladoQueVai = sc.nextInt();
        arqueiroOponente.verificaMovimento(ladoQueVai, tabuleiro);
        int c = arqueiroOponente.possiveisMovimentosLinha.size() - 1;

        System.out.println("Você pode mover até " + arqueiroOponente.possiveisMovimentosColuna.size() + " para frente, quantas deseja mover?");
        arqueiroOponente.movimento(sc.nextInt(), tabuleiro, ladoQueVai);

        tabuleiro.constroiTabuleiro();

    }

    private static void game() {
        System.out.println("Primeiramente, nos dê seus nomes para que seja iniciado um grande jogo.");
        System.out.println("Jogador 1: ");
        Jogador jogador1 = new Jogador(sc.next());
        System.out.println("Jogador 2: ");
        Jogador jogador2 = new Jogador(sc.next());
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
                "pelo lado negativo, você escolherá seu esquadrão antecipadamente, com direito a vetar" +
                "um Deus do seu adversário.");
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


    }

    private static void escolhePersonagens(Jogador jogador) {
        Personagem arqueiroSelecao = new Arqueiro();
        Personagem centauroSelecao = new Centauro();
        Personagem ninfaDoBosqueSelecao = new NinfaDoBosque();
        Deus zeusSelecao = new Zeus();
        Deus hadesSelecao = new Hades();
        Deus poseidonSelecao = new Poseidon();
        int deuses = 0;

        do {

            if (deuses == 0) {
                System.out.println("Você tem direito a jogar com um Deus de sua escolha: ");
                System.out.println("1 - Zeus, custo: " + zeusSelecao.custo);
                System.out.println("2 - Hades, custo: " + hadesSelecao.custo);
                System.out.println("3 - Poseidon, custo: " + poseidonSelecao.custo);
                System.out.println("4 - Não desejo usar nenhum Deus. ");
                int opcaoDeus = sc.nextInt();
                switch (opcaoDeus) {
                    case 1:
                        jogador.personagensEscolhidos.add(zeusSelecao);
                        jogador.elixir -= zeusSelecao.custo;
                        deuses = 1;
                        break;
                    case 2:
                        jogador.personagensEscolhidos.add(hadesSelecao);
                        jogador.elixir -= hadesSelecao.custo;
                        deuses = 1;
                        break;
                    case 3:
                        jogador.personagensEscolhidos.add(poseidonSelecao);
                        jogador.elixir -= poseidonSelecao.custo;
                        deuses = 1;
                        break;
                    case 4:
                        deuses = -1;
                        break;
            }

            System.out.println("Você possuí no momento " + jogador.elixir);
            System.out.println("Escolha o personagem que você deseja adicionar ao seu exército");
            System.out.println("1 - Arqueiro, custo: " + arqueiroSelecao.custo);
            System.out.println("2 - Centauro, custo: " + centauroSelecao.custo);
            System.out.println("3 - Ninfa do Bosque, custo: " + ninfaDoBosqueSelecao.custo);
            System.out.println("4 - Estou satisfeito com o que tenho. ");
            System.out.println("0 - Não quero mais jogar essa partida");
            int opcaoPersona = sc.nextInt();
            switch (opcaoPersona) {
                case 1:
                    Personagem arqueiro = new Arqueiro();
                    jogador.personagensEscolhidos.add(arqueiro );
                    break;
                case 2:
                    Personagem centauro = new Centauro();
                    jogador.personagensEscolhidos.add(centauro);
                    break;
                case 3:
                    jogador.personagensEscolhidos.add(ninfaDoBosqueSelecao);
                    break;
                case 4:
                    jogador.elixir = 0;
                    break;
                case 0:
                    System.out.println("Jogo encerrado.");
                    break;


            }
        } while (jogador.elixir >= 2);


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
                Arqueiro arqueiroInfo = new Arqueiro();
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

}
