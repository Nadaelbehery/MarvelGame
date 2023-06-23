package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
	public String AbilitiesDetails() {
		String r="Name: "+ this.getName() +'\n'+
				"Mana Cost: "+this.getManaCost() +'\n'+
				"Base Cooldown: "+this.getBaseCooldown()+'\n'+
				"Area of Effect: "+this.getCastArea()+'\n'+
				"Cast Range: "+this.getCastRange()+'\n'+
				"Cast Area: "+this.getCastRange()+'\n'+
				"Action Cost: "+this.getRequiredActionPoints()+'\n'+
				"Type:Damaging Ability "+'\n'+"Dmaage Amount: "+ damageAmount;
		return r;
	}
}
