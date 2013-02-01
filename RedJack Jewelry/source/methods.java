package source;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: RedJack
 */
public class methods {
    public static void sleep (int i){
               Task.sleep(i);
    }

    public static int rand (int l, int h){
          return Random.nextInt(l,h);
    }
}
