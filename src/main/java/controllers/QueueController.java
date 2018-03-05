package controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aircraft.Aircraft;

@RestController
public class QueueController {

   private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

   LinkedList<Aircraft> aircraftList = new LinkedList<>();

   @RequestMapping("/addAircraft/{name}/{pr}")
   public synchronized void queue(@PathVariable("name") int name, @PathVariable("pr") int pr) {
      logger.info("Received queueing request, aircraft {}, priority {}", name, pr);
      // add the aircraft then sort to maintain priority
      aircraftList.add(new Aircraft(name, pr));
      Collections.sort(aircraftList);
   }

   @RequestMapping("/removeAircraft/{name}")
   public synchronized void dequeue(@PathVariable("name") int name) throws IOException {
      logger.info("Received removal request, aircraft {}", name);

      for (Iterator<Aircraft> it = aircraftList.iterator(); it.hasNext();) {
         if (it.next().getId() == name) {

            it.remove();
            logger.info("Removed aircraft {} from list", name);
            return;
         }
      }

      logger.error("Aircraft not found in list");
      throw new IOException("Aircraft not found in list");
   }

}
