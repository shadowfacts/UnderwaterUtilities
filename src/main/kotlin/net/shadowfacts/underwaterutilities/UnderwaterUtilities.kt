package net.shadowfacts.underwaterutilities

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.GameRegistry
import net.shadowfacts.shadowmc.capability.NoOpStorage
import net.shadowfacts.underwaterutilities.api.item.BreathingAid
import net.shadowfacts.underwaterutilities.api.item.Goggles
import net.shadowfacts.underwaterutilities.block.UUBlocks
import net.shadowfacts.underwaterutilities.gui.UUGUIHandler
import net.shadowfacts.underwaterutilities.item.UUItems

/**
 * @author shadowfacts
 */
@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = "required-after:tesla;required-after:shadowmc@[3.4.8,);", guiFactory = "net.shadowfacts.underwaterutilities.gui.UUGUIFactory", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", updateJSON = "https://update.shadowfacts.net/underwater-utilities")
object UnderwaterUtilities {

	//	Content
	var blocks = UUBlocks
	var items = UUItems

	@Mod.EventHandler
	fun preInit(event: FMLPreInitializationEvent) {
		UUConfig.init(event.modConfigurationDirectory)
		UUConfig.load()

		NetworkRegistry.INSTANCE.registerGuiHandler(this, UUGUIHandler)

		CapabilityManager.INSTANCE.register(Goggles::class.java, NoOpStorage<Goggles>(), Goggles::class.java)
		CapabilityManager.INSTANCE.register(BreathingAid::class.java, NoOpStorage<BreathingAid>(), BreathingAid::class.java)
	}

	@Mod.EventBusSubscriber
	object RegistrationHandler {

		@JvmStatic
		@SubscribeEvent
		fun registerBlocks(event: RegistryEvent.Register<Block>) {
			event.registry.registerAll(
					blocks.collector,
					blocks.tank
			)

			GameRegistry.registerTileEntity(blocks.collector.tileEntityClass, blocks.collector.name)
			GameRegistry.registerTileEntity(blocks.tank.tileEntityClass, blocks.tank.name)
		}

		@JvmStatic
		@SubscribeEvent
		fun registerItems(event: RegistryEvent.Register<Item>) {
			event.registry.registerAll(
					blocks.collector.createItemBlock(),
					blocks.tank.createItemBlock(),
					items.goggles,
					items.goggleLens,
					items.breather,
					items.tank,
					items.blade,
					items.fan,
					items.snorkelTube,
					items.snorkel
			)
		}

		@JvmStatic
		@SubscribeEvent
		fun registerModels(event: ModelRegistryEvent) {
			blocks.collector.initItemModel()
			blocks.tank.initItemModel()
			items.goggles.initItemModel()
			items.goggleLens.initItemModel()
			items.breather.initItemModel()
			items.tank.initItemModel()
			items.blade.initItemModel()
			items.fan.initItemModel()
			items.snorkelTube.initItemModel()
			items.snorkel.initItemModel()
		}

	}

}
