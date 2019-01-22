package co.megadodo.chessai;

public enum PieceColor {

	White,
	Black,
	None;
	
	public PieceColor opp() {
		switch(this) {
		case White:
			return Black;
		case Black:
			return White;
		}
		return None;
	}
	
}
