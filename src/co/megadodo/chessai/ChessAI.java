//package co.megadodo.chessai;
//
//import java.awt.geom.Point2D;
//import java.awt.geom.Point2D.Float;
//import java.util.ArrayList;
//
//public abstract class ChessAI {
//
//	public static float evalPosition(Board board, PieceColor me) {
//		if(board.isCheckmate(me))return -1000000;
//		if(board.isCheckmate(me.opp()))return 100000;
//		float f=0;
//		for(int x=0;x<8;x++) {
//			for(int y=0;y<8;y++) {
//				float v=board.arr[x][y].type.getValue();
//				if(board.arr[x][y].color!=me) {
//					v*=-1;
//				}
//				f+=v;
//			}
//		}
////		if(board.isKingInCheck(me))f-=50;
////		if(board.isKingInCheck(me.opp()))f+=20;
//		return f;
//	}
//	
//	public static final float[][] pawnWeight = transpose(new float[][]{
//			{0,0,0,0,0,0,0,0},
//			{50,50,50,50,50,50,50,50},
//			{10,10,20,30,30,20,10,10},
//			{5,5,10,25,25,10,5,5},
//			{0,0,0,20,20,0,0,0},
//			{5,-5,-10,0,0,-10,-5,5},
//			{5,10,10,-20,-20,10,10,5},
//			{0,0,0,0,0,0,0,0}
//	});
//	
//	public static final float[][] rookWeight = transpose(new float[][] {
//		{0,0,0,0,0,0,0,0},
//		{5,10,10,10,10,10,10,5},
//		{-5,0,0,0,0,0,0,-5},
//		{-5,0,0,0,0,0,0,-5},
//		{-5,0,0,0,0,0,0,-5},
//		{-5,0,0,0,0,0,0,-5},
//		{-5,0,0,0,0,0,0,-5},
//		{0,0,0,5,5,0,0,0}
//	});
//	
//	public static final float[][] knightWeight = transpose(new float[][] {
//		{-50,-40,-30,-30,-30,-30,-40,-50},
//		{-40,-20,0,0,0,0,-20,-40},
//		{-30,0,10,15,15,10,0,-30},
//		{-30,5,15,20,20,15,5,-30},
//		{-30,0,15,20,20,15,0,-30},
//		{-30,5,10,15,15,10,5,-30},
//		{-40,-20,0,5,5,0,-20,-40},
//		{-50,-40,-30,-30,-30,-30,-40,-50}
//	});
//	
//	public static final float[][] bishopWeight = transpose(new float[][] {
//		{-20,-10,-10,-10,-10,-10,-10,-20},
//		{-10,0,0,0,0,0,0,-10},
//		{-10,0,5,10,10,5,0,-10},
//		{-10,5,5,10,10,5,5,-10},
//		{-10,0,10,10,10,10,0,-10},
//		{-10,10,10,10,10,10,10,-10},
//		{-10,5,0,0,0,0,5,-10},
//		{-20,-10,-10,-10,-10,-10,-10,-20}
//	});
//	
//	public static final float[][] kingWeight = transpose(new float[][] {
//		{-30,-40,-40,-50,-50,-40,-40,-30},
//		{-30,-40,-40,-50,-50,-40,-40,-30},
//		{-30,-40,-40,-50,-50,-40,-40,-30},
//		{-30,-40,-40,-50,-50,-40,-40,-30},
//		{-20,-30,-30,-40,-40,-30,-30,-20},
//		{-10,-20,-20,-20,-20,-20,-20,-10},
//		{20,20,0,0,0,0,20,20},
//		{20,30,10,0,0,10,30,20}
//	});
//	
//	public static final float[][] queenWeight = transpose(new float[][] {
//		{-20,-10,-10,-5,-5,-10,-10,-20},
//		{-10,0,0,0,0,0,0,-10},
//		{-10,0,5,5,5,5,0,-10},
//		{-5,0,5,5,5,5,0,-5},
//		{0,0,5,5,5,5,0,0},
//		{-10,5,5,5,5,5,5,-10},
//		{-10,0,5,0,0,5,0,-10},
//		{-20,-10,-10,-5,-5,-10,-10,-20}
//	});
//	
//	public static float[][] transpose(float[][]arr){
//		for(int x=0;x<8;x++) {
//			for(int y=x+1;y<8;y++) {
//				float temp=arr[x][y];
//				arr[x][y]=arr[y][x];
//				arr[y][x]=temp;
//			}
//		}
//		return arr;
//	}
//	
//	public static float evalPositionWeighted(Board board,PieceColor me) {
//		if(board.isCheckmate(me))return -1000000;
//		if(board.isCheckmate(me.opp()))return 100000;
//		float f=0;
//		for(int x=0;x<8;x++) {
//			for(int y=0;y<8;y++) {
//				float w=1.0f;
//				int by=y;
//				if(me==PieceColor.Black)by=7-by;
//				if(board.arr[x][y].type==PieceType.Pawn) {
//					w=pawnWeight[x][by];
//				}else if(board.arr[x][y].type==PieceType.Rook) {
//					w=rookWeight[x][by];
//				}else if(board.arr[x][y].type==PieceType.Knight) {
//					w=knightWeight[x][by];
//				}else if(board.arr[x][y].type==PieceType.Bishop) {
//					w=bishopWeight[x][by];
//				}else if(board.arr[x][y].type==PieceType.King) {
//					w=kingWeight[x][by];
//				}else if(board.arr[x][y].type==PieceType.Queen) {
//					w=queenWeight[x][by];
//				}
//				float v=board.arr[x][y].type.getValue();
//				if(board.arr[x][y].color!=me) {
//					v*=-1;
//				}
//				f+=v;
//			}
//		}
////		if(board.isKingInCheck(me))f-=50;
////		if(board.isKingInCheck(me.opp()))f+=20;
//		return f;
//	}
//	
//	public static Float[] getBestMove(Board board, PieceColor color) {
//		ArrayList<Float[]>moves=board.getMoves(color);
//		int bestI=(int)(Math.random()*moves.size());
//		float bestF=evalPosition(board.makeMove(moves.get(bestI)),color);
//		for(int i=0;i<moves.size();i++) {
//			float f=evalPosition(board.makeMove(moves.get(i)),color);
//			if(f>bestF) {
//				bestI=i;
//				bestF=f;
//			}
//		}
//		return moves.get(bestI);
//	}
//	
//	
//	public abstract Point2D.Float[] getMove(Board board,PieceColor color);
//
//}

