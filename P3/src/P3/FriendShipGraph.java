package P3;

import java.util.*;



import P3.Person;
public class FriendShipGraph {
	adjGraph adjGraph = new adjGraph();
	//���������������Դ���
	//��ϵ�˱�
	private class adjGraph 
	{
		int VertexCount = 0, EdgeCount = 0;
		ArrayList<Person> VertexList = new ArrayList<>();
	}
	private int getPosition(Person Person) {
		for (int i = 0; i < this.adjGraph.VertexList.size(); i++)
			if (this.adjGraph.VertexList.get(i) == Person)
				return i;
		return -1;
	}
	//�����ϵ��
	public  boolean addVertex(Person name)
	{
		if(getPosition(name)!=-1)
		{
			System.out.println("This name has already exist!");
			return false;
		}
		else {
			this.adjGraph.VertexCount++;
			this.adjGraph.VertexList.add(name);
			return true;
		}
	}
	//��������ϵ��֮����ӱ�
		public boolean addEdge(Person name1,Person name2)
		{
			int name1position=getPosition(name1);
			int name2position=getPosition(name2);
			if(name1position==name2position)
			{
				System.out.println("Not leagle!");
				return false;
			}
			if(name1position==-1||name2position==-1)
			{
				System.out.println(" Name not exist!");
				return false;
			}
			if(name1.edgeExist(name1position)&&name2.edgeExist(name2position))
			{
				System.out.println("Has exist!");
				return false;
			}
			else {
				this.adjGraph.EdgeCount++;
				name1.linkListAdd(name2position);
				return true;
			}
		}
		//���������ϵ��֮��ľ���
		public int getDistance(Person name1,Person name2)
		{
			return BFS(this.adjGraph,name1,name2);
		}
		//�ù�������㷨���Ͷ��нṹ������
		public int BFS(adjGraph graph,Person name1,Person name2)
		{
			Map<Integer, Integer> routeMap = new HashMap<>();
			int start = getPosition(name1);
			int end = getPosition(name2);
			if (start == end)
				return 0;
			else if ((start == -1) || (end == -1)) {
				System.out.printf("The Person doesn't exist");
				return -1;
			}
			boolean visited[] = new boolean[graph.VertexCount];
			for(int i = 0; i < visited.length; i++) 
				visited[i] = false;
			Queue<Integer> queue = new LinkedList<Integer>();
			visited[start] = true;
			queue.add(start);
			while (!queue.isEmpty()) {
				int curValue = queue.remove();//��ȡ�Ӷ����ݲ�����
				int next=0;
				for ( next = 0; next < graph.VertexList.get(curValue).netWorkSize(); next++) {
					int curvisit = graph.VertexList.get(curValue).getSocial(next);//��ȡÿһ������curvalue�����λ��
					if (!visited[curvisit]) {
						visited[curvisit] = true;
						routeMap.put(curvisit, curValue);
						queue.add(curvisit);//���
					}
				}
			}
			int i=0;
			int distence;
			for (i = 0, distence = 1; i < routeMap.keySet().size(); i++, distence++) {
				if (!routeMap.containsKey(end))
					return -1;

				if (routeMap.get(end) == start)
					return distence;

				end = routeMap.get(end);
			}
			return -1;
		}
	}
	

