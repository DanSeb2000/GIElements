package me.danseb.elements.combinations.types;

import me.danseb.elements.Elements;
import me.danseb.elements.combinations.ElementsCombinations;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DendroCombination implements ElementsCombinations {
    private final Map<Elements, Consumer<EntityDamageByEntityEvent>> consumerMap;

    public DendroCombination() {
        consumerMap = new HashMap<Elements, Consumer<EntityDamageByEntityEvent>>(){{
            put(Elements.PYRO, event -> {
                LivingEntity entity = (LivingEntity)event.getEntity();
                Bukkit.broadcastMessage("Burning");
                entity.setFireTicks(80);
            });
        }};
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        return consumerMap;
    }
}
