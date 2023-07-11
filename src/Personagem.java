import java.util.ArrayList;

public  abstract class Personagem {
    int vida;
    int ataque;
    int defesa;
    int alcance;
    int movimento;
    String nome;
    String inGame;

    ArrayList<Integer> possiveisMovimentosLinha = new ArrayList<Integer>();
    ArrayList<Integer> possiveisMovimentosColuna = new ArrayList<Integer>();

    public abstract void atacar(Personagem oponente);
    public abstract void defender(int ataque);
    public abstract void movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai);
}
