import org.example.RussianNaziDetector;
import org.junit.Assert;
import org.junit.Test;

public class RussianNaziDetectorTest {

    @Test
    public void analyzeTest() {
        String russianString = "Эх, Севастьян, не узнал своих крестьян.";
        String notRussianString = "Лисий кінь — краса, лисий парубок — біда.";

        Assert.assertTrue(RussianNaziDetector.getRussianNaziDetector().analyze(russianString));
        Assert.assertFalse(RussianNaziDetector.getRussianNaziDetector().analyze(notRussianString));
    }
}
