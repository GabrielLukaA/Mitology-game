public class Hades extends Deus {
    public Hades(int numero) {

        this.setVida(175);
        this.setVidaMax(175);
        this.setAtaque(110);
        this.setDefesa(0);
        this.setAlcance(1);
        this.setMovimento(4);
        this.setNome("Hades");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(5);

    }

    @Override
    public void realizarEspecial() {

    }

}
