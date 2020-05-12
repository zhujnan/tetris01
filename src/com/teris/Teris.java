package com.teris;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JPanel一个面板类
 * @author zhujn
 *
 */
public class Teris extends JPanel{
	
	//声明一个成员变量
	private Tetromino tetromino;//正在下落的方块
	
	public static BufferedImage backgroud;
	public static BufferedImage T;
	public static BufferedImage I;
	public static BufferedImage S;
	public static BufferedImage Z;
	public static BufferedImage J;
	public static BufferedImage L;
	public static BufferedImage O;
	
	public static final int ROWS = 20;
	public static final int COLS = 10;
	
	private Cell[][] wall = new Cell[ROWS][COLS];
	
	static {
		try {
			backgroud = ImageIO
					.read(Teris.class//获取Class对象
							.getResource("TETRIS.png"));
			
			T = ImageIO.read(Teris.class.getResource("T.png"));
			S = ImageIO.read(Teris.class.getResource("S.png"));
			Z = ImageIO.read(Teris.class.getResource("Z.png"));
			J = ImageIO.read(Teris.class.getResource("J.png"));
			L = ImageIO.read(Teris.class.getResource("L.png"));
			I = ImageIO.read(Teris.class.getResource("I.png"));
			O = ImageIO.read(Teris.class.getResource("O.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {//绘制容器方法
		g.drawImage(backgroud, 0, 0, null);
		//用画笔对象绘制背景图片
		g.translate(15, 15);
		//将原点平移到当前坐标系中的位置
		paintWall(g);//画网格墙
		paintTetromino(g);//画出场的俄罗斯方块
	}
	private void paintTetromino(Graphics g) {
		// TODO Auto-generated method stub
		if(tetromino ==null) {
			return;
		}
		Cell[] cells = tetromino.cells;
		for(int i=0;i<cells.length;i++) {
			Cell cell = cells[i];
			int x = cell.getCol()*CELL_SIZE;
			int y = cell.getRow()*CELL_SIZE;
			g.drawImage(cell.getImage(), x, y, null);
		}
	}
	/**
	 * 绘制游戏面板中的网格强
	 */
	public static final int CELL_SIZE=26;//指定方格的宽度
	private void paintWall(Graphics g) {
		for(int row = 0;row<wall.length;row++) {
			for(int col = 0;col<wall[row].length;col++) {
				int x = col * CELL_SIZE;
				int y = row * CELL_SIZE;
				Cell cell = wall[row][col];
				if(cell == null) {
					g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
					//绘制指定 矩形边框
				}else {
					g.drawImage(cell.getImage(), x, y, null);
					//绘制指定图像中当前可用的图像
				}
			}
		}
	}

	public static void main(String[] args) {
		JFrame fram = new JFrame("俄罗斯方块");//窗格
		Teris teris = new Teris();//游戏面板
		fram.add(teris);//将面板添加到窗格中
		fram.setSize(525,550);//设置窗格的大小
		fram.setAlwaysOnTop(true);//总在最上
		fram.setUndecorated(true);//去掉边框
		fram.setLocationRelativeTo(null);
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fram.setVisible(true);//调用paint()方法
		teris.action();
	}

	private void action() {
		tetromino = Tetromino.randomOne();
	}
}
