package devices;

import java.util.Random;

public class RandomFailing implements FailingPolicy {
    private final Random random;
    private boolean failed = false;

    public RandomFailing() {
        this.random = new Random();
    }

    public RandomFailing(Random random) {
        this.random = random;
    }

    @Override
    public boolean attemptOn() {
        this.failed = this.failed || random.nextBoolean();
        return !this.failed;
    }

    @Override
    public void reset() {
        this.failed = false;
    }

    @Override
    public String policyName() {
        return "random";
    }
}
