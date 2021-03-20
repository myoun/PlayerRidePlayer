package kr.myoung2.prp.listners

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class EntityRightClickListener : Listener {

    @EventHandler
    fun onPlayerRightClickEntity(e:PlayerInteractEntityEvent) {
        val entity = e.rightClicked
        val player = e.player
        if (player.inventory.itemInMainHand.itemMeta.displayName != "${ChatColor.LIGHT_PURPLE}UUID Checker")
            return
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(entity.uniqueId.toString()))

    }
}