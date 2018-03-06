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

   @Test
   public void testRuleA() {
      // a. VIP aircraft has precedence over all other ACs except Emergency.
      // Emergency aircraft has highest priority.

      // add a VIP then EMERGENCY
      testQueueController.queue(1, 0);
      testQueueController.queue(0, 0);

      testQueueController.dequeue();

      // test that dequeue removed VIP
      assertEquals(Aircraft.Priority.EMERGENCY, testQueueController.aircraftList.pop().getPriority());

   }

   @Test
   public void testRuleB() {
      // b. Passenger AC’s have removal precedence over Cargo AC’s.

      // add a cargo then add a passenger
      testQueueController.queue(3, 0);
      testQueueController.queue(2, 0);

      testQueueController.dequeue();

      // test that dequeue removed passenger
      assertEquals(Aircraft.Priority.PASSENGER, testQueueController.aircraftList.pop().getPriority());
   }

   @Test
   public void testRuleC() {
      // c. Large AC’s of a given type have removal precedence over Small AC’s
      // of the same type.

      // add a small then add a large
      testQueueController.queue(3, 1);
      testQueueController.queue(3, 0);

      testQueueController.dequeue();

      // test that dequeue removed small
      assertEquals(Aircraft.Size.LARGE, testQueueController.aircraftList.pop().getSize());
   }

   @Test
   public void testRuleD() {
      // d. Earlier enqueued AC’s of a given type and size have precedence over
      // later enqueued AC’s of the same type and size.

      // add an element then save a ref to it
      testQueueController.queue(3, 0);
      Aircraft testRef = testQueueController.aircraftList.getFirst();

      testQueueController.queue(3, 0);

      testQueueController.dequeue();

      // test that dequeue removed small
      assertEquals(testRef, testQueueController.aircraftList.pop());
   }

}
