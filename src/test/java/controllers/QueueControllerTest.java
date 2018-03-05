package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aircraft.Aircraft;
import mockit.Tested;

public class QueueControllerTest {

   @Tested
   QueueController testQueueController;

   @Test
   public void testAddPlaneAddsPlane() {
      testQueueController.queue(55, 1);
      Aircraft result = testQueueController.aircraftList.getFirst();
      assertEquals(55, result.getId());
      assertEquals(Aircraft.Priority.VIP, result.getPriority());
   }

}
