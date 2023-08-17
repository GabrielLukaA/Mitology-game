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

    public boolean atacar(Personagem inimigo, Tabuleiro tabuleiro, Jogador adversario) {

        double vidaInicial = inimigo.getVida();
        int indiceTropaInimiga = 0;
        int indiceAliado = 0;

        //Encontra o index do player e de seu inimigo
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == inimigo) {
                indiceTropaInimiga = i;
            }

            if (tabuleiro.getPosicoes().get(i).getPersonagem() == this) {
                indiceAliado = i;
            }
        }

        //Realiza o ataque se o inimigo está acima
        for (int i = indiceAliado + (this.getAlcance() * -10); indiceAliado > i; i += 10) {

            if (indiceTropaInimiga == i) {
                for (int l = indiceAliado - 10; l > indiceTropaInimiga; l -= 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                inimigo.setVida(inimigo.getVida() - (this.ataque - inimigo.getDefesa()));

                //Seta o personagem como morto.
                if (inimigo.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == inimigo) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o inimigo está abaixo
        for (int i = indiceAliado + (this.getAlcance() * 10); indiceAliado < i; i -= 10) {

            if (indiceTropaInimiga == i) {
                for (int l = indiceAliado + 10; l < indiceTropaInimiga; l += 10) {
                    if (l <= 10) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                inimigo.setVida(inimigo.getVida() - (this.ataque - inimigo.getDefesa()));

                if (inimigo.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == inimigo) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }

        }

        //Realiza o ataque se o inimigo está à esquerda
        for (int i = indiceAliado + this.alcance; indiceAliado < i; i -= 1) {

            if (indiceTropaInimiga == i) {
                for (int l = indiceAliado + 1; l < indiceTropaInimiga; l += 1) {
                    if ((l + 1) % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                inimigo.setVida(inimigo.getVida() - (this.ataque - inimigo.getDefesa()));

                if (inimigo.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == inimigo) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

        //Realiza o ataque se o inimigo está à direita
        for (int i = indiceAliado - this.alcance; indiceAliado > i; i += 1) {

            if (indiceTropaInimiga == i) {
                for (int l = indiceAliado - 1; l > indiceTropaInimiga; l -= 1) {
                    if (l % 10 == 0) {
                        return false;
                    }

                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                }

                inimigo.setVida(inimigo.getVida() - (this.ataque - inimigo.getDefesa()));

                if (inimigo.getVida() <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == inimigo) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            adversario.setMortos(adversario.getMortos() + 1);
                            return true;
                        }
                    }
                }
            }
        }

//Verifica se ainda assim nenhum inimigo foi atacado, indicando que foi um ataque na diagonal,
// logo se retorna falso pois nesse  game não são aceitos ataqeus diagonais.
        double vidaAoFim = inimigo.getVida();

        if (vidaAoFim == vidaInicial) {
            return false;
        }

        return true;
    }

    public abstract boolean defender(Tabuleiro tabuleiro);


    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int direcao) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int indiceTropa = 0;

        //Identifica o index do personagem
        //PS. Se possível mudar para foreach apenas para todas as classes estarem padronizadas 
        //Pensar em transformar isso em uma unica função. para que todas as classes possam fazer uso.
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == this) {
                indiceTropa = i;
            }
        }

        switch (direcao) {
            case 1:
                int indiceLocalDesejado = indiceTropa - (10 * quantiaAandar);

                if (indiceLocalDesejado < 0) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indiceLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Se estiver na primeira linha e quiser ir pra cima não pode ser possível.
                if (indiceTropa <= 10) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indiceTropa - 10; i > indiceLocalDesejado; i -= 10) {
                    if (i <= 10) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indiceTropa, indiceLocalDesejado, tabuleiro);
                break;

            case 2:
                indiceLocalDesejado = indiceTropa + (10 * quantiaAandar);

                if (indiceLocalDesejado > 99) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indiceLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Se estiver na ultima linha e quiser ir pra baixo não pode ser possível.
                if (indiceTropa >= 91) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indiceTropa + 10; i < indiceLocalDesejado; i += 10) {
                    if (i >= 91) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indiceTropa, indiceLocalDesejado, tabuleiro);
                break;

            case 3:
                indiceLocalDesejado = indiceTropa - quantiaAandar;

                if (indiceLocalDesejado < 0) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indiceLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indiceTropa - 1; i > indiceLocalDesejado; i -= 1) {
                    //Se está na lateral e ainda tem que ir mais não pode ser possível
                    if (i % 10 == 0) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indiceTropa, indiceLocalDesejado, tabuleiro);
                break;

            case 4:
                indiceLocalDesejado = indiceTropa + quantiaAandar;

                if (indiceLocalDesejado > 99) {
                    return false;
                }

                //Barra caso tenha peça no local
                if (posicoes.get(indiceLocalDesejado).getPersonagem() != null) {
                    return false;
                }

                //Verifica se não há peças no caminho até o local desejado
                for (int i = indiceTropa + 1; i < indiceLocalDesejado; i += 1) {
                    //Se está na lateral e ainda tem que ir mais não pode ser possível
                    if ((i + 1) % 10 == 0) {
                        return false;
                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }

                setarMovimento(indiceTropa, indiceLocalDesejado, tabuleiro);
                break;
        }

        return true;
    }

    //Função que auxilia na reutilização de código e apenas setta a posição antiga como nula e a nova com o personagem.
    private void setarMovimento(int indiceTropa, int indiceLocalDesejado, Tabuleiro tabuleiro) {
        Posicao posicao = new Posicao();
        posicao.setPersonagem(this);
        Posicao antiga = tabuleiro.getPosicoes().get(indiceTropa);
        antiga.setPersonagem(null);
        Posicao nova = tabuleiro.getPosicoes().get(indiceLocalDesejado);
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
