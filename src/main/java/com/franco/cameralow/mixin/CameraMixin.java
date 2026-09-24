package com.franco.cameralow.mixin;

import com.franco.cameralow.CameraLow;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow private Vec3d pos;
    @Shadow protected abstract void setPos(double x, double y, double z);

    @Inject(method = "update", at = @At("TAIL"))
    private void cameralow$lowerCamera(World area, Entity focusedEntity, boolean thirdPerson,
                                       boolean inverseView, float tickProgress, CallbackInfo ci) {
        double off = CameraLow.getOffset();
        if (thirdPerson || off == 0.0) return;
        Vec3d p = this.pos;
        // setPos también actualiza blockPos (fog, submersión, etc.)
        setPos(p.x, p.y - off, p.z);
    }
}
