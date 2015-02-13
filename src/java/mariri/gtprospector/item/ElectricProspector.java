package mariri.gtprospector.item;

import gregtech.common.blocks.GT_Block_Ores;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ElectricProspector extends Item implements IElectricItem{
	
	private int tier;
	private int maxCharge;
	private int transferLimit;
	
	private String name;
	
	public static int USE_ENERGY_BASE = 640;
	public static int FIND_RANGE_BASE = 32;
	public static int FIND_RADIUS = 2;
	public static int FEW_THREASHOLD_BASE = 8;
	public static int MANY_THREASHOLD = 120;
	
	public ElectricProspector(String name, int tier, int maxCharge, int transferLimit){
    	this.setCreativeTab(CreativeTabs.tabTools);
    	this.setUnlocalizedName(name);
    	GameRegistry.registerItem(this, name);
    	
    	this.tier = tier;
    	this.maxCharge = maxCharge;
    	this.transferLimit = transferLimit;
    	this.name = name;
    	
    	this.setMaxDamage(101);
    	this.setMaxStackSize(1);
    	
    	this.addRecipe();
	}
	
	public abstract void addRecipe();
	
	@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int face, float offsetX, float offsetY, float offsetZ){
		if (!world.isRemote && player != null){
			if(ElectricItem.manager.use(itemstack, USE_ENERGY_BASE * tier, player)){
				ForgeDirection dir = ForgeDirection.getOrientation(face);
				
				int minX = (dir.offsetX == 0) ? -1 * FIND_RADIUS : (dir.offsetX == 1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
				int maxX = (dir.offsetX == 0) ? FIND_RADIUS : (dir.offsetX == -1) ? (int)(FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0; 
				int minY = (dir.offsetY == 0) ? -1 * FIND_RADIUS : (dir.offsetY == 1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
				int maxY = (dir.offsetY == 0) ? FIND_RADIUS : (dir.offsetY == -1) ? (int)(FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0; 
				int minZ = (dir.offsetZ == 0) ? -1 * FIND_RADIUS : (dir.offsetZ == 1) ? (int)(-1 * FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0;
				int maxZ = (dir.offsetZ == 0) ? FIND_RADIUS : (dir.offsetZ == -1) ? (int)(FIND_RANGE_BASE * Math.pow(2, tier - 1)) : 0; 
				
				int count_all = 0;
				int count_ore = 0;
				
				for(int x = posX + minX; x <= posX + maxX; x++){
					for(int y = posY + minY; y <= posY + maxY; y++){
						for(int z = posZ + minZ; z <= posZ + maxZ; z++){
							if(world.getBlock(x, y, z) instanceof GT_Block_Ores){
								TileEntity tileentity = world.getTileEntity(x, y, z);
								NBTTagCompound tag = new NBTTagCompound();
								tileentity.writeToNBT(tag);
								Short meta =  tag.getShort("m");
//								player.addChatMessage(new ChatComponentText("meta" + meta));
								if(meta < 16000){
									count_all++;
									if(meta != 0){
										count_ore++;
									}
								}
							}
						}
					}
				}
				
				
				if(count_ore > 0 || count_all > FEW_THREASHOLD_BASE * Math.pow(2, tier - 1)){
					if(count_all >= MANY_THREASHOLD){
						player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.many")));
					}else{
						player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.few")));
					}
				}else{
					player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.prospector.none")));
				}
				
				world.playSoundAtEntity(player, "ic2:tools.Dynamiteomote", 1.0F, 1.0F);
//	            player.playSound("ic2:tools.Dynamiteomote", 1.0F, 1.0F);
				
				player.addChatMessage(new ChatComponentText("Found :" + count_all + ", " + count_ore + " : " + dir.offsetX + ", " + dir.offsetY + ", " + dir.offsetZ));
//				player.addChatMessage(new ChatComponentText("Tier: " + tier));
			}
		}
		return true;
	}
	
    @Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return false;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return this;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		return maxCharge;
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return tier;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		return transferLimit;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister){
		this.itemIcon = iconregister.registerIcon("gtprospector:" + name);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List tooltip, boolean par4) {
		tooltip.add(StatCollector.translateToLocal("tooltip." + name + ".0"));
	}

}
