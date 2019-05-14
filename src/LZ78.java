import java.util.*;
public class LZ78 {
	private String Sentence;
	Vector<String> Dictionary=new Vector<String>();
	Vector<Integer> index=new Vector<Integer>();
	Vector<Character> Next=new Vector<Character>();
	public LZ78(String s)
	{
		Sentence=s;
		Dictionary.add(null);
	}
	public int Search(String x)
	{
		for(int i=0;i<Dictionary.size();i++)
		{
			if(x.equals((Dictionary.elementAt(i))))
				{return i;}
		}
		return 0;
	}
	public void Compress()
	{
		String CheckPoint="";
		CheckPoint+=Sentence.charAt(0);
		for(int y=0;y<Sentence.length();y++)
		{
			if(Search(CheckPoint)>0)
			{
				if(y!=Sentence.length()-1)
					CheckPoint+=Sentence.charAt(y+1);
				else
				{String SubCheckPoint = CheckPoint.substring(0, CheckPoint.length() - 1);
				index.add(Search(SubCheckPoint));
					Next.add(Sentence.charAt(y));
				}
			}
			else
			{
				Dictionary.add(CheckPoint);
		       String SubCheckPoint = CheckPoint.substring(0, CheckPoint.length() - 1);
				index.add(Search(SubCheckPoint));
				Next.add(Sentence.charAt(y));
				CheckPoint="";
				if(y!=Sentence.length()-1)
				{
					CheckPoint+=Sentence.charAt(y+1);
				}
			}				
		}
		System.out.println(Dictionary);
		for(int i=0;i<Next.size();i++)
		System.out.println("<"+index.elementAt(i)+","+Next.elementAt(i)+">");
		Dictionary.clear();
	}   
	public void DeCompress()
	{
		String CheckPoint = "";
		Dictionary.add(null);
		for(int i=0;i<Next.size();i++)
		{
			if(index.elementAt(i)==0)
			{
				CheckPoint+=Next.elementAt(i);
				System.out.print(Next.elementAt(i));
				Dictionary.add(CheckPoint);
				CheckPoint="";
			}
			else
			{
				CheckPoint+=Dictionary.elementAt(index.elementAt(i));
				CheckPoint+=Next.elementAt(i);
				Dictionary.add(CheckPoint);
				System.out.print(CheckPoint);
				CheckPoint="";
			}
		}
		Dictionary.clear();
	}
	public static void main(String[] args) {
		String Sentence;
		System.out.print("Please Enter Your Sentence \n");
		Sentence=new Scanner(System.in).nextLine();
		LZ78 obj= new LZ78(Sentence);
		obj.Compress();
		obj.DeCompress();
	}
}
