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
		generateMagicSquare(39);//主函数调用generattemagicsquare函数来生成magicsquare
		boolean a;
		ArrayList<ArrayList<Integer>> ms = new ArrayList<ArrayList<Integer>>();
		File file = new File("src/P1/txt/6.txt");//文件位置
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  //正则表达式
			while (( tempString = reader.readLine()) != null )
			{
				String[] stmp = tempString.split("\t");
				ArrayList<Integer> itmp = new ArrayList<Integer>();
				for ( int i = 0; i < stmp.length; i++ )
				{
					
					if ( !pattern.matcher(stmp[i]).matches())//判断非法输入
					{
						System.out.println("存在非法输入!");
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
			if ( r != c )//判断是否是方阵
			{
				System.out.println("不是一个方阵！");
				a= false;
				System.out.println(a);
				return;
			}
			vor = 0;//开始计算行值
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
			if ( voc[k] != vos )//检查列的数量
			{
				a=false;
			}
		}
		
		int vod = 0;
		
		for ( int i = 0; i < r ; i ++ )
		{
			vod += ms.get(i).get(i);//检查对角线
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
		 
		  int magic[][] = new int[n][n];  //创造一个拥有n*n个int类型的对象
		  int row = 0, col = n / 2, i, j, square = n * n; //初始化一些对象
		 
		  for (i = 1; i <= square; i++) {    //给数组赋值
			  magic[row][col] = i;    //先对中间的数组赋值
			  if (i % n == 0)     //检查换行
				  row++;    //换行就是对row+1
			  else {     //判断row从第一行开始就跳到最后一行
				  if (row == 0)      
					  row = n - 1;     
				  else      //从最后一行开始检查
					  row--;     
				  if (col == (n - 1))      
					  col = 0;     
				  else      
						  col++;    
				  }   } 
		  File file =new File("src/P1/txt/6.txt");//创建文件
		  try {
			  FileWriter fileWritter = new FileWriter(file.getName(),true);
			  for (i = 0; i < n; i++) {    
				  for (j = 0; j < n; j++)     
					  fileWritter.write(magic[i][j]+" ");//写入文件
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


