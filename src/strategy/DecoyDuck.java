package strategy;

public class DecoyDuck extends Duck {
	@Override
	void display() {
		//���������ն�Ѽ
	}
	public DecoyDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new MuteQuack();
	}
}
