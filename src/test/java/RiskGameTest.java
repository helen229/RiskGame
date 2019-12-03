import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RiskGameTest {

    @Test
    public void testMain() {
        String[] args = {"start","old","game"};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RiskGame.main(args);;
        });
    }
}
