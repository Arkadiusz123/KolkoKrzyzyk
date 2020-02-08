import java.util.*;

public class KolkoKrzyzyk {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Wybierz pole od 1 do 9:");
            int playerPosition = scan.nextInt();
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("Pozycja zajęta, wybierz inną!");
                playerPosition = scan.nextInt();
            }
            put(gameBoard, playerPosition, "player");
            Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = random.nextInt(9) + 1;
            }
            put(gameBoard, cpuPosition, "cpu");
            printBoard(gameBoard);
            String result = checkWinner();
            if(result.length() > 0){
            System.out.println(result);
            break;}
        }
    }

    public static void printBoard(char [] [] gameBoard){
        for(char [] row : gameBoard){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void put(char [] [] gameBoard, int pos, String user){
        char mark = ' ';
        if (user.equals("player")){
            mark = 'x';
            playerPositions.add(pos);
        }else{
            mark = 'O';
            cpuPositions.add(pos);
        }

        switch (pos){
            case 1: gameBoard [0][0] = mark; break;
            case 2: gameBoard [0][2] = mark; break;
            case 3: gameBoard [0][4] = mark; break;
            case 4: gameBoard [2][0] = mark; break;
            case 5: gameBoard [2][2] = mark; break;
            case 6: gameBoard [2][4] = mark; break;
            case 7: gameBoard [4][0] = mark; break;
            case 8: gameBoard [4][2] = mark; break;
            case 9: gameBoard [4][4] = mark; break;
            default: break;
        }
    }
    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List xLeft = Arrays.asList(3, 5, 7);
        List xRight = Arrays.asList(1, 5, 9);

        List<List> winConditions = new ArrayList<>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(bottomRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(xLeft);
        winConditions.add(xRight);

        for (List list: winConditions){
            if (playerPositions.containsAll(list)){
                return "Gratulacje, wygrałeś!";
            }else if(cpuPositions.containsAll(list)){
               return  "Przegrałeś, spróbuj  jeszcze raz!";
            }else if(playerPositions.size()+cpuPositions.size()==9){
                return "Remis!";
            }
        }

        return "";
    }
    }

