package ru.vyukov.bakapa.controller.controller.pub;

import org.junit.Assert;
import org.junit.Test;
import ru.vyukov.bakapa.controller.Greeting;

public class DetectionConfigControllerTest {
    DetectionConfigController detectionConfigController = new DetectionConfigController();

    @Test
    public void testGetCredentials() throws Exception {
        Greeting result = detectionConfigController.getCredentials(null, new Greeting("content"));
        Assert.assertEquals(new Greeting("content"), result);
    }
}