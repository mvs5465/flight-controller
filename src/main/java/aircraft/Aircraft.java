package aircraft;

public class Aircraft implements Comparable<Aircraft> {

   public enum Priority {
      EMERGENCY, VIP, PASSENGER, CARGO
   }

   private Priority priority = Priority.CARGO; // default to the lowest priority
   private int id;
   
   public Aircraft(int id) {
      this.setId(id);
   }

   public Aircraft(int id, Priority priority) {
      this.setId(id);
      this.setPriority(priority);
   }

   public Aircraft(int id, int priority) {
      this.setId(id);
      this.setPriority(Priority.values()[priority]);
   }

   public Priority getPriority() {
      return priority;
   }

   public void setPriority(Priority priority) {
      this.priority = priority;
   }
   
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Override
   public int compareTo(Aircraft o) {
      return this.getPriority().compareTo(o.getPriority());
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((priority == null) ? 0 : priority.hashCode());
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
      return true;
   }

}
