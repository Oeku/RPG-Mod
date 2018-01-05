package net.zeldadungeons.init.cooking;

public class CookingEffectAmpl implements Comparable<CookingEffectAmpl>{
	private ECookingEffect effect;
	private int ampl;

	public CookingEffectAmpl(ECookingEffect effect, int ampl) {
	    this.effect = effect;
	    this.ampl = ampl;
	}

	public void setEffect(ECookingEffect effect) {
	    this.effect = effect;
	}

	public int getAmpl() {
	    return ampl;
	}

	public void setAmpl(int ampl) {
	    this.ampl = ampl;
	}
	public ECookingEffect getEffect() {
	    return effect;
	}

	@Override
	public int compareTo(CookingEffectAmpl o) {
	    return this.ampl-o.getAmpl();
	}	
}
