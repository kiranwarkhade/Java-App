package java_program;

// Has a reletionsheep
public class Car {

	private String color;
	private int maxSpeed;

	public static void main(String[] args) {

		Car nano = new Car();
		nano.setColor("Red");

		nano.setMaxSpeed(329);

		nano.carInfo();

		Marsdis port = new Marsdis();

		port.marsdisStartDemo();

	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void carInfo() {
		System.out.println("car color= " + color + "\n" + "Max Speed= " + maxSpeed);
	}

}

class Marsdis extends Car {
	public void marsdisStartDemo() {
		Engine MarsdisEngine = new Engine();
		MarsdisEngine.start();
		MarsdisEngine.stop();
	}

}

class Engine {

	public void start() {
		System.out.println("Started: ");
	}

	public void stop() {
		System.out.println("Stopped: ");
	}
}
