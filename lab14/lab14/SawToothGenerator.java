package lab14;
import lab14lib.*;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state % period);
    }

    private double normalize(double num) {
        return -1 + 2 * num / (period - 1);
    }

    public static void main(String[] args) {
        Generator generator = new SawToothGenerator(520);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }
}
