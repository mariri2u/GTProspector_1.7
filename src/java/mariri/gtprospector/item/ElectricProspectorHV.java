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
	    		new Object[] { "PGP", "MRd", "BCS",
	    			Character.valueOf('P'), OrePrefixes.plate.get(Materials.StainlessSteel),
	    			Character.valueOf('G'), OrePrefixes.gear.get(Materials.StainlessSteel),
	    			Character.valueOf('M'), ItemList.Electric_Piston_HV,
	    			Character.valueOf('R'), OrePrefixes.stick.get(Materials.TungstenSteel),
	    			Character.valueOf('B'), OrePrefixes.battery.get(Materials.Elite),
	    			Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Advanced),
	    			Character.valueOf('S'), OrePrefixes.plate.get(Materials.TungstenSteel)
	    		});
	}
}
