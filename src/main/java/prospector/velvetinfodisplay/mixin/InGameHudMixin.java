package prospector.velvetinfodisplay.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prospector.velvetinfodisplay.gui.VelvetHud;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	public VelvetHud velvet;

	@Inject(at = @At("RETURN"), method = "draw(F)V")
	public void draw(float elapsedTicks, CallbackInfo info) {
		if (velvet == null) {
			velvet = new VelvetHud();
		}
		velvet.draw();
	}
}
