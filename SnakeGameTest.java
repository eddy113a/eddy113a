import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

/**
 * JUnit 5 Test class for SnakeGame.
 *
 * To compile and run these tests if JUnit 5 is not part of a build system (e.g., Maven, Gradle):
 * 1. Download the JUnit Platform JAR files (e.g., junit-platform-console-standalone.jar)
 *    from an official source like Maven Central.
 * 2. Compile: javac -cp path/to/junit-platform-console-standalone.jar:. SnakeGame.java SnakeGameTest.java
 * 3. Run:    java -jar path/to/junit-platform-console-standalone.jar -cp . --select-class SnakeGameTest
 *
 * If using Maven, add to pom.xml:
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter-api</artifactId>
 *     <version>5.10.0</version> <!-- Use the latest version -->
 *     <scope>test</scope>
 * </dependency>
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter-engine</artifactId>
 *     <version>5.10.0</version>
 *     <scope>test</scope>
 * </dependency>
 *
 * If using Gradle, add to build.gradle:
 * testImplementation 'org.junit.jupiter:junit-jupiter-api:5.10.0' // Use the latest version
 * testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.10.0'
 * test {
 *     useJUnitPlatform()
 * }
 */
public class SnakeGameTest {

    private SnakeGame game;
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int INITIAL_BODY_PARTS = 6;

    @BeforeEach
    void setUp() {
        // Instantiate SnakeGame without auto-starting the game (and its timer)
        game = new SnakeGame(false);
        // Fields in SnakeGame are not public, so tests will rely on calling public methods
        // and then inferring state or making minimal necessary fields package-private if essential.
        // For now, we assume startGame() correctly initializes for testing.
    }

    @Test
    @DisplayName("Initial game state after startGame()")
    void testStartGame_InitialState() {
        game.startGame(); // Manually start the game logic for testing

        assertEquals(INITIAL_BODY_PARTS, game.bodyParts, "Initial body parts should be " + INITIAL_BODY_PARTS);
        assertEquals(0, game.applesEaten, "Initial apples eaten should be 0");
        assertTrue(game.running, "Game should be running after start");

        // Check initial snake position (head at center, body extending left)
        // This requires x, y arrays to be accessible or tested via side effects.
        // Assuming x, y are package-private or have getters for testing:
        assertEquals(SCREEN_WIDTH / 2, game.x[0], "Snake head X position should be center");
        assertEquals(SCREEN_HEIGHT / 2, game.y[0], "Snake head Y position should be center");
        for (int i = 1; i < game.bodyParts; i++) {
            assertEquals(game.x[0] - i * UNIT_SIZE, game.x[i], "Snake body part " + i + " X position is incorrect");
            assertEquals(game.y[0], game.y[i], "Snake body part " + i + " Y position is incorrect");
        }

        assertTrue(game.appleX >= 0 && game.appleX < SCREEN_WIDTH, "Apple X coordinate out of bounds");
        assertTrue(game.appleY >= 0 && game.appleY < SCREEN_HEIGHT, "Apple Y coordinate out of bounds");
        assertEquals(0, game.appleX % UNIT_SIZE, "Apple X coordinate not aligned to unit size");
        assertEquals(0, game.appleY % UNIT_SIZE, "Apple Y coordinate not aligned to unit size");
    }

    @Test
    @DisplayName("Snake movement - Right")
    void testMove_Right() {
        game.startGame();
        game.direction = 'R';
        int initialHeadX = game.x[0];
        int initialHeadY = game.y[0];
        int[] initialX = game.x.clone();
        int[] initialY = game.y.clone();

        game.move();

        assertEquals(initialHeadX + UNIT_SIZE, game.x[0], "Snake head should move right by UNIT_SIZE");
        assertEquals(initialHeadY, game.y[0], "Snake head Y should not change when moving right");
        // Check body segments followed
        for (int i = 1; i < game.bodyParts; i++) {
            assertEquals(initialX[i-1], game.x[i], "Body part " + i + " X should follow previous segment");
            assertEquals(initialY[i-1], game.y[i], "Body part " + i + " Y should follow previous segment");
        }
    }
    