package co.megadodo.chessai;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public abstract class ChessAI {
	
	public static float[][] transpose(float[][]arr){
		for(int x=0;x<8;x++) {
			for(int y=x+1;y<8;y++) {
				float temp=arr[x][y];
				arr[x][y]=arr[y][x];
				arr[y][x]=temp;
			}
		}
		return arr;
	}
	
	public static final float[][] pawnWeight = transpose(new float[][]{
			{0,0,0,0,0,0,0,0},
			{50,50,50,50,50,50,50,50},
			{10,10,20,30,30,20,10,10},
			{5,5,10,25,25,10,5,5},
			{0,0,0,20,20,0,0,0},
			{5,-5,-10,0,0,-10,-5,5},
			{5,10,10,-20,-20,10,10,5},
			{0,0,0,0,0,0,0,0}
	});
	
	public static final float[][] rookWeight = transpose(new float[][] {
		{0,0,0,0,0,0,0,0},
		{5,10,10,10,10,10,10,5},
		{-5,0,0,0,0,0,0,-5},
		{-5,0,0,0,0,0,0,-5},
		{-5,0,0,0,0,0,0,-5},
		{-5,0,0,0,0,0,0,-5},
		{-5,0,0,0,0,0,0,-5},
		{0,0,0,5,5,0,0,0}
	});
	
	public static final float[][] knightWeight = transpose(new float[][] {
		{-50,-40,-30,-30,-30,-30,-40,-50},
		{-40,-20,0,0,0,0,-20,-40},
		{-30,0,10,15,15,10,0,-30},
		{-30,5,15,20,20,15,5,-30},
		{-30,0,15,20,20,15,0,-30},
		{-30,5,10,15,15,10,5,-30},
		{-40,-20,0,5,5,0,-20,-40},
		{-50,-40,-30,-30,-30,-30,-40,-50}
	});
	
	public static final float[][] bishopWeight = transpose(new float[][] {
		{-20,-10,-10,-10,-10,-10,-10,-20},
		{-10,0,0,0,0,0,0,-10},
		{-10,0,5,10,10,5,0,-10},
		{-10,5,5,10,10,5,5,-10},
		{-10,0,10,10,10,10,0,-10},
		{-10,10,10,10,10,10,10,-10},
		{-10,5,0,0,0,0,5,-10},
		{-20,-10,-10,-10,-10,-10,-10,-20}
	});
	
	public static final float[][] kingWeight = transpose(new float[][] {
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-20,-30,-30,-40,-40,-30,-30,-20},
		{-10,-20,-20,-20,-20,-20,-20,-10},
		{20,20,0,0,0,0,20,20},
		{20,30,10,0,0,10,30,20}
	});
	
	public static final float[][] queenWeight = transpose(new float[][] {
		{-20,-10,-10,-5,-5,-10,-10,-20},
		{-10,0,0,0,0,0,0,-10},
		{-10,0,5,5,5,5,0,-10},
		{-5,0,5,5,5,5,0,-5},
		{0,0,5,5,5,5,0,0},
		{-10,5,5,5,5,5,5,-10},
		{-10,0,5,0,0,5,0,-10},
		{-20,-10,-10,-5,-5,-10,-10,-20}
	});

	public static float evalPosition(Board board, PieceColor me) {
		if(board.isCheckmate(me))return -1000;
		if(board.isCheckmate(me.opp()))return 1000;
		float f=0;
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				float w=1.0f;
				int by=y;
				if(me==PieceColor.Black)by=7-by;
				if(board.arr[x][y].type==PieceType.Pawn) {
					w=pawnWeight[x][by];
				}else if(board.arr[x][y].type==PieceType.Rook) {
					w=rookWeight[x][by];
				}else if(board.arr[x][y].type==PieceType.Knight) {
					w=knightWeight[x][by];
				}else if(board.arr[x][y].type==PieceType.Bishop) {
					w=bishopWeight[x][by];
				}else if(board.arr[x][y].type==PieceType.King) {
					w=kingWeight[x][by];
				}else if(board.arr[x][y].type==PieceType.Queen) {
					w=queenWeight[x][by];
				}
				float v=board.arr[x][y].type.getValue()*10+0.1f*w;
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