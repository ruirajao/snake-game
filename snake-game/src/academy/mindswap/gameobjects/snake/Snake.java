package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;
    private LinkedList<Position> fullSnake;


    public Snake() {
        this.direction = Direction.LEFT;
        this.alive = true;
        this.fullSnake = new LinkedList<Position>();

        fullSnake.add(new Position(Field.getWidth() / 2, Field.getHeight() / 2));

        for (int i = 1; i < SNAKE_INITIAL_SIZE; i++) {
            fullSnake.add(new Position(fullSnake.getFirst().getXPosition() + i, fullSnake.getFirst().getYPosition()));
        }
    }

    public void increaseSize() {
        switch (direction) {
            case UP:
                fullSnake.add(new Position(fullSnake.getLast().getXPosition(), fullSnake.getLast().getYPosition() - 1));
                break;
            case DOWN:
                fullSnake.add(new Position(fullSnake.getLast().getXPosition(), fullSnake.getLast().getYPosition() + 1));
                break;
            case LEFT:
                fullSnake.add(new Position(fullSnake.getLast().getXPosition() + 1, fullSnake.getLast().getYPosition()));
                break;
            case RIGHT:
                fullSnake.add(new Position(fullSnake.getLast().getXPosition() - 1, fullSnake.getLast().getYPosition()));
                break;
        }
    }

    public void move(Direction direction) {
        if (checkMovements(direction)) return;

        this.direction = direction;
        switch (direction) {
            case UP:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getFullSnake().get(0).getXPosition(), getFullSnake().get(0).getYPosition() - 1));
                break;
            case DOWN:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getFullSnake().get(0).getXPosition(), getFullSnake().get(0).getYPosition() + 1));
                break;
            case LEFT:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getFullSnake().get(0).getXPosition() - 1, getFullSnake().get(0).getYPosition()));
                break;
            case RIGHT:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getFullSnake().get(0).getXPosition() + 1, getFullSnake().get(0).getYPosition()));
                break;
        }
    }

    private boolean checkMovements(Direction direction) {
        if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return true;
        }

        if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return true;
        }

        if (this.direction == Direction.UP && direction == Direction.DOWN) {
            return true;
        }
        if (this.direction == Direction.DOWN && direction == Direction.UP) {
            return true;
        }
        return false;
    }

    public void move() {
        move(direction);
    }

    public boolean die() {
        alive = false;
        return false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return fullSnake.getFirst();
    }

    public Position getTail() {
        return fullSnake.getLast();
    }

    public LinkedList<Position> getFullSnake() {
        return fullSnake;
    }

    public int getSnakeSize() {
        return fullSnake.size();
    }

}

