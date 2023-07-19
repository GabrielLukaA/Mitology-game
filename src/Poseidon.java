public class Poseidon extends Deus {
    public Poseidon(int numero) {
        this.vida = 200;
        this.vidaMax = 200;
        this.ataque = 75;
        this.defesa = 0;
        this.alcance = 4;
        this.movimento = 2;
        this.nome = "Poseidon";
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