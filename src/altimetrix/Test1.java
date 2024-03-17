package altimetrix;

 class AProgram{
int x=20;
public void print(){
System.out.println("HelloA");
}
}
 class BProgram extends AProgram {
int x=30;
public void print(){
System.out.println("HelloB");
}
}

public class Test1{
public static void main(String args[]){
AProgram aobj = new AProgram();
BProgram bobj = new BProgram();
AProgram a2obj = new BProgram();
       aobj.print(); // answer
       bobj.print();  // answer
       a2obj.print();  // answer
       System.out.println(a2obj.x); // answer
}
 
}
