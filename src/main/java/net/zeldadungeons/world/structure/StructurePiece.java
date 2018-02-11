package net.zeldadungeons.world.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class StructurePiece {
    /** All the templates that this structure piece holds **/
    public ModTemplate[] templates;
    
    /** Higher weights will get generated more often **/
    private int pieceWeight;
    
    public StructurePiece(int weight, ModTemplate... templates){
	this.templates = templates;
	this.pieceWeight = weight;
    }
    
    public ModTemplate[] getTemplates() {
        return templates;
    }
    public void setTemplates(ModTemplate... templates) {
        this.templates = templates;
    }
    
    public int getPieceWeight() {
        return pieceWeight;
    }
    public void setPieceWeight(int pieceWeight) {
        this.pieceWeight = pieceWeight;
    }
    
}
