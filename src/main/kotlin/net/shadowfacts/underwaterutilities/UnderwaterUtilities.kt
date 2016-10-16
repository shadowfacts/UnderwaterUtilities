package net.shadowfacts.underwaterutilities

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.shadowfacts.shadowmc.capability.Storage
import net.shadowfacts.underwaterutilities.api.item.Goggles
import net.shadowfacts.underwaterutilities.block.UUBlocks
import net.shadowfacts.underwaterutilities.event.ClientEventHandler
import net.shadowfacts.underwaterutilities.gui.UUGUIHandler
import net.shadowfacts.underwaterutilities.item.UUItems
import net.shadowfacts.underwaterutilities.recipe.ModRecipes

/**
 * @author shadowfacts
 */
@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = "required-after:shadowmc@[3.4.8,);", acceptedMinecraftVersions = "[1.10.2]", guiFactory = "net.shadowfacts.underwaterutilities.gui.UUGUIFactory", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object UnderwaterUtilities {

	//	Content
	var blocks = UUBlocks
	var items = UUItems

	@Mod.EventHandler
	fun preInit(event: FMLPreInitializationEvent) {
		UUConfig.init(event.modConfigurationDirectory)
		UUConfig.load()

		blocks.init()
		items.init()

		NetworkRegistry.INSTANCE.registerGuiHandler(this, UUGUIHandler)

		registerCapabilities()

		MinecraftForge.EVENT_BUS.register(ClientEventHandler)
	}

	@Mod.EventHandler
	fun init(event: FMLInitializationEvent) {
		ModRecipes.init()
	}

	private fun registerCapabilities() {
		CapabilityManager.INSTANCE.register(Goggles::class.java, Storage<Goggles>(), Goggles::class.java)
	}

}