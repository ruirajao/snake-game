package academy.mindswap;

import academy.mindswap.field.Field;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.FileDescriptor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        try
        {
            Game game = new Game(100, 25, 100);
            game.start();
            game.gameOver();
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
}
