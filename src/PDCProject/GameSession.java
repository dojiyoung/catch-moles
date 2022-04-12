/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDCProject;

import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author ssr7324
 */
public class GameSession {

    private final Gameboard gameboard;
    private final Scanner scanner;
    private final Score score = new Score();
    private final Timer time = new Timer();
    private final UserScoreManager userScoreManager = new UserScoreManager();
    private User checkUser = new User();
    public GameSession(Gameboard gameboard, Scanner scanner) {
        this.gameboard = gameboard;
        this.scanner = scanner;
    }

    public void gameStart() {

        PrintGameTask printGameTask = new PrintGameTask(gameboard);
        UpdateBoardTask updateBoardTask = new UpdateBoardTask(gameboard);

        time.schedule(printGameTask, 100, 2000);
        time.schedule(updateBoardTask, 0, 2001);
        while (true) {
            boolean isValidIndex = false;
            String inputFromUser = null;
            int indexFromUser = -1;
            do {
                inputFromUser = scanner.nextLine();
                if ("q".equalsIgnoreCase(inputFromUser)) {
                    
                    userScoreManager.updateUserScore(new User(checkUser.getUserName()), score);
                    System.out.println("Score: " + score.getScore());
                    System.exit(0);
                } else {
                    try {
                        indexFromUser = Integer.parseInt(inputFromUser);

                        if (indexFromUser < 1 || 9 < indexFromUser) {
                            System.out.println(" Invalid Input ! ! ! ");
                        } else {
                            isValidIndex = true;

                        }
                    } catch (NumberFormatException e) {
                        
                        System.out.println("Please enter 'q' for quit anytime or "
                                + "a number between 1 and 9!");
                    }
                }
            } while (!isValidIndex);

            System.out.println(gameboard.isMoleVisibleAtIndex(indexFromUser - 1));
            if (gameboard.isMoleVisibleAtIndex(indexFromUser - 1) == true) {
                score.setScore(score.getScore() + 10);
            }
        }
    }
}
