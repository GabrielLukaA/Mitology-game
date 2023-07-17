import java.util.ArrayList;

public class Arqueiro extends Personagem {


    public Arqueiro(int numero){
        this.vida = 100;
        this.ataque = 90;
        this.defesa = 20;
        this.alcance =3;
        this.movimento = 4;
        this.nome = "Arqueiro";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 2;
        this.player = numero;

    }

    @Override
    public void atacar(Personagem oponente) {

        int ataque = this.ataque - oponente.defesa;
//        oponente.defender(ataque);

    }

    @Override
    public void defender(Tabuleiro tabuleiro) {


    }

    @Override
 public boolean movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {
    ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int casa = 0;
        for(int k=0;k<tabuleiro.getPosicoes().size();k++) {
            if (tabuleiro.getPosicoes().get(k).getPersonagem()==this){
                  casa = k;
            }
        }
        System.out.println(ladoQueVai);
        boolean oxi = true;
    switch (ladoQueVai){
        case 1:
            System.out.println(this+ " o porra");
            System.out.println(casa + " o caraio");
           int ondeIr = casa -(10*quantiaAandar);
            System.out.println("onde eu vou? "+ondeIr);
            for (int i = casa-10; i != ondeIr; i=i-10) {
                if (posicoes.get(i)!=null){
                    oxi = false;
                    break;
                }
            }
            Posicao posicao = new Posicao();
            posicao.setPersonagem(this);
           Posicao antiga = tabuleiro.getPosicoes().get(casa);
           antiga.setPersonagem(null);
          Posicao nova =  tabuleiro.getPosicoes().get(ondeIr);
          nova.setPersonagem(this);
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
    }
    return oxi;
       }

    @Override
    public String toString() {
        return "Arqueiro{" +
                "vida=" + vida +
                ", ataque=" + ataque +
                ", defesa=" + defesa +
                ", alcance=" + alcance +
                ", movimento=" + movimento +
                ", nome='" + nome + '\'' +
                '}';
    }
}
