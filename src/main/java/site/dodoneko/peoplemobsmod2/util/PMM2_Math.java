package site.dodoneko.peoplemobsmod2.util;

public class PMM2_Math {

    public static final float Deg2Rad = 0.01745F;
    public static final float Rad2deg = 57.29577F;
    public static final float PI = 3.14159F;
    public static final float PI_Half = 1.57080F;

    public static float lerpRotation(float a, float b, float rate) {
        if (b-a > PMM2_Math.PI) b -= PMM2_Math.PI;
        if (b-a < -PMM2_Math.PI) b += PMM2_Math.PI;
        return a + (b - a) * rate;
    }

    public static float lerp(float a, float b, float rate) {
        return a + (b - a) * rate;
    }

    public static float clamp(float value, float min, float max)
    {
        return Math.min(max, Math.max(min, value));
    }
    public static int clamp(int value, int min, int max)
    {
        return Math.min(max, Math.max(min, value));
    }

    public static float sqrt(float value)
    {
        return (float)Math.sqrt(value);
    }
}
