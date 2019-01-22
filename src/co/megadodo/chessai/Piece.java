package co.megadodo.chessai;

public class Piece {
	
	public PieceType type;
	public PieceColor color;
	
	public Piece(PieceType t,PieceColor c) {
		type=t;
		color=c;
	}

	public static final Piece whiteRook=new Piece(PieceType.Rook,PieceColor.White);
	public static final Piece whiteKnight=new Piece(PieceType.Knight,PieceColor.White);
	public static final Piece whiteBishop=new Piece(PieceType.Bishop,PieceColor.White);
	public static final Piece whiteQueen=new Piece(PieceType.Queen,PieceColor.White);
	public static final Piece whiteKing=new Piece(PieceType.King,PieceColor.White);
	public static final Piece whitePawn=new Piece(PieceType.Pawn,PieceColor.White);
	public static final Piece blackRook=new Piece(PieceType.Rook,PieceColor.Black);
	public static final Piece blackKnight=new Piece(PieceType.Knight,PieceColor.Black);
	public static final Piece blackBishop=new Piece(PieceType.Bishop,PieceColor.Black);
	public static final Piece blackQueen=new Piece(PieceType.Queen,PieceColor.Black);
	public static final Piece blackKing=new Piece(PieceType.King,PieceColor.Black);
	public static final Piece blackPawn=new Piece(PieceType.Pawn,PieceColor.Black);
	public static final Piece none=new Piece(PieceType.None,PieceColor.None);
	
	public boolean isNone() {
		return type==PieceType.None||color==PieceColor.None;
	}

	public boolean sameAs(Piece o) {
		return type==o.type&&color==o.color;
	}
	
}
