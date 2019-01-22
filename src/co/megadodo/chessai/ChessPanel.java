package co.megadodo.chessai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessPanel extends JPanel implements MouseListener{
	
	public JFrame frame;
	public Board board;
	
	public ChessAI ai;
	
	public Image whiteRook,whiteKnight,whiteBishop,whiteQueen,whiteKing,whitePawn,blackRook,blackKnight,blackBishop,blackQueen,blackKing,blackPawn;
	
	public PieceColor humanColor;
	
	public ChessPanel(JFrame f) {
		frame=f;
		try {
			whiteRook=ImageIO.read(new File("Sprites/whiteRook.png"));
			whiteKnight=ImageIO.read(new File("Sprites/whiteKnight.png"));
			whiteBishop=ImageIO.read(new File("Sprites/whiteBishop.png"));
			whiteQueen=ImageIO.read(new File("Sprites/whiteQueen.png"));
			whiteKing=ImageIO.read(new File("Sprites/whiteKing.png"));
			whitePawn=ImageIO.read(new File("Sprites/whitePawn.png"));
			blackRook=ImageIO.read(new File("Sprites/blackRook.png"));
			blackKnight=ImageIO.read(new File("Sprites/blackKnight.png"));
			blackBishop=ImageIO.read(new File("Sprites/blackBishop.png"));
			blackQueen=ImageIO.read(new File("Sprites/blackQueen.png"));
			blackKing=ImageIO.read(new File("Sprites/blackKing.png"));
			blackPawn=ImageIO.read(new File("Sprites/blackPawn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		board=new Board();
		board.setup();
//		board.arr[0][0]=Piece.whiteKing;
//		board.arr[7][1]=Piece.blackQueen;
//		board.arr[4][3]=Piece.blackQueen;
//		board.arr[7][7]=Piece.blackKing;

		tw=frame.getWidth()/8;
		th=(frame.getHeight()-15)/8;
		
		this.addMouseListener(this);
		repaint();
		ai=new AI2();
		humanColor=PieceColor.White;
		
		if(humanColor==PieceColor.Black) {
			makeAIMove();
		}
	}
	
	Point2D.Float selection=new Point2D.Float(-1, -1);
	
	int tw;
	int th;
	
	public void makeAIMove() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Point2D.Float[] moves = ai.getMove(board, humanColor.opp());
				if (moves == null)
					return;
				System.out.println("AI Move " + moves[0].getX() + " " + moves[0].getY() + " to " + moves[1].getX() + " "
						+ moves[1].getY());
				board = board.makeMove(moves);
				repaint();
			}
		}, 10);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		ArrayList<Point2D.Float>moves=board.getMoves((int)selection.x, (int)selection.y);
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				
				float gray=0.3f;
				if((x+y)%2==0) {
					g2d.setColor(new Color(1-gray,1-gray,1-gray));
				}else{
					g2d.setColor(new Color(gray,gray,gray));
				}
				if(selection!=null) {
					if(selection.getX()==x&&selection.getY()==y)g2d.setColor(Color.yellow);
				}
				
				g2d.fillRect(x*tw, (7-y)*th, tw, th);
				
				Image img=null;
				Piece p=board.arr[x][y];

				if(p.sameAs(Piece.whiteRook))img=whiteRook;
				if(p.sameAs(Piece.whiteKnight))img=whiteKnight;
				if(p.sameAs(Piece.whiteBishop))img=whiteBishop;
				if(p.sameAs(Piece.whiteQueen))img=whiteQueen;
				if(p.sameAs(Piece.whiteKing))img=whiteKing;
				if(p.sameAs(Piece.whitePawn))img=whitePawn;
				if(p.sameAs(Piece.blackRook))img=blackRook;
				if(p.sameAs(Piece.blackKnight))img=blackKnight;
				if(p.sameAs(Piece.blackBishop))img=blackBishop;
				if(p.sameAs(Piece.blackQueen))img=blackQueen;
				if(p.sameAs(Piece.blackKing))img=blackKing;
				if(p.sameAs(Piece.blackPawn))img=blackPawn;
				
				if(img!=null) {
					g2d.drawImage(img,x*tw,(7-y)*th,tw,th,this);
				}
				
				if(moves.contains(new Point2D.Float(x,y))) {
					g2d.setColor(new Color(0.5f,0.5f,0.5f));
					int r=5;
					g2d.fillOval(x*tw+tw/2-r, (7-y)*th+th/2-r, r*2, r*2);
				}
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
	}

	@Override
	public void mouseExited(MouseEvent evt) {
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int mx=evt.getX();
		int my=evt.getY();
		int tx=mx/tw;
		int ty=7-my/th;
		ArrayList<Point2D.Float>moves=board.getMoves((int)selection.x, (int)selection.y);
		if(selection.getX()!=-1&&selection.getY()!=-1&&moves.contains(new Point2D.Float(tx,ty))) {
			if(board.arr[(int)selection.getX()][(int)selection.getY()].color==humanColor.opp())return;
			board=board.makeMove((int)selection.getX(),(int)selection.getY(),tx,ty);
			if(board.isCheckmate(PieceColor.White)) {
				JOptionPane.showMessageDialog(this, "Black has won", "Game over", JOptionPane.INFORMATION_MESSAGE);
			}
			if(board.isCheckmate(PieceColor.Black)) {
				JOptionPane.showMessageDialog(this, "White has won", "Game over", JOptionPane.INFORMATION_MESSAGE);
			}
			makeAIMove();
			selection.setLocation(-1, -1);
		}
		selection.setLocation(tx, ty);
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
	}

}
