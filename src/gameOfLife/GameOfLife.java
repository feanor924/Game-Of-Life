
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by caner on 21/12/14.
 */
public class GameOfLife {

    private Cell []livingCells;

    private Cell []livingCellsTemp;

    private static int cellnumber;

    private static int tempcellnumber;

    private static int another=0;

    private int currentX;

    private int currentY;

    private int StartRow;

    private int StartColumn;

    public GameOfLife(){

    }

    public static int cellNumber(){

        return another;
    }

    public int getStartCol() {

        return StartColumn;
    }

    public int getStartRow() {

        return StartRow;
    }

    public void setStartRow(int ro){

        StartRow=ro;
    }

    public void setStartColumn(int col){

        StartColumn=col;
    }

    public int getCurrentX(){

        return currentX;
    }

    public int getCurrentY(){

        return currentY;
    }

    public void setCurrentX(int x){

        currentX=x;
    }

    public void setCurrentY(int y) {

        currentY = y;
    }

    private boolean checkLives(int coory,int coorx){

        for (int i=0;i<livingCells.length;++i){
            Cell c1=new Cell();
            c1.setCoorX(coorx);
            c1.setCoorY(coory);
            if ( livingCells[i].getCoorX() == c1.getCoorX() && livingCells[i].getCoorY() == c1.getCoorY()  )
                return true;
        }
        return false;
    }

    public void printGame(){

        for (int i=getStartRow(); i<getCurrentY(); ++i){
            for (int j=getStartCol(); j<getCurrentX(); ++j){
                if ( checkLives(i,j) == true )
                    System.out.printf("X");
                else
                    System.out.printf("-");
            }
            System.out.printf("\n");
        }
    }

    public void getInitials(String fileName){

        int number,row,column,rowc=1,columnc=1;
        String line;
        cellnumber=0;

        Scanner inputStream=null;

        try
        {
            inputStream = new Scanner(new FileInputStream(fileName));
        }
        catch(FileNotFoundException e )
        {
            System.out.println("File was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }

        row=inputStream.nextInt();
        column=inputStream.nextInt();

        for (int i=0;i<row*column;++i){
            number=inputStream.nextInt();
            if ( number == 1)
                ++cellnumber;
        }

        inputStream.close();

        try
        {
            inputStream = new Scanner(new FileInputStream(fileName));
        }
        catch(FileNotFoundException e )
        {

            System.out.println("File was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }

        livingCells=new Cell[cellnumber];

        cellnumber=0;

        row=inputStream.nextInt();
        column=inputStream.nextInt();

        setCurrentY(row+1);
        setCurrentX(column+1);

        setStartRow(1);
        setStartColumn(1);


        for (int i=0;i<row*column;++i) {
            number = inputStream.nextInt();
            line = inputStream.toString();
            if (number == 1) {
                Cell c1 = new Cell();
                c1.setCoorX(columnc);
                c1.setCoorY(rowc);
                livingCells[cellnumber] = c1;
                ++cellnumber;
                ++another;
            }
            if (columnc == column) {
                ++rowc;
                columnc = 0;
            }
            ++columnc;
        }
        inputStream.close();
    }

    public void playGame(){

        int k=0,rowc,columnc,numbers=0;
        tempcellnumber=0;
        livingCellsTemp=new Cell[cellnumber+cellnumber];

        rowc=getCurrentY()+1;
        columnc=getCurrentX()+1;

        for (int i = getStartRow()-1; i<rowc; ++i){
            for (int j = getStartCol()-1; j<columnc; ++j){
                if (checkLives(i,j+1) == true )
                    ++k;
                if (checkLives(i,j-1) == true)
                    ++k;
                if (checkLives(i+1,j) == true)
                    ++k;
                if (checkLives(i-1,j) == true)
                    ++k;
                if (checkLives(i+1,j+1) == true)
                    ++k;
                if (checkLives(i-1,j-1) == true)
                    ++k;
                if (checkLives(i+1,j-1) == true)
                    ++k;
                if (checkLives(i-1,j+1) == true)
                    ++k;
                if ( checkLives(i,j) == false){
                    if( k == 3 ){
                        ++tempcellnumber;
                        Cell c2=new Cell();
                        c2.setCoorX(j);
                        c2.setCoorY(i);
                        livingCellsTemp[numbers]=c2;
                        ++numbers;
                    }
                }
                else if ( checkLives(i,j) == true){
                    if(k == 3 || k == 2){
                        ++tempcellnumber;
                        Cell c2=new Cell();
                        c2.setCoorX(j);
                        c2.setCoorY(i);
                        livingCellsTemp[numbers]=c2;
                        ++numbers;
                    }
                }
                k = 0;
            }
        }
        livingCells=new Cell[tempcellnumber];

        for (int a=0; a<tempcellnumber; ++a)
            livingCells[a]=livingCellsTemp[a];

        another=another-(cellnumber-tempcellnumber);
        cellnumber=tempcellnumber;
        checkColumnsRows();

    }


    private void checkColumnsRows(){
        
        int a,b;
        int temp1=-20,temp2=-20,temp3=20,temp4=20;

        for (int i=0; i<2; ++i){
            for (int k=0; k<livingCells.length; ++k){
                a=livingCells[k].getCoorX();
                b=livingCells[k].getCoorY();
                if(a>temp1)
                    temp1=a;
                else if (a<temp3)
                    temp3=a;
                if (b>temp2)
                    temp2=b;
                else if (b<temp4)
                    temp4=b;
            }
        }
        setCurrentY(temp2+1);
        setCurrentX(temp1+1);
        setStartRow(temp4);
        setStartColumn(temp3);
    }

    public void joinsOtherCells(GameOfLife g1){

        int a=livingCells.length;
        Cell []livingCell=new Cell[livingCells.length+g1.livingCells.length];

        for (int i=0;i<livingCells.length;++i)
            livingCell[i]=livingCells[i];


        for (int i=0;i<g1.livingCells.length;++i){
            livingCell[a]=g1.livingCells[i];
            ++a;
        }

        livingCells=new Cell[livingCell.length];

        for (int i=0;i<livingCell.length;++i)
            livingCells[i]=livingCell[i];

        cellnumber=livingCells.length;
        another=livingCells.length;

    }

}
