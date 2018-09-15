package andersonfds.pibic.Classes;

import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class Task implements Runnable
{

    @Override
    public void run()
    {
        for (int i = 0; i < 2; i++)
        {
          try
          {
            Thread.sleep(1500);
          } catch (InterruptedException ieE)
          {
              Log.d(TAG, "run: Task Error");
          }
        }
    }
}