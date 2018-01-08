package net.zeldadungeons.capability.cookingdata;

public class CookingData implements ICookingData {
    private CookingDataContainer container;

    public CookingData(){
	this.container = new CookingDataContainer();
    }
    
    @Override
    public CookingDataContainer getContainer() {
	return this.container;
    }

    @Override
    public void setContainer(CookingDataContainer c) {
	this.container = c;
    }
}
