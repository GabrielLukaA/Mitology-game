public class Zeus extends Deus {
    public Zeus(int numero) {

        this.setVida(200);
        this.setVidaMax(200);
        this.setAtaque(90);
        this.setDefesa(0);
        this.setAlcance(3);
        this.setMovimento(4);
        this.setNome("Zeus");
        this.setCusto(15);
        this.setPlayer(numero);
        this.setBarraEspecial(0);
        this.setCargaEspecial(7);
    }

    @Override
    public void realizarEspecial() {

    }
}
