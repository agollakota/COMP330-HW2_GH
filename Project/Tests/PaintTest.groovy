import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PaintTest extends GroovyTestCase {
    void testPaintComponent() {
        PaintTest paintTest = new PaintTest();
        paintTest.testClear();
        paintTest.testBlack();
        assertEquals(paintTest.testBlue(),"Blue");
        assertEquals(paintTest.testGreen(),"Green");

    }

    void testClear() {
        System.out.println("cleared");
    }

    void testRed() {
        System.out.println("Red");
    }

    void testBlack() {
        System.out.println("Black");
    }


    void testYellow() {
        System.out.println("Yellow");
    }

    void testGreen() {
        System.out.println("Green");

    }

    void testBlue() {
        System.out.println("Blue");
    }

    void testWhite() {
        System.out.println("White");
    }
}
