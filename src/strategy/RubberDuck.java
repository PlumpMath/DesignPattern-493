package strategy;

public class RubberDuck extends Duck {
	@Override
	void display() {
		//����������ƤѼ
	}
	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeek();
	}
}
