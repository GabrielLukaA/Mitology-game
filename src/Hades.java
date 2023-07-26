import java.util.ArrayList;

public class Hades extends Deus {
    public Hades(int numero) {

        this.setVida(175);
        this.setVidaMax(175);
        this.setAtaque(135);
        this.setDefesa(25);
        this.setAlcance(1);
        this.setMovimento(2);
        this.setNome("Hades");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(4);

    }

    @Override
    public boolean realizarEspecial(Tabuleiro tabuleiro, Jogador jogador) {

        int curados = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int multiplicadorDeVida = jogador.getMortos();
        if (jogador.getMortos() == 0) {
            multiplicadorDeVida = 1;
        }

        //Realiza a cura nos personagens aliados
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() != null) {
                if (posicao.getPersonagem().getPlayer() == jogador.getNumero()) {
                    curados++;
                    posicao.getPersonagem().setVida(posicao.getPersonagem().getVida() + posicao.getPersonagem().getVidaMax() / 100 * (multiplicadorDeVida * 6));
                }
            }
        }

        //Verifica se nenhum aliado foi curado.
        if (curados == 0) {
            return false;
        }
        return true;
    }

}
