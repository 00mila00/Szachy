package Algorithm;



import Piece.Move;

import java.util.List;


public class Easy extends Algorithm{


    @Override
    public Move makeMove() {

        List<Move> moves = allySet.getPossibleMoves();
        Move nextMove =  getRandomMove(moves);
        allySet.move(nextMove, enemySet);

        return nextMove;
    }
}
