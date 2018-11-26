package prospector.velvetinfodisplay.mixin;

import net.minecraft.client.gui.hud.HudInGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import prospector.velvetinfodisplay.gui.HudVelvet;

@Mixin(HudInGame.class)
public class HudInGameMixin {
	@Inject(at = @At("RETURN"), method = "draw(F)V")
	public void draw(float elapsedTicks, CallbackInfo info) {
		new HudVelvet().draw();
	}
}
