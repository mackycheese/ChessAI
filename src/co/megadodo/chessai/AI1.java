package co.megadodo.chessai;

import java.awt.geom.Point2D.Float;

public class AI1 extends ChessAI {

	@Override
	public Float[] getMove(Board board, PieceColor color) {
		return ChessAI.getBestMove(board,color);
	}

}
