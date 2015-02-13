package mariri.gtprospector.item;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class ElectricProspectorLV extends ElectricProspector {

	public ElectricProspectorLV(){
		super("electricProspectorLV", 1, 10000, 10);
	}
	
	@Override
	public void addRecipe(){
	    GT_ModHandler.addCraftingRecipe(
	    		new ItemStack(this), GT_ModHandler.RecipeBits.NOT_REMOVABLE,
	    		new Object[] { "PES", "BGd", "CRW",
	    			Character.valueOf('P'), OrePrefixes.plate.get(Materials.Steel),
	    			Character.valueOf('E'), ItemList.Electric_Piston_LV,
	    			Character.valueOf('S'), OrePrefixes.screw.get(Materials.Steel),
	    			Character.valueOf('B'), OrePrefixes.battery.get(Materials.Basic),
	    			Character.valueOf('G'), OrePrefixes.gear.get(Materials.StainlessSteel),
	    			Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic),
	    			Character.valueOf('R'), OrePrefixes.stick.get(Materials.StainlessSteel),
	    			Character.valueOf('W'), OrePrefixes.cableGt01.get(Materials.Tin)
	    		});
	}
}
