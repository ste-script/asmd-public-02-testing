package devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BaseStandardDeviceTest {

    private Device device;
    @Mock
    private FailingPolicy policy;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup() {
        device = new StandardDevice(policy);
    }

    @Test
    void testConstructor() {
        assertThrows(NullPointerException.class, () -> new StandardDevice(null));
    }

    @Test
    void isOffAtCreation() {
        assertFalse(device.isOn());
    }

    @Test
    void throwsWhenPolicyCannotBeTurnedOn() {
        when(policy.attemptOn()).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> device.on());
    }

    @Test
    void turnsOnWhenPolicyPassed() {
        when(policy.attemptOn()).thenReturn(true);
        device.on();
        assertTrue(device.isOn());
    }

    @Test
    void turnsOffAfterTurningOn() {
        when(policy.attemptOn()).thenReturn(true);
        device.on();
        device.off();
        assertFalse(device.isOn());
    }

    @Test
    void resetTurnsOffAfterTurningOn() {
        when(policy.attemptOn()).thenReturn(true);
        device.on();
        device.reset();
        assertFalse(device.isOn());
    }

    @Test
    void stringGeneration() {
        assertFalse(device.toString().isEmpty());
    }

}
