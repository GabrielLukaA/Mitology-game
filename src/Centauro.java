public class Centauro extends Personagem {

    public Centauro(int numero) {
        this.vida = 150;
        this.ataque = 70;
        this.defesa = 25;
        this.alcance = 1;
        this.movimento = 3;
        this.nome = "Centauro";
        this.inGame = this.nome + " Vida:" + this.vida;
        this.custo = 2;
        this.player = numero;

    }


    @Override
    public void defender(Tabuleiro tabuleiro) {

    }
}
