package co.megadodo.chessai;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public abstract class ChessAI {

	public static float evalPosition(Board board, PieceColor me) {
		if(board.isCheckmate(me))return -1000;
		if(board.isCheckmate(me.opp()))return 1000;
		float f=0;
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				float v=board.arr[x][y].type.getValue();
				if(board.arr[x][y].color!=me) {
					v*=-1;
				}
				f+=v;
			}
		}
//		if(board.isKingInCheck(me))f-=20;
//		if(board.isKingInCheck(me.opp()))f+=20;
		return f;
	}
	
	public static Float[] getBestMove(Board board, PieceColor color) {
		ArrayList<Float[]>moves=board.getMoves(color);
		int bestI=(int)(Math.random()*moves.size());
		float bestF=evalPosition(board.makeMove(moves.get(bestI)),color);
		for(int i=0;i<moves.size();i++) {
			float f=evalPosition(board.makeMove(moves.get(i)),color);
			if(f>bestF) {
				bestI=i;
				bestF=f;
			}
		}
		return moves.get(bestI);
	}
	
	
	public abstract Point2D.Float[] getMove(Board board,PieceColor color);

}
