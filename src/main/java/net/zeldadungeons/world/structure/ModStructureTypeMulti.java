package net.zeldadungeons.world.structure;

import java.util.List;

public class ModStructureTypeMulti extends ModStructureType{
    protected int maxPieces;
    protected int minPieces;
    protected List<StructurePiece> validPieces;
    
    public ModStructureTypeMulti(){
	super();
    }

    public void generate(ModStructure structure) {
	
    }
}
