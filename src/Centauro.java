import java.util.ArrayList;

public class Centauro extends Personagem {

    public Centauro(int numero) {
        this.setVida(185);
        this.setVidaMax(150);
        this.setAtaque(70);
        this.setDefesa(50);
        this.setAlcance(1);
        this.setMovimento(3);
        this.setNome("Centauro");
        this.setCusto(2);
        this.setPlayer(numero);
    }

    @Override
    public boolean defender(Tabuleiro tabuleiro) {
        int quantidadeSemCura = 0;
        ArrayList<Posicao> posicoes = tabuleiro.getPosicoes();
        Personagem personagemACurar = null;
        int indicePersonagem = 0;


        // Pega o index do Centauro
        for (Posicao posicao : posicoes) {
            if (posicao.getPersonagem() == this) {
                indicePersonagem = tabuleiro.getPosicoes().indexOf(posicao);
            }
        }
        //Centauro cura para o eixo x, então verifica se ele não está em alguma lateral.
        if (!(indicePersonagem % 10 == 0) && !((indicePersonagem + 1) % 10 == 0)) {

            //Realiza a cura à direita
            if (posicoes.get(indicePersonagem + 1).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem + 1).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                quantidadeSemCura++;
            }

            //Realiza a cura à esquerda
            if (posicoes.get(indicePersonagem - 1).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem - 1).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                quantidadeSemCura++;
            }

            //Realiza cura apenas para o lado interno do tabuleiro evitando que a cura ocorra de maneira inesperada.
        } else if (indicePersonagem % 10 == 0) {
            if (posicoes.get(indicePersonagem + 1).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem + 1).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                return false;
            }
        } else if ((indicePersonagem + 1) % 10 == 0) {
            if (posicoes.get(indicePersonagem - 1).getPersonagem() != null) {
                personagemACurar = posicoes.get(indicePersonagem - 1).getPersonagem();
                personagemACurar.setVida(personagemACurar.getVida() + personagemACurar.getVidaMax() / 100 * 15);
                personagemACurar.verificaVidaMaxima();
            } else {
                return false;
            }
        }

        //Verificação caso não haja nenhuma peça nos dois lados, pois em um é possível.
        if (quantidadeSemCura == 2) {
            return false;
        }
        return true;
    }
}
