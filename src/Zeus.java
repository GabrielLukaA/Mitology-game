import java.util.ArrayList;

public class Zeus extends Deus {
    public Zeus(int numero) {

        this.setVida(200);
        this.setVidaMax(200);
        this.setAtaque(90);
        this.setDefesa(30);
        this.setAlcance(2);
        this.setMovimento(3);
        this.setNome("Zeus");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(6);
    }

    @Override
    public boolean realizarEspecial(Tabuleiro tabuleiro, Jogador adversario) {

        int atingidos = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();

        //Causa dano em todos os inimigos
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() != null) {
                if (posicao.getPersonagem().getPlayer() == adversario.getNumero()) {
                    atingidos++;
                    posicao.getPersonagem().setVida(posicao.getPersonagem().getVida() - 35);
                    if (posicao.getPersonagem() instanceof Poseidon) {
                        posicao.getPersonagem().setVida(posicao.getPersonagem().getVida() - 35);
                    }
                    if (posicao.getPersonagem().getVida() <= 0) {
                        posicao.setPersonagem(null);
                        adversario.setMortos(adversario.getMortos() + 1);
                    }

                }
            }
        }

        //Verifica se algum inimigo foi atingido.
        if (atingidos == 0) {
            return false;
        }
        return true;
    }
}
