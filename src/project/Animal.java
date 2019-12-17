package project;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private IWorldMap map;
    private Vector2d position;
    private MoveDirections direction;
    private Genotype genotype;
    private final int startEnergy;
    private int energy;
    protected Vector2d position;
    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
    public Animal(IWorldMap map, Vector2d initialPosition, int energy){
        this.position=initialPosition;
        this.map=map;
        this.energy=energy;

    }
    public Animal(IWorldMap map){
        this.map=map;
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition,newPosition, this);
        }
    }
    public String toString() {
        switch (this) {
            case NORTH:
                return new String("N");
            case SOUTH:
                return new String("S");
            case EAST:
                return new String("E");
            case WEST:
                return new String("W");
            case NORTHWEST:
                return new String("NW");
            case SOUTHWEST:
                return new String("SW");
            case NORTHEAST:
                return new String("NE");
            case SOUTHEAST:
                return new String("SE");
        }
        return null;
    }
    public Vector2d getPosition() {
        return new Vector2d(this.position.x,this.position.y);
    }
    public void move(MoveDirections direct) {
        switch(direct) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction=this.direction.previous();
                break;
            case BACKWARD:
                if(this.map.canMoveTo(this.position.add(this.direction.toUnitVector())))
                    this.position=this.position.add(this.direction.toUnitVector());
                break;
            case FORWARD:
                if(this.map.canMoveTo(this.position.subtract(this.direction.toUnitVector())))
                    this.position=this.position.subtract(this.direction.toUnitVector());
                break;
        }
    }
    public boolean isDead() {
        return this.energy <= 0;
    }

    public void changeEnergy(int value) {
        if(!isDead())
        this.energy += value;
    }
    public void turn(int turnNumber){
        for(int i=0; i<turnNumber; i++) {
            this.move(MoveDirections.RIGHT);
        }
    }
}