public class Arqueiro extends Personagem {

    public Arqueiro(){
        this.vida = 100;
        this.ataque = 90;
        this.defesa = 20;
        this.alcance =3;
        this.movimento = 1;
    }

    @Override
    public void atacar(Personagem oponente) {
        int ataque = this.ataque - oponente.defesa;
        oponente.vida =- ataque;
    }

    @Override
    public void defender() {

    }

    @Override
    public void moivmento() {

    }
}
