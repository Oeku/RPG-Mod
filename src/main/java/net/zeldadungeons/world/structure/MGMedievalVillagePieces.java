package net.zeldadungeons.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class MGMedievalVillagePieces {

    public static final MGMedievalVillagePieces.PieceWeight[] pieceList = {
	    new PieceWeight(new MGMedievalVillagePieces.TemplateVillage(), 0, 5),
	    new PieceWeight(new MGMedievalVillagePieces.TemplateVillage(), 0, 5),
	    new PieceWeight(new MGMedievalVillagePieces.TemplateVillage(), 0, 5),

	    }; 
    
    interface IGenerator
    {
        void init();

        boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_);
    }
    
    public static TemplateVillage getStartPiece(StructureStart start){
	return null;
    }
    
    private static class TemplateVillage extends TemplateBase{
	
    }
    
    static class PieceWeight
    {
        public TemplateVillage pieceClass;
        /**
         * This basically keeps track of the 'epicness' of a structure. Epic structure components have a higher
         * 'weight', and Structures may only grow up to a certain 'weight' before generation is stopped
         */
        public final int pieceWeight;
        public int instancesSpawned;
        /** How many Structure Pieces of this type may spawn in a structure */
        public int instancesLimit;

        /**
         * Creates a piece weight, holding a template and its individual spawning configurations.
         * @param template The Template
         * @param sizeNeeded How big the structure needs to be before this template can be spawned.
         * @param maxSpawns How many of this components on structure can contain.
         */
        public PieceWeight(TemplateVillage template, int sizeNeeded, int maxSpawns)
        {
            this.pieceClass = template;
            this.pieceWeight = sizeNeeded;
            this.instancesLimit = maxSpawns;
        }

        /**
         * Checks if more components of this type can be spawned.
         * @param Who cares really(I think this was supposed to be a special method for different type components but it is the same as for other components)
         * @return True or False
         */
        public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }

        public boolean canSpawnMoreStructures()
        {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }
}
