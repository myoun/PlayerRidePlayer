package kr.myoung2.prp

import kr.entree.spigradle.annotations.SpigotPlugin
import kr.myoung2.prp.commands.RideCommand
import kr.myoung2.prp.listners.EntityRightClickListener
import org.bukkit.plugin.java.JavaPlugin

@SpigotPlugin
class PlayerRidePlayer : JavaPlugin() {

    val manager = server.pluginManager

    override fun onEnable() {
        getCommand("ride")?.also {
            it.setExecutor(RideCommand(this))
            it.tabCompleter = RideCommand(this)
        }
        manager.registerEvents(EntityRightClickListener(),this)
        super.onEnable()
    }

    override fun onDisable() {
        super.onDisable()
    }
}