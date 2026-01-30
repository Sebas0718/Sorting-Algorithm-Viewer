package Block;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Block  {

    private int value;
    private Color color;
    private BlockStatus status;

    public Block(int value){
        this.value = value;
        Random r = new Random();
        this.status = BlockStatus.INACTIVE;
        this.color = new Color(120,120,120);
    }

    public void setColor(Color c){
        this.color = c;
    }
    public Color getColor(){
        return this.color;
    }
    public int getValue(){
        return this.value;
    }
    public BlockStatus getStatus(){
        return this.status;
    }
    public void setStatus(BlockStatus st){
        this.status = st;
    }
}
