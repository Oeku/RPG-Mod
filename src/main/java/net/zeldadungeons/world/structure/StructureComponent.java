package net.zeldadungeons.world.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class StructureComponent {
    private BlockPos position;
    private Rotation rotation;
    public StructurePiece piece;
    
    public StructureComponent(StructurePiece piece){
	this.piece = piece;
    }
    
    public BlockPos getPosition() {
        return position;
    }
    public void setPosition(BlockPos position) {
        this.position = position;
    }
    public Rotation getRotation() {
        return rotation;
    }
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
