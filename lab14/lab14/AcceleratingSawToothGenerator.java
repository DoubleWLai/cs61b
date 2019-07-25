package lab14;
import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;


public class AcceleratingSawToothGenerator implements Generator{
    private int state;
    private int period;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state = state + 1;
        if (state % period == 0) {
            period = (int) (period * factor);
            state = 0;
        }
        return normalize(state);
    }

    private double normalize(double num) {
        return -1 + 2 * (num % period) / (period - 1);
    }

    public static void main(String[] args) {
        Generator generator = new AcceleratingSawToothGenerator(200, 1.1);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }

}