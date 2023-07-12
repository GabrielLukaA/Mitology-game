public class Arqueiro extends Personagem {


    public Arqueiro(){
        this.vida = 100;
        this.ataque = 90;
        this.defesa = 20;
        this.alcance =3;
        this.movimento = 4;
        this.nome = "Arqueiro";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 2;

    }

    @Override
    public void atacar(Personagem oponente) {

        int ataque = this.ataque - oponente.defesa;
        oponente.defender(ataque);

    }

    @Override
    public void defender(int ataque) {
       this.vida -= ataque;
        if (this.vida <= 0){

        }

    }

    @Override
    public void movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {
        Personagem posicoes [][] = tabuleiro.getPosicoes();
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                if (posicoes[i][j] == this) {
                    switch (ladoQueVai){
                        case 0:
                           posicoes[i][j] = null;
                            System.out.println(i+quantiaAandar);
                           posicoes[i+quantiaAandar][j] = this;
                           i=11;
                            j=11;
                            break;
                    }
                    break;
                }
            }
        }
    }

    public void verificaMovimento(int ladoQueVai, Tabuleiro tabuleiro) {
        Personagem[][] posicoes = tabuleiro.getPosicoes();

    switch (ladoQueVai){
        case 0:
            for(int i=0;i<10;i++) {
                for (int j = 0; j < 10; j++) {
                    if (posicoes[i][j] == this) {
                        for (int c = i+1; c < movimento+i+1; c++) {
                            System.out.println(c);
                            if (posicoes[c][j] == null) {
                                possiveisMovimentosLinha.add(c);
                                possiveisMovimentosColuna.add(j);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(possiveisMovimentosLinha);
            System.out.println(possiveisMovimentosColuna);
           break;
        case 1:
            break;

        case 2:
            break;

        case 3:
            break;

    }
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
