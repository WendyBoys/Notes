package Node;

public class SingleLinkedListTest{
	public static void main(String[] args) {
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(new Node(3,"cccc"));
		singleLinkedList.add(new Node(1,"aaaa"));
		singleLinkedList.add(new Node(2,"bbbb"));
		singleLinkedList.show();
		
		SingleLinkedList singleLinkedList2 = new SingleLinkedList();
		singleLinkedList2.addByOrder(new Node(3,"cccc"));
		singleLinkedList2.addByOrder(new Node(1,"aaaa"));
		singleLinkedList2.addByOrder(new Node(2,"bbbb"));
		singleLinkedList2.show();
	}
}


class SingleLinkedList {
	//头节点
	private Node headNode=new Node(0,"");
	//按照插入顺序添加
	public void add(Node node)
	{
		//由于头节点不能移动，所以制造一个临时变量
		Node head=headNode;
		//先遍历到尾节点
		while(true)
		{
			if(head.next==null)
			{
				break;
			}
			head=head.next;
		}
		head.next=node;
	}
	
	//按照no大小排序添加
	public void addByOrder(Node node)
	{
		Node head=headNode;
		boolean flag=false;
		while(true)
		{
			if(head.next==null)
			{
				break;
			}
			else if(head.next.no>node.no)
			{
				break;
			}
			else if(head.next.no==node.no)
			{
				
				flag=true;
				break;
			}
			head=head.next;
		}
		if(flag)
		{
			System.out.println("已有重复数据，无法插入！");
			return;
		}
		else
		{
			node.next=head.next;
			head.next=node;
		}
	}
	//打印链表
	public void show()
	{
		if(headNode.next==null)
		{
			System.out.println("链表为空！");
		}
		Node head=headNode.next;
		while(true)
		{
			if(head==null)
			{
				break;
			}
			System.out.println(head);
			head=head.next;
		}
	}

}

class Node{
	 int no;
	 String value;
	 Node next;
	
	public Node(int no, String value) {
		
		this.no = no;
		this.value = value;
		
	}

	@Override
	public String toString() {
		return "Node [no=" + no + ", value=" + value + "]";
	}
	
}
