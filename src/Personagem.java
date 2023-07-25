import java.util.ArrayList;

public abstract class Personagem {
    private int vida;
    private int ataque;
    private int defesa;
    private int alcance;
    private int movimento;
    private int custo;
    private int vidaMax;
    private String nome;
    private String inGame;
    private int player;
    private Posicao posicao;

    public boolean atacar(Personagem oponente, Tabuleiro tabuleiro, Jogador adversario) {

        int vidaInicial = oponente.vida;
        int indexOponente = 0;
        int indexJogador = 0;

        //Encontra o index do player e de seu oponente
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == oponente) {
                indexOponente = i;
            }

            if (tabuleiro.getPosicoes().get(i).getPersonagem() == this) {
                indexJogador = i;
            }
        }

        //Realiza o ataque se o oponente está acima
        for (int i = indexJogador + (alcance * -10); indexJogador > i; i += 10) {

            if (indexOponente == i) {
                for (int l = indexJogador - 10; l > indexOponente; l -= 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos()+1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o oponente está abaixo
        for (int i = indexJogador + (alcance * 10); indexJogador < i; i -= 10) {

            if (indexOponente == i) {
                for (int l = indexJogador + 10; l < indexOponente; l += 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos()+1);
                            return true;
                        }
                    }
                }
            }

        }

        //Realiza o ataque se o oponente está à esquerda
        for (int i = indexJogador + alcance; indexJogador < i; i -= 1) {

            if (indexOponente == i) {
                for (int l = indexJogador + 1; l < indexOponente; l += 1) {
                    if ((l + 1) % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos()+1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o oponente está à direita
        for (int i = indexJogador - alcance; indexJogador > i; i += 1) {

            if (indexOponente == i) {
                for (int l = indexJogador - 1; l > indexOponente; l -= 1) {
                    if (l % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos()+1);
                            return true;
                        }
                    }
                }
            }
        }

//Verifica se ainda assim nenhum oponente foi atacado, indicando que foi um ataque na diagonal,
// logo se retorna falso pois nesse  game não são aceitos ataqeus diagonais
        int vidaAoFim = oponente.vida;
        if (vidaAoFim == vidaInicial) {
            return false;
        }

        return true;
    }

    public abstract boolean defender(Tabuleiro tabuleiro);


    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int casa = 0;

        for (int k = 0; k < tabuleiro.getPosicoes().size(); k++) {
            if (tabuleiro.getPosicoes().get(k).getPersonagem() == this) {
                casa = k;
            }
        }

        switch (ladoQueVai) {
            case 1:
                int ondeIr = casa - (10 * quantiaAandar);
                if (ondeIr < 0) {
                    return false;
                }
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }

                if (casa <= 10) {
                    return false;
                }

                for (int i = casa - 10; i > ondeIr; i -= 10) {
                    if (i <= 10) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(casa, ondeIr, tabuleiro);
                break;

            case 2:
                ondeIr = casa + (10 * quantiaAandar);
                if (ondeIr > 99) {
                    return false;
                }
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }

                if (casa >= 91) {
                    return false;
                }

                for (int i = casa + 10; i < ondeIr; i += 10) {
                    if (i >= 91) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(casa, ondeIr, tabuleiro);
                break;

            case 3:
                ondeIr = casa - quantiaAandar;
                if (ondeIr < 0) {
                    return false;
                }
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }

                for (int i = casa - 1; i > ondeIr; i -= 1) {
                    //                    if ((casa-quantiaAandar+1)%10==0)
                    if (i % 10 == 0) {
                        return false;
                    }

                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(casa, ondeIr, tabuleiro);
                break;

            case 4:
                ondeIr = casa + quantiaAandar;
                if (ondeIr > 99) {
                    return false;
                }
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }

                for (int i = casa + 1; i < ondeIr; i += 1) {
                    if ((i + 1) % 10 == 0) {
                        return false;
                    }

                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(casa, ondeIr, tabuleiro);
                break;
        }

        return true;
    }

    private void setarMovimento(int casa, int ondeIr, Tabuleiro tabuleiro) {
        Posicao posicao = new Posicao();
        posicao.setPersonagem(this);
        Posicao antiga = tabuleiro.getPosicoes().get(casa);
        antiga.setPersonagem(null);
        Posicao nova = tabuleiro.getPosicoes().get(ondeIr);
        nova.setPersonagem(this);
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInGame() {
        return inGame;
    }

    public void setInGame(String inGame) {
        this.inGame = inGame;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
