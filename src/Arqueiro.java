import java.util.ArrayList;

public class Arqueiro extends Personagem {


    public Arqueiro(int numero) {
        this.vida = 100;
        this.ataque = 90;
        this.defesa = 20;
        this.alcance = 3;
        this.movimento = 4;
        this.nome = "Arqueiro";
        this.inGame = this.nome + " Vida:" + this.vida;
        this.custo = 2;
        this.player = numero;

    }

    @Override
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
                }

//        oponente.defender(ataque);
            }
            return true;

    }
    @Override
    public void defender(Tabuleiro tabuleiro) {


    }


    @Override
    public String toString() {
        return "Arqueiro{" +
                "vida=" + vida +
                ", ataque=" + ataque +
                ", defesa=" + defesa +
                ", alcance=" + alcance +
                ", movimento=" + movimento +
                ", nome='" + nome + '\'' +
                '}';
    }
}
