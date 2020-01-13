package link.infra.borderlessmining.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

import java.util.function.Function;

public class ModMenuCompat implements ModMenuApi {
	@Override
	public String getModId() {
		return "borderlessmining";
	}

	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return parent -> {
			ConfigBuilder builder = ConfigBuilder.create();
			builder.setParentScreen(parent);
			builder.setTitle("config.borderlessmining.title");
			builder.setSavingRunnable(WIPConfig::saveToFile);

			ConfigEntryBuilder entryBuilder = builder.entryBuilder();
			ConfigCategory general = builder.getOrCreateCategory("config.borderlessmining.general");
			general.addEntry(entryBuilder.startBooleanToggle("config.borderlessmining.general.enabled", WIPConfig.enabled)
					.setDefaultValue(true)
					.setTooltip(I18n.translate("config.borderlessmining.general.enabled.tooltip_1"), I18n.translate("config.borderlessmining.general.enabled.tooltip_2"))
					.setSaveConsumer(enabled -> WIPConfig.enabled = enabled)
					.build());
			general.addEntry(entryBuilder.startBooleanToggle("config.borderlessmining.general.videomodeoption", WIPConfig.optionEnabled)
					.setDefaultValue(true)
					.setTooltip(I18n.translate("config.borderlessmining.general.videomodeoption.tooltip_1"), I18n.translate("config.borderlessmining.general.videomodeoption.tooltip_2"))
					.setSaveConsumer(enabled -> WIPConfig.optionEnabled = enabled)
					.build());

			return builder.build();
		};
	}
}