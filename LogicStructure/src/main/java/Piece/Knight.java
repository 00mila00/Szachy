package Piece;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(int x, int y, PieceColor color, int value, String id) {
        super(x, y, color, value, id);
    }

    @Override
    public Move[] getPossibleMoves(List<Piece> allOtherPieces) {
        List<Move> listPossibleMoves = new ArrayList<>();

        for(int direction = 0; direction < 8; direction++) {
            int xFactor = 0;
            int yFactor = 0;
            int tmpX = currentX;
            int tmpY = currentY;

            switch (direction) {
                    //UP RIGHT
                case 0:
                    xFactor = 1;
                    yFactor = 2;
                    break;
                case 1:
                    xFactor = 2;
                    yFactor = 1;
                    break;

                    // DOWN RIGHT
                case 2:
                    xFactor = 2;
                    yFactor = -1;
                    break;
                case 3:
                    xFactor = 1;
                    yFactor = -2;
                    break;

                    //DOWN LEFT
                case 4:
                    xFactor = -1;
                    yFactor = -2;
                    break;
                case 5:
                    xFactor = -2;
                    yFactor = -1;
                    break;

                   //UP LEFT
                case 6:
                    xFactor = -2;
                    yFactor = 1;
                    break;
                case 7:
                    xFactor = -1;
                    yFactor = 2;
                    break;
            }

            tmpX = tmpX + xFactor;
            tmpY = tmpY + yFactor;
            if(checkIfPossibleMove(allOtherPieces,tmpX,tmpY)) {
                Move move = new Move(currentX, currentY, tmpX, tmpY);
                move.setAttack(isMoveAttack(allOtherPieces, move));
                listPossibleMoves.add(move);
            }
        }

        Move[] possibleMoves = new Move[listPossibleMoves.size()];
        for(int i = 0; i < listPossibleMoves.size(); i++) {
            possibleMoves[i] = listPossibleMoves.get(i);
        }
        return possibleMoves;
    }

    private boolean checkIfPossibleMove(List<Piece> allOtherPieces, int x, int y) {
        if( (x < 0) || (x > 7) ) {
            return false;
        }
        if( (y < 0) || (y > 7) ) {
            return false;
        }
        return isMovePossible(allOtherPieces, x, y);
    }

}
