import java.util.ArrayList;

public abstract class Personagem {
    private double vida;
    private int ataque;
    private int defesa;
    private int alcance;
    private int movimento;
    private int custo;
    private double vidaMax;
    private String nome;
    private String inGame;
    private int player;
    private Posicao posicao;

    public boolean atacar(Personagem oponente, Tabuleiro tabuleiro, Jogador adversario) {

        double vidaInicial = oponente.getVida();
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
        for (int i = indexJogador + (this.getAlcance() * -10); indexJogador > i; i += 10) {

            if (indexOponente == i) {
                for (int l = indexJogador - 10; l > indexOponente; l -= 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.setVida(oponente.getVida() - (this.ataque - oponente.getDefesa()));

                //Seta o personagem como morto.
                if (oponente.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o oponente está abaixo
        for (int i = indexJogador + (this.getAlcance() * 10); indexJogador < i; i -= 10) {

            if (indexOponente == i) {
                for (int l = indexJogador + 10; l < indexOponente; l += 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.setVida(oponente.getVida() - (this.ataque - oponente.getDefesa()));

                if (oponente.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }

        }

        //Realiza o ataque se o oponente está à esquerda
        for (int i = indexJogador + this.alcance; indexJogador < i; i -= 1) {

            if (indexOponente == i) {
                for (int l = indexJogador + 1; l < indexOponente; l += 1) {
                    if ((l + 1) % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.setVida(oponente.getVida() - (this.ataque - oponente.getDefesa()));

                if (oponente.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o oponente está à direita
        for (int i = indexJogador - this.alcance; indexJogador > i; i += 1) {

            if (indexOponente == i) {
                for (int l = indexJogador - 1; l > indexOponente; l -= 1) {
                    if (l % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                oponente.setVida(oponente.getVida() - (this.ataque - oponente.getDefesa()));

                if (oponente.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

//Verifica se ainda assim nenhum oponente foi atacado, indicando que foi um ataque na diagonal,
// logo se retorna falso pois nesse  game não são aceitos ataqeus diagonais.
        double vidaAoFim = oponente.getVida();

        if (vidaAoFim == vidaInicial) {
            return false;
        }

        return true;
    }

    public abstract boolean defender(Tabuleiro tabuleiro);


    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int indexPersonagem = 0;

        //Identifica o index do personagem
        //PS. Se possível mudar para foreach apenas para todas as classes estarem padronizadas 
        //Pensar em transformar isso em uma unica função. para que todas as classes possam fazer uso.
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == this) {
                indexPersonagem = i;
            }
        }

        switch (ladoQueVai) {
            case 1:
                int indexLocalDesejado = indexPersonagem - (10 * quantiaAandar);

                if (indexLocalDesejado < 0) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indexLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Se estiver na primeira linha e quiser ir pra cima não pode ser possível.
                if (indexPersonagem <= 10) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indexPersonagem - 10; i > indexLocalDesejado; i -= 10) {
                    if (i <= 10) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indexPersonagem, indexLocalDesejado, tabuleiro);
                break;

            case 2:
                indexLocalDesejado = indexPersonagem + (10 * quantiaAandar);

                if (indexLocalDesejado > 99) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indexLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Se estiver na ultima linha e quiser ir pra baixo não pode ser possível.
                if (indexPersonagem >= 91) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indexPersonagem + 10; i < indexLocalDesejado; i += 10) {
                    if (i >= 91) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indexPersonagem, indexLocalDesejado, tabuleiro);
                break;

            case 3:
                indexLocalDesejado = indexPersonagem - quantiaAandar;

                if (indexLocalDesejado < 0) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indexLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indexPersonagem - 1; i > indexLocalDesejado; i -= 1) {
                    //Se está na lateral e ainda tem que ir mais não pode ser possível
                    if (i % 10 == 0) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indexPersonagem, indexLocalDesejado, tabuleiro);
                break;

            case 4:
                indexLocalDesejado = indexPersonagem + quantiaAandar;

                if (indexLocalDesejado > 99) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indexLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indexPersonagem + 1; i < indexLocalDesejado; i += 1) {
                    //Se está na lateral e ainda tem que ir mais não pode ser possível
                    if ((i + 1) % 10 == 0) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indexPersonagem, indexLocalDesejado, tabuleiro);
                break;
        }

        return true;
    }

    //Função que auxilia na reutilização de código e apenas setta a posição antiga como nula e a nova com o personagem.
    private void setarMovimento(int indexPersonagem, int indexLocalDesejado, Tabuleiro tabuleiro) {
        Posicao posicao = new Posicao();
        posicao.setPersonagem(this);
        Posicao antiga = tabuleiro.getPosicoes().get(indexPersonagem);
        antiga.setPersonagem(null);
        Posicao nova = tabuleiro.getPosicoes().get(indexLocalDesejado);
        nova.setPersonagem(this);
    }

    //Função responsável pela verificação da vida do personagem curado.
    //PS. Lembrar de implementar essa função na "ninfa do bosque", pois ela tem uma própria sem necessidade.
    public void verificaVidaMaxima() {
        if (this.getVida() >= this.getVidaMax()) {
            this.setVida(this.getVidaMax());
        }
    }

    //region getters and setters
    public Posicao getPosicao() {
        return posicao;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
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

    public double getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(double vidaMax) {
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

    //endregion
}
