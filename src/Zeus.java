public class Zeus extends Deus {
    public Zeus(){
        this.vida = 200;
        this.ataque = 90;
        this.defesa = 0;
        this.alcance =3;
        this.movimento = 4;
        this.nome = "Arqueiro";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 15;
        this.barraEspecial = 0;
        this.cargaEspecial = 7;

    }

    @Override
    public void carregarEspecial() {

    }

    @Override
    public void realizarEspecial() {

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
