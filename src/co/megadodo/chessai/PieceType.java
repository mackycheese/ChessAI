package co.megadodo.chessai;

public enum PieceType {
	
	Rook(5),
	Knight(3),
	Bishop(3),
	Queen(9),
	King(1000000000),
	Pawn(1),
	None(0);
	
	private PieceType(float v) {
		value=v;
	}

	private float value;
	
	public float getValue() {
		return value;
	}
	
}