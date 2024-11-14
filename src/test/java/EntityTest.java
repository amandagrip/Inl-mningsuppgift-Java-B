import org.junit.jupiter.api.Test;
import se.amanda.game.model.Burglar;
import se.amanda.game.model.Resident;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    public void testPunchAndHealth() {
        Resident resident = new Resident("Boende", 12, 3);
        Burglar burglar = new Burglar("Inbrottstjuv", 12, 4);

        int health = resident.getHealth();

        burglar.Punch(resident);

        assertEquals(health - burglar.getDamage(), resident.getHealth());
    }
}
