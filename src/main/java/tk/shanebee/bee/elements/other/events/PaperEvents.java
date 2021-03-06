package tk.shanebee.bee.elements.other.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import com.destroystokyo.paper.event.entity.EntityPathfindEvent;
import com.destroystokyo.paper.event.entity.SkeletonHorseTrapEvent;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PaperEvents {

    static {
        // == PLAYER EVENTS == //

        // Player Armor Change Event
        if (Skript.classExists("com.destroystokyo.paper.event.player.PlayerArmorChangeEvent")) {
            Skript.registerEvent("Armor Change Event", SimpleEvent.class, PlayerArmorChangeEvent.class, "player change armor")
                    .description("Called when the player themselves change their armor items. Requires Paper 1.12.2+")
                    .examples("on player change armor:",
                            "\tset helmet of player to pumpkin")
                    .since("1.3.1");
        }

        // Player Recipe Book Click Event
        if (Skript.classExists("com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent")) {
            Skript.registerEvent("Recipe Book Click Event", SimpleEvent.class, PlayerRecipeBookClickEvent.class, "[player] recipe book click")
                    .description("Called when the player clicks on a recipe in their recipe book. Requires Paper 1.15+")
                    .examples("on recipe book click:",
                            "\tif event-string = \"minecraft:diamond_sword\":",
                            "\t\tcancel event")
                    .since("1.5.0");

            EventValues.registerEventValue(PlayerRecipeBookClickEvent.class, String.class, new Getter<String, PlayerRecipeBookClickEvent>() {
                @Nullable
                @Override
                public String get(@NotNull PlayerRecipeBookClickEvent event) {
                    return event.getRecipe().toString();
                }
            }, 0);
        }

        // == ENTITY EVENTS == //

        // Entity Pathfind Event
        if (Skript.classExists("com.destroystokyo.paper.event.entity.EntityPathfindEvent")) {
            Skript.registerEvent("Entity Pathfind Event", SimpleEvent.class, EntityPathfindEvent.class, "entity start[s] pathfinding")
                    .description("Called when an Entity decides to start moving towards a location. This event does not fire for the entities " +
                            "actual movement. Only when it is choosing to start moving to a location. Requires Paper.")
                    .examples("on entity starts pathfinding:",
                            "\tif event-entity is a sheep:",
                            "\t\tcancel event")
                    .since("1.5.0");

            EventValues.registerEventValue(EntityPathfindEvent.class, Location.class, new Getter<Location, EntityPathfindEvent>() {
                @Nullable
                @Override
                public Location get(@NotNull EntityPathfindEvent event) {
                    return event.getLoc();
                }
            }, 0);
        }

        // Skeleton Horse Trap Event
        if (Skript.classExists("com.destroystokyo.paper.event.entity.SkeletonHorseTrapEvent")) {
            Skript.registerEvent("Skeleton Horse Trap Event", SimpleEvent.class, SkeletonHorseTrapEvent.class, "skeleton horse trap")
                    .description("Called when a player gets close to a skeleton horse and triggers the lightning trap. Requires Paper 1.13+")
                    .examples("on skeleton horse trap:",
                            "\tloop all players in radius 10 around event-entity:",
                            "\t\tif loop-player is an op:",
                            "\t\t\tcancel event")
                    .since("1.5.0");
        }

        // == BLOCK EVENTS == //

        // Anvil Damaged Event
        if (Skript.classExists("com.destroystokyo.paper.event.block.AnvilDamagedEvent")) {
            Skript.registerEvent("Anvil Damaged Event", SimpleEvent.class, AnvilDamagedEvent.class, "anvil damage")
                    .description("Called when an anvil is damaged from being used. Requires Paper 1.13+")
                    .examples("on anvil damage:",
                            "\tloop viewers of event-inventory:",
                            "\t\tif loop-player has permission \"no.anvil.break\"",
                            "\t\t\tcancel event")
                    .since("1.5.0");
            EventValues.registerEventValue(AnvilDamagedEvent.class, Inventory.class, new Getter<Inventory, AnvilDamagedEvent>() {
                @Nullable
                @Override
                public Inventory get(@NotNull AnvilDamagedEvent event) {
                    return event.getInventory();
                }
            }, 0);
        }
    }

}
