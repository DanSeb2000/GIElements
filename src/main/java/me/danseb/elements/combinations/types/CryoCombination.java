package me.danseb.elements.combinations.types;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
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

public class CryoCombination implements ElementsCombinations {
    private final Map<Elements, Consumer<EntityDamageByEntityEvent>> consumerMap;

    public CryoCombination() {
        consumerMap = ImmutableMap.of(
                Elements.HYDRO, event -> Bukkit.broadcastMessage("Frozen"),

                Elements.GEO, event -> {
                    LivingEntity entity = (LivingEntity)event.getEntity();
                    Bukkit.broadcastMessage("Crystallize");
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.SNOWBALL));
                },
                Elements.ELECTRO, event -> Bukkit.broadcastMessage("Superconduct"),

                Elements.PYRO, event -> {
                    double damage = event.getDamage()*2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Melt");
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
                },

                Elements.ANEMO, event -> {
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

        consumerMap.forEach((elements, entityDamageByEntityEventConsumer) -> {
            if (elements == null){
                System.out.println("nuloooooooooooOOOOOOOOOOOOOO");
            } else if (entityDamageByEntityEventConsumer == null){
                System.out.println("ConSumeR NuLL");
            } else {
                System.out.println(elements.name());
            }
        });

               /* <Elements, Consumer<EntityDamageByEntityEvent>>(){
            {
                put(Elements.HYDRO, event -> {
                    Bukkit.broadcastMessage("Frozen");
                    //entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
                });

                put(Elements.GEO, event -> {
                    LivingEntity entity = (LivingEntity)event.getEntity();
                    Bukkit.broadcastMessage("Crystallize");
                    entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.SNOWBALL));
                });

                put(Elements.ELECTRO, event -> {
                    Bukkit.broadcastMessage("Superconduct");
                });

                put(Elements.PYRO, event -> {
                    double damage = event.getDamage()*2;
                    LivingEntity entity = (LivingEntity)event.getEntity();

                    Bukkit.broadcastMessage("Melt");
                    if (entity.getHealth()-damage <= 0){
                        event.setDamage(entity.getHealth());
                    } else{
                        event.setDamage(damage);
                    }
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
            }
        };*/
    }

    @Override
    public Map<Elements, Consumer<EntityDamageByEntityEvent>> getCombinations() {
        return consumerMap;
    }
}
