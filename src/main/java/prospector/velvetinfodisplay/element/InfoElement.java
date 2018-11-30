package prospector.velvetinfodisplay.element;

import net.minecraft.client.MinecraftClient;
import prospector.velvetinfodisplay.gui.VelvetHud;

public abstract class InfoElement {
	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void pre(MinecraftClient client);

	public abstract void draw(int x, int y, VelvetHud hud);

	public boolean isVisible() {
		return true;
	}

	public void onRemove() {

	}
}
