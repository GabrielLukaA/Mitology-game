import java.util.ArrayList;

public class Arqueiro extends Personagem {


    public Arqueiro(int numero) {

        this.setVida(100);
        this.setVidaMax(100);
        this.setAtaque(90);
        this.setDefesa(50);
        this.setAlcance(3);
        this.setMovimento(4);
        this.setNome("Arqueiro");
        this.setCusto(2);
        this.setPlayer(numero);

    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int semCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem aSerCurado = null;
        int indexPersonagem = 0;

        //Pega o index do Arqueiro
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indexPersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        //Semelhante ao Centauro, mas agora realizando verificações se não está na primeira ou ultima linha do tabuleiro,
        //pois o arqueiro cura no eixo y
        if (indexPersonagem >= 10 && indexPersonagem <= 89) {

            if (posicoes.get(indexPersonagem + 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }

            if (posicoes.get(indexPersonagem - 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }

        } else if (indexPersonagem < 10) {
            if (posicoes.get(indexPersonagem + 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                return false;
            }

        } else if (indexPersonagem > 89) {
            if (posicoes.get(indexPersonagem - 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                return false;
            }
        }

//Verificação se não conseguiu curar ninguém.
        if (semCura == 2) {
            return false;
        }
        return true;
    }

}
