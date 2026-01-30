package Block;

import java.util.Random;

public class BlockGenerator {



    public Block[] generateBlocks(int q){

        Random rand = new Random();
        Block[] blocks = new Block[q];
        for (int i = 0; i < q; i++){
            blocks[i] = new Block(rand.nextInt(350) + 50 );
        }
        return blocks;
    }



    public void printBlocks(Block[] blocks){
        for (int i = 0; i < blocks.length; i++){
            System.out.println("Num " + i + " ->"  + blocks[i].getValue() + " Color: " + blocks[i].getColor());
        }
    }


}
