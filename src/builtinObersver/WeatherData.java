package builtinObersver;

import java.util.Observable;

public class WeatherData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {} //������Ҫ�ֶ�����ArrayList
	
	public void measurementsChanged(){
		setChanged(); 
		notifyObservers();//û��������������,��ʾʹ�õ���pull�ķ�ʽ,push��ʽ��Ҫ������������
	}
	
	public void setMeasurements(float temperature,  float humidity, float pressure){
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}
	
	
}
