import java.util.ArrayList;

public class Arqueiro extends Personagem {


    public Arqueiro(int numero) {

        this.setVida(100);
        this.setVidaMax(100);
        this.setAtaque(90);
        this.setDefesa(50);
        this.setAlcance(3);
        this.setMovimento(4);
        this.setNome("Arqueiro");
        this.setCusto(2);
        this.setPlayer(numero);

    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int quantidadeSemCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem personagemACurar = null;
        int indicePersonagem = 0;

        //Pega o index do Arqueiro
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indicePersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        //Semelhante ao Centauro, mas agora realizando verificações se não está na primeira ou ultima linha do tabuleiro,
        //pois o arqueiro cura no eixo y
        if (indicePersonagem >= 10 && indicePersonagem <= 89) {

            if (posicoes.get(indicePersonagem + 10).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem + 10).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                quantidadeSemCura++;
            }

            if (posicoes.get(indicePersonagem - 10).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem - 10).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                quantidadeSemCura++;
            }

        } else if (indicePersonagem < 10) {
            if (posicoes.get(indicePersonagem + 10).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem + 10).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                return false;
            }

        } else if (indicePersonagem > 89) {
            if (posicoes.get(indicePersonagem - 10).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem - 10).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                return false;
            }
        }

//Verificação se não conseguiu curar ninguém.
        if (quantidadeSemCura == 2) {
            return false;
        }
        return true;
    }

}
