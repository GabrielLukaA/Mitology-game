import java.util.ArrayList;

public abstract class Personagem {
    int vida;
    int ataque;
    int defesa;
    int alcance;
    int movimento;
    int custo;
    String nome;
    String inGame;

    int player;

    private Posicao posicao;

    ArrayList<Integer> possiveisMovimentosLinha = new ArrayList<Integer>();
    ArrayList<Integer> possiveisMovimentosColuna = new ArrayList<Integer>();

    public boolean atacar(Personagem oponente, Tabuleiro tabuleiro) {
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
            System.out.println(i + " sou do for pra cima");
            if (oponenteP == i) {
                for (int l = meP - 10; l > oponenteP; l -= 10) {
                    if (l <= 10) {
                        return false;
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                    System.out.println("Ele quer atacar pra cima");
                    break;
                }
                oponente.vida -= this.ataque;
                System.out.println(oponente.vida);
                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem()==oponente){
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            System.out.println("Morto");
                        }
                    }

                }
//        oponente.defender(ataque);
            }
        }
        for (int i = meP + (alcance * 10); meP < i; i -= 10) {
            System.out.println(i + " sou do for pra baixo");
            if (oponenteP == i) {
                for (int l = meP + 10; l < oponenteP; l += 10) {
                    if (l <= 10) {
                        return false;
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                    System.out.println("Ele quer atacar pra baixo");
                    break;
                }
                oponente.vida -= this.ataque;
                System.out.println(oponente.vida);
                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem()==oponente){
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            System.out.println("Morto");
                        }
                    }

                }
            }

//        oponente.defender(ataque);
        }


        for (int i = meP + alcance; meP < i; i -= 1) {
            System.out.println(i + " sou do for pra direita");
            if (oponenteP == i) {
                for (int l = meP + 1; l < oponenteP; l += 1) {
                    if ((l + 1) % 10 == 0) {
                        return false;
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                    System.out.println("Ele quer atacar pra direita");
                    break;
                }
                oponente.vida -= this.ataque;
                System.out.println(oponente.vida);
                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem()==oponente){
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            System.out.println("Morto");
                        }
                    }

                }
            }

//        oponente.defender(ataque);
        }

        for (int i = meP - alcance; meP > i; i += 1) {
            System.out.println(i + " sou do for pra esquerda");
            if (oponenteP == i) {
                for (int l = meP - 1; l > oponenteP; l -= 1) {
                    if (l % 10 == 0) {
                        return false;
                    }
                    if (tabuleiro.getPosicoes().get(l).getPersonagem() != null) {
                        return false;
                    }
                    System.out.println("Ele quer atacar pra esquerda");
                    break;
                }
                oponente.vida -= this.ataque;
                System.out.println(oponente.vida);
                if (oponente.vida <= 0) {
                    for (int c = 0; c < tabuleiro.getPosicoes().size(); c++) {
                        if (tabuleiro.getPosicoes().get(c).getPersonagem()==oponente){
                            tabuleiro.getPosicoes().get(c).setPersonagem(null);
                            System.out.println("Morto");
                        }
                    }

                }
            }

//        oponente.defender(ataque);
        }


        return true;

    }

    public abstract void defender(Tabuleiro tabuleiro);

    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int casa = 0;
        for (int k = 0; k < tabuleiro.getPosicoes().size(); k++) {
            if (tabuleiro.getPosicoes().get(k).getPersonagem() == this) {
                casa = k;
            }
        }
        System.out.println(ladoQueVai);
        boolean oxi = true;
        switch (ladoQueVai) {
            case 1:
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                int ondeIr = casa - (10 * quantiaAandar);
                System.out.println("onde eu vou? " + ondeIr);
                if (casa <= 10) {
                    return false;
                }
                for (int i = casa - 10; i > ondeIr; i -= 10) {
                    if (i<=10){
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

                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa + (10 * quantiaAandar);
                System.out.println("onde eu vou? " + ondeIr);
                if (casa >= 91) {
                    return false;
                }
                for (int i = casa + 10; i < ondeIr; i += 10) {
                    if (i>=91){
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
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa - quantiaAandar;
                System.out.println("onde eu vou? " + ondeIr);

                for (int i = casa - 1; i > ondeIr; i -= 1) {
                    if (i%10==0){
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
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa + quantiaAandar;
                System.out.println("onde eu vou? " + ondeIr);
                if (posicoes.get(ondeIr).getPersonagem()!=null){
                    return false;
                }

                for (int i = casa + 1; i < ondeIr; i += 1) {
                    if ((i + 1) % 10 == 0) {
                            oxi = false;

                    }
                    System.out.println("oi"+ posicoes.get(i).getPersonagem()+" meudeus "+ i);
                    if (posicoes.get(i).getPersonagem() != null) {
                        System.out.println("Entrei aq");
                        oxi = false;
                    }
                }
                if (oxi){
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
