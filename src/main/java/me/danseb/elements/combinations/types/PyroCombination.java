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

public class PyroCombination implements ElementsCombinations {
    private final Map<Elements, Consumer<EntityDamageByEntityEvent>> consumerMap;

    public PyroCombination() {
        consumerMap = new HashMap<Elements, Consumer<EntityDamageByEntityEvent>>() {
            {
            put(Elements.HYDRO, event -> {
                double damage = event.getDamage()*2;
                LivingEntity entity = (LivingEntity)event.getEntity();

                Bukkit.broadcastMessage("Vaporize");
                entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                if (entity.getHealth()-damage <= 0){
                    event.setDamage(entity.getHealth());
                } else{
                    event.setDamage(damage);
                }
            });

            put(Elements.DENDRO, event -> {
                LivingEntity entity = (LivingEntity)event.getEntity();
                Bukkit.broadcastMessage("Burning");
                entity.setFireTicks(80);
            });

            put(Elements.ELECTRO, event -> {
                float damage = 0.4F;
                LivingEntity entity = (LivingEntity)event.getEntity();

                Bukkit.broadcastMessage("Overloaded");

                entity.getWorld().createExplosion(entity.getLocation(), damage);
            });

            put(Elements.ANEMO, event -> {
                double damage = event.getDamage()*2;
                LivingEntity entity = (LivingEntity)event.getEntity();

                Bukkit.broadcastMessage("Swirl");
                if (entity.getHealth()-damage <= 0){
                    event.setDamage(entity.getHealth());
                } else{
                    event.setDamage(damage);
                }
            });

            put(Elements.CRYO, event -> {
                double damage = event.getDamage()*1.5;
                LivingEntity entity = (LivingEntity)event.getEntity();

                Bukkit.broadcastMessage("Melt");
                if (entity.getHealth()-damage <= 0){
                    event.setDamage(entity.getHealth());
                } else{
                    event.setDamage(damage);
                }
            });

            put(Elements.GEO, event -> {
                LivingEntity entity = (LivingEntity)event.getEntity();
                Bukkit.broadcastMessage("Crystallize");
                entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.BLAZE_POWDER));
            });
        }
        };
        System.out.println("Map Created");
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        System.out.println("getCombinations");
        return consumerMap;
    }
}
