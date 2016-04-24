package gradientdescent;

import java.io.FileInputStream;
import java.util.Scanner;

public class GradientDescent
{
	private double[][] x;
	private double[][] y;
	private double[][] para;
	
	public GradientDescent(String filePath)throws Exception
	{
		// TODO Auto-generated constructor stub
		FileInputStream fileIn=new FileInputStream(filePath);
		Scanner scanner=new Scanner(fileIn);
		int m=scanner.nextInt();//训练样本数目
		int n=scanner.nextInt();//特征数
		x=new double[m][n];
		y=new double[m][1];
		para=new double[n][1];
		
		for(int i=0;i<m;i++)
		{
			y[i][0]=scanner.nextDouble();
			for(int j=0;j<n;j++)
			  x[i][j]=scanner.nextDouble();
		}
		training();
		scanner.close();
	}
	private void training() throws Exception
	{
		
		para=Matrix.times(Matrix.times(Matrix.inverse( Matrix.times(Matrix.transposition(x), x)) , Matrix.transposition(x)), y);         
	}
	
	public void showPara()
	{
		System.out.println("Parameters");
		for(int i=0;i<para.length;i++)
			System.out.println(para[i][0]);
	}
	public double predict(double[][] x)
	{
		double predict=0.0;
		for(int i=0;i<para.length;i++)
			predict+=para[i][0]*x[i][0];
		return predict;
	}
	public static void main(String[] args)throws Exception
	{
		GradientDescent gradientDescent=new GradientDescent("src/data/house.txt");
		gradientDescent.showPara();
		double[][] x=new double[2][1];
		x[0][0]=1600.0;
		x[1][0]=3.0;
		System.out.println("predict of (1600,3)");
		System.out.println(gradientDescent.predict(x));
	}
}
