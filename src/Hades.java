public class Hades extends Deus {
    public Hades(int numero) {
        this.vida = 200;
        this.ataque = 90;
        this.defesa = 0;
        this.alcance = 3;
        this.movimento = 4;
        this.nome = "Hades";
        this.inGame = this.nome + " Vida:" + this.vida;
        this.custo = 15;
        this.barraEspecial = 0;
        this.cargaEspecial = 5;
        this.player = numero;
    }

    @Override
    public void carregarEspecial() {

    }

    @Override
    public void realizarEspecial() {

    }


    @Override
    public void defender(Tabuleiro tabuleiro) {

    }
}
