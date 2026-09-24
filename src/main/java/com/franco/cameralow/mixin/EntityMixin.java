package com.franco.cameralow.mixin;

import com.franco.cameralow.CameraLow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "getCameraPosVec", at = @At("RETURN"), cancellable = true)
    private void cameralow$shiftRaycastOrigin(float tickProgress, CallbackInfoReturnable<Vec3d> cir) {
        double off = CameraLow.getOffset();
        if (off == 0.0) return;

        MinecraftClient client = MinecraftClient.getInstance();
        // Solo la entidad de la cámara, y solo en primera persona.
        // Evita afectar mobs y el jugador del servidor integrado (singleplayer).
        if ((Object) this != client.getCameraEntity()) return;
        if (!client.options.getPerspective().isFirstPerson()) return;

        Vec3d v = cir.getReturnValue();
        cir.setReturnValue(new Vec3d(v.x, v.y - off, v.z));
    }
}
