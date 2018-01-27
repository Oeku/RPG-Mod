package net.zeldadungeons.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class MGMedievalVillagePieces {

    
    interface IGenerator
    {
        void init();

        boolean generate(TemplateManager p_191086_1_, int p_191086_2_, StructureEndCityPieces.CityTemplate p_191086_3_, BlockPos p_191086_4_, List<StructureComponent> p_191086_5_, Random p_191086_6_);
    }
}
