package javaapplication1;
import java.util.concurrent.Semaphore;
public class ReaderWriter implements Runnable {
    int rc;
    Semaphore S=new Semaphore(1);
    Semaphore wrt=new Semaphore(2);
   /* public void acquire(int S)
    {
        while(S<=0)
        {
            S++;
        }
    }
    public void release(int S)
    {
        S++;
    }*/
    public void run() 
    {
        try
        {
        S.acquire();
        rc++;
        if(rc==1)
            wrt.acquire();
            S.release();
            System.out.println("reading started by"+Thread.currentThread().getName());
            Thread.sleep(100);
            System.out.println("Read finish by"+Thread.currentThread().getName());
            S.acquire();
            rc--;
            if(rc==0)
                wrt.release();
            S.release();
        }
        catch(Exception e)
        {
            System.out.println("exception caught");
        }
    }
    
    
    }
class Writer implements Runnable{
    int rc;
    Semaphore S=new Semaphore(1);
    Semaphore wrt=new Semaphore(2);
    public void run()
    {
        try{
            wrt.acquire();
            System.out.println("writing started by"+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("write finish by"+Thread.currentThread().getName());
            //System.out.println("Writing finished");
            wrt.release();
        }
        catch(Exception e)
        {
            System.out.println("Exception caught");  
        }
    }
}
