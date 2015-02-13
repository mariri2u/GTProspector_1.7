package mariri.gtprospector.item;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class ElectricProspectorMV extends ElectricProspector{

	public ElectricProspectorMV(){
		super("electricProspectorMV", 2, 40000, 40);
	}
	
	@Override
	public void addRecipe(){
	    GT_ModHandler.addCraftingRecipe(
	    		new ItemStack(this), GT_ModHandler.RecipeBits.NOT_REMOVABLE,
	    		new Object[] { "PES", "BGd", "CRW",
	    			Character.valueOf('P'), OrePrefixes.plate.get(Materials.Aluminium),
	    			Character.valueOf('E'), ItemList.Electric_Piston_MV,
	    			Character.valueOf('S'), OrePrefixes.screw.get(Materials.Aluminium),
	    			Character.valueOf('B'), OrePrefixes.battery.get(Materials.Advanced),
	    			Character.valueOf('G'), OrePrefixes.gear.get(Materials.Titanium),
	    			Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Good),
	    			Character.valueOf('R'), OrePrefixes.stick.get(Materials.Titanium),
	    			Character.valueOf('W'), OrePrefixes.cableGt01.get(Materials.Copper)
	    		});
	}
}
