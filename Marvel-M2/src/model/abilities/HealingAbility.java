package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() + healAmount);

	}
	public String AbilitiesDetails() {
	String r="Name: "+ this.getName() +'\n'+
			"Mana Cost: "+this.getManaCost() +'\n'+
			"Base Cooldown: "+this.getBaseCooldown()+'\n'+
			"Area of Effect: "+this.getCastArea()+'\n'+
			"Cast Range: "+this.getCastRange()+'\n'+
			"Cast Area: "+this.getCastRange()+'\n'+
			"Action Cost: "+this.getRequiredActionPoints()+'\n'+
			"Type:Healing Ability "+'\n'+"Heal Amount: "+ healAmount;
	return r;
}
	

}
