package aircraft;

public class Aircraft implements Comparable<Aircraft> {

   public enum Priority {
      EMERGENCY, VIP, PASSENGER, CARGO
   }

   public enum Size {
      LARGE, SMALL
   }

   private Priority priority;
   private Size size;

   public Aircraft(Priority priority, Size size) {
      this.setSize(size);
      this.setPriority(priority);
   }

   public void setSize(Size size) {
      this.size = size;
   }

   public Size getSize() {
      return size;
   }

   public Priority getPriority() {
      return priority;
   }

   public void setPriority(Priority priority) {
      this.priority = priority;
   }

   @Override
   public int compareTo(Aircraft o) {
      
      // This method handles our prioritizing
      // Rules:
      //   a. VIP aircraft has precedence over all other ACs except Emergency.
      //      Emergency aircraft has highest priority.
      //   b. Passenger AC’s have removal precedence over Cargo AC’s.
      //   c. Large AC’s of a given type have removal precedence over Small AC’s
      //      of the same type.
      //   d. Earlier enqueued AC’s of a given type and size have precedence over
      //      later enqueued AC’s of the same type and size.
      
      // Reasons why this satisfies the requirements:
      //   "Higher" priority values get pushed back in the list
      //   "Smaller" aircraft get pushed back in the list
      //   Collections.sort() is a STABLE SORT, so older enqueued aircraft retain priority
      int priorityComparison = this.getPriority().compareTo(o.getPriority());
      if (priorityComparison == 0) {
         return this.getSize().compareTo(o.getSize());
      } else {
         return priorityComparison;
      }
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((priority == null) ? 0 : priority.hashCode());
      result = prime * result + ((size == null) ? 0 : size.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Aircraft other = (Aircraft) obj;
      if (priority != other.priority)
         return false;
      if (size != other.size)
         return false;
      return true;
   }

}
