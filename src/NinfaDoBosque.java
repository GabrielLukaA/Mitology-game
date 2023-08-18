import java.util.ArrayList;

public class NinfaDoBosque extends Personagem {
    public NinfaDoBosque(int numero) {
        this.setVida(75);
        this.setVidaMax(75);
        this.setAtaque(55);
        this.setDefesa(20);
        this.setAlcance(1);
        this.setMovimento(2);
        this.setNome("Ninfa do Bosque");
        this.setCusto(6);
        this.setPlayer(numero);
    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int curados = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();

        //Ninfa cura todos ao seu redor, ou seja, com um máximo de 8 peças.
        for (Posicao posicao : posicoes) {

            //Encontra ninfa do bosque.
            if (posicao.getPersonagem() == this) {
                int l = posicoes.indexOf(posicao);
                System.out.println(l);

                // Caso seja possível curar até 8 casas
                if (l > 9 & l < 90) {
                    //Verifica se está em alguma das laterais e cura somente para a parte interna.
                    if (l % 10 == 0) {
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                    } else if ((l + 1) % 10 == 0) {
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                        //Caso esteja em algum lugar que não seja em "cantos".
                    } else {
                        curados = contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    }

                } else {

                    //Verifica os quatro cantos e também a primeira e ultima linha para que a cura seja feita sem erro de indíce ou
                    // cure em lugar indevido.
                    //PS. Pensar em uma lógica mais simplificada para que não exija esse excesso de linhas.
                    if (l == 90) {
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                    } else if (l == 99) {
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    } else if (l == 0) {
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                    } else if (l == 9) {
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                    } else if (l < 9) {
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                    } else {
                        curados = contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        curados = contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    }
                }
            }
        }

        //Verifica se pelo menos um aliado foi curado.
        if (curados > 0) {
            return true;
        }
        return false;
    }

    //Função que retornará um boolean para ver se a cura executada.
    public boolean curar(Personagem personagem) {

        //Evita null exception negando o nulo.
        if (!(personagem == null)) {
            personagem.setVida(personagem.getVida() + (personagem.getVida() / 100 * 40));

            //Apenas garante que,  nenhum personagem tenha mais vida do que o definido como vida máxima.
            personagem.verificaVidaMaxima();
            return true;
        }
        return false;
    }

    //Serve para atualizar a contagem de curados, recebe como parametro a função "curar()" e o contador atual.
    public int contagem(boolean curado, int curados) {

        if (curado) {
            return curados + 1;
        }
        return curados;
    }

}