public  abstract class Personagem {
    int vida;
    int ataque;
    int defesa;
    int alcance;
    int movimento;

    public abstract void atacar(Personagem oponente);
    public abstract int defender();
    public abstract void moivmento();
}
