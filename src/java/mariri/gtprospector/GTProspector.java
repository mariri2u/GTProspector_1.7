package mariri.gtprospector;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mariri.gtprospector.item.ItemProspector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = GTProspector.MODID, version = GTProspector.VERSION, dependencies = GTProspector.DEPENDENCIES )
public class GTProspector {
    public static final String MODID = "GTProspector";
    public static final String VERSION = "1.7.10-1.1-alpha1";
    public static final String DEPENDENCIES = "required-after:gregtech;";

    public static boolean MINI_FIX;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        Property prop;

        prop = config.get(Configuration.CATEGORY_GENERAL, "useEnergyBase", 640);
        ItemProspector.USE_ENERGY_BASE = prop.getInt();

        prop = config.get(Configuration.CATEGORY_GENERAL, "findRangeBase", 32);
        ItemProspector.FIND_RANGE_BASE = prop.getInt();

        prop = config.get(Configuration.CATEGORY_GENERAL, "findRadius", 2);
        ItemProspector.FIND_RADIUS = prop.getInt();

        prop = config.get(Configuration.CATEGORY_GENERAL, "manyThreashold", 80);
        ItemProspector.MANY_THREASHOLD = prop.getInt();

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	ItemProspector prospectorLV = new ItemProspector("itemProspector");
    }

}
