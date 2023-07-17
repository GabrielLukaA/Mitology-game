public class NinfaDoBosque extends Personagem{
    public NinfaDoBosque(int numero){
        this.vida = 75;
        this.ataque = 25;
        this.defesa = 20;
        this.alcance = 1;
        this.movimento = 2;
        this.nome = "Ninfa do Bosque";
        this.inGame = this.nome+" Vida:"+this.vida;
        this.custo = 6;
        this.player = numero;

    }

    @Override
    public void atacar(Personagem oponente) {

    }

    @Override
    public void defender(Tabuleiro tabuleiro) {
        for (Posicao posicao:tabuleiro.getPosicoes()) {
            if (posicao.getPersonagem()==this){
                System.out.println("Entrei");
                int l = tabuleiro.getPosicoes().indexOf(posicao.getPersonagem());
                if (l>9&l<90){
                    if (l%10==0){
                        tabuleiro.getPosicoes().get(l+10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+11).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-9).getPersonagem().vida += (this.vida/100*30);
                    } else if ((l+1)%10==0){
                        tabuleiro.getPosicoes().get(l+10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-11).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+9).getPersonagem().vida += (this.vida/100*30);
                    } else {
                        tabuleiro.getPosicoes().get(l+9).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+11).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-9).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-11).getPersonagem().vida += (this.vida/100*30);
                    }
                } else {
                    if (l==90){
                        tabuleiro.getPosicoes().get(l+1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-9).getPersonagem().vida += (this.vida/100*30);
                    } else if (l==99){
                        tabuleiro.getPosicoes().get(l-1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l-11).getPersonagem().vida += (this.vida/100*30);
                    } else if (l==0){
                        tabuleiro.getPosicoes().get(l+1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+11).getPersonagem().vida += (this.vida/100*30);
                    } else if (l==9){
                        tabuleiro.getPosicoes().get(l-1).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+10).getPersonagem().vida += (this.vida/100*30);
                        tabuleiro.getPosicoes().get(l+9).getPersonagem().vida += (this.vida/100*30);
                    }
                }
            }
        }
    }
    public void curar(int ataque,Tabuleiro tabuleiro) {

    }

    @Override
    public boolean movimento(int quantiaAandar, Tabuleiro tabuleiro, int ladoQueVai) {
        return  false;
    }
}
