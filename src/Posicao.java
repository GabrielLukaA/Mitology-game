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
        String letra = personagem.getNome().substring(0, 1);
        String vida = personagem.getVida()+"";
        if (personagem.getVida()<10){
            vida = "00"+personagem.getVida();
        } else if (personagem.getVida()<100){
            vida = "0"+personagem.getVida();
        }
        return letra+" - "+personagem.getPlayer()+" V - "+vida;
    }
}
