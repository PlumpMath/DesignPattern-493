package strategy;

public class MallardDuck extends Duck {
	@Override
	void display() {
		//����������ͷѼ
	}
	public MallardDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
}
