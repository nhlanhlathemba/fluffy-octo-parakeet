
public interface IQueue<T> {
	public void enqueue(T elem);
	public T first();
	public T dequeue();
  
	public int size();
	public boolean isEmpty();
}

