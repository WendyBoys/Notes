package queue;

import java.util.Arrays;

public class CircleQueue {
		public static void main(String[] args) {
			Queue queue=new Queue(10);
			queue.add("我是一个莫的感情的字符串");
			queue.add(1);			
			queue.add(Arrays.asList(new int[] {1,2,3}));
			queue.add(1);
			queue.add(1);
			queue.add(1);
			queue.add(1);
			queue.add(1);
			queue.add(1);
			queue.add(1);
			System.out.println(queue.get());
			queue.get();
			queue.get();
			queue.add("我是一个莫的感情的字符串");
			queue.add("我是一个莫的感情的字符串");
			queue.show();
		

		}
}

class Queue{
	private int maxSize;
	private int front;
	private int rear;
	private Object array[];
	Queue(int maxSize)
	{
		this.maxSize=maxSize+1;
		front=0;
		rear=0;
		array=new Object[this.maxSize];
	}
	
	public boolean isFull()
	{
		return (rear+1)%maxSize==front;
	}
	public boolean isEmpty()
	{
		return front==rear;
	}
	public void add(Object data)
	{
		if(isFull())
		{
			throw new RuntimeException("队列已满，无法添加新数据！");
		}
		array[rear]=data;
		rear=(rear+1)%maxSize;
	}
	public Object get()
	{
		if(isEmpty())
		{
			throw new RuntimeException("队列为空，无法取出数据！");
		}
		Object data=array[front];
		front=(front+1)%maxSize;
	//	array[front]=null;
		return data;
	}
	public void show()
	{
		if(isEmpty())
		{
			throw new RuntimeException("队列为空，无法取出数据！");
		}
		for(int i=front;i<front+(rear-front+maxSize)%maxSize;i++)
		{
			System.out.println((i+maxSize)%maxSize+"---"+array[(i+maxSize)%maxSize]);
		}
		
	}
	public Object getFirst()
	{
		if(isEmpty())
		{
			throw new RuntimeException("队列为空，无法取出数据！");
		}
		return array[front];
	}
	
	
	
}
