package io.github.prospector.velvetinfodisplay;

import net.fabricmc.api.ModInitializer;
import io.github.prospector.velvetinfodisplay.element.BlockRaytraceElement;
import io.github.prospector.velvetinfodisplay.element.FluidRaytraceElement;
import io.github.prospector.velvetinfodisplay.gui.VelvetHud;

public class VelvetInfoDisplay implements ModInitializer {

	@Override
	public void onInitialize() {
		VelvetHud.addElementAdder(() -> VelvetHud.addElement(new BlockRaytraceElement()));
		VelvetHud.addElementAdder(() -> VelvetHud.addElement(new FluidRaytraceElement()));
	}
}
