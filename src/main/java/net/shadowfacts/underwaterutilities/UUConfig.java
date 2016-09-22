package net.shadowfacts.underwaterutilities;

import net.minecraftforge.common.config.Configuration;
import net.shadowfacts.config.Config;
import net.shadowfacts.config.ConfigManager;

import java.io.File;

/**
 * @author shadowfacts
 */
@Config(name = UnderwaterUtilities.modId)
public class UUConfig {

	public static Configuration config;

	public static void init(File configDir) {
		config = new Configuration(new File(configDir, "shadowfacts/UnderwaterUtilities.cfg"));
	}

	public static void load() {
		ConfigManager.load(UUConfig.class, Configuration.class, config);
		if (config.hasChanged()) config.save();
	}

}
