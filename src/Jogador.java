import java.util.ArrayList;

public class Jogador {
    String nome;
    int elixir;
    ArrayList<Personagem> personagensEscolhidos;
    public Jogador (String nome){
        this.nome = nome;
        this.elixir = 50;
        this.personagensEscolhidos = new ArrayList<>();
    }
}
