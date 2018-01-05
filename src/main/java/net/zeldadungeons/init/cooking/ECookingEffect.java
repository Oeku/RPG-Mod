package net.zeldadungeons.init.cooking;

public enum ECookingEffect {

    HEART("Hearty "), STAMINA("Energizing "), FIRE("Fireproof "), CHILLY("Chilly "), SPICY("Spicy "), NEUTRAL(""), SATURATING("Saturating"), DISGUSTING("Disgusting");

    private final String displayName;

    private ECookingEffect(String display) {
	this.displayName = display;
    }

    public String getDisplay() {
	return this.displayName;
    }
}
