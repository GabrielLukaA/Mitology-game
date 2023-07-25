import java.util.ArrayList;

public class Centauro extends Personagem {

    public Centauro(int numero) {
        this.setVida(150);
        this.setVidaMax(150);
        this.setAtaque(70);
        this.setDefesa(25);
        this.setAlcance(1);
        this.setMovimento(3);
        this.setNome("Centauro");
        this.setCusto(2);
        this.setPlayer(numero);
    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int semCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem aSerCurado = null;
        int posicaoPersonagem = 0;

        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                posicaoPersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        if (!(posicaoPersonagem % 10 == 0) && !((posicaoPersonagem + 1) % 10 == 0)) {
            if (posicoes.get(posicaoPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(posicaoPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
            if (posicoes.get(posicaoPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(posicaoPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        } else if (posicaoPersonagem % 10 == 0) {
            if (posicoes.get(posicaoPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(posicaoPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                return false;
            }
        } else if ((posicaoPersonagem + 1) % 10 == 0) {
            if (posicoes.get(posicaoPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(posicaoPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                return false;
            }
        }

        if (semCura == 2) {
            return false;
        }
        return true;
    }
}
