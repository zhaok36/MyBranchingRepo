/**
   A program that demonstrates the LinkedList class
*/
public class ListDemo
{
   public static void main(String[] args)
   {
      //create LinkedList and add to it
      LinkedList staff = new LinkedList();

      //staff.getFirst(); //produces NoSuchElementException

      staff.addFirst("Tom");
      staff.addFirst("Ron");
      staff.addFirst("Harry");
      staff.addFirst("Dom");
      System.out.println(staff.contains("Dom"));
      System.out.println(staff.contains("false"));

      /*
      // | in the comments indicates the iterator position
      ListIterator iterator = staff.listIterator(); // |HRT

      iterator.next(); //H|RT
      iterator.next(); //HR|T

      // Add more elements after second element
      iterator.add("Janet"); //HRJ|T
      System.out.println("Size: " + staff.size());
      iterator.add("Neha"); //HRJN|T
                            //doesn't know the previous is J
      System.out.println("Size: " + staff.size());

      iterator.next(); //HRJNT|

      // Remove last traversed element
      iterator.remove(); //HRJN|
                         //doesn't know the previous is J, so you can't call again
      System.out.println("Size: " + staff.size());
      /**/

      // Print all elements

      /*
      ListIterator iterator = staff.listIterator();
      while (iterator.hasNext())
      {
         System.out.print(iterator.next() + " ");
      }
      System.out.println();
      /**/
   }
}
