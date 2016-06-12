package tianchi;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gradientdescent.GradientDescent;
import util.Dao;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		Map<String, Integer> map = new HashMap<>();
		Set<Data> trainSet=new HashSet<>();
		Set<Data> preSet=new HashSet<>();
		
		
		int index = 10000;
		double[][] x;
		double[][] y;
		double[][] para;
		String sql = "select * from plays";
		ResultSet rs = Dao.executeQuery(sql);
		rs.last(); // 游标移到最后, 获得rs长度
		int length = rs.getRow();
		rs.first(); // 还原游标到rs开头

//		x = new double[length][2];
//		y = new double[length][1];
//		para = new double[2][1];

		for (int i = 0; i < length; i++)
		{
			String artist = rs.getString("Artist_id");
			int date=Integer.parseInt(rs.getString("Time"));
			int id;
			int count=rs.getInt("cout");
			if (map.containsKey(artist))
			{
				
				id= map.get(artist);
			} else
			{
				id = index;
				map.put(artist,  index++);
			}
		if(date<=20150630)
		{
			trainSet.add(new Data(artist,id,date,count));
		}else
		{
			preSet.add(new Data(artist,id,date,count));
		}
//			x[i][0]=id;
//			x[i][1] = date;
//			y[i][0] = rs.getInt("cout");
			
			rs.next();
		}
		
		
		int m=trainSet.size();
		x = new double[m][2];
		y = new double[m][1];
		para = new double[2][1];
		
		int c=0;
		for(Data data:trainSet)
		{
			x[c][0]=data.getArtistId();
			x[c][1]=data.getDate();
			y[c][0]=data.getCount();
			c++;
		}
		
		
		GradientDescent gradientDescent = new GradientDescent(x, y, para);
		for (String artist : map.keySet())
		{
			for (int date = 20150701; date <= 20150830; date++)
			{
				double[][] pre = new double[2][1];
				pre[0][0] = map.get(artist);
				pre[1][0] = date;
				int preCount=(int)gradientDescent.predict(pre);
//				String insetSql= "insert tb_predict values('" + artist
//				+ "','" + preCount + "','" + date+"')";
//				Dao.executeUpdate(insetSql);
				
				String preS=artist+","+preCount+","+date;
				System.out.println(preS);
				if (date == 20150731)
				{
					date = 20150801;
				}
			}
		}

	

	}

	// private static double String2Hash(String s)
	// {
	//
	// }
	//
	// private static String hash2String(double hash)
	// {
	//
	// }
}
