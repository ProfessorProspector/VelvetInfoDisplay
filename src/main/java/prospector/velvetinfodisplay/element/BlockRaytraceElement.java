package prospector.velvetinfodisplay.element;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftGame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;
import prospector.velvetinfodisplay.gui.HudVelvet;

public class BlockRaytraceElement extends InfoElement {
	public String name = "";
	public ItemStack stack = ItemStack.AIR;
	public int width;
	public boolean visible = true;

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public void pre(MinecraftGame game) {
		HitResult result = game.player.rayTrace(game.playerCapabilities.getReachDistance(), 0, FluidRayTraceMode.NONE);
		if (result != null && result.type == HitResult.Type.BLOCK) {
			BlockState state = game.world.getBlockState(result.getBlockPos());
			Item item = state.getBlock().getItem();
			stack = item.getDefaultStack();
			name = item.getTranslatedNameTrimmed(stack).getText();
			int textWidth = game.fontRenderer.getStringWidth(name) + (stack.isEmpty() ? 0 : 20);
			if (width < textWidth) {
				width = textWidth;
			}
		}
		if (name.isEmpty() || stack.isEmpty()) {
			visible = false;
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void draw(int x, int y, HudVelvet hud) {
		hud.renderItemStack(stack, x, y);
		hud.game.fontRenderer.drawWithShadow(name, x + 20, y + (getHeight() / 2) - (hud.game.fontRenderer.FONT_HEIGHT / 2), 0xFFFFFFFF);
	}
}
