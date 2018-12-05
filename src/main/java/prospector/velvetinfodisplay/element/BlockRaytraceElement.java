package prospector.velvetinfodisplay.element;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;
import prospector.velvetinfodisplay.gui.VelvetHud;

public class BlockRaytraceElement extends InfoElement {
	public String name = "";
	public ItemStack stack = ItemStack.EMPTY;
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
	public void pre(MinecraftClient client) {
		visible = true;
		HitResult result = client.player.rayTrace(client.interactionManager.getReachDistance(), 0, FluidRayTraceMode.NONE);
		if (result != null && result.type == HitResult.Type.BLOCK) {
			BlockState state = client.world.getBlockState(result.getBlockPos());
			Item item = state.getBlock().getItem();
			stack = item.getDefaultStack();
			name = item.getTranslatedNameTrimmed(stack).getText();
			int textWidth = client.fontRenderer.getStringWidth(name) + (stack.isEmpty() ? 0 : 20);
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
	public void draw(int x, int y, VelvetHud hud) {
		hud.renderItemStack(stack, x, y);
		hud.client.fontRenderer.drawWithShadow(name, x + 20, y + (getHeight() / 2) - (hud.client.fontRenderer.FONT_HEIGHT / 2), 0xFFFFFFFF);
	}
}
