package kr.myoung2.prp.commands

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RideItem : TabExecutor {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return when (args.size) {
            1 -> mutableListOf<String>("uuid_checker")
            else -> mutableListOf()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name != "rideitem") return false
        if (args.size != 1) return false
        if (args[0] != "uuid_checker") return false
        if (sender !is Player) return false
        val item = ItemStack(Material.STICK,1)
        item.let {
            val meta = it.itemMeta
            meta.setDisplayName("${ChatColor.LIGHT_PURPLE}UUID Checker")
            it.itemMeta = meta
            it
        }
        sender.inventory.addItem(item)
        return true
    }
}