package csc2a.designpatterns;

public interface AbstractSubject {
	 public void attach(AbstractObserver obs); 
	 public void detach(AbstractObserver obs); 
	 public void notifyObservers(); 
	 public Object getUpdate(AbstractObserver obs); 
}
