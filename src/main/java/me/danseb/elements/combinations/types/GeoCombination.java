package me.danseb.elements.combinations.types;

import me.danseb.elements.Elements;
import me.danseb.elements.combinations.ElementsCombinations;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GeoCombination implements ElementsCombinations {
    private Map<Elements, Consumer<EntityDamageByEntityEvent>> elementsConsumerMap;

    public GeoCombination() {
        elementsConsumerMap = new HashMap<>();
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        return elementsConsumerMap;
    }
}
