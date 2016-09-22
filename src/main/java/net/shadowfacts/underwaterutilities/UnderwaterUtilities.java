package net.shadowfacts.underwaterutilities;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.shadowfacts.underwaterutilities.api.item.Goggles;
import net.shadowfacts.underwaterutilities.block.ModBlocks;
import net.shadowfacts.underwaterutilities.event.ClientEventHandler;
import net.shadowfacts.underwaterutilities.gui.UUGUIHandler;
import net.shadowfacts.underwaterutilities.item.ModItems;
import net.shadowfacts.underwaterutilities.recipe.ModRecipes;
import net.shadowfacts.shadowmc.capability.Storage;

/**
 * @author shadowfacts
 */
@Mod(modid = UnderwaterUtilities.modId, name = UnderwaterUtilities.name, version = UnderwaterUtilities.version, dependencies = "required-after:shadowmc;", acceptedMinecraftVersions = "[1.10.2]", guiFactory = "net.shadowfacts.underwaterutilities.gui.UUGUIFactory")
public class UnderwaterUtilities {

//	TODO: set min shadowmc version

	public static final String modId = "underwaterutilities";
	public static final String name = "Underwater Utilities";
	public static final String version = "@VERSION@";

	@Mod.Instance(modId)
	public static UnderwaterUtilities instance;

//	Content
	public static ModBlocks blocks = new ModBlocks();
	public static ModItems items = new ModItems();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		UUConfig.init(event.getModConfigurationDirectory());
		UUConfig.load();

		blocks.init();
		items.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new UUGUIHandler());

		registerCapabilities();

		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.init();
	}

	private void registerCapabilities() {
		CapabilityManager.INSTANCE.register(Goggles.class, new Storage<>(), Goggles.class);
	}

}
