package compound;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class BeatModel implements BeatModelInterface, MetaEventListener {
	Sequencer sequencer;
	List<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	List<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	int bpm= 90;
	Sequence sequence;
	Track track;
	
	@Override
	public void meta(MetaMessage meta) {
		if(meta.getType()==47){
			beatEvent();
			sequencer.start();
			setBPM(getBPM());
		}
	}

	@Override
	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}

	private void buildTrackAndStart() {
		int[] trackList = {35, 0, 46, 0};
		sequence.deleteTrack(null);
		track = sequence.createTrack();
		makeTracks(trackList);
		track.add(makeEvent(192,9,1,0,4));
		try {
			sequencer.setSequence(sequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MidiEvent makeEvent(int i, int j, int k, int l, int m) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(i,j,k,l);
			event = new MidiEvent(a, m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	private void makeTracks(int[] trackList) {
		for(int i =0;i<trackList.length;i++){
			int key = trackList[i];
			if(key!=0){
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i+1));
			}
		}
	}

	private void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void on() {
		sequencer.start();
		setBPM(90);
	}

	@Override
	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	@Override
	public void setBPM(int bpm) {
		this.bpm = bpm;
		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers();
	}


	private void notifyBPMObservers() {
		for(int i=0;i<bpmObservers.size();i++){
			BPMObserver observer = bpmObservers.get(i);
			observer.updateBPM();
		}
	}

	@Override
	public int getBPM() {
		return bpm;
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
	
	public void beatEvent(){
		notifyBeatObservers();
	}

	private void notifyBeatObservers() {
		for(int i=0;i<beatObservers.size();i++){
			BeatObserver observer = beatObservers.get(i);
			observer.updateBeat();
		}
	}
	
	

}
