package prospector.velvetinfodisplay;

import net.fabricmc.api.ModInitializer;
import prospector.velvetinfodisplay.element.BlockRaytraceElement;
import prospector.velvetinfodisplay.element.FluidRaytraceElement;
import prospector.velvetinfodisplay.gui.HudVelvet;

public class VelvetInfoDisplay implements ModInitializer {

	@Override
	public void onInitialize() {
		HudVelvet.addElementAdder(() -> HudVelvet.addElement(new BlockRaytraceElement()));
		HudVelvet.addElementAdder(() -> HudVelvet.addElement(new FluidRaytraceElement()));
	}
}
