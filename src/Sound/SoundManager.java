package Sound;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;

public class SoundManager {
    private final AudioFormat af = new AudioFormat(44100f, 16, 1, true, false);
    private SourceDataLine sdl;
    private final int SAMPLE_RATE = 44100;

    public SoundManager() {
        try {
            sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af, 4096);
            sdl.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playTone(int value, int maxValues) {
        if (sdl == null) return;

        // Mapeo de frecuencia: de 200Hz a 1200Hz para que no sea chillón
        double freq = 200 + ((double) value / maxValues) * 1000;
        int durationMs = 25; // Nota muy corta para algoritmos rápidos
        int numSamples = (int) (durationMs * SAMPLE_RATE / 1000);
        byte[] buffer = new byte[numSamples * 2];

        for (int i = 0; i < numSamples; i++) {
            double angle = 2.0 * Math.PI * i / (SAMPLE_RATE / freq);

            // ENVOLVENTE: Esto hace que el sonido no sea "cuadrado"
            // El volumen baja gradualmente (fade out) para que suene como una gota
            double volumePower = 1.0 - ((double) i / numSamples);
            short sample = (short) (Math.sin(angle) * 32767 * 0.3 * volumePower);

            buffer[i * 2] = (byte) (sample & 0xff);
            buffer[i * 2 + 1] = (byte) ((sample >> 8) & 0xff);
        }
        sdl.write(buffer, 0, buffer.length);
    }

    public void close() {
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
}