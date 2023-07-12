public class Centauro extends Personagem{

    public Centauro(){
        this.vida = 150;
        this.ataque = 70;
        this.defesa = 25;
        this.alcance =1;
        this.movimento = 3;
        this.nome = "Centauro";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 2;

    }

    @Override
    public void atacar(Personagem oponente) {

    }

    @Override
    public void defender(int ataque) {

    }

    @Override
    public void movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

    }
}
