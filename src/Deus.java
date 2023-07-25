import java.util.ArrayList;

public abstract class Deus extends Personagem {

    private int barraEspecial;
    private int cargaEspecial;

    public abstract void realizarEspecial();

    public int getBarraEspecial() {
        return barraEspecial;
    }

    public void setBarraEspecial(int barraEspecial) {
        this.barraEspecial = barraEspecial;
    }

    public int getCargaEspecial() {
        return cargaEspecial;
    }

    public void setCargaEspecial(int cargaEspecial) {
        this.cargaEspecial = cargaEspecial;
    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int semCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem aSerCurado = null;
        int indexPersonagem = 0;

        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indexPersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        if (indexPersonagem >= 10 && indexPersonagem <= 89) {
            if (posicoes.get(indexPersonagem + 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
            if (posicoes.get(indexPersonagem - 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        } else if (indexPersonagem < 10) {
            if (posicoes.get(indexPersonagem + 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        } else if (indexPersonagem > 89) {
            if (posicoes.get(indexPersonagem - 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        }

        if (!(indexPersonagem % 10 == 0) && !((indexPersonagem + 1) % 10 == 0)) {
            if (posicoes.get(indexPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
            if (posicoes.get(indexPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        } else if (indexPersonagem % 10 == 0) {
            if (posicoes.get(indexPersonagem + 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        } else if ((indexPersonagem + 1) % 10 == 0) {
            if (posicoes.get(indexPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 10);
            } else {
                semCura++;
            }
        }


        if (semCura == 4) {
            return false;
        }
        return true;
    }
}
