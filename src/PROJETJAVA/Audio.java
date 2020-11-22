package PROJETJAVA;
import java.io.File;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Audio {

	public Clip clip;
	public void setFile(String son)
	{
		File file=new File(son);
		try {
			AudioInputStream sound =AudioSystem.getAudioInputStream(file);
		clip=AudioSystem.getClip();
		clip.open(sound);
		}catch(Exception e) {
	}
	}
		public void loop()
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		}

		public void start () {
			clip.start();
		}
	
		public void stop () {
			clip.close();
			clip.stop();
		}
	
}
