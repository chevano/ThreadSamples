// Inter Process Thread Communication

class MyData {
	int value;
	boolean flag = true;

	public synchronized void set(int value) {
		while(flag != true) {
			try {
				wait();
			}
			catch(InterruptedException e){}
		}
		this.value = value;
		flag = false; // Consumer Turn 
		notify();
		
	}

	public synchronized int get() {
		int x = 0;

		while(flag != false) {
			try {
				wait();
			}
			catch(InterruptedException e){}
		}
		x = value;
		flag = true; // Producer Turn
		notify();

		return x;
	}
}

class Producer extends Thread {
	MyData data;

	public Producer(MyData data) {
		this.data = data;
	}

	public void run() {
		int count = 1;

		while(true) {
			data.set(count);
			System.out.println("Producer = " + count);
			count++;
		}
	}
}

class Consumer extends Thread {
	MyData data;

	public Consumer(MyData data) {
		this.data = data;
	}

	public void run() {
		int value;

		while(true) {
			value = data.get();
			System.out.println("Consumer = " + value);
		}
	}
}
public class ProducerConsumer {
	public static void main(String[] args) {
		MyData data = new MyData();
		Consumer consumer = new Consumer(data);
		Producer producer = new Producer(data);

		producer.start();
		consumer.start();
	}
}
	