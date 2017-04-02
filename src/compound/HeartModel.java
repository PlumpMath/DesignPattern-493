package compound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeartModel implements HeartModelInterface, Runnable {
	List<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	List<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	int time = 1000;
	int bpm = 90;
	Random random = new Random(System.currentTimeMillis());
	Thread thread;
	
	public HeartModel() {
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		int lastrate = -1;
		for(;;){
			int change = random.nextInt(10);
			if(random.nextInt(2)==0){
				change = 0-change;
			}
			int rate = 60000/(time+change);
			if(rate<120&&rate>50){
				time+=change;
				notifyBeatObservers();
				if(rate!=lastrate){
					lastrate = rate;
					notifyBPMObservers();
				}
			}
			try {
				Thread.sleep(time);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	


	private void notifyBPMObservers() {
		for(BPMObserver o: bpmObservers){
			o.updateBPM();
		}
	}
	private void notifyBeatObservers() {
		for(BeatObserver o: beatObservers){
			o.updateBeat();
		}
	}
	@Override
	public int getHeartRate() {
		return 60000/time;
	}

	@Override
	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	@Override
	public void removeObserver(BeatObserver o) {
		try {
			beatObservers.remove(o);
		} catch (Exception e) {}
	}

	@Override
	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	@Override
	public void removeObserver(BPMObserver o) {
		try {
			bpmObservers.remove(o);
		} catch (Exception e) {}
	}

}
