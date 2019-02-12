package helio.framework.objects;

public class Tuple<T, P> {

	private T firstElement;
	private P secondElement;
	
	public Tuple() {
		// empty
	}
	
	public Tuple(T firstElement, P secondElement) {
		super();
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	public T getFirstElement() {
		return firstElement;
	}

	public void setFirstElement(T firstElement) {
		this.firstElement = firstElement;
	}

	public P getSecondElement() {
		return secondElement;
	}

	public void setSecondElement(P secondElement) {
		this.secondElement = secondElement;
	}

	@Override
	public String toString() {
		return "(" + firstElement + ", " + secondElement + ")";
	}
	
	
	
}
