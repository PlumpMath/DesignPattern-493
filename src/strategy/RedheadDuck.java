package strategy;

public class RedheadDuck extends Duck {
	@Override
	void display() {
		//���������ͷѼ
	}
	public RedheadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
}
