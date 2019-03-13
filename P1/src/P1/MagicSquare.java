package P1;
/**
 * @version 11
 * @author yanzhao
 *
 */
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.IOException;
public class MagicSquare {
	static int N = 100;
	public static void main (String[] args) {
		generateMagicSquare(39);//����������generattemagicsquare����������magicsquare
		boolean a;
		ArrayList<ArrayList<Integer>> ms = new ArrayList<ArrayList<Integer>>();
		File file = new File("src/P1/txt/6.txt");//�ļ�λ��
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  //������ʽ
			while (( tempString = reader.readLine()) != null )
			{
				String[] stmp = tempString.split("\t");
				ArrayList<Integer> itmp = new ArrayList<Integer>();
				for ( int i = 0; i < stmp.length; i++ )
				{
					
					if ( !pattern.matcher(stmp[i]).matches())//�жϷǷ�����
					{
						System.out.println("���ڷǷ�����!");
						a= false;
						System.out.println(a);
						return;
					}
					
					itmp.add(Integer.valueOf(stmp[i]));
					
				}
				ms.add(itmp);
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if ( reader != null ) 
			{
				try 
				{
					reader.close();
				}catch(IOException e1) 
				{
				}
			}
		}
		
		int r = ms.size();
		int vos = 0, c = 0, vor = 0;

		int[] voc = new int[r];
		for (int i = 0; i < r; i++)
		{
			voc[i] = 0;
		}
		
		for ( int i = 0; i < r; i++)
		{
			c = ms.get(i).size();
			
			ArrayList<Integer> row = new ArrayList<Integer>(ms.get(i));
			if ( r != c )//�ж��Ƿ��Ƿ���
			{
				System.out.println("����һ������");
				a= false;
				System.out.println(a);
				return;
			}
			vor = 0;//��ʼ������ֵ
			for ( int j = 0; j < c; j ++ )
			{
				voc[j] += row.get(j);
				vor += row.get(j);
			}
			if ( i == 0)
			{
				vos = vor;
			}
			
			if ( vor != vos )
			{
				a= false;
			}
		}
		for ( int k = 0; k < c; k++)
		{
			if ( voc[k] != vos )//����е�����
			{
				a=false;
			}
		}
		
		int vod = 0;
		
		for ( int i = 0; i < r ; i ++ )
		{
			vod += ms.get(i).get(i);//���Խ���
		}
		if ( vod != vos )
		{
			a= false;
		}
		
		vod = 0;
		for ( int i = r-1; i > -1 ; i-- )
		{
			vod += ms.get(i).get(i);
		}
		if ( vod != vos )
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


