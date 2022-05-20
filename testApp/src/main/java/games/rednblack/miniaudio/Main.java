package games.rednblack.miniaudio;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import games.rednblack.miniaudio.effect.MADelayNode;
import games.rednblack.miniaudio.filter.MABiquadFilter;

public class Main implements ApplicationListener {

    MiniAudio miniAudio;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.disableAudio(true);
        new Lwjgl3Application(new Main(), configuration);
    }

    MASound maSound;
    MANode effectNode;

    @Override
    public void create() {
        miniAudio = new MiniAudio();
        //int res = miniAudio.playSound("Median_test.ogg");
        //int res = miniAudio.playSound("piano2.wav");
        //System.out.println(res);
        //maSound = miniAudio.createSound("Median_test.ogg");

        //effectNode = new MADelayNode(miniAudio, 0.2f, 0.5f);
        effectNode = new MABiquadFilter(miniAudio, .0102f, .0105f, .011f, .109f, .01047f, .1028f);

        miniAudio.attachToOutput(effectNode, 0);
        //effectNode.attachToNode(maSound, 0);

        //maSound.loop();
        //MAWaveform waveform = miniAudio.createWaveform(2, MAWaveformType.SAWTOOTH, 1, 400);
        //maSound = miniAudio.createSound(waveform);
        //maSound.play();
        MANoise waveform = miniAudio.createNoise(2, MANoiseType.PINK, 0, 400);
        maSound = miniAudio.createSound(waveform);
        maSound.play();
        System.out.println(maSound.isPlaying());
        System.out.println(maSound.isLooping());
        System.out.println(maSound.isEnd());
        System.out.println(maSound.isPlaying());
        System.out.println(maSound.getLength());
    }

    @Override
    public void resize(int width, int height) {

    }
    float i = 0;

    @Override
    public void render() {
        //System.out.println(maSound.getCursorPosition());
        //System.out.println("isLooping " + maSound.isLooping());
        //System.out.println("isEnd " + maSound.isEnd());
        //if (Gdx.graphics.getFrameId() == 200) maSound.seekTo(45);
        //if (Gdx.graphics.getFrameId() == 500) maSound.setPitch(1);
        i += 0.1f;
        //miniAudio.setListenerPosition(MathUtils.cosDeg(i)*5, 0, 0);
    }

    @Override
    public void pause() {
        miniAudio.stopEngine();
    }

    @Override
    public void resume() {
        miniAudio.startEngine();
    }

    @Override
    public void dispose() {
        maSound.dispose();
        effectNode.dispose();
        miniAudio.dispose();
    }
}
