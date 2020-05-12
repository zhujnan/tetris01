package com.teris;

import java.util.Random;

/**
 * 俄罗斯方块类，
 * 			L型，S型，O型，等。。。方块的父类
 * @author zhujn
 *
 */
public class Tetromino {
	protected Cell[] cells = new Cell[4];
	//protected State[] states;
	public static Tetromino randomOne() {
		/**
		 * 工厂方法：用于生成一个对象的方法，封装了
		 * 复杂的创建过程使用方便。
		 * 随机生成一个下落的俄罗斯方块
		 */
		Random random = new Random();
		int type = random.nextInt(7);
		switch (type) {
			case 0:return new T();
			case 1:return new I();
			case 2:return new S();
			case 3:return new Z();
			case 4:return new J();
			case 5:return new L();
			case 6:return new O();
		}
		return null;
	}
}
