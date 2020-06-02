package utils.observer;

public interface Observable {
	
	/**
	 * Register an observer
	 * 
	 * @param observer the observer
	 */
	public void addObserver(Observer observer);
	
	/**
	 * Notify all observers
	 */
	public void notifyObservers();
	
	/**
	 * Remove an observer
	 * 
	 * @param the observer
	 */
	public void removeObserver(Observer observer);
	
}
