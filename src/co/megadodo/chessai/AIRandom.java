package co.megadodo.chessai;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class AIRandom extends ChessAI {

	@Override
	public Point2D.Float[] getMove(Board board,PieceColor color) {
		ArrayList<Point2D.Float[]>moves=board.getMoves(color);
		return moves.get((int)(Math.random()*moves.size()));
	}

}
