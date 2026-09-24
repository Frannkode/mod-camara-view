package com.franco.cameralow.mixin;

import com.franco.cameralow.CameraLowScreen;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Unique private boolean cameralow$wasDown;

    @Inject(method = "tick", at = @At("HEAD"))
    private void cameralow$openScreen(CallbackInfo ci) {
        MinecraftClient self = (MinecraftClient) (Object) this;
        boolean down = GLFW.glfwGetKey(self.getWindow().getHandle(), GLFW.GLFW_KEY_P) == GLFW.GLFW_PRESS;
        if (down && !cameralow$wasDown && self.currentScreen == null && self.world != null) {
            self.setScreen(new CameraLowScreen());
        }
        cameralow$wasDown = down;
    }
}
