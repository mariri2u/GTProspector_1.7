package mariri.gtprospector;

import mariri.gtprospector.item.ElectricProspector;
import mariri.gtprospector.item.ElectricProspectorHV;
import mariri.gtprospector.item.ElectricProspectorLV;
import mariri.gtprospector.item.ElectricProspectorMV;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GTProspector.MODID, version = GTProspector.VERSION, dependencies = GTProspector.DEPENDENCIES )
public class GTProspector {
    public static final String MODID = "GTProspector";
    public static final String VERSION = "1.7.10-0.1";
    public static final String DEPENDENCIES = "required-after:IC2; required-after:gregtech";

	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        Property prop;
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "useEnergyBase", 640);
        ElectricProspector.USE_ENERGY_BASE = prop.getInt();
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "findRangeBase", 32);
        ElectricProspector.FIND_RANGE_BASE = prop.getInt();
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "findRadius", 2);
        ElectricProspector.FIND_RADIUS = prop.getInt();
        
        prop = config.get(Configuration.CATEGORY_GENERAL, "manyThreashold", 80);
        ElectricProspector.MANY_THREASHOLD = prop.getInt();
        
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
    	ElectricProspector prospectorLV = new ElectricProspectorLV();
    	ElectricProspector prospectorMV = new ElectricProspectorMV();
    	ElectricProspector prospectorHV = new ElectricProspectorHV();
    }

}
