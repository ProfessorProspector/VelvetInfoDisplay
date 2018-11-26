package prospector.velvetinfodisplay.element;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface InfoProvider<T> {
	public InfoElement getElement(T object, World world, EntityPlayer player);
}
