package me.danseb.elements.combinations.types;

import me.danseb.elements.Elements;
import me.danseb.elements.combinations.ElementsCombinations;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ElectroCombination implements ElementsCombinations {
    private final Map<Elements, Consumer<EntityDamageByEntityEvent>> consumerMap;

    public ElectroCombination() {
        consumerMap = new HashMap<Elements, Consumer<EntityDamageByEntityEvent>>(){
            {
                put(Elements.HYDRO, event -> {
                    Bukkit.broadcastMessage("Electro-Charged");
                    //entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                });

                put(Elements.GEO, event -> {
                    LivingEntity entity = (LivingEntity)event.getEntity();
                    Bukkit.broadcastMessage("Crystallize");
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.MAGENTA_DYE));
                });

                put(Elements.PYRO, event -> {
                    float damage = 0.4F;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Overloaded");

                    entity.getWorld().createExplosion(entity.getLocation(), damage);
                });

                put(Elements.ANEMO, event -> {
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
                    Bukkit.broadcastMessage("Superconduct");
                });
            }
        };
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        return consumerMap;
    }
}
