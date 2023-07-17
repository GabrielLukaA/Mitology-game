import java.util.ArrayList;

public  abstract class Personagem {
    int vida;
    int ataque;
    int defesa;
    int alcance;
    int movimento;
    int custo;
    String nome;
    String inGame;

    int player;

    private Posicao posicao;

    ArrayList<Integer> possiveisMovimentosLinha = new ArrayList<Integer>();
    ArrayList<Integer> possiveisMovimentosColuna = new ArrayList<Integer>();

    public abstract void atacar(Personagem oponente);
    public abstract void defender(Tabuleiro tabuleiro);
    public abstract boolean movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai);

    public Posicao getPosicao() {
        return posicao;
    }
}
