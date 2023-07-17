import java.util.ArrayList;

public class Tabuleiro {


    private ArrayList<Posicao> listaPosicoes = new ArrayList<>(100);

    public Tabuleiro(){
        for (int i = 0; i < 100; i++) {

            if (i==4){
                Posicao posicao = new Posicao();
                posicao.setPersonagem(new NinfaDoBosque(1));
                listaPosicoes.add(posicao);
            } else {
                listaPosicoes.add(new Posicao());
            }
        }
    }

    public void setPosicoes(int i, Personagem personagem) {
        Posicao posicao = new Posicao();
        posicao.setPersonagem(personagem);
        this.listaPosicoes.get(i).setPersonagem(personagem);
    }

    public ArrayList<Posicao> getPosicoes() {
        return listaPosicoes;
    }
}
