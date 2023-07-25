import java.util.ArrayList;

public class Jogador {
     private String nome;
    private int elixir;

    private int numero;
    private int casasInicio;
    private int casasFinal;

    private int mortos;
    private ArrayList<Personagem> personagensEscolhidos;

    public Jogador(String nome, int numero, int casasInicio, int casasFinal) {
        this.nome = nome;
        this.elixir = 50;
        this.numero = numero;
        this.casasInicio = casasInicio;
        this.casasFinal = casasFinal;
        this.mortos = 0;
        this.personagensEscolhidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCasasInicio() {
        return casasInicio;
    }

    public void setCasasInicio(int casasInicio) {
        this.casasInicio = casasInicio;
    }

    public int getCasasFinal() {
        return casasFinal;
    }

    public void setCasasFinal(int casasFinal) {
        this.casasFinal = casasFinal;
    }

    public int getMortos() {
        return mortos;
    }

    public void setMortos(int mortos) {
        this.mortos = mortos;
    }

    public ArrayList<Personagem> getPersonagensEscolhidos() {
        return personagensEscolhidos;
    }

    public void setPersonagensEscolhidos(ArrayList<Personagem> personagensEscolhidos) {
        this.personagensEscolhidos = personagensEscolhidos;
    }
}
