import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that generates the Fibonacci sequence in the form of a Java Iterator.
 *
 * This file demonstrates two related Java interfaces: Iterator and Iterable.
 *
 * Iterators exist because there are many different kinds of collections in Java,
 * and they might be implemented in various ways. For example a List of integers
 * could just be stored as just an Array:
 *
 *  +---+---+---+---+---+
 *  | 0 | 1 | 2 | 3 | 4 |
 *  +---+---+---+---+---+
 *
 *  But there is another kind of list called a "Linked List". In a linked list of integers
 *  every element is an object with a "value" field that contains the actual integer
 *  value and a "next" field that is a reference to the next element in the list:
 *
 *  +----------+   +----------+   +----------+   +----------+   +----------+
 *  | value: 0 |   | value: 1 |   | value: 2 |   | value: 3 |   | value: 4 |
 *  |  next: ----->|  next: ----->|  next: ----->|  next: ----->|  next: -----> null
 *  +----------+   +----------+   +----------+   +----------+   +----------+
 *
 * For now let's not get into why you might want one rather than the other. The
 * important part is that both of these behave like lists. Both of them are an ordered
 * collection of values, but the code you'd need to loop over each integer in the list
 * will be different because the underlying data is structured differently.
 *
 * If we follow this way of doing things, you end up needing to know a lot about how
 * every kind of collection is implemented and write code specific for that collection
 * type when looping over it. But that doesn't make a lot of sense. All you really ought 
 * to have to know about is, "I have a bucket of things and I want to do something with 
 * each thing in the bucket."
 *
 * Iterators are what implements that general concept in a consistent way for any
 * collection type. It's the iterator classes that know the difference between how an
 * array list and a linked list work. The most basic Iterator implementation provides two
 * operations (there are a couple of others that aren't important right now):
 *
 *  1. Are there any more elements in the list? (true or false)
 *  2. Give me the next element in the list. (a value, or an exception if there isn't one)
 *
 * As long as you can get an Iterator for a collection, you can use "hasNext()" and 
 * "next()" to loop over the contents of the collection without knowing or caring how
 * the data in the collection is stored. You can even change how the data is stored later
 * without having to change any of the code that uses the collection.
 *
 * The related interface it's useful to know is "Iterable". There is only one method that's
 * really important here: if a collection implements Iterable, that means it provides an
 * "iterator()" method that returns an appropriate Iterator for looping over the collection.
 *
 * Implementing Iterable and Iterator for collections is really handy because Java has
 * a special for-loop syntax specifically for Iterable collections:
 *
 *   for(MyElement element : myCollection)
 * 
 * This uses the Iterable and Iterator methods behind the scenes to make looping over a
 * collection really easy. All the built-in Java collections already provide support for this.
 * You can add support for this to your own collection classes just by implementing the
 * interfaces.
 *
 *============================================================================================
 *
 * I'm going to do all that down below in a simple example. Rather than deal with an actual
 * collection like a list, this is going to be an iterator that generates the Fibonacci
 * sequence. This also demonstrates how ANYTHING you can loop over can be Iterable.
 *
 * The Fibonacci sequence is produced by starting with [0, 1] and then each subsequent
 * number in the sequence is produced by adding the previous two numbers in the sequence.
 * So the output sequence will be [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...]
 *
 * Of course the Fibonacci sequence is infinite, so our Iterator class will define a
 * maximum value, so we know where to stop.
 *
 * Because this is a simplified example, I'm not implementing a separate collection and 
 * Iterator class. The Fibonacci class simply IS an Iterator that generates the sequence.
 * It also will directly implement Iterable so we can demonstrate the for-loop behavior.
 */
class Fibonacci implements Iterator<Integer>, Iterable<Integer> {
  // To keep this from running forever, we keep track of a maximum value.
  Integer max = 0;

  // To produce the Fibonacci sequence, we need to remember the previous two numbers.
  // Start with null values because we need to bootstrap the first two digits in the sequence,
  // but those also count as values we need to return as part of the output.
  Integer[] lastTwo = { null, null };

  /**
   * Create a Fibonacci sequence generator object.
   *
   * @param max the largest value this generator will return.
   */
  public Fibonacci(Integer max) {
    this.max = max;
  }

  /*******************************************************************************************
   * Implementation of "Iterator" methods below here                                         *
   *******************************************************************************************/

  /**
   * Determine whether there is a next element in the sequence within the configured maximum.
   *
   * @return true if there is a next element lower than this.max
   */
  public boolean hasNext() {
    return (

        // These are the bootstrap cases, to return 0 and 1.
        (this.lastTwo[0] == null && this.max >= 0) || // We'll generate the 0 unless "max" is negative
        (this.lastTwo[1] == null && this.max >= 1) || // We'll generate the 1 unless "max" is smaller

        // This is the normal case, once we already have two previous numbers to add.
        (this.max >= (this.lastTwo[0] + this.lastTwo[1])) // We'll generate the sum unless it's bigger than "max"

    );
  }

  /**
   * Get the next value from the Fibonacci sequence up to the configured maximum.
   *
   * @return the next value in the sequence (if available)
   * @throws NoSuchElementException if the next element would be larger than this.max
   */
  public Integer next() throws NoSuchElementException {
    // First use our existing "hasNext" test and throw the required exception if
    // the next digit would be too large. This way we don't change anything in the
    // case that there's no next integer.
    if (!this.hasNext()) throw new NoSuchElementException();

    // Now we know there is a next integer we can generate. We just need to
    // calculate the appropriate one to return.
    if (this.lastTwo[0] == null) {
      // We have not yet generated the leading 0, so do that first.
      this.lastTwo[0] = 0;
      return 0;
    } else if (this.lastTwo[1] == null) {
      // We have generated the leading 0, but not the first 1, so do that next.
      this.lastTwo[1] = 1;
      return 1;
    } else {
      // This is the normal case. We have two previous numbers, so add them together,
      // make the resulting sum the new most-recent Integer, and then return it as the next value.
      int sum = this.lastTwo[0] + this.lastTwo[1];  // Add together the previous two.
      this.lastTwo[0] = this.lastTwo[1];            // Update the previous-numbers array.
      this.lastTwo[1] = sum;
      return sum;                                   // return the new value.
    }
  }


  /*******************************************************************************************
   * Implementation of "Iterable" methods below here                                         *
   *******************************************************************************************/

  /**
   * Since Fibonacci is already its own Iterator, we can make it Iterable easily by just returning 
   * itself. The for-loop syntax won't work without the "iterator()" method.
   *
   * @return an iterator for the Fibonacci sequence
   */
  public Iterator<Integer> iterator() {
    return this;
  }


  /*******************************************************************************************
   * Implementation of "main" method below here                                              *
   *******************************************************************************************/

  public static void main(String[] args) {
    Fibonacci fib = new Fibonacci(100);
    System.out.println("Generating the Fibonacci sequence using the Iterator interface directly...");
    while(fib.hasNext()) {
      System.out.println(String.format("Next Digit: %d", fib.next()));
    }


    fib = new Fibonacci(100);
    System.out.println("Now doing the same thing using the Iterable for-loop behavior...");
    for(Integer i : fib) {
      System.out.println(String.format("Next Digit: %d", i));
    }
  }
}

