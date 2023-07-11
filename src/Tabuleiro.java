public class Tabuleiro {

    private Personagem posicoes [][];

    public Tabuleiro(){
        this.posicoes = new Personagem[10][10];
    }

    public void adicionaTabuleiro(int i, int j, Personagem personagem){
        this.posicoes[i][j] = personagem;
    }

    private void limparTabuleiro(){
    this.posicoes = new Personagem[10][10];
    }
    private void verificaMovimento(){
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if(posicoes[i][j]==null){

                }

            }
        }
    }
    public void retiraDoTabuleiro(Personagem personagem) {
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (this.posicoes[i][j] == personagem){
                    this.posicoes[i][j] = null;
                }
            }
        }
    }
    public Personagem pegaPosicao(int i, int j){
        return this.posicoes[i][j];
    }
    public void constroiTabuleiro(){
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (j==0){
                    if (posicoes[i][j]!=null){
                        System.out.print("| x  "+posicoes[i][j].nome+", "+posicoes[i][j].vida+"  Linha: "+i+"  Coluna: "+j+" 2 |  ");
                    } else {
                        System.out.print("| x ");
                    }
                }
                 if (j==9){
                    if (posicoes[i][j]!=null){
                        System.out.println(posicoes[i][j].nome+", "+posicoes[i][j].vida+"  Linha: "+i+"  Coluna: "+j+" x |");
                    } else {
                        System.out.println(" x |");
                    }

                }
                else {
                    if (posicoes[i][j]!=null){
                        System.out.print(posicoes[i][j].nome+", "+posicoes[i][j].vida+"  Linha: "+i+"  Coluna: "+j+" 3 |  ");
                    } else {
                        System.out.print("  |  ");
                    }

                }
            }
        }

    }

    public Personagem[][] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Personagem[][] posicoes) {
        this.posicoes = posicoes;
    }
}
