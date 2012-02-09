/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laytonsmith.core.events.drivers;

import com.laytonsmith.core.docs;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventHandlerInterface;


/**
 *
 * @author layton
 */
public class PlayerEvents {
    public static String docs(){
        return "Contains events related to a player";
    }
    
    @docs(type= docs.type.EVENT)
    public static class player_join extends AbstractEvent{
        
        public player_join(EventHandlerInterface handler){
            super(handler);
        }

        public String getName() {
            return "player_join";
        }

        public String docs() {
            return "{player: <string match> |"
                    + "join_message: <regex>} This event is called when a player logs in. "
                    + "Setting join_message to null causes it to not be displayed at all. Cancelling "
                    + "the event does not prevent them from logging in. Instead, you should just kick() them."
                    + "{player: The player's name | join_message: The default join message}"
                    + "{join_message}"
                    + "{player|join_message}";
        }

        public String since() {
            return "3.3.0";
        }
        
        public Driver driver(){
            return Driver.PLAYER_JOIN;
        }

        
        
    }
    
    @docs(type= docs.type.EVENT)
    public static class player_interact extends AbstractEvent{
        
        public player_interact(EventHandlerInterface handler){
            super(handler);
        }

        public String getName() {
            return "player_interact";
        }

        public String docs() {
            return "{block: <item match> If the block the player interacts with is this"
                    + " | button: <string match> left or right. If they left or right clicked |"
                    + " item: <item match> The item they are holding when they interacted |"
                    + " player: <string match> The player that triggered the event} "
                    + "Fires when a player left or right clicks a block or the air"
                    + "{action: One of either: left_click_block, right_click_block, left_click_air, or right_click_air |"
                    + "block: The id of the block they clicked, or 0 if they clicked the air. If they clicked the air, "
                    + " neither facing or location will be present. |"
                    + "player: The player associated with this event |"
                    + "facing: The (lowercase) face of the block they clicked. See <<jd:[bukkit]org.bukkit.block.BlockFace>> for"
                    + " the possible values |"
                    + "location: The (x, y, z, world) location of the block they clicked}"
                    + "{}"
                    + "{player|action|item|location|facing}";
        }

        public String since() {
            return "3.3.0";
        }

        public Driver driver() {
            return Driver.PLAYER_INTERACT;
        }

        
        
    }
    
    @docs(type = docs.type.EVENT)
    public static class player_spawn extends AbstractEvent {
        
        public player_spawn(EventHandlerInterface handler) {
            super(handler);
        }
        
        public String getName() {
            return "player_spawn";
        }
        
        public String docs() {
            return "{x: <expression>| y: <expression>| z: <expression>| world: <string match>| player: <macro>}"
                    + "Fires when a player respawns"
                    + "{player: The player that is respawning | "
                    + "location: The location they are going to respawn at}"
                    + "{location}"
                    + "{player|location}";
        }
        
        public Driver driver() {
            return Driver.PLAYER_SPAWN;
        }
        
        public String since() {
            return "3.3.0";
        }
    }
    
    @docs(type = docs.type.EVENT)
    public static class player_death extends AbstractEvent {
        
        public player_death(EventHandlerInterface handler) {
            super(handler);
        }
        
        public String getName() {
            return "player_death";
        }
        
        public String docs() {
            return "{player: <macro>}"
                    + "Fired when a player dies."
                    + "{player: The player that died | drops: An array of the dropped items"
                    + "| xp: The xp that will be dropped | cause: The cause of death}"
                    + "{xp}"
                    + "{player, drops}";
        }
        
        public Driver driver() {
            return Driver.PLAYER_DEATH;
        }
        
        public String since() {
            return "3.3.0";
        }
    }
    
    
}