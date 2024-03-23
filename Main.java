import java.util.Random;;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Box myBox = Box.getInstance();
        for (int i = 0; i < 3; i++) {
            myBox.particlesGenerate(rand.nextInt(myBox.getWidth()), rand.nextInt(myBox.getHeight()));
        }
        //int iterationCounter = 0;

        while (!myBox.overload) {
            //System.out.print("\033[H\033[2J");
            System.out.flush();
            myBox.visualize();
            //iterationCounter++;
            //System.out.println("Iteration No." + iterationCounter);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // myBox.visualize();
    }
}
