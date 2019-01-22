package co.megadodo.chessai;

import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public class AI2 extends ChessAI {
	
	int positions=0;
	int calls=0;
	
	float minimax(int depth,Board game,boolean isMaxPlayer,PieceColor col) {
		calls+=1;
		if(depth<=0) {
			positions+=1;
			return -ChessAI.evalPosition(game, PieceColor.White);
		}
		ArrayList<Float[]>moves=game.getMoves(col);
		if(isMaxPlayer) {
			float best=-100000;
			for(Float[]move:moves) {
				Board newGame=game.makeMove(move);
				best=Math.max(best, minimax(depth-1,newGame,!isMaxPlayer,col.opp()));
			}
			return best;
		}else {
			float best=100000;
			for(Float[]move:moves) {
				Board newGame=game.makeMove(move);
				best=Math.min(best, minimax(depth-1,newGame,!isMaxPlayer,col.opp()));
			}
			return best;
		}
	}
	
	@Override
	public Float[] getMove(Board board, PieceColor color) {
		positions=0;
		calls=0;
		
		Float[]bestMove=null;
		float bestF=-10000;
		
		ArrayList<Float[]>moves=board.getMoves(color);
		
		for(Float[]move:moves) {
			float val=minimax(2,board.makeMove(move),false,color.opp());
			if(val>=bestF) {
				bestF=val;
				bestMove=move;
			}
		}
		
		System.out.println("Positions searched: "+positions);
		System.out.println("Calls to recursive function: "+calls);
		
		return bestMove;
	}

}
