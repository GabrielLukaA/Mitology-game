import java.util.ArrayList;

public class Jogador {
    String nome;
    int elixir;

    int numero;
    int casasInicio;
    int casasFinal;

    int mortos;
    ArrayList<Personagem> personagensEscolhidos;

    public Jogador(String nome, int numero, int casasInicio, int casasFinal) {
        this.nome = nome;
        this.elixir = 50;
        this.numero = numero;
        this.casasInicio = casasInicio;
        this.casasFinal = casasFinal;
        this.mortos = 0;
        this.personagensEscolhidos = new ArrayList<>();
    }
}
