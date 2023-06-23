package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}
	public String AbilitiesDetails() {
		String r="Name: "+ this.getName() +'\n'+
				"Mana Cost: "+this.getManaCost() +'\n'+
				"Base Cooldown: "+this.getBaseCooldown()+'\n'+
				"Area of Effect: "+this.getCastArea()+'\n'+
				"Cast Range: "+this.getCastRange()+'\n'+
				"Cast Area: "+this.getCastRange()+'\n'+
				"Action Cost: "+this.getRequiredActionPoints()+'\n'+
				"Type:Crowd Control Ability "+'\n'+"Effect : "+ effect.getName() + " Effect duration: "+effect.getDuration();
		return r;
	}

}
