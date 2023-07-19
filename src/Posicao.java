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
            return "             ";
        }
        String letra = personagem.nome.substring(0, 1);
        String vida = personagem.vida+"";
        if (personagem.vida<10){
            vida = "00"+personagem.vida;
        } else if (personagem.vida<100){
            vida = "0"+personagem.vida;
        }
        return letra+" - "+personagem.player+" V - "+vida;
    }
}
