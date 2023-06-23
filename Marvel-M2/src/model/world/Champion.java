package model.world;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import model.abilities.Ability;
import model.effects.Effect;

@SuppressWarnings("rawtypes")
public abstract class Champion implements Damageable, Comparable {
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	private String URL;

	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints = maxActionPointsPerTurn;
		setURL(name);
	}

	private void setURL(String name) {
		if (name.equals("Captain America")) {
			URL="src/resources/captainamerica.png";

		}
		if (name.equals("Deadpool")) {
			URL="src/resources/Deadpool.png";

		}
		if (name.equals("Dr Strange")) {
			URL="src/resources/DrStrange.png";

		}
		if (name.equals("Electro")) {
			URL="src/resources/Electro.png";

		}
		if (name.equals("Ghost Rider")) {
			URL="src/resources/GhostRider.png";

		}
		if (name.equals("Hela")) {
			URL="src/resources/hela.png";

		}
		if (name.equals("Hulk")) {
			URL="src/resources/Hulk.png";

		}
		if (name.equals("Iceman")) {
			URL="src/resources/IceMan.png";

		}
		if (name.equals("Ironman")) {
			URL="src/resources/IronMan.png";

		}
		if (name.equals("Loki")) {
			URL="src/resources/Loki.png";

		}
		if (name.equals("Quicksilver")) {
			URL="src/resources/QuickSilver.png";

		}
		if (name.equals("Spiderman")) {
			URL="src/resources/Spiderman.png";

		}
		if (name.equals("Thor")) {
			URL="src/resources/Thor.png";

		}
		if (name.equals("Venom")) {
			URL="src/resources/Venom.png";

		}
		if (name.equals("Yellow Jacket")) {
			URL="src/resources/YellowJack.png";

		}
		

	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp <= 0) {
			currentHP = 0;
			condition = Condition.KNOCKEDOUT;

		} else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}

	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public String getURL() {
		return this.URL;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if (currentActionPoints > maxActionPointsPerTurn)
			currentActionPoints = maxActionPointsPerTurn;
		else if (currentActionPoints < 0)
			currentActionPoints = 0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int compareTo(Object o) {
		Champion c = (Champion) o;
		if (speed == c.speed)
			return name.compareTo(c.name);
		return -1 * (speed - c.speed);
	}

	public abstract void useLeaderAbility(ArrayList<Champion> targets);
	
	public ImageView DisplayChamp(Champion champ) {
		ImageView ChampionImage = new ImageView(champ.getURL());
		ChampionImage.setFitHeight(100);
		ChampionImage.setFitWidth(100);
		Tooltip tip = new Tooltip(ChampDetails(champ));

		Tooltip.install(ChampionImage, tip);
		return ChampionImage;

	}
	public String ChampDetails(Champion champ) {
		String r = "Name:" + champ.getName() + '\n' + "Current Health Points: " + champ.getCurrentHP() + '\n'
				+ "Actions Points Per Turn: " + champ.getMaxActionPointsPerTurn() + '\n' + "Mana: " + champ.getMana()
				+ '\n' + "Attack Range: " + champ.getAttackRange() + '\n' + "Attack Damage: " + champ.getAttackDamage()
				+ '\n' + "Champions's type ";
		if(champ instanceof Hero)
			r=r+"Hero"+'\n';
		if(champ instanceof Villain)
			r=r+"Villian"+'\n';
		if(champ instanceof AntiHero)
			r=r+"Antihero"+'\n';
		r=r+"Applied Effects: ";
		
		for (int i = 0; i < champ.getAppliedEffects().size(); i++) {
			r = r + champ.getAppliedEffects().get(i).getName() + " Duration:"+champ.getAppliedEffects().get(i).getDuration() ;
			if(i!=champ.getAppliedEffects().size()-1) {
				r=r+",";
			}

		}
		r = r + '\n';

		return r;

	}
}
