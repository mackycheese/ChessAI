package co.megadodo.chessai;

import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public class AI2 extends ChessAI {
	
	int positions=0;
	int calls=0;
	int prunedCalls=0;
	
	float minimax(int depth,Board game,float alpha,float beta,boolean isMaxPlayer,PieceColor col) {
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
				best=Math.max(best, minimax(depth-1,newGame,alpha,beta,!isMaxPlayer,col.opp()));
				alpha=Math.max(alpha,best);
				if(beta<=alpha) {
					prunedCalls+=1;
					return best;
				}
			}
			return best;
		}else {
			float best=100000;
			for(Float[]move:moves) {
				Board newGame=game.makeMove(move);
				best=Math.min(best, minimax(depth-1,newGame,alpha,beta,!isMaxPlayer,col.opp()));
				beta=Math.min(beta, best);
				if(beta<=alpha) {
					prunedCalls+=1;
					return best;
				}
			}
			return best;
		}
	}
	
	@Override
	public Float[] getMove(Board board, PieceColor color) {
		positions=0;
		calls=0;
		prunedCalls=0;
		
		Float[]bestMove=null;
		float bestF=-10000;
		
		ArrayList<Float[]>moves=board.getMoves(color);
		
		for(Float[]move:moves) {
			float val=minimax(3,board.makeMove(move),-10000,10000,false,color.opp());
			if(val>=bestF) {
				bestF=val;
				bestMove=move;
			}
		}
		
		System.out.println();
		System.out.println("# of leaf nodes          : "+positions);
		System.out.println("# of intermediate nodes  : "+calls);
		System.out.println("# of pruned nodes        : "+prunedCalls);
		System.out.println();
		System.out.println("% of leaf nodes          : "+(float)(100.0f*positions/calls));
		System.out.println("% of intermediate nodes  : "+(float)(100.0f*(calls-positions-prunedCalls)/calls));
		System.out.println("% pruned nodes           : "+(float)(100.0f*prunedCalls/calls));
		
		return bestMove;
	}

}