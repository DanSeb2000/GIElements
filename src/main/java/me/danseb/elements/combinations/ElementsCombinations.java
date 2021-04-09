package me.danseb.elements.combinations;

import me.danseb.elements.Elements;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Map;
import java.util.function.Consumer;

public interface ElementsCombinations {
    Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations();
}
