package prospector.velvetinfodisplay.element;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface InfoProvider<T> {
	public InfoElement getElement(T object, World world, PlayerEntity player);
}
