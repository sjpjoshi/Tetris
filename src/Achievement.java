
public class Achievement 
{
	private int highScore = 15000;
	
	public void setHighScore(int h)
	{
		highScore = h;
	}
	
	public void update(int score)
	{
		if(score > highScore)
			highScore = score;
	}
	
	public boolean d2Check()
	{
		if(highScore >= 2000)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean d3Check()
	{
		if(highScore >= 5000)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean d4Check()
	{
		if(highScore >= 10000)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean d5Check()
	{
		if(highScore >= 15000)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
