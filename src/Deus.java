import java.util.ArrayList;

public abstract class Deus extends Personagem {

    private int barraEspecial;
    private int cargaEspecial;

    public abstract boolean realizarEspecial(Tabuleiro tabuleiro, Jogador adversario);

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int semCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem aSerCurado = null;
        int indexPersonagem = 0;

        //Pega o index do Deus
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indexPersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }

        //Verifica se está na primeira ou ultima linha
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

            //Verifica primeira e ultima linha
        } else if (indexPersonagem < 10) {
            if (posicoes.get(indexPersonagem + 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem + 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }
        } else if (indexPersonagem > 89) {
            if (posicoes.get(indexPersonagem - 10).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 10).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }
        }

        //Verifica se ele não está em alguma lateral.
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
                semCura++;
            }
        } else if ((indexPersonagem + 1) % 10 == 0) {
            if (posicoes.get(indexPersonagem - 1).getPersonagem() != null) {
                aSerCurado = posicoes.get(indexPersonagem - 1).getPersonagem();
                aSerCurado.setVida(aSerCurado.getVida() + aSerCurado.getVidaMax() / 100 * 15);
                aSerCurado.verificaVidaMaxima();
            } else {
                semCura++;
            }
        }


        //Verificação caso não haja nenhuma peça curada.
        if (semCura == 4) {
            return false;
        }
        return true;
    }

    //region getters and setters
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

    //endregion
}
