public class Poseidon extends Deus {
    public Poseidon(int numero) {

        this.setVida(200);
        this.setVidaMax(200);
        this.setAtaque(75);
        this.setDefesa(0);
        this.setAlcance(4);
        this.setMovimento(2);
        this.setNome("Poseidon");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(5);

    }

    @Override
    public void realizarEspecial() {

    }

}