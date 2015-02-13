package mariri.gtprospector.item;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class ElectricProspectorHV extends ElectricProspector{

	public ElectricProspectorHV(){
		super("electricProspectorHV", 3, 160000, 160);
	}
	
	@Override
	public void addRecipe(){
	    GT_ModHandler.addCraftingRecipe(
	    		new ItemStack(this), GT_ModHandler.RecipeBits.NOT_REMOVABLE,
	    		new Object[] { "PES", "BGd", "CRW",
	    			Character.valueOf('P'), OrePrefixes.plate.get(Materials.StainlessSteel),
	    			Character.valueOf('E'), ItemList.Electric_Piston_HV,
	    			Character.valueOf('S'), OrePrefixes.screw.get(Materials.StainlessSteel),
	    			Character.valueOf('B'), OrePrefixes.battery.get(Materials.Elite),
	    			Character.valueOf('G'), OrePrefixes.gear.get(Materials.TungstenSteel),
	    			Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Advanced),
	    			Character.valueOf('R'), OrePrefixes.stick.get(Materials.TungstenSteel),
	    			Character.valueOf('W'), OrePrefixes.cableGt01.get(Materials.Gold)
	    		});
	}
}
