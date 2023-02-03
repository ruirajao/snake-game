package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

public class Fruit {
    private Position position;

    private int randomGenerator(int max){
        return (int) (Math.random() * (max - 2 + 1) + 2);
    }

    /*
    private int randomGenerator(int max, int min){
        return (int) (Math.random() * (max - min + 1) + min);
    }
     */

    int randomXFruitPosition = randomGenerator(Field.getWidth()-2);
    int randomYFruitPosition = randomGenerator(Field.getHeight()-2);



    public Fruit() {
        this.position = new Position(randomXFruitPosition,randomYFruitPosition);
    }

    public Position getPosition() {
        return this.position;
    }
}