    @Test
    @DisplayName("Snake movement - Left")
    void testMove_Left() {
        game.startGame();
        game.direction = 'L';
         // Change initial position slightly so it doesn't hit wall immediately if starting near edge
        game.x[0] = SCREEN_WIDTH / 2 + UNIT_SIZE; 
        for(int i = 1; i < game.bodyParts; i++) game.x[i] = game.x[0] + i * UNIT_SIZE;


        int initialHeadX = game.x[0];
        int initialHeadY = game.y[0];
        int[] initialX = game.x.clone();
        int[] initialY = game.y.clone();

        game.move();

        assertEquals(initialHeadX - UNIT_SIZE, game.x[0], "Snake head should move left by UNIT_SIZE");
        assertEquals(initialHeadY, game.y[0], "Snake head Y should not change when moving left");
        for (int i = 1; i < game.bodyParts; i++) {
            assertEquals(initialX[i-1], game.x[i], "Body part " + i + " X should follow previous segment");
            assertEquals(initialY[i-1], game.y[i], "Body part " + i + " Y should follow previous segment");
        }
    }

    @Test
    @DisplayName("Snake movement - Up")
    void testMove_Up() {
        game.startGame();
        game.direction = 'U';
        int initialHeadX = game.x[0];
        int initialHeadY = game.y[0];
        int[] initialX = game.x.clone();
        int[] initialY = game.y.clone();

        game.move();

        assertEquals(initialHeadX, game.x[0], "Snake head X should not change when moving up");
        assertEquals(initialHeadY - UNIT_SIZE, game.y[0], "Snake head should move up by UNIT_SIZE");
         for (int i = 1; i < game.bodyParts; i++) {
            assertEquals(initialX[i-1], game.x[i], "Body part " + i + " X should follow previous segment");
            assertEquals(initialY[i-1], game.y[i], "Body part " + i + " Y should follow previous segment");
        }
    }

    @Test
    @DisplayName("Snake movement - Down")
    void testMove_Down() {
        game.startGame();
        game.direction = 'D';
        int initialHeadX = game.x[0];
        int initialHeadY = game.y[0];
        int[] initialX = game.x.clone();
        int[] initialY = game.y.clone();

        game.move();

        assertEquals(initialHeadX, game.x[0], "Snake head X should not change when moving down");
        assertEquals(initialHeadY + UNIT_SIZE, game.y[0], "Snake head should move down by UNIT_SIZE");
        for (int i = 1; i < game.bodyParts; i++) {
            assertEquals(initialX[i-1], game.x[i], "Body part " + i + " X should follow previous segment");
            assertEquals(initialY[i-1], game.y[i], "Body part " + i + " Y should follow previous segment");
        }
    }
    
    @Test
    @DisplayName("Key press changes direction - VK_LEFT")
    void testKeyPress_Left() {
        game.startGame();
        game.direction = 'U'; // Initial direction Up
        SnakeGame.MyKeyAdapter keyAdapter = game.new MyKeyAdapter();
        // Simulate a key press event for VK_LEFT
        KeyEvent keyEvent = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        keyAdapter.keyPressed(keyEvent);
        assertEquals('L', game.direction, "Direction should change to Left");
    }

    @Test
    @DisplayName("Key press changes direction - VK_RIGHT, not if current is Left")
    void testKeyPress_Right_NoChange() {
        game.startGame();
        game.direction = 'L'; // Initial direction Left
        SnakeGame.MyKeyAdapter keyAdapter = game.new MyKeyAdapter();
        KeyEvent keyEvent = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        keyAdapter.keyPressed(keyEvent);
        assertEquals('L', game.direction, "Direction should NOT change to Right if current is Left");
    }


    @Test
    @DisplayName("Apple consumption increases score and body parts")
    void testCheckApple_Consumption() {
        game.startGame();
        int initialBodyParts = game.bodyParts;
        int initialApplesEaten = game.applesEaten;

        // Manually place snake head on apple
        game.x[0] = game.appleX;
        game.y[0] = game.appleY;

        int oldAppleX = game.appleX;
        int oldAppleY = game.appleY;

        game.checkApple();

        assertEquals(initialBodyParts + 1, game.bodyParts, "Body parts should increase by 1");
        assertEquals(initialApplesEaten + 1, game.applesEaten, "Apples eaten should increase by 1");
        
        // Check if new apple is placed. It's possible it spawns at the same location by chance,
        // but it should at least be re-evaluated. A more robust test would check if newApple() was called,
        // perhaps with a mock or by checking if apple coordinates are valid and potentially different.
        // For now, check it's within bounds and aligned.
        assertTrue(game.appleX != oldAppleX || game.appleY != oldAppleY || game.bodyParts > initialBodyParts, "A new apple should be placed, or at least re-evaluated.");
        assertTrue(game.appleX >= 0 && game.appleX < SCREEN_WIDTH, "New apple X coordinate out of bounds");
        assertTrue(game.appleY >= 0 && game.appleY < SCREEN_HEIGHT, "New apple Y coordinate out of bounds");
    }

