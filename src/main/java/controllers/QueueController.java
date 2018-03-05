package controllers;

import java.util.Collections;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aircraft.Aircraft;

@RestController
public class QueueController {

   private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

   LinkedList<Aircraft> aircraftList = new LinkedList<>();

   @RequestMapping(value = "/queue", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public synchronized ResponseEntity<String> queue(@RequestParam(value = "priority", required = true) int aPriority,
         @RequestParam(value = "size", required = true) int aSize) {
      Aircraft.Priority priority = Aircraft.Priority.values()[aPriority];
      Aircraft.Size size = Aircraft.Size.values()[aSize];
      logger.info("Received aircraft queueing request priority {}, size {}", priority, size);

      // add the aircraft then sort to maintain priority
      aircraftList.add(new Aircraft(priority, size));
      Collections.sort(aircraftList);

      return ResponseEntity.status(HttpStatus.OK).body(jsonPair("List size", Integer.toString(aircraftList.size())));

   }

   @RequestMapping("/dequeue")
   @ResponseBody
   public synchronized ResponseEntity<String> dequeue() {

      logger.info("Received request to dequeue an aircraft...");

      Aircraft removed = aircraftList.removeLast();

      ResponseEntity<String> response;

      if (removed == null) {
         String message = "List is empty";
         logger.info(message);
         response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonPair("Error", message));
      } else {
         String message = String.format("Removed aircraft queueing request priority %s, size %s", removed.getPriority(),
               removed.getSize());
         logger.info(message);
         response = ResponseEntity.status(HttpStatus.OK).body(jsonPair("Success", message));
      }

      return response;
   }

   @RequestMapping("/list")
   @ResponseBody
   public ResponseEntity<String> list() {

      logger.info("Received request to print list of aircraft...");

      ResponseEntity<String> response;

      if (aircraftList.isEmpty()) {
         String message = "No aircraft in list";
         logger.info(message);
         response = ResponseEntity.status(HttpStatus.OK).body(jsonPair("Error", message));
      } else {
         logger.info("Response: " + aircraftList.toString());
         response = ResponseEntity.status(HttpStatus.OK).body(jsonPair("Success", aircraftList.toString()));
      }

      return response;
   }

   public String jsonPair(String key, String value) {
      return "{\"" + key + "\":\"" + value + "\"}";
   }

}
