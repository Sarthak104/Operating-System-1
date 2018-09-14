
import java.util.ArrayList;
import java.util.List;

public class ProdCons {
 public static void main(String[] args) {
List<Integer> b = new ArrayList<Integer>();
Thread p = new Thread(new Producer(b));
Thread c = new Thread(new Consumer(b));
p.start();
c.start();
    }   
}

class Producer implements Runnable
{
    List<Integer> b = null;
private int i=0;
final int max=5;
Producer(List<Integer> b)
{
    this.b=b;
}
public void Produce(int i) throws InterruptedException
{
    synchronized(b)
            {
                while(b.size()==max)
                {
                    System.out.println("Producer is waiting");
                    b.wait();
                }
            }
    synchronized(b)
            {
                b.add(i);
                System.out.println("Producer is producing");
                Thread.sleep(500);
                b.notify();
            }
}
public void run()
    {
        try{
            while(true)
            {
                i++;
                Produce(i);
            }
        }
        catch(Exception e){
            System.out.println("Interrupted Exception");
        }
    }
}
class Consumer implements Runnable
{
    List<Integer> b = null;
Consumer(List<Integer> b)
{
    this.b=b;
}
public void Consume() throws InterruptedException
{
    synchronized(b)
            {
                while(b.isEmpty())
                {
                    System.out.println("Consumer is waiting");
                    b.wait();
                }
            }
    synchronized(b)
            {
                b.remove(0);
                System.out.println("Consumer is consuming");
                Thread.sleep(500);
                b.notify();
            }
}
public void run()
    {
        try{
            while(true)
            {
                Consume();
            }
        }
        catch(Exception e){
            System.out.println("Interrupted Exception");
        }
    }

}