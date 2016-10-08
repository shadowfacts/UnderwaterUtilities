package net.shadowfacts.underwaterutilities

import net.minecraftforge.common.config.Configuration

import java.io.File

/**
 * @author shadowfacts
 */
object UUConfig {

	var config: Configuration? = null

	fun init(configDir: File) {
		config = Configuration(File(configDir, "shadowfacts/UnderwaterUtilities.cfg"))
	}

	fun load() {
		val config = config!!
		if (config.hasChanged()) config.save()
	}

}
