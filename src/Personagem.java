import java.util.ArrayList;

public abstract class Personagem {
    int vida;
    int ataque;
    int defesa;
    int alcance;
    int movimento;
    int custo;

    int vidaMax;
    String nome;
    String inGame;

    int player;

    private Posicao posicao;

    ArrayList<Integer> possiveisMovimentosLinha = new ArrayList<Integer>();
    ArrayList<Integer> possiveisMovimentosColuna = new ArrayList<Integer>();

    public String atacar(Personagem oponente, Tabuleiro tabuleiro, Jogador adversario) {
        int vidaInicial = oponente.vida;
        int mortos = 0;
        int oponenteP = 0;
        int meP = 0;
        for (int i = 0; i < tabuleiro.getPosicoes().size(); i++) {
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == oponente) {
                oponenteP = i;
            }
            if (tabuleiro.getPosicoes().get(i).getPersonagem() == this) {
                meP = i;
            }
        }
        for (int i = meP + (alcance * -10); meP > i; i += 10) {
            if (oponenteP == i) {
                for (int l = meP - 10; l > oponenteP; l -= 10) {
                    if (l <= 10) {
                        return "não dá";
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return "não dá";
                    }
                    break;
                }
                oponente.vida -= this.ataque;
                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            mortos++;
                            return "" + mortos;
                        }
                    }
                }
//        oponente.defender(ataque);
            }
        }
        for (int i = meP + (alcance * 10); meP < i; i -= 10) {
            if (oponenteP == i) {
                for (int l = meP + 10; l < oponenteP; l += 10) {
                    if (l <= 10) {
                        return "não dá";
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return "não dá";
                    }
                    break;
                }
                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            mortos++;
                            adversario.mortos++;
                            if (adversario.mortos==adversario.personagensEscolhidos.size()){
                                return "finishGame";
                            }
                            return "" + mortos;
                        }
                    }
                }
            }

//        oponente.defender(ataque);
        }


        for (int i = meP + alcance; meP < i; i -= 1) {
            if (oponenteP == i) {
                for (int l = meP + 1; l < oponenteP; l += 1) {
                    if ((l + 1) % 10 == 0) {
                        return "não dá";
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return "não dá";
                    }
                    break;
                }
                oponente.vida -= this.ataque;


                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);

                            mortos++;
                            return "" + mortos;
                        }
                    }
                }
            }

//        oponente.defender(ataque);
        }

        for (int i = meP - alcance; meP > i; i += 1) {

            if (oponenteP == i) {
                for (int l = meP - 1; l > oponenteP; l -= 1) {
                    if (l % 10 == 0) {
                        return "não dá";
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return "não dá";
                    }
                    break;
                }
                oponente.vida -= this.ataque;

                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem() == oponente) {
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);

                            mortos++;
                            return "" + mortos;
                        }
                    }
                }
            }

//        oponente.defender(ataque);
        }
        int vidaAoFim= oponente.vida;
        if (vidaAoFim==vidaInicial){
            return "ataque na diagonal";
        }
        return "bateu";
    }

    public void defender(Tabuleiro tabuleiro) {
        this.vida += vidaMax / 100 * 15;
        if (this.vida > vidaMax) {
            this.vida = vidaMax;
        }
    }

    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int casa = 0;
        for (int k = 0; k < tabuleiro.getPosicoes().size(); k++) {
            if (tabuleiro.getPosicoes().get(k).getPersonagem() == this) {
                casa = k;
            }
        }

        boolean oxi = true;
        switch (ladoQueVai) {
            case 1:
                int ondeIr = casa - (10 * quantiaAandar);

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
                        oxi = false;
                        break;
                    }
                }
                Posicao posicao = new Posicao();
                posicao.setPersonagem(this);
                Posicao antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                Posicao nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 2:
                ondeIr = casa + (10 * quantiaAandar);
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
                        oxi = false;
                        break;
                    }
                }
                posicao = new Posicao();
                posicao.setPersonagem(this);
                antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 3:
                ondeIr = casa - quantiaAandar;
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }
                for (int i = casa - 1; i > ondeIr; i -= 1) {
                    if (i % 10 == 0) {
                        return false;

                    }
//                    if ((casa-quantiaAandar+1)%10==0)
                    if (posicoes.get(i).getPersonagem() != null) {
                        return false;
                    }
                }
                posicao = new Posicao();
                posicao.setPersonagem(this);
                antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 4:
                ondeIr = casa + quantiaAandar;
                if (posicoes.get(ondeIr).getPersonagem() != null) {
                    return false;
                }

                for (int i = casa + 1; i < ondeIr; i += 1) {
                    if ((i + 1) % 10 == 0) {
                        oxi = false;

                    }
                    if (posicoes.get(i).getPersonagem() != null) {
                        oxi = false;
                    }
                }
                if (oxi) {
                    posicao = new Posicao();
                    posicao.setPersonagem(this);
                    antiga = tabuleiro.getPosicoes().get(casa);
                    antiga.setPersonagem(null);
                    nova = tabuleiro.getPosicoes().get(ondeIr);
                    nova.setPersonagem(this);
                }

                break;
        }
        return oxi;
    }

    public Posicao getPosicao() {
        return posicao;
    }
}
