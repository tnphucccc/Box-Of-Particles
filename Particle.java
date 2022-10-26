import java.util.Random;

enum Direction {
    N, NE, E, SE, S, SW, W, NW
}

public class Particle {
    private int x, y;
    private Direction dir;
    private final Box myBox = Box.getInstance();
    private static final Direction[] DIRECTIONS = Direction.values();
    private Random rand = new Random();

    public Particle(int x, int y) {
        if (x >= 0 && x <= myBox.getWidth())
            this.x = x;
        if (y >= 0 && y <= myBox.getHeight())
            this.y = y;
        this.dir = DIRECTIONS[rand.nextInt(8)];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        int nextX = this.x, nextY = this.y;
        switch (dir) {
            case N -> {
                nextY -= 1;
            }
            case NE -> {
                nextY -= 1;
                nextX += 1;
            }
            case E -> {
                nextX += 1;
            }
            case SE -> {
                nextY += 1;
                nextX += 1;
            }
            case S -> {
                nextY += 1;
            }
            case SW -> {
                nextY += 1;
                nextX -= 1;
            }
            case W -> {
                nextX -= 1;
            }
            case NW -> {
                nextY -= 1;
                nextX -= 1;
            }
        }
        if (nextX < 0 || nextX >= myBox.getWidth() || nextY < 0 || nextY >= myBox.getHeight()) {
            this.dir = DIRECTIONS[rand.nextInt(8)];
            this.move();
        } else {
            particleDraw(nextX, nextY);
            this.x = nextX;
            this.y = nextY;
        }
    }

    public void particleDraw(int x, int y) {
        this.myBox.boxArr[this.y][this.x] = " ";
        if (isCollide(x, y)) {
            // myBox.particlesGenerate(rand.nextInt(myBox.getWidth()),
            // rand.nextInt(myBox.getHeight()));
            myBox.genCounter += 1;
        }
        this.myBox.boxArr[y][x] = "*";
    }

    public boolean isCollide(int atX, int atY) {
        return this.myBox.boxArr[atY][atX].equals("*");
    }
}
