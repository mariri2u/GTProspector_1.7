package mariri.gtprospector.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.prefixblock.PrefixBlock;
import gregapi.block.prefixblock.PrefixBlockItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemProspector extends Item{

	private int tier;
	private int maxCharge;
	private int transferLimit;

	private String name;

	public static int USE_ENERGY_BASE = 640;
	public static int FIND_RANGE_BASE = 32;
	public static int FIND_RADIUS = 2;
	public static int MANY_THREASHOLD = 80;

	public ItemProspector(String name){
    	this.setCreativeTab(CreativeTabs.tabTools);
    	this.setUnlocalizedName(name);
    	GameRegistry.registerItem(this, name);

    	this.name = name;
    	this.tier = 2;

    	this.setMaxDamage(511);
    	this.setMaxStackSize(1);

    	this.addRecipe();
	}

	public void addRecipe(){
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 0),
        		"DSD", " B ", " B ",
        		'D', "plateDoubleAnyIron",
        		'S', "springAnyBronze",
        		'B', "stickAnyBronze"
        	));
	}

	@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int face, float offsetX, float offsetY, float offsetZ){
		if (!world.isRemote && player != null){
			ForgeDirection dir = ForgeDirection.getOrientation(face);

			int minX = (dir.offsetX == 0) ? -1 * FIND_RADIUS : (dir.offsetX ==  1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
			int maxX = (dir.offsetX == 0) ?      FIND_RADIUS : (dir.offsetX == -1) ? (int)(     FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
			int minY = (dir.offsetY == 0) ? -1 * FIND_RADIUS : (dir.offsetY ==  1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
			int maxY = (dir.offsetY == 0) ?      FIND_RADIUS : (dir.offsetY == -1) ? (int)(     FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
			int minZ = (dir.offsetZ == 0) ? -1 * FIND_RADIUS : (dir.offsetZ ==  1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
			int maxZ = (dir.offsetZ == 0) ?      FIND_RADIUS : (dir.offsetZ == -1) ? (int)(     FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;

			int count = 0;

			for(int x = posX + minX; x <= posX + maxX; x++){
				for(int y = posY + minY; y <= posY + maxY; y++){
					for(int z = posZ + minZ; z <= posZ + maxZ; z++){
						Block b = world.getBlock(x, y, z);
						int m = world.getBlockMetadata(x, y, z);
						if(b instanceof PrefixBlock){
							PrefixBlock pfb =(PrefixBlock)b;
							ArrayList<ItemStack> drops = b.getDrops(world, posX, posY, posZ, m, 0);
							if(drops.size() > 0 && drops .get(0).getItem() instanceof PrefixBlockItem){
								count++;
							}
						}
					}
				}
			}

			if(count >= MANY_THREASHOLD){
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.many")));
			}else if(count > 0){
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.few")));
			}else{
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.none")));
			}

			world.playSoundAtEntity(player, "ic2:tools.Dynamiteomote", 1.0F, 1.0F);

//				player.addChatMessage(new ChatComponentText("Found :" + count + " : " + dir.offsetX + ", " + dir.offsetY + ", " + dir.offsetZ));
		}
		itemstack.attemptDamageItem(1, player.getRNG());

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister){
		this.itemIcon = iconregister.registerIcon("gtprospector:" + name);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List tooltip, boolean par4) {
		tooltip.add(String.format(StatCollector.translateToLocal("tooltip." + name + ".0"), FIND_RANGE_BASE * (int)Math.pow(2, tier - 1)));
	}

}
