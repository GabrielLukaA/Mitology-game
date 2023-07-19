public class NinfaDoBosque extends Personagem {
    public NinfaDoBosque(int numero) {
        this.vida = 75;
        this.vidaMax=75;
        this.ataque = 25;
        this.defesa = 20;
        this.alcance = 1;
        this.movimento = 2;
        this.nome = "Ninfa do Bosque";
        this.inGame = this.nome + " Vida:" + this.vida;
        this.custo = 6;
        this.player = numero;

    }

    public void defender(Tabuleiro tabuleiro) {
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPersonagem() == this) {
                System.out.println("Entrei");
                int l = tabuleiro.getPosicoes().indexOf(posicao);
                System.out.println(l);
                if (l > 9 & l < 90) {
                    System.out.println("If comum");
                    if (l % 10 == 0) {
                        System.out.println("to nas ponta da esquerda");
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 11).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 9).getPersonagem());
                    } else if ((l + 1) % 10 == 0) {
                        System.out.println("to na ponta da direita");
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 11).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 9).getPersonagem());
                    } else {
                        System.out.println("To em algum lugar");
                        curar(tabuleiro.getPosicoes().get(l + 9).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 11).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 9).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 11).getPersonagem());
                    }
                } else {
                    if (l == 90) {
                        curar(tabuleiro.getPosicoes().get(l + 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 9).getPersonagem());
                    } else if (l == 99) {
                        curar(tabuleiro.getPosicoes().get(l - 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l - 11).getPersonagem());
                    } else if (l == 0) {
                        curar(tabuleiro.getPosicoes().get(l + 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 11).getPersonagem());
                    } else if (l == 9) {
                        curar(tabuleiro.getPosicoes().get(l - 1).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 10).getPersonagem());
                        curar(tabuleiro.getPosicoes().get(l + 9).getPersonagem());
                    } else if (l < 9) {
                        tabuleiro.getPosicoes().get(l - 1).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l + 1).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l + 9).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l + 10).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l + 11).getPersonagem().vida += (this.vida / 100 * 30);
                    } else {
                        tabuleiro.getPosicoes().get(l + 1).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l - 1).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l - 9).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l - 10).getPersonagem().vida += (this.vida / 100 * 30);
                        tabuleiro.getPosicoes().get(l - 11).getPersonagem().vida += (this.vida / 100 * 30);
                    }
                }
            }
        }
    }

    public void curar(Personagem personagem) {
        if (!(personagem == null)) {
            personagem.vida += (personagem.vidaMax / 100 * 10);
            if (personagem.vida > personagem.vidaMax) {
                personagem.vida = personagem.vidaMax;
            }
        }

    }

}