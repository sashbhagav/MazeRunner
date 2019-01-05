/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinogame;

import java.util.List;

/**
 *
 * @author sashreek
 */
public class Depth {

    public static boolean randomPath(int[][] puzzle, int x, int y, int _max_x, int _max_y, List<Integer> path) {
     	if (x > 0 && y > 0 && x < _max_x && y < _max_y) {
            if (puzzle[y][x]==9){
                path.add(y);
                path.add(x);
                return true;
            }
        if (puzzle[y][x] == 1) {//will go through maze to find number 1's which is where player can move
                puzzle[y][x] = 2;
                int fx = -1;
                int fy = 0;
                if (randomPath(puzzle, x + fx, y + fy, _max_x, _max_y, path)) {
                    path.add(x);
                    path.add(y);
                    return true;
                  //goes through path to find possible ways to create maze
                }
                fx = 1;
                fy = 0;
                if (randomPath(puzzle, x + fx, y + fy, _max_x, _max_y, path)) {
                    path.add(x);
                    path.add(y);
                    return true;
                }
                fx = 0;
                fy = -1;
                if (randomPath(puzzle, x + fx, y + fy, _max_x, _max_y, path)) {
                    path.add(x);
                    path.add(y);
                    return true;
                }
                fx = 0;
                fy = 1;
                if (randomPath(puzzle, x + fx, y + fy, _max_x, _max_y, path)) {
                    path.add(x);
                    path.add(y);
                    return true;
                }
            }
            return false;
         } else {
        	 return false;
         }
   }
    
}
