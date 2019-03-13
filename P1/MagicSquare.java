package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MagicSquare {
	static int N = 100;
	public static void main (String[] args) {
		generateMagicSquare(121);
		boolean a = isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println(a);
	}
	
	public static boolean isLegalMagicSquare (String filename){
		ArrayList<ArrayList<Integer>> ms = new ArrayList<ArrayList<Integer>>();
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
			while (( tempString = reader.readLine()) != null )
			{
				String[] stmp = tempString.split("\t");
				ArrayList<Integer> itmp = new ArrayList<Integer>();
				for ( int i = 0; i < stmp.length; i++ )
				{
					
					//判断是否存在非法输入
					if ( !pattern.matcher(stmp[i]).matches())
					{
						System.out.println("存在非法输入!");
						return false;
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
			//判断输入是否是一个矩阵
			if ( r != c )
			{
				System.out.println("不是一个方阵！");
				return false;
			}
			
			//积累列值，计算行值
			vor = 0;
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
//				System.out.println("94");
				return false;
			}
		}
		
		//检测列值
		for ( int k = 0; k < c; k++)
		{
			if ( voc[k] != vos )
			{
//				System.out.println("103");
				return false;
			}
		}
		
		//判断对角线
		int vod = 0;
		
		for ( int i = 0; i < r ; i ++ )
		{
			vod += ms.get(i).get(i);
		}
		if ( vod != vos )
		{
			return false;
		}
		
		vod = 0;
		for ( int i = r-1; i > -1 ; i-- )
		{
			vod += ms.get(i).get(i);
		}
		if ( vod != vos )
		{
			return false;
		}
		
//		System.out.println(ms.size());
		
		
		return true;
	}
	
	public static boolean generateMagicSquare( int n ) {
		
		//判断非法输入偶数
		if(n % 2 == 0 )
		{
			System.out.println("不能输入偶数！");
			return false;
		}
		
		//判断非法输入负数
		if ( n < 0 )
		{
			System.out.println("不能输入负数！");
			return false;
		}
		
		int magic[][] = new int [n][n];
		int row = 0, col = n/2, i, j, square = n*n;
		
		//从magic[0][n/2]开始开始循环向数组中添加数据
		for ( i = 1; i <= square; i ++ )
		{
			magic [row][col] = i;
			
			//如果已经添加了n*k个数（k为正整数），则跳转到行数+1
			if ( i % n == 0 )
				row ++;
			
			//如果当前为第一行，则跳转到最后一行，否则跳转到上一行
			//如果当前为最后一列，则跳转到第一列，否则跳转到下一列
			else {
				if ( row == 0 )
					row = n - 1;
				else 
					row --;
				if ( col == ( n-1))
					col = 0;
				else
					col ++;
			}
		}
		
		//输出数组
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("src/P1/txt/6.txt"));
			for ( i = 0; i < n; i++)
			{
				for ( j = 0; j < n; j++)
					out.write(magic[i][j] + "\t");
				out.write("\n");
			}
			out.close();
		}catch(IOException e) {
			
		}
		
		
		
		return true;
	}

}
