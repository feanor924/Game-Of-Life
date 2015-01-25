import java.awt.datatransfer.SystemFlavorMap;

public class Main {

    public static void main(String[] args) {

        GameOfLife g1=new GameOfLife();
        GameOfLife g2=new GameOfLife();
        GameOfLife g3=new GameOfLife();
        GameOfLife g4=new GameOfLife();
        GameOfLife g5=new GameOfLife();

        g1.getInitials("1.txt");
        System.out.printf("GAME1");
        System.out.printf("\n");
        g1.printGame();

        g2.getInitials("2.txt");
        System.out.printf("GAME2");
        System.out.printf("\n");
        g2.printGame();
        System.out.printf("\n");
        g1.joinsOtherCells(g2);
        System.out.printf("GAME1+GAME2\n");
        g1.printGame();
        System.out.printf("\n");
        g3.getInitials("3.txt");
        System.out.printf("GAME3\n");
        g3.printGame();
        g3.playGame();
        System.out.printf("\n");
        System.out.printf("GAME3 PLAYED\n");
        g3.printGame();

        System.out.printf("\n");
        g4.getInitials("4.txt");
        System.out.printf("GAME4\n");
        g4.printGame();
        g4.playGame();
        System.out.printf("\n");
        System.out.printf("GAME4 PLAYED\n");
        g4.printGame();

        System.out.printf("\n");
        g5.getInitials("5.txt");
        System.out.printf("GAME5\n");
        g5.printGame();
        g5.playGame();
        System.out.printf("\n");
        System.out.printf("GAME5 PLAYED\n");
        g5.printGame();
        System.out.printf("\n");
        System.out.printf("All Cell numbers in game:: %d\n",g1.cellNumber());

    }
}
