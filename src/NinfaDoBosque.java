public class NinfaDoBosque extends Personagem{
    public NinfaDoBosque(){
        this.vida = 75;
        this.ataque = 25;
        this.defesa = 20;
        this.alcance = 1;
        this.movimento = 2;
        this.nome = "Ninfa do Bosque";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 6;

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
