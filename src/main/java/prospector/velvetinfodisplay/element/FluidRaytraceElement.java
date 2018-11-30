package prospector.velvetinfodisplay.element;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HitResult;
import net.minecraft.world.FluidRayTraceMode;
import prospector.velvetinfodisplay.gui.VelvetHud;

public class FluidRaytraceElement extends InfoElement {
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
		HitResult result = client.player.rayTrace(client.playerCapabilities.getReachDistance(), 0, FluidRayTraceMode.ALWAYS);
		if (result != null && result.type == HitResult.Type.BLOCK) {
			FluidState fluidState = client.world.getFluidState(result.getBlockPos());
			if (fluidState.getFluid() != Fluids.EMPTY) {
				Item item = fluidState.getFluid().getBucketItem();
				stack = item.getDefaultStack();
				BlockState blockState = fluidState.getBlockState();
				name = blockState.getBlock().getTextComponent().getText();
				if (blockState.getBlock() instanceof FluidBlock && blockState.get(FluidBlock.field_11278) == 0) {
					name = name + " (Source)";
				}
				int textWidth = client.fontRenderer.getStringWidth(name) + (stack.isEmpty() ? 0 : 20);
				if (width < textWidth) {
					width = textWidth;
				}
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
		hud.renderItemStack(stack, x, y + (getHeight() / 2) - 8);
		hud.client.fontRenderer.drawWithShadow(name, x + 20, y + (getHeight() / 2) - (hud.client.fontRenderer.FONT_HEIGHT / 2), 0xFFFFFFFF);
	}
}
