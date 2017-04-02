package observer;

public class CurrentConditionsDisplay implements DisplayElemet, Observer {

	private float temperature;
	private float humidity;
	private Subject weatherData;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		this.weatherData.registerObserver(this);
	}
	
	@Override
	public void display() {
		//ֻ��ʾ���º�ʪ��
		System.out.println("Current condition: "+temperature+" "+humidity);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		display();
	}

}
