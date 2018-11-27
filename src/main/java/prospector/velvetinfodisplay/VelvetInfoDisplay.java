package prospector.velvetinfodisplay;

import net.fabricmc.api.ModInitializer;
import prospector.velvetinfodisplay.element.BlockRaytraceElement;
import prospector.velvetinfodisplay.element.FluidRaytraceElement;
import prospector.velvetinfodisplay.gui.VelvetHud;

public class VelvetInfoDisplay implements ModInitializer {

	@Override
	public void onInitialize() {
		VelvetHud.addElementAdder(() -> VelvetHud.addElement(new BlockRaytraceElement()));
		VelvetHud.addElementAdder(() -> VelvetHud.addElement(new FluidRaytraceElement()));
	}
}
