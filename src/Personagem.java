import java.util.ArrayList;

public abstract class Personagem {
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

    public abstract boolean atacar(Personagem oponente, Tabuleiro tabuleiro);

    public abstract void defender(Tabuleiro tabuleiro);

    public boolean mover(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {

        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        int casa = 0;
        for (int k = 0; k < tabuleiro.getPosicoes().size(); k++) {
            if (tabuleiro.getPosicoes().get(k).getPersonagem() == this) {
                casa = k;
            }
        }
        System.out.println(ladoQueVai);
        boolean oxi = true;
        switch (ladoQueVai) {
            case 1:
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                int ondeIr = casa - (10 * quantiaAandar);
                System.out.println("onde eu vou? " + ondeIr);
                if (casa <= 10) {
                    return false;
                }
                for (int i = casa - 10; i > ondeIr; i -= 10) {
                    if (i<=10){
                        return false;
                    }
                    if (posicoes.get(i) != null) {
                        oxi = false;
                        break;
                    }
                }
                Posicao posicao = new Posicao();
                posicao.setPersonagem(this);
                Posicao antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                Posicao nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 2:

                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa + (10 * quantiaAandar);
                System.out.println("onde eu vou? " + ondeIr);
                if (casa >= 91) {
                    return false;
                }
                for (int i = casa + 10; i < ondeIr; i += 10) {
                    if (i>=91){
                        return false;
                    }
                    if (posicoes.get(i) != null) {
                        oxi = false;
                        break;
                    }
                }
                posicao = new Posicao();
                posicao.setPersonagem(this);
                antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 3:
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa - quantiaAandar;
                System.out.println("onde eu vou? " + ondeIr);

                for (int i = casa - 1; i > ondeIr; i -= 1) {
                    if (i%10==0){
                            return false;

                    }
//                    if ((casa-quantiaAandar+1)%10==0)
                    if (posicoes.get(i) != null) {
                        oxi = false;
                        break;
                    }
                }
                posicao = new Posicao();
                posicao.setPersonagem(this);
                antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
            case 4:
                System.out.println(this + " o porra");
                System.out.println(casa + " o caraio");
                ondeIr = casa + quantiaAandar;
                System.out.println("onde eu vou? " + ondeIr);

                for (int i = casa + 1; i < ondeIr; i += 1) {
                    if ((i + 1) % 10 == 0) {
                            return false;

                    }
                    if (posicoes.get(i) != null) {
                        break;
                    }
                }
                posicao = new Posicao();
                posicao.setPersonagem(this);
                antiga = tabuleiro.getPosicoes().get(casa);
                antiga.setPersonagem(null);
                nova = tabuleiro.getPosicoes().get(ondeIr);
                nova.setPersonagem(this);
                break;
        }
        return oxi;
    }

    public Posicao getPosicao() {
        return posicao;
    }
}
