package com.example.bigkb;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class BigKnockback implements ModInitializer {
    // knockback enchant level jaisa. 30 = bahut tez launch
    public static double strength = 30.0;

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(CommandManager.literal("kbstrength")
                .requires(src -> src.hasPermissionLevel(2))
                .then(CommandManager.argument("value", DoubleArgumentType.doubleArg(0, 1000))
                    .executes(ctx -> {
                        strength = DoubleArgumentType.getDouble(ctx, "value");
                        ctx.getSource().sendFeedback(() -> Text.literal("Knockback strength = " + strength), false);
                        return 1;
                    }))));
    }
}
