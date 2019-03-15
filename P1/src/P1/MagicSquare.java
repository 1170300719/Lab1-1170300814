package P1;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.IOException;
public class MagicSquare {
	static int number = 100;
	public static void main (String[] args) {
		generateMagicSquare(39);//����������generattemagicsquare����������magicsquare
		boolean a;
		ArrayList<ArrayList<Integer>> tts = new ArrayList<ArrayList<Integer>>();
		File filename = new File("src/P1/txt/2.txt");//�ļ�λ��
		BufferedReader hello = null;
		try {
			hello = new BufferedReader(new FileReader(filename));
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  //������ʽ
			String mytemp = null;	
			while (( mytemp = hello.readLine()) != null )
			{
				String[] tt = mytemp.split("\t");
				ArrayList<Integer> itmp = new ArrayList<Integer>();
				for ( int i = 0; i < tt.length; i++ )
				{
					
					if ( !pattern.matcher(tt[i]).matches())//�жϷǷ�����
					{
						System.out.println("���ڷǷ�����!");
						a= false;
						System.out.println(a);
						return;
					}
					
					itmp.add(Integer.valueOf(tt[i]));
					
				}
				tts.add(itmp);
			}
			hello.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if ( hello != null ) 
			{
				try 
				{
					hello.close();
				}catch(IOException e1) 
				{
				}
			}
		}
		
		int r = tts.size();
		int vos = 0;
		int  c = 0; 
		int vorect = 0;

		int[] vect = new int[r];
		for (int i = 0; i < r; i++)
		{
			vect[i] = 0;
		}
		
		for ( int i = 0; i < r; i++)
		{
			c = tts.get(i).size();
			
			ArrayList<Integer> row = new ArrayList<Integer>(tts.get(i));
			if ( r != c )//�ж��Ƿ��Ƿ���
			{
				System.out.println("����һ������");
				a= false;
				System.out.println(a);
				return;
			}
			vorect = 0;//��ʼ������ֵ
			for ( int j = 0; j < c; j ++ )
			{
				vect[j] += row.get(j);//�ۼƿ�ʼ
				vorect += row.get(j);
			}
			if ( i == 0)
			{
				vos = vorect;
			}
			
			if ( vorect != vos )
			{
				a= false;
			}
		}
		for ( int k = 0; k < c; k++)
		{
			if ( vect[k] != vos )//����е�����
			{
				a=false;
			}
		}
		
		int vod = 0;
		
		for ( int i = 0; i < r ; i ++ )
		{
			vod += tts.get(i).get(i);//���Խ���
		}
		if ( vod != vos )
		{
			a= false;
		}
		
		 int  vod1 = 0;
		for ( int i = r-1; i > -1 ; i-- )
		{
			vod1 += tts.get(i).get(i);
		}
		if ( vod1 != vos )
		{
			a=false;
		}	
		a= true;
		System.out.println(a);
		return;
	}
	public static boolean generateMagicSquare(int n) { 
		 
		  int magic[][] = new int[n][n];  //����һ��ӵ��n*n��int���͵Ķ���
		  int row = 0, col = n / 2, i, j, square = n * n; //��ʼ��һЩ����
		 
		  for (i = 1; i <= square; i++) {    //�����鸳ֵ
			  magic[row][col] = i;    //�ȶ��м�����鸳ֵ
			  if (i % n == 0)     //��黻��
				  row++;    //���о��Ƕ�row+1
			  else {     //�ж�row�ӵ�һ�п�ʼ���������һ��
				  if (row == 0)      
					  row = n - 1;     
				  else      //�����һ�п�ʼ���
					  row--;     
				  if (col == (n - 1))      
					  col = 0;     
				  else      
						  col++;    
				  }   } 
		  File file =new File("src/P1/txt/6.txt");//�����ļ�
		  try {
			  FileWriter fileWritter = new FileWriter(file.getName(),true);
			  for (i = 0; i < n; i++) {    
				  for (j = 0; j < n; j++)     
					  fileWritter.write(magic[i][j]+" ");//д���ļ�
				  System.out.println();   
				  } 
			  fileWritter.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("wrong");
		}
		  
		return true; 
		} 
}


