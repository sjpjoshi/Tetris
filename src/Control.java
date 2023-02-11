
public class Control extends board {

	private int number = 0;
	
	public Control(int d) {
		
		super(d);
		// TODO Auto-generated constructor stub
		
	} // Control

	public void count(){
		
		if(number == 10) {
		
			number++;
		
		} // if
		
	} // count
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int i)
	{
		number = i;
	}
	
	public void printNumbers()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(i + j);
					}
				}
			}
		}
	}
	
	public void printNumbers1()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(i - j);
					}
				}
			}
		}
	}
	
	public void printNumbers2()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(j - i);
					}
				}
			}
		}
	}
	
	public void printNumbers3()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(i * j);
					}
				}
			}
		}
	}
	
	public void printNumbers4()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(i / j);
					}
				}
			}
		}
	}
	
	public void printNumbers5()
	{
		for(int i = 0; i < 99; ++i)
		{
			if(i > 4)
			{
				for(int j = 0; j < 99; ++j)
				{
					if(j > 5 && i > 2)
					{
						System.out.println(j / i);
					}
				}
			}
		}
	}
	
} // class
