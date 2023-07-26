import java.text.DecimalFormat;

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
        DecimalFormat formato = new DecimalFormat("#0.0");
        if (this.personagem == null) {
            return "               ";
        }
        String letra = personagem.getNome().substring(0, 1);
        String vida = formato.format(personagem.getVida()) + "";
        if (personagem.getVida() < 10) {
            String formatada = formato.format(personagem.getVida());
            vida = "00" + formatada;
        } else if (personagem.getVida() < 100) {
            String formatada = formato.format(personagem.getVida());
            vida = "0" + formatada;
        }
        return letra + " - " + personagem.getPlayer() + " V - " + vida;
    }
}
