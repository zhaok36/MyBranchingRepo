public class QueueDemo
{
   public static void main(String[] args)
   {
      CircularArrayQueue q = new CircularArrayQueue();

      if (true){
			System.out.println("True");
  	  }

      System.out.println("ADD");
      q.add("Hi");
      q.add("Friday");
      System.out.println(q);

      System.out.println("ADD FIRST");
      q.addFirst("Hayes");
      System.out.println(q);

      System.out.println("FIRST TO LAST");
      q.firstToLast();
      System.out.println(q);

      System.out.println("LAST TO FIRST");
      q.lastToFirst();
      System.out.println(q);

      System.out.println("REMOVE");
      q.remove();
      System.out.println(q);

      System.out.println("REMOVE LAST");
      q.removeLast();
      System.out.println(q);


   }
}
