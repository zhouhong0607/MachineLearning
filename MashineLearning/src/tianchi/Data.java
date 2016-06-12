package tianchi;

public class Data
{
	String artist;
	int artistId;
	int date;
	int count;
	
	public Data(String s,int aid,int date1,int count1)
	{
		// TODO Auto-generated constructor stub
		this.artist=s;
		this.artistId=aid;
		this.date=date1;
		this.count=count1;
	}

	/**
	 * @return the artist
	 */
	public String getArtist()
	{
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	/**
	 * @return the artistId
	 */
	public int getArtistId()
	{
		return artistId;
	}

	/**
	 * @param artistId
	 *            the artistId to set
	 */
	public void setArtistId(int artistId)
	{
		this.artistId = artistId;
	}

	/**
	 * @return the date
	 */
	public int getDate()
	{
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date)
	{
		this.date = date;
	}

	/**
	 * @return the count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

}
