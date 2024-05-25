package Items;

public class Inventory {
	private int maxSize;
	private int currentSize;
	private Item[] contenant;

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public Item[] getContenant() {
		return contenant;
	}

	public void setContenant(Item[] contenant) {
		this.contenant = contenant;
	}

	public boolean isFull() {
		return this.getMaxSize() == getCurrentSize();

	}

	public Item getItem(int indice) {
		if (indice < this.currentSize)
			return contenant[indice];
		else
			return null;
	}

	public void addItem(Item i) {
		if (!isFull()) {
			contenant[getCurrentSize()] = i;
			this.currentSize++;
		}
	}


	public void dropItem(Item item) {
		boolean marker = false;
		System.out.println(item);
		System.out.println(getCurrentSize());
		for (int i = 0; i < getCurrentSize(); i++) {
			System.out.println(i);
			if ((contenant[i]) == item) {
				marker = true;
				contenant[i] = null;
			}
			if (marker & i < getCurrentSize()) {
				contenant[i] = contenant[i + 1];
			}
				
		}
	}

	public Inventory(Item[] contenant) {
		this.maxSize = INVENTORY_SIZE;
		this.contenant = new Item[maxSize];
		for(Item i : contenant) {
			addItem(i);
		}
		this.currentSize = contenant.length;
	}

	public Inventory() {
		this.maxSize = INVENTORY_SIZE;
		this.contenant = new Item[this.maxSize];
		this.currentSize = 0;
	}

	public static final int INVENTORY_SIZE = 20;
}
