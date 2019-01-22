package co.megadodo.chessai;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Board {

	public Piece[][] arr;
	public boolean[] castleWhite;
	public boolean[] castleBlack;
	
	public Board() {
		arr = new Piece[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				arr[i][j]=Piece.none;
			}
		}
		castleWhite=new boolean[] {true,true,true};
		castleBlack=new boolean[] {true,true,true};
	}
	
	public void setup() {
		arr[0][0]=arr[7][0]=Piece.whiteRook;
		arr[1][0]=arr[6][0]=Piece.whiteKnight;
		arr[2][0]=arr[5][0]=Piece.whiteBishop;
		arr[3][0]=Piece.whiteQueen;
		arr[4][0]=Piece.whiteKing;
		
		arr[0][7]=arr[7][7]=Piece.blackRook;
		arr[1][7]=arr[6][7]=Piece.blackKnight;
		arr[2][7]=arr[5][7]=Piece.blackBishop;
		arr[3][7]=Piece.blackQueen;
		arr[4][7]=Piece.blackKing;
		
		for(int i=0;i<8;i++) {
			arr[i][1]=Piece.whitePawn;
			arr[i][6]=Piece.blackPawn;
		}
		
		
	}
	
	public Board makeMove(int x0,int y0,int x1,int y1) {
		Board b=new Board();
//		
//		if(x0==0&&y0==0) {
//			castleWhite[0]=false;
//		}
//		if(x0==4&&y0==0) {
//			castleWhite[1]=false;
//		}
//		if(x0==7&&y0==0) {
//			castleWhite[2]=false;
//		}
//
//		if(x0==0&&y0==7) {
//			castleBlack[0]=false;
//		}
//		if(x0==4&&y0==7) {
//			castleBlack[1]=false;
//		}
//		if(x0==7&&y0==7) {
//			castleBlack[2]=false;
//		}
//
//		if(x0==4&&y0==0&&x1==6&&y1==0&&arr[x0][y0]==Piece.whiteKing) {
//			arr[5][0]=Piece.whiteRook;
//			arr[7][0]=Piece.none;
//		}
//		if(x0==4&&y0==0&&x1==2&&y1==0&&arr[x0][y0]==Piece.whiteKing) {
//			arr[3][0]=Piece.whiteRook;
//			arr[0][0]=Piece.none;
//		}
//
//		if(x0==4&&y0==7&&x1==6&&y1==7&&arr[x0][y0]==Piece.blackKing) {
//			arr[5][7]=Piece.blackRook;
//			arr[7][7]=Piece.none;
//		}
//		if(x0==4&&y0==7&&x1==2&&y1==7&&arr[x0][y0]==Piece.blackKing) {
//			arr[3][7]=Piece.blackRook;
//			arr[0][7]=Piece.none;
//		}
//		
//		b.castleWhite=castleWhite;
//		b.castleBlack=castleBlack;
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				b.arr[x][y]=arr[x][y];
			}
		}
		
		b.arr[x1][y1]=b.arr[x0][y0];
		b.arr[x0][y0]=Piece.none;
		return b;
	}
	
	public static final Point2D.Float[] knightOffsets=new Point2D.Float[] {
			new Point2D.Float(-2,-1),
			new Point2D.Float(-2,1),
			new Point2D.Float(2,-1),
			new Point2D.Float(2,1),
			new Point2D.Float(-1,-2),
			new Point2D.Float(-1,2),
			new Point2D.Float(1,-2),
			new Point2D.Float(1,2)
	};
	
	public ArrayList<Point2D.Float> movesForPawn(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		if(arr[x][y].color==PieceColor.White) {
			if(y==1) {
				if(arr[x][2].isNone()) {
					list.add(new Point2D.Float(x, 2));
					if(arr[x][3].isNone()) {
						list.add(new Point2D.Float(x, 3));
					}
				}
			}else if(y<7) {
				if(arr[x][y+1].isNone()) {
					list.add(new Point2D.Float(x, y+1));
				}
			}
			if(x>0&&y<7) {
				if(arr[x-1][y+1].color==arr[x][y].color.opp()) {
					list.add(new Point2D.Float(x-1, y+1));
				}
			}
			if(x<7&&y<7) {
				if(arr[x+1][y+1].color==arr[x][y].color.opp()) {
					list.add(new Point2D.Float(x+1, y+1));
				}
			}
		}
		if(arr[x][y].color==PieceColor.Black) {
			if(y==6) {
				if(arr[x][5].isNone()) {
					list.add(new Point2D.Float(x, 5));
					if(arr[x][4].isNone()) {
						list.add(new Point2D.Float(x, 4));
					}
				}
			}else if(y>0) {
				if(arr[x][y-1].isNone()) {
					list.add(new Point2D.Float(x, y-1));
				}
			}
			if(x>0&&y>0) {
				if(arr[x-1][y-1].color==arr[x][y].color.opp()) {
					list.add(new Point2D.Float(x-1, y-1));
				}
			}
			if(x<7&&y>0) {
				if(arr[x+1][y-1].color==arr[x][y].color.opp()) {
					list.add(new Point2D.Float(x+1, y-1));
				}
			}
		}
		return list;
	}
	
	public ArrayList<Point2D.Float> getMovesInDir(int x,int y,int dx,int dy){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		int i=x;
		int j=y;
		while(true) {
			i+=dx;
			j+=dy;
			if(i<0||j<0||i>7||j>7) {
				break;
			}
			if(arr[i][j].isNone()) {
				list.add(new Point2D.Float(i, j));
			}else if(arr[i][j].color!=arr[x][y].color) {
				list.add(new Point2D.Float(i, j));
				break;
			}else {
				break;
			}
		}
		return list;
	}
	
	public ArrayList<Point2D.Float>movesForRook(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		list.addAll(getMovesInDir(x,y,-1,0));
		list.addAll(getMovesInDir(x,y,1,0));
		list.addAll(getMovesInDir(x,y,0,-1));
		list.addAll(getMovesInDir(x,y,0,1));
		return list;
	}
	
	public ArrayList<Point2D.Float>movesForBishop(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		list.addAll(getMovesInDir(x,y,-1,-1));
		list.addAll(getMovesInDir(x,y,1,-1));
		list.addAll(getMovesInDir(x,y,-1,1));
		list.addAll(getMovesInDir(x,y,1,1));
		return list;
	}
	
	public ArrayList<Point2D.Float>movesForQueen(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		list.addAll(movesForRook(x,y));
		list.addAll(movesForBishop(x,y));
		return list;
	}
	
	public ArrayList<Point2D.Float>movesForKnight(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		for(Point2D.Float p:knightOffsets) {
			list.add(new Point2D.Float((float)p.getX()+x, (float)p.getY()+y));
		}
		return list;
	}
	
	public boolean wouldKingBeInCheck(int x0,int y0,int x1,int y1) {
		Board newBoard=makeMove(x0,y0,x1,y1);
		return newBoard.isKingInCheck(arr[x0][y0].color);
	}
	
	public ArrayList<Point2D.Float>movesForKing(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		for(int dx=-1;dx<=1;dx++) {
			for(int dy=-1;dy<=1;dy++) {
				if(dx==0&&dy==0)continue;
				list.add(new Point2D.Float(x+dx, y+dy));
			}
		}
//		
//		PieceColor c=arr[x][y].color;
//		
//		boolean[]castle=new boolean[] {false,false,false};
//		if(c==PieceColor.White)castle=castleWhite;
//		if(c==PieceColor.Black)castle=castleBlack;
//		
//		if(castle[0]&&castle[1]&&arr[1][y].isNone()&&arr[2][y].isNone()&&arr[3][y].isNone()) {
//			if(!wouldKingBeInCheck(4,y, 3,y)&&!wouldKingBeInCheck(4,y, 2,y)) {
//				list.add(new Point2D.Float(2	, y));
//			}
//		}
//		
//		if(castle[1]&&castle[2]&&arr[5][y].isNone()&&arr[6][y].isNone()) {
//			if(!wouldKingBeInCheck(4,y, 5,y)&&!wouldKingBeInCheck(4,y, 6,y)) {
//				list.add(new Point2D.Float(6, y));
//			}
//		}
		
		return list;
	}
	
	public ArrayList<Point2D.Float>getGoodMoves(ArrayList<Point2D.Float>list,int x,int y){
		ArrayList<Point2D.Float>moves=new ArrayList<Point2D.Float>();
		for(Point2D.Float p:list) {
			int tx=(int)p.getX();
			int ty=(int)p.getY();
			if(tx<0||ty<0||tx>7||ty>7) {
				continue;
			}
			if(arr[x][y].color==arr[tx][ty].color) {
				continue;
			}
			if(makeMove(x,y,tx,ty).isKingInCheck(arr[x][y].color)) {
				continue;
			}
			moves.add(p);
		}
		return moves;
	}
	
	public boolean checkForPieceInDir(PieceType checkFor,int x,int y,int dx,int dy) {
		PieceColor origColor=arr[x][y].color;
		x+=dx;
		y+=dy;
		while(x>=0&&y>=0&&x<=7&&y<=7) {
			if(arr[x][y].isNone()) {
				x+=dx;
				y+=dy;
				continue;
			}
			if(arr[x][y].color==origColor)return false;
			if(arr[x][y].color==origColor.opp())return arr[x][y].type==checkFor;
			x+=dx;
			y+=dy;
		}
		return false;
	}
	
	public boolean isKingInCheck(PieceColor c) {
		int kingX=0;
		int kingY=0;
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				if(arr[x][y].type==PieceType.King&&arr[x][y].color==c) {
					kingX=x;
					kingY=y;
				}
			}
		}

		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,-1,-1))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,-1,1))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,1,-1))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,1,1))return true;

		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,-1,0))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,1,0))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,0,-1))return true;
		if(checkForPieceInDir(PieceType.Queen,kingX,kingY,0,1))return true;
		
		if(checkForPieceInDir(PieceType.Rook,kingX,kingY,-1,0))return true;
		if(checkForPieceInDir(PieceType.Rook,kingX,kingY,1,0))return true;
		if(checkForPieceInDir(PieceType.Rook,kingX,kingY,0,-1))return true;
		if(checkForPieceInDir(PieceType.Rook,kingX,kingY,0,1))return true;

		if(checkForPieceInDir(PieceType.Bishop,kingX,kingY,-1,-1))return true;
		if(checkForPieceInDir(PieceType.Bishop,kingX,kingY,-1,1))return true;
		if(checkForPieceInDir(PieceType.Bishop,kingX,kingY,1,-1))return true;
		if(checkForPieceInDir(PieceType.Bishop,kingX,kingY,1,1))return true;
		
		for(Point2D.Float p:knightOffsets) {
			int x=(int)p.getX()+kingX;
			int y=(int)p.getY()+kingY;
			if(x>=0&&y>=0&&x<=7&&y<=7) {
				if(arr[x][y].type==PieceType.Knight&&arr[x][y].color==arr[kingX][kingY].color.opp()) {
					return true;
				}
			}
		}
		
		if(arr[kingX][kingY].color==PieceColor.White) {
			if(kingX>0&&kingY<7) if(arr[kingX-1][kingY+1].sameAs(Piece.blackPawn))return true;
			if(kingX<7&&kingY<7) if(arr[kingX+1][kingY+1].sameAs(Piece.blackPawn))return true;
		}else {
			if(kingX>0&&kingY>0) if(arr[kingX-1][kingY-1].sameAs(Piece.whitePawn))return true;
			if(kingX<7&&kingY>0) if(arr[kingX+1][kingY-1].sameAs(Piece.whitePawn))return true;
		}
		
		for(int x=-1;x<=1;x++) {
			for(int y=-1;y<=1;y++) {
				if(x+kingX>=0&&y+kingY>=0&&x+kingX<=7&&y+kingY<=7) {
					if(arr[x+kingX][y+kingY].color==arr[kingX][kingY].color.opp()&&arr[x+kingX][y+kingY].type==PieceType.King)return true;
				}
			}
		}
		
		return false;
	}
	
	public ArrayList<Point2D.Float> getMoves(int x,int y){
		ArrayList<Point2D.Float>list=new ArrayList<Point2D.Float>();
		if(x<0||y<0||x>7||y>7)return list;
		Piece p=arr[x][y];
		if(!p.isNone()) {
			if(p.type==PieceType.Pawn) {
				list.addAll(movesForPawn(x,y));
			}
			if(p.type==PieceType.Rook) {
				list.addAll(movesForRook(x,y));
			}
			if(p.type==PieceType.Bishop) {
				list.addAll(movesForBishop(x,y));
			}
			if(p.type==PieceType.Queen) {
				list.addAll(movesForQueen(x,y));
			}
			if(p.type==PieceType.Knight) {
				list.addAll(movesForKnight(x,y));
			}
			if(p.type==PieceType.King) {
				list.addAll(movesForKing(x,y));
			}
		}
		return getGoodMoves(list,x,y);
	}
	
	public boolean isCheckmate(PieceColor c) {
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				if(arr[x][y].color==c) {
					if(getMoves(x,y).size()>0)return false;
				}
			}
		}
		return true;
	}
	
}
