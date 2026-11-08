package com.example.bigkb.mixin;

import com.example.bigkb.BigKnockback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("RETURN"))
    private void bigkb$extraKnockback(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (!cir.getReturnValue() || self.getWorld().isClient) return;
        if (BigKnockback.strength <= 0) return;
        if (source.getAttacker() instanceof PlayerEntity p && source.getSource() == p) {
            self.takeKnockback(BigKnockback.strength * 0.5, p.getX() - self.getX(), p.getZ() - self.getZ());
            self.velocityModified = true;
        }
    }
}
