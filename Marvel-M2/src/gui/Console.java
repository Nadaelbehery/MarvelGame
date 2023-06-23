package gui;

import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import engine.Player;
import model.world.Champion;
//import views.Main;

public class Console {
	Game game;
	Player player1;
	Player player2;

	public static void main(String[] args) {
		try {
			Game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Abilities csv file not available");
		}

		try {
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Champions csv file not available");
		}

	}

	public static ArrayList<Champion> getChamp() {
		ArrayList<Champion> c = new ArrayList<Champion>();
		for (int i = 0; i < Game.getAvailableChampions().size(); i++) {
			c.add(Game.getAvailableChampions().get(i));
		}
		return c;

	}

}
