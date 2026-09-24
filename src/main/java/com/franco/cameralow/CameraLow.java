package com.franco.cameralow;

public final class CameraLow {
    public static final double DEFAULT_OFFSET = 0.62;
    public static final double MAX_OFFSET = 1.5;

    private static double offset = DEFAULT_OFFSET;

    private CameraLow() {}

    public static double getOffset() { return offset; }

    public static void setOffset(double value) {
        offset = Math.max(0.0, Math.min(MAX_OFFSET, value));
    }

    public static void reset() { offset = DEFAULT_OFFSET; }

    public static double sliderToOffset(double slider) { return slider * MAX_OFFSET; }

    public static double offsetToSlider(double value) { return value / MAX_OFFSET; }
}
