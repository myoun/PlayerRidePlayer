package kr.myoung2.prp

import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

@SpigotPlugin
class PlayerRidePlayer : JavaPlugin() {

    override fun onEnable() {
        getCommand("ride")?.setExecutor(this)
        getCommand("ride")?.tabCompleter = this
        super.onEnable()
    }

    override fun onDisable() {
        super.onDisable()
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return when (args.size) {
            1 -> mutableListOf<String>("player","entity")
            2 -> {
                if (args[0] == "entity") {
                    mutableListOf<String>("UUID")
                }
                else if (args[0] == "player") {
                    val list:MutableList<String> = mutableListOf()
                    server.onlinePlayers.let {
                        for (player in it) {
                            list.add(player.name)
                        }
                    }
                    list
                }
                else {
                    mutableListOf<String>()
                }
            }
            else -> mutableListOf()
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name != "ride") return false
        if (sender !is Player) return false
        if (args.size < 2) return false
        val p = sender
        var entity:Entity? = null
        try {
            if (args[0] == "entity") entity = server.getEntity(UUID.fromString(args[1]))
            else if (args[0] == "player") entity = server.getPlayer(args[1])
        }
        catch(e:IllegalArgumentException) {
            sender.sendMessage("Strange UUID")
            return false
        }
        if (entity == null) return false
        if (p == entity) return false
        entity.addPassenger(p)
        return true
    }
}