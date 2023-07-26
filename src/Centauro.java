import java.util.ArrayList;

public class Centauro extends Personagem {

    public Centauro(int numero) {
        this.setVida(185);
        this.setVidaMax(150);
        this.setAtaque(70);
        this.setDefesa(50);
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
        int indexPersonagem = 0;


        // Pega o index do Centauro
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indexPersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        //Centauro cura para o eixo x, então verifica se ele não está em alguma lateral.
        if (!(indexPersonagem % 10 == 0) && !((indexPersonagem + 1) % 10 == 0)) {

            //Realiza a cura à direita
            if (posicoes.get(indexPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }

            //Realiza a cura à esquerda
            if (posicoes.get(indexPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }

            //Realiza cura apenas para o lado interno do tabuleiro evitando que a cura ocorra de maneira inesperada.
        } else if (indexPersonagem % 10 == 0) {
            if (posicoes.get(indexPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                return false;
            }
        } else if ((indexPersonagem + 1) % 10 == 0) {
            if (posicoes.get(indexPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                return false;
            }
        }

        //Verificação caso não haja nenhuma peça nos dois lados, pois em um é possível.
        if (semCura == 2) {
            return false;
        }
        return true;
    }
}
