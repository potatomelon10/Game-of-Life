import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class Window extends JFrame {

    public boolean runstart = false;

    int spacing = 1;
    public Grid grid = new Grid(75,128);

    public int mx = -100;
    public int my = -100;

    public Window() {
        this.setTitle("Conway's Game of Life");
        this.setSize(1294, 837);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board grid = new Board();
        this.setContentPane(grid);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        JTextField text = new JTextField(30);
    }

    public class Board extends JPanel {
        public void paintComponent(Graphics g){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,1280,800);
            for(int i = 0; i < 128; i++ ){
                for(int j = 0; j < 75; j++){
                    g.setColor(Color.gray);
                    if(mx >= spacing+i*10+6 && mx < spacing+i*10+10-spacing+6 && my >= spacing+j*10+50+29 && my < spacing+j*10+50+10-spacing+29){
                        g.setColor(Color.yellow);
                    }
                    if(grid.board[j][i] == 1){
                        g.setColor(Color.yellow);
                    }
                    g.fillRect(i*10,j*10+50, 10-spacing,10-spacing);
                }
            }
            if(runstart) {
                grid.scan_neighbors();
                grid.appraise_board();
            }
        }
    }

    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }
    }

    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(inBoxX() != -1 && inBoxY() != -1){
                if(grid.board[inBoxY()][inBoxX()] == 1){
                    grid.board[inBoxY()][inBoxX()] = 0;
                }
                else{
                    grid.board[inBoxY()][inBoxX()] = 1;
                }
            }
            if(my <= 50){
                runstart = !runstart;
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public int inBoxX() {
        for (int i = 0; i < 128; i++) {
            if (mx >= spacing + i * 10 + 6 && mx < spacing + i * 10 + 10 - spacing + 6) {
                return i;
            }
        }
        return -1;
    }
    public int inBoxY() {
        for (int j = 0; j < 75; j++) {
            if (my >= spacing + j * 10 + 50 + 29 && my < spacing + j * 10 + 50 + 10 - spacing + 29) {
                return j;
            }
        }
        return -1;
    }
}

