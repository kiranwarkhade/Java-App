package altimetrix;

public class ArrayRotation {

	public static void main(String[] args) {
		int[] array= {1,2,3,4,5,6,7};
	  int n=6;
	  rotateArray(array, n);
	  
	  for(int num: array)
	  {
		  System.out.print(num + " ");
	  }
	}
	private static void rotateArray(int[] array, int n)
	{
		int length=array.length;
		int[] temp=new int[n];
		for(int i=0;i<n;i++)
		{
			temp[i]=array[i];
		}
		for(int i=n;i<length;i++)
		{
			array[i-n]=array[i];
		}
		
		for(int i=0;i<n;i++)
		{
			array[length-n +i] = temp[i];
			
		}
	}

}
