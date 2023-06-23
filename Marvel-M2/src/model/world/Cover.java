package model.world;

import java.awt.Point;

public class Cover implements Damageable {
	private int currentHP;

	private Point location;
	private String URL="src/resources/Cover.png";

	public Cover(int x, int y) {
		this.currentHP = (int)(( Math.random() * 900) + 100);
		location = new Point(x, y);
	}

	public int getCurrentHP() {
		return this.currentHP;
	}

	public void setCurrentHP(int newHp) {
		if (newHp < 0) {
			currentHP = 0;
		
		} else
			currentHP = newHp;
	}

	public Point getLocation() {
		return location;
	}
	public String getURL() {
		return this.URL;
	}

	

	

}