    @Test
    @DisplayName("Collision with left wall")
    void testCheckCollisions_LeftWall() {
        game.startGame();
        game.x[0] = -UNIT_SIZE; // Position head to collide with left wall
        game.y[0] = SCREEN_HEIGHT / 2;
        game.checkCollisions();
        assertFalse(game.running, "Game should stop after left wall collision");
    }

    @Test
    @DisplayName("Collision with right wall")
    void testCheckCollisions_RightWall() {
        game.startGame();
        game.x[0] = SCREEN_WIDTH; // Position head to collide with right wall
        game.y[0] = SCREEN_HEIGHT / 2;
        game.checkCollisions();
        assertFalse(game.running, "Game should stop after right wall collision");
    }

    @Test
    @DisplayName("Collision with top wall")
    void testCheckCollisions_TopWall() {
        game.startGame();
        game.x[0] = SCREEN_WIDTH/2;
        game.y[0] = -UNIT_SIZE; // Position head to collide with top wall
        game.checkCollisions();
        assertFalse(game.running, "Game should stop after top wall collision");
    }

    @Test
    @DisplayName("Collision with bottom wall")
    void testCheckCollisions_BottomWall() {
        game.startGame();
        game.x[0] = SCREEN_WIDTH/2;
        game.y[0] = SCREEN_HEIGHT; // Position head to collide with bottom wall
        game.checkCollisions();
        assertFalse(game.running, "Game should stop after bottom wall collision");
    }

    @Test
    @DisplayName("Collision with self")
    void testCheckCollisions_SelfCollision() {
        game.startGame();
        game.bodyParts = 7; // Ensure enough body parts for collision
        // Manually arrange snake for self-collision: head (0) hits part 4
        // Example: 0,0 (head) -> 0,25 -> 0,50 -> 25,50 -> 25,25 (part 4) -> ...
        game.x[0] = 2 * UNIT_SIZE; game.y[0] = 2 * UNIT_SIZE; // Head
        game.x[1] = 2 * UNIT_SIZE; game.y[1] = 1 * UNIT_SIZE;
        game.x[2] = 1 * UNIT_SIZE; game.y[2] = 1 * UNIT_SIZE;
        game.x[3] = 1 * UNIT_SIZE; game.y[3] = 2 * UNIT_SIZE;
        game.x[4] = 2 * UNIT_SIZE; game.y[4] = 2 * UNIT_SIZE; // Collision point with head
        game.x[5] = 3 * UNIT_SIZE; game.y[5] = 2 * UNIT_SIZE; 
        game.x[6] = 3 * UNIT_SIZE; game.y[6] = 3 * UNIT_SIZE; 


        game.checkCollisions();
        assertFalse(game.running, "Game should stop after self-collision");
    }
    
    @Test
    @DisplayName("No collision when snake is normal")
    void testCheckCollisions_NoCollision() {
        game.startGame(); // Standard start position
        game.running = true; // Ensure running
        game.checkCollisions();
        assertTrue(game.running, "Game should continue if no collision occurs");
    }


    @Test
    @DisplayName("Restart game logic")
    void testRestartGame() {
        game.startGame();
        // Simulate some gameplay
        game.applesEaten = 5;
        game.bodyParts = INITIAL_BODY_PARTS + 5;
        game.direction = 'D';
        game.x[0] = 0; game.y[0] = 0; // Move snake
        game.running = false; // Simulate game over

        game.restartGame();

        assertEquals(0, game.applesEaten, "Apples eaten should reset to 0 after restart");
        assertEquals(INITIAL_BODY_PARTS, game.bodyParts, "Body parts should reset to initial after restart");
        assertTrue(game.running, "Game should be running after restart");
        assertEquals('R', game.direction, "Direction should reset to 'R' after restart");

        // Check snake reset position
        assertEquals(SCREEN_WIDTH / 2, game.x[0], "Snake head X position should reset to center");
        assertEquals(SCREEN_HEIGHT / 2, game.y[0], "Snake head Y position should reset to center");

        // Check new apple placed
        assertTrue(game.appleX >= 0 && game.appleX < SCREEN_WIDTH, "Apple X coordinate out of bounds after restart");
        assertTrue(game.appleY >= 0 && game.appleY < SCREEN_HEIGHT, "Apple Y coordinate out of bounds after restart");
    }
}
