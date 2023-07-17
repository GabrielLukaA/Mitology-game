public class Posicao {
    private Personagem personagem;

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    @Override
    public String toString() {
        if (this.personagem == null){
            return " ";
        }
        String letra = personagem.nome.substring(0, 1);
        return letra;
    }
}
