package me.danseb.elements.combinations.types;

import me.danseb.elements.Elements;
import me.danseb.elements.combinations.ElementsCombinations;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AnemoCombination implements ElementsCombinations {
    private final Map<Elements, Consumer<EntityDamageByEntityEvent>> consumerMap;

    public AnemoCombination() {
        consumerMap = new HashMap<Elements, Consumer<EntityDamageByEntityEvent>>(){
            {
                put(Elements.HYDRO, event -> {
                    double damage = event.getDamage()*0.2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Swirl");
                    entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
                });

                put(Elements.CRYO, event -> {
                    double damage = event.getDamage()*0.2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Swirl");
                    entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
                });

                put(Elements.ELECTRO, event -> {
                    double damage = event.getDamage()*0.2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Swirl");
                    entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
                });
                put(Elements.PYRO, event -> {
                    double damage = event.getDamage()*0.2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Swirl");
                    entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
                });

            }
        };
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        return consumerMap;
    }
}
