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
      testQueueController.queue(1, 0);
      Aircraft result = testQueueController.aircraftList.getFirst();
      
      assertEquals(Aircraft.Priority.VIP, result.getPriority());
      assertEquals(Aircraft.Size.LARGE, result.getSize());
   }

}
