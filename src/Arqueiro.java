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
