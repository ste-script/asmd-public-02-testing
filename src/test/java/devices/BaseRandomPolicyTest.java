package devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Random;


public class BaseRandomPolicyTest {

    private FailingPolicy policy;
    @Mock
    Random random;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup(){
        policy = new RandomFailing(random);
    }

    @Test
    void emptyConstructor() {
        policy = new RandomFailing();
    }

    @Test
    void testAttemptOnWork()
    {
        when(random.nextBoolean()).thenReturn(false);
        assertTrue(policy.attemptOn());
        assertTrue(policy.attemptOn());
        assertTrue(policy.attemptOn());
        assertTrue(policy.attemptOn());
    }

    @Test
    void testAttemptOnFails()
    {
        when(random.nextBoolean()).thenReturn(true, false, false);
        assertFalse(policy.attemptOn());
        assertFalse(policy.attemptOn());
        assertFalse(policy.attemptOn());
    }

    @Test
    void resetFault()
    {
        when(random.nextBoolean()).thenReturn(true, false, false);
        assertFalse(policy.attemptOn());
        assertFalse(policy.attemptOn());
        policy.reset();
        assertTrue(policy.attemptOn());
    }

    @Test
    void policyName(){
        assertEquals("random", policy.policyName());
    }


}
