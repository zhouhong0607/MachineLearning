package gradientdescent;

import javax.print.CancelablePrintJob;

public class Matrix
{
	/**
	 * 矩阵相乘
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 * @throws Exception
	 */
	public static double[][] times(double[][] m1, double[][] m2) throws Exception
	{
		int m1high, m1width, m2high, m2width;
		m1high = m1.length;
		m1width = m1[0].length;
		m2high = m2.length;
		m2width = m2[0].length;

		if (m1width != m2high)
			throw new Exception("m1 can not times m2");

		double[][] c = new double[m1high][m2width];
		for (int i = 0; i < m1high; i++)
			for (int j = 0; j < m2width; j++)
				for (int k = 0; k < m1width; k++)
					c[i][j] += m1[i][k] * m2[k][j];

		return c;
	}

	/**
	 * 转置
	 * 
	 * @param m
	 * @return
	 */
	public static double[][] transposition(double[][] m)
	{
		int high = m.length;
		int width = m[0].length;
		double[][] t = new double[width][high];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < high; j++)
				t[i][j] = m[j][i];
		return t;
	}

	/**
	 * 在右面添加单位阵
	 * 
	 * @param m
	 * @return
	 */
	private static double[][] appendUnitMatrix(double[][] m)
	{
		int n = m.length;
		double[][] c = new double[n][2 * n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				c[i][j] = m[i][j];

		for (int k = 0; k < n; k++)
			c[k][k + n] = 1.0;
		return c;
	}
/**
 * 矩阵求逆  未完成
 * @param m
 * @return
 * @throws Exception
 */
	public static double[][] inverse(double[][] m) throws Exception
	{
		int high = m.length;
		int width = m[0].length;
		if (high != width)
			throw new Exception("m is not square matrix");

		// 先在右边加上一个单位矩阵。
		double[][] tempData = appendUnitMatrix(m);
		// 再进行初等变换，把左边部分变成单位矩阵

		int tempRow = tempData.length;
		int tempCol = tempData[0].length;
		// 对角线上数字为0时，用于交换的行号
		int line = 0;
		// 对角线上数字的大小
		double bs = 0;
		// 一个临时变量，用于交换数字时做中间结果用
		double swap = 0;
		for (int i = 0; i < tempRow; i++)
		{
			// 将左边部分对角线上的数据等于0，与其他行进行交换
			if (tempData[i][i] == 0)
			{
				if (++line >= tempRow)
				{
					System.out.println("此矩阵没有逆矩阵！");
					return null;
				}

				for (int j = 0; j < tempCol; j++)
				{
					swap = tempData[i][j];
					tempData[i][j] = tempData[line][j];
					tempData[line][j] = swap;
				}

				// 当前行（第i行）与第line行进行交换后，需要重新对第i行进行处理
				// 因此，需要将行标i减1，因为在for循环中会将i加1。
				i--;
				// 继续第i行处理，此时第i行的数据是原来第line行的数据。
				continue;
			}

			// 将左边部分矩阵对角线上的数据变成1.0
			if (tempData[i][i] != 1)
			{
				bs = tempData[i][i];
				for (int j = tempCol - 1; j >= 0; j--)
				{
					tempData[i][j] /= bs;
				}
				// 将左边部分矩阵变成上对角矩阵，
				// 所谓上对角矩阵是矩阵的左下角元素全为0
				for (int iNow = i + 1; iNow < tempRow; iNow++)
				{
					for (int j = tempCol - 1; j >= i; j--)
					{
						tempData[iNow][j] -= tempData[i][j] * tempData[iNow][i];
					}
				}
			}
		}

		// 将左边部分矩阵从上对角矩阵变成单位矩阵，即将矩阵的右上角元素也变为0
		for (int i = 0; i < tempRow - 1; i++)
		{
			for (int iNow = i; iNow < tempRow - 1; iNow++)
			{
				for (int j = tempCol - 1; j >= 0; j--)
				{
					tempData[i][j] -= tempData[i][iNow + 1] * tempData[iNow + 1][j];
				}
			}
		}

		// 右边部分就是它的逆矩阵
		
		int cRow = tempRow;
		int cColumn = tempCol / 2;
		double[][] cData = new double[cRow][cColumn];
		// 将右边部分的值赋给cData
		for (int i = 0; i < cRow; i++)
		{
			for (int j = 0; j < cColumn; j++)
			{
				cData[i][j] = tempData[i][cColumn + j];
			}
		}
		// 得到逆矩阵，返回

		return cData;

	}

	public static void show(double[][] m)
	{
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[0].length; j++)
			{
				System.out.print(" " + m[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception
	{
		double[][] a = { { 1, 2, 3 }, { 4, 5, 6 } };
		double[][] b = { { 1 }, { 1 }, { 1 } };
		double[][] n = { { 1, 2 }, { 3, 4 } };
		// int[][] c = Matrix.times(a, b);
		// double[][] c = Matrix.transposition(a);
		double[][] c = Matrix.inverse(n);
		Matrix.show(c);
	}
}
