import java.util.ArrayList;

public class Poseidon extends Deus {
    public Poseidon(int numero) {

        this.setVida(200);
        this.setVidaMax(200);
        this.setAtaque(50);
        this.setDefesa(50);
        this.setAlcance(3);
        this.setMovimento(2);
        this.setNome("Poseidon");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(5);

    }

    @Override
    public boolean realizarEspecial(Tabuleiro tabuleiro, Jogador adversario) {

        int atingidos = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();

        //Retira a defesa de todos os adversários.
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() != null) {
                if (posicao.getPersonagem().getPlayer() == adversario.getNumero()) {
                    atingidos++;
                    posicao.getPersonagem().setDefesa(0);
                    if (posicao.getPersonagem() instanceof Zeus) {
                        ((Zeus) posicao.getPersonagem()).setBarraEspecial(0);
                    }
                }

            }
        }

        //Verifica se algum inimigo foi atingido
        if (atingidos == 0) {
            return false;
        }
        return true;
    }


}