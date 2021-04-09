package me.danseb.elements;

import me.danseb.elements.combinations.ElementsCombinations;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.function.Consumer;

public class Listeners implements Listener {

    private final HashMap<Entity, Elements> currentElement;
    private final HashMap<Entity, Integer> currentTask;
    private final HashMap<Entity, Color> currentColor;

    public Listeners() {
        currentElement = new HashMap<>();
        currentTask = new HashMap<>();
        currentColor = new HashMap<>();
    }

    @EventHandler
    public void onDamageDealt(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            Material item = player.getInventory().getItemInMainHand().getType();
            Elements element = null;
            Bukkit.broadcastMessage("Jugador a entidad ->");

            switch (item) {
                case WOODEN_SWORD:
                    element = Elements.DENDRO;
                    break;
                case STONE_SWORD:
                    element = Elements.GEO;
                    break;
                case GOLDEN_SWORD:
                    element = Elements.ELECTRO;
                    break;
                case IRON_SWORD:
                    element = Elements.CRYO;
                    break;
                case DIAMOND_SWORD:
                    element = Elements.HYDRO;
                    break;
                case NETHERITE_SWORD:
                    element = Elements.PYRO;
                    break;
                case STICK:
                    element = Elements.ANEMO;
                    break;
            }

            if (!(event.getEntity() instanceof LivingEntity)) {
                return;
            }

            LivingEntity entity = (LivingEntity) event.getEntity();

            BukkitRunnable r = new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    if (i == 120) {
                        setCurrentElement(entity, null);
                        cancel();
                        return;
                    }

                    Location location = entity.getEyeLocation();

                    entity.getWorld().spawnParticle(Particle.REDSTONE,
                            location.getX(), location.getY() + 0.25, location.getZ(),
                            3, 0, 0, 0, 1,
                            new Particle.DustOptions(currentColor.get(entity), 1), true);
                    i++;
                }
            };

            if (element != null) {
                Bukkit.broadcastMessage(" Tiene elemento ->");
                Elements currentElement = getCurrentElement(entity);

                if (currentElement == element || currentElement == null){
                    if (!(element == Elements.ANEMO || element == Elements.GEO)){

                        Bukkit.broadcastMessage("  Nuevo aplicando ->");

                        setCurrentElement(entity, element);
                        currentColor.put(entity, element.getColor());

                        try {
                            Bukkit.getScheduler().cancelTask(currentTask.get(entity));
                        } catch (Exception ignored) {
                        }

                        r.runTaskTimer(Main.getInstance(), 1, 1);
                        currentTask.put(entity, r.getTaskId());
                    }
                } else {
                    Bukkit.broadcastMessage("  Reacción ->");

                    try {
                        Bukkit.getScheduler().cancelTask(currentTask.get(entity));
                    } catch (Exception ignored) {
                    }

                    Location location = entity.getEyeLocation();

                    entity.getWorld().spawnParticle(Particle.REDSTONE,
                            location.getX(), location.getY() + 0.5, location.getZ(),
                            15, 0.3, 0, 0, 0,
                            new Particle.DustOptions(currentColor.get(entity), 1), true);

                    entity.getWorld().spawnParticle(Particle.REDSTONE,
                            location.getX(), location.getY() + 0.5, location.getZ(),
                            15, -0.3, 0, 0, 0,
                            new Particle.DustOptions(element.getColor(), 1), true);

                    dispatchElementalReaction(currentElement, element, event);
                    setCurrentElement(entity, null);
                    currentColor.put(entity, element.getColor());
                }
            }
        }
    }

    private Elements getCurrentElement(Entity entity) {
        return currentElement.get(entity);
    }

    private void setCurrentElement(Entity entity, Elements element) {
        currentElement.put(entity, element);
    }

    private void dispatchElementalReaction(Elements oldElement, Elements newElement, EntityDamageByEntityEvent event) {
        Bukkit.broadcastMessage(oldElement +" - "+ newElement);

        ElementsCombinations combinations = oldElement.getCombinations();

        /*if (combinations != null){
            combinations.getCombinations().keySet().forEach(elements -> System.out.println(elements.name()));
        }*/

        Consumer<EntityDamageByEntityEvent> consumer = combinations.getCombinations().get(newElement);

        if (consumer != null){
            Bukkit.broadcastMessage("   Reacción aceptada ->");
            consumer.accept(event);
        }
    }

    @EventHandler
    public void onExplosion(BlockExplodeEvent event){
        event.blockList().clear();
    }
}
