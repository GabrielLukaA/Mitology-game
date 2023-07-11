import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Tabuleiro tabuleiro  = new Tabuleiro();
        Arqueiro arqueiro = new Arqueiro();
        Arqueiro arqueiroOponente = new Arqueiro();
        arqueiro.atacar(arqueiroOponente);
        tabuleiro.adicionaTabuleiro(1,1,arqueiro);
        tabuleiro.adicionaTabuleiro(1,0,arqueiroOponente);

        tabuleiro.constroiTabuleiro();
        System.out.println("Informe para que lado deseja mover seu personagem: ");
        int ladoQueVai = sc.nextInt();
        arqueiroOponente.verificaMovimento(ladoQueVai,tabuleiro);
        int c = arqueiroOponente.possiveisMovimentosLinha.size()-1;

        System.out.println("Você pode mover até "+arqueiroOponente.possiveisMovimentosColuna.size()+" para frente, quantas deseja mover?");
       arqueiroOponente.movimento(sc.nextInt(),tabuleiro,ladoQueVai);

        tabuleiro.constroiTabuleiro();

    }

}
