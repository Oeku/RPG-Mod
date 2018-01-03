package net.zeldadungeons.init.crafting;

public enum ECookingEffect {

    HEART("Hearty "), STAMINA("Energizing "), FIRE("Fireproof "), CHILLY("Chilly "), SPICY("Spicy "), NEUTRAL(""), Saturating("");

    private final String displayName;

    private ECookingEffect(String display) {
	this.displayName = display;
    }

    public String getDisplay() {
	return this.displayName;
    }
}
