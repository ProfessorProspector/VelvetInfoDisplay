package prospector.velvetinfodisplay.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.class_308;
import net.minecraft.client.MinecraftGame;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import prospector.velvetinfodisplay.element.InfoElement;

import java.util.ArrayList;
import java.util.List;

public class VelvetHud extends Drawable {
	public static int x = 5;
	public static int y = 5;
	public MinecraftGame game = MinecraftGame.getInstance();
	private static List<Runnable> adders = new ArrayList<>();
	private static List<InfoElement> elements = new ArrayList<>();
	public boolean displayActive = false;

	public static void addElementAdder(Runnable elementAdder) {
		adders.add(elementAdder);
	}

	public static void addElement(InfoElement element) {
		elements.add(element);
	}

	public static void removeElement(InfoElement element) {
		elements.remove(element);
	}

	private static void clearElements() {
		elements.clear();
	}

	public static List<InfoElement> getElements() {
		return elements;
	}

	public void draw() {
		for (Runnable adder : adders) {
			adder.run();
		}
		int backgroundColor = 0xFFFFFFFF;
		x = 10;
		y = 10;
		boolean active = false;
		int defaultWidth = 0;
		int defaultHeight = 0;
		int width = 0;
		int height = 0;
		int paddingX = 4;
		int paddingY = 4;
		int paddingBetweenElements = 2;
		int currentRenderY = y + paddingY;
		defaultWidth += paddingX * 2;
		defaultHeight += paddingY * 2;
		width = defaultWidth;
		height = defaultHeight;
		for (InfoElement element : elements) {
			element.pre(game);
			if (element.isVisible()) {
				if (width < element.getWidth() + defaultWidth)
					width = element.getWidth() + defaultWidth;
				height += element.getHeight();
				if (!isLastVisible(element))
					height += paddingBetweenElements;
				active = true;
			}
		}
		if (active) {
			drawGradientRect(x, y, x + width, y + height, 0xFF930000, 0xFF690000);
			drawGradientRect(x - 1, y, x, y + height, 0xFF1B0202, 0xFF1B0202);
			drawGradientRect(x, y - 1, x + width, y, 0xFF1B0202, 0xFF1B0202);
			drawGradientRect(x + width, y, x + width + 1, y + height, 0xFF1B0202, 0xFF1B0202);
			drawGradientRect(x, y + height, x + width, y + height + 1, 0xFF1B0202, 0xFF1B0202);
			drawGradientRect(x + 1, y + 1, x + width - 1, y + height - 1, 0xFF1B0202, 0xFF1B0202);
			for (InfoElement element : elements) {
				if (element.isVisible()) {
					element.draw(x + paddingX, currentRenderY, this);
					if (!isLastVisible(element))
						currentRenderY += element.getHeight() + paddingBetweenElements;
				}
			}
		}
		displayActive = active;
		clearElements();
	}

	private boolean isLastVisible(InfoElement element) {
		InfoElement lastVisible = null;
		for (InfoElement e : elements) {
			if (e.isVisible()) {
				lastVisible = e;
			}
		}
		if (lastVisible != null && lastVisible == element) {
			return true;
		}
		return false;
	}

	public void renderItemStack(ItemStack stack, int x, int y) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SrcBlendFactor.SRC_ALPHA, GlStateManager.DstBlendFactor.ONE_MINUS_SRC_ALPHA);
			class_308.method_1453();

			ItemRenderer itemRenderer = MinecraftGame.getInstance().getItemRenderer();
			itemRenderer.renderItemAndGlowInGui(stack, x, y);

			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void drawGradientRect(int left, int top, int right, int bottom, int topLeftColor, int bottomRightColor) {
		super.drawGradientRect(left, top, right, bottom, topLeftColor, bottomRightColor);
	}
}
