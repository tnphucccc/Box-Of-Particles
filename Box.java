import java.util.ArrayList;
import java.util.Random;

public class Box {
    private final int height = 10, width = 20;
    private static Box singletonBox = new Box();
    Random rand = new Random();

    public int genCounter = 0, maxParticlesCount = height * width;
    public boolean overload = false;
    public String[][] boxArr = new String[height][width];
    ArrayList<Particle> particles = new ArrayList<>();

    private Box() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boxArr[i][j] = " ";
            }
        }
    }

    public static Box getInstance() {
        return singletonBox;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getParticleCount() {
        return this.particles.size();
    }

    public void particlesGenerate(int x, int y) {
        Particle P = new Particle(x, y);
        // System.out.println("x = " + x);
        // System.out.println("y = " + y);
        this.particles.add(P);
        // P.particleDraw(x, y);
    }

    public void visualize() {
        int pNum = 0;
        genCounter = 0;
        while (pNum < particles.size()) {
            particles.get(pNum).move();
            pNum += 1;
            if (this.getParticleCount() + this.genCounter >= this.maxParticlesCount) {
                this.overload = true;
                break;
            }
        }

        for (int i = 0; i < genCounter && this.getParticleCount() < this.maxParticlesCount; i++) {
            int randX = rand.nextInt(this.width), randY = rand.nextInt(this.height);
            this.particlesGenerate(randX, randY);
        }

        for (int i = 0; i < width + 2; i++)
            System.out.print("-");
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(boxArr[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < width + 2; i++)
            System.out.print("-");
        System.out.println();
        System.out.println("No. of particles " + getParticleCount());
    }
}