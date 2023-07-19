public class Hades extends Deus {
    public Hades(int numero) {
        this.vida = 175;
        this.vidaMax=175;
        this.ataque = 110;
        this.defesa = 0;
        this.alcance = 1;
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

}
