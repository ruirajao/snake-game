package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;

import javax.swing.text.Style;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit(); // uncomment when it's time to introduce fruits

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            Field.drawFruit(fruit);
        }
    }

    private void generateFruit() {
        this.fruit = new Fruit();

    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        //CHECK WALLS COLLISION
        if (snake.getHead().getXPosition() == 0 || snake.getHead().getXPosition() == Field.getWidth() - 1 || snake.getHead().getYPosition() == 0 || snake.getHead().getYPosition() == Field.getHeight() - 1) {
            snake.die();
        }

        //CHECK BODY COLLISION
        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().getXPosition() == snake.getFullSnake().get(i).getXPosition() && snake.getHead().getYPosition() == snake.getFullSnake().get(i).getYPosition()) {
                snake.die();
            }
        }
        //CHECK FRUIT COLLISION
        if (snake.getHead().getXPosition() == fruit.getPosition().getXPosition() && snake.getHead().getYPosition() == fruit.getPosition().getYPosition()) {
            snake.increaseSize();
            this.fruit = null;
        }

        if (this.fruit == null) {
            generateFruit();
        }
    }

    public void gameOver() throws InterruptedException {
        Field.printString(0, 0, "GAME OVER");
       // Field.printString(5, 5, "Wanna play again? (y/n)");

        /* Key k = Field.readInput();

        if (k != null) {
            if(k.getCharacter() == 'y')
            {
                this.restart();
            }
        }
    }

    public void restart() throws  InterruptedException {
        start();
    }
     */
    }
}
