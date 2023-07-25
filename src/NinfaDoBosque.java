import java.util.ArrayList;

public class NinfaDoBosque extends Personagem {
    public NinfaDoBosque(int numero) {
        this.setVida(75);
        this.setVidaMax(75);
        this.setAtaque(25);
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
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                System.out.println("Entrei");
                int l = posicoes.indexOf(posicao);
                System.out.println(l);
                if (l > 9 & l < 90) {
                    System.out.println("If comum");
                    if (l % 10 == 0) {
                        System.out.println("to nas ponta da esquerda");
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                    } else if ((l + 1) % 10 == 0) {
                        System.out.println("to na ponta da direita");
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                    } else {
                        System.out.println("To em algum lugar");
                        contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    }
                } else {
                    if (l == 90) {
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                    } else if (l == 99) {
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    } else if (l == 0) {
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                    } else if (l == 9) {
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                    } else if (l < 9) {
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 9).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l + 11).getPersonagem()), curados);
                    } else {
                        contagem(curar(posicoes.get(l + 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 1).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 9).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 10).getPersonagem()), curados);
                        contagem(curar(posicoes.get(l - 11).getPersonagem()), curados);
                    }
                }
            }
        }
        if (curados > 0) {
            return true;
        }
        return false;
    }

    public boolean curar(Personagem personagem) {
        if (!(personagem == null)) {
            personagem.setVida(personagem.getVida() / 100 * 30);
            if (personagem.getVida() > personagem.getVidaMax()) {
                personagem.setVida(personagem.getVidaMax());
            }
            return true;
        }
        return false;
    }

    public int contagem(boolean curado, int curados) {
        if (curado) {
            return curados + 1;
        }
        return curados;
    }

}