package com.franco.cameralow;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public final class CameraLowScreen extends Screen {
    private CameraSlider slider;

    public CameraLowScreen() {
        super(Text.literal("Camera Low"));
    }

    @Override
    protected void init() {
        int w = Math.min(380, this.width - 40);
        int x = (this.width - w) / 2;
        int y = Math.max(30, this.height / 2 - 45);

        this.slider = this.addDrawableChild(
                new CameraSlider(x, y, w, 20, CameraLow.offsetToSlider(CameraLow.getOffset())));

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal(String.format("Reset to %.2f blocks", CameraLow.DEFAULT_OFFSET)),
                        b -> {
                            CameraLow.reset();
                            this.slider.setFromOffset(CameraLow.getOffset());
                        })
                .dimensions(x, y + 32, w, 20)
                .build());
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private static final class CameraSlider extends SliderWidget {
        CameraSlider(int x, int y, int w, int h, double initial) {
            super(x, y, w, h, Text.empty(), initial);
            updateMessage();
        }

        void setFromOffset(double offset) {
            // setValue() es private en SliderWidget: se asigna el campo protected directo
            this.value = Math.max(0.0, Math.min(1.0, CameraLow.offsetToSlider(offset)));
            applyValue();
            updateMessage();
        }

        @Override
        protected void applyValue() {
            CameraLow.setOffset(CameraLow.sliderToOffset(this.value));
        }

        @Override
        protected void updateMessage() {
            double off = CameraLow.getOffset();
            setMessage(Text.literal(String.format(
                    "Camera: %.2f blocks lower  |  Eye Y: %.2f", off, 1.62 - off)));
        }
    }
}
