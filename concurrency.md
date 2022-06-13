<h1> Java concurrency </h1>

---
<details>
  <summary> <b><u>Introduction</u></b> </summary>
<p>
  Computer users take it for granted that their systems can do more than one thing at a time. They assume that they can
  continue to work in a word processor, while other applications download files, manage the print queue, and stream audio.
  Even a single application is often expected to do more than one thing at a time. For example, that streaming audio
  application must simultaneously read the digital audio off the network , decompress ir, manage playback, and update its
  display. Even the word processor should always be ready to respond to keyboard and mouse event, no matter how busy it is
  reformatting text or updating the display. Software that can do such things is known as concurrent software.
</p>
<p>
  The Java platform is designed from the ground up to support concurrent programming, with basic concurrency support in
  the Java programming language and the Java class libraries. Since version 5.0, the Java platform has also included
  high-level concurrency APIs. This lesson introduces the platform's basic concurrency support and summarizes some of the
  high-level APIs in the <code>java.util.concurrent</code> packages.
</p>
</details>

---

<h2>Processes and Threads</h2>
<details>

  <summary><b>Expand</b></summary>

<p>
  In concurrent programming, there are two basic units of execution: <b>processes</b> and <b>threads</b>
  Int the Java programming language, concurrent programming is mostly concerned with threads. However, processes are 
  also important.
</p>
<p>
  It's becoming more and more common for computer systems to have multiple processors or processors with multiple
  execution cores. This greatly enhances a system's capacity for concurrent execution of processes
  and threads - but concurrency is possible even on simple systems, without multiple processors or execution cores.
</p>
<H3>Processes</H3>
<p>
  A <b>process</b> has a self-contained execution environment. A process generally has a complete, private set of basic 
  <i>run-time resources</i> ; in particular, each process has its own memory space.
</p>
<p>
  <b>Processes</b> are often seen as synonymous with programs or applications. However, what the user sees as a single
  application may in fact be a set of cooperating processes. To facilitate communication between processes, most operating
  systems support <i>Inter Process Communication</i> (IPC) resources, such as <b>pipes</b> and <b>sockets</b>. IPC is used not just
  for communication between processes on the same system, but processes on different systems.
</p>
<p>
  Most implementations of the Java virtual machine run as a single process. A java application can create additional
  processes using a <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html">ProcessBuilder</a>
  object. <br>
</p>
<h3>Threads</h3>
<p>
  <b>Threads</b> are sometimes called <i>lightweight processes</i>. Both <b>processes</b> and <b>threads</b> provide an execution environment,
  but creating a new thread requires fewer resources than creating a new process. <b>Threads</b> exist within a process
  - every process has at least one. <b>Threads</b> share the process's resources, including memory and open files. This makes for
  efficient, but potentially problematic, communication.
</p>
<p>
  Multithreaded execution is an essential feature of the Java platform. Every application has at least one thread - or
  several, if you count "system" threads that do things like memory management and signal handling. But from the application
  programmer's point of view, you start with just one thread, called the main thread. This thread has the ability to 
  create additional thread.
</p>
</details>

---

<h2>Thread Objects</h2>

<details>

<summary><b>Expand</b></summary>

<p>
  Each thread is associated with an instance of the class <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html">Thread</a>.
  There are two basic strategies for using <code>Thread</code> objects to create a concurrent application.
</p>

+ <p>
    To directly control thread creation and management, simply instantiate <code>Thread</code> each time the application needs
    to initiate an asynchronous task.
  </p>
+ <p>
    To abstract thread management from the rest of your application, pass the application's task to an <i>executor</i> task.
  </p>

<p>
  This section documents the use of Thread objects. Executors are discussed with other
  <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/highlevel.html">high-level concurrency objects</a>.
</p>
<h3>Defining and starting a Thread</h3>
<p>
  An application that creates an instance of <code>Thread</code> must provide the code that will run in that thread.
  There are two ways to do this:
</p>

+ Provide a ``Runnable`` object. The <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html">
  Runnable</a>
  interface defines a single method, ``run()``, meant to contain the code executed in the thread. The ``Runnable``
  object is passed to the ``Thread`` constructor, as in the Example:

```java
public class HelloRunnable implements Runnable {

  public void run() {
    System.out.println("Hello from a thread!");
  }

  public static void main(String args[]) {
    (new Thread(new HelloRunnable())).start();
  }

}
```

+ Subclass ``Thread``. The ``Thread`` class itself implements ``Runnable``, though its ``run()`` method does nothing. An
  application can subclass ``Thread``, providing its own implementation of ``run()``, as in the Example:

```java
 public class HelloThread extends Thread {

  public void run() {
    System.out.println("Hello from a thread!");
  }

  public static void main(String args[]) {
    (new HelloThred()).start();
  }

}
```

<p>Notice that both examples invoke <code>Thread.start()</code> in order to start the new thread.</p>
<p>
  Which of these idioms should you use? The first idiom, which employs a Runnable object, is more general, because the 
  <code>Runnable</code> object can subclass a class other than <code>Thread</code>. The second idiom is easier to use in 
  simple applications, but is limited by the fact that your task class must be a descendant of <code>Thread</code>.
</p>
<p>
  The <code>Thread</code> class defines a number of methods useful for thread management. These include <code>static</code>
  methods, which provide information about, or effect the status of, the thread invoking the method. The other methods
  are invoked from other threads involved in managing the thread and <code>Thread</code> object.
</p>

</details>

---

<h2>Pausing Execution with Sleep</h2>

<details>

  <summary><b>Expand</b></summary>

<p>
  <code>Thread.sleep()</code> causes the current thread to suspend execution for a specified period. This is an efficient
  means of making processor time available to the other threads of an applications that might be running on a computer system.
  The <code>sleep()</code> method can also be used for pacing, as shown in the example that follows, and waiting for 
  another thread with duties that are understood to have time requirements, as with the <code>SimpleThread</code> example 
  in a later section.
</p>
<p>
  Two overloaded versions of sleep are provided: one that specifies the sleep time to the millisecond and one that 
  specifies the sleep time to the nanosecond. However, these sleep times are not guaranteed to be precise, because they 
  are limited by the facilities provided by the underlying OS. Also, the sleep period can be terminated by interrupts, 
  as we'll see in a later section. In any case, you cannot assume that invoking sleep will suspend the thread for 
  precisely the time period specified.
</p>

```JAVA
  public class SleepMessages {
  public static void main(String[] args) throws InterruptedException {
    String[] importantIfo = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
    };

    for(int i = 0; i < importantInfo.lenght; i++) {
      //Pause for 4 seconds
      Thread.sleep(4000);
      //Print a message
      System.out.println(importantInfo[i]);
    }
  }
}
```

<p>
  Notice that <code>main()</code> declares that it <code>throws InterruptedException</code>. This is an exception that 
  <code>sleep</code> throws when another thread interrupts the current thread while <code>sleep</code> is active.
  Since this application has not defined another thread to cause the interrupt, it doesn't bother to catch 
  <code>InterruptedException</code>.
</p>
</details>

---

<h2>Interrupts</h2>

<details>

<summary><b>Expand</b></summary>

<p>
  An <i>Interrupt</i> is an indicator to a thread that it should stop what it is doing and do something else. It's up to the 
  programmer to decide how a thread responds to an interrupt, but it is very common for the thread to terminate. This is 
  the usage emphasized in this lesson.<br>
  A thread sends an interrupt by invoking <code>interrupt()</code> on the <code>Thread</code> object for the thread to be 
  interrupted. For the interrupt mechanism to work correctly, the interrupted thread must support its own interruption.
</p>

<h3>Support Interruption</h3>

<p>
  How does a thread support its own interruption? This depends on what it's currently doing. If the thread is frequently 
  invoking methods that throw <code>InterruptedException</code>, it simply returns from <code>run()</code> method after it 
  catches that exception. For example,<br> suppose the central message loop in the <code>SleepMessage</code> example were
  int the <code>run()</code> method of a thread's <code>Runnable</code> object. Then it might be modified as follows to support interrupts:
</p>

```java
  for(int i=0;i<importantInfo.lenght;i++){
        // Pause for 4 seconds
        try{
        Thread.sleep(4000);
        }catch(InterruptedException e){
        // W've been interrupted: no more messages.
        return;
        }
        // Print a message
        System.out.printlm(importantInfo[i]);
        }

```

<p>
  Many methods that throw <code>InterruptedException</code>, such as sleep. are disigned to cancel their current 
  operation and return immediately when an interrupt is received.<br>
  What if a thread goes a long time without invoking a method that throws <code>InterruptedException</code>? Then it must
  periodically invoke <code>Thread.interrupted()</code>, which returns <code>true</code> if an interrupt has been received.
  For example:
</p>

```java
  for(int i=0;i<inputs.length;i++){
        heavyCrunch(inputs[i]);
        if(Thread.interrupted()){
        // We'v been interrupted: no more crunching.
        return;
        }
        }
```

<p>
  In this simple example, the code simply tests for the interrupt and exits the thread if one has been received. In more 
  complex applications, it might make more sense to throw an <code>InterruptedException</code>:
</p>

```java
  if(Thread.interrupted()){
        throw new InterruptedException();
        }
```

<p>
  This allows interrupt handling code to be centralized in a <code>catch</code> clause.
</p>

<h3>The Interrupt Status Flag</h3>

<p>
  The interrupt mechanism is implemented using an internal flag known as the <i>interrupt status</i>. Invoking <code>Thread.interrupt()</code>
  sets this flag. When a thread checks for an interrupt by invoking the static method <code>Thread.interrupted()</code>, 
  interrupt status is cleared. The non-static <code>isInterrupted()</code> method, which is used by one thread to query 
  the interrupt status of another, does not change the interrupt status flag.<br> By convention, any method that exits 
  by throwing an <code>InterruptedException</code> clears interrupted status when it does so. However, it's always 
  possible that interrupt status will immediately be set again, by another thread invoking <code>interrupt()</code>.
</p>

</details>

<h2>Joins</h2>

<details>

<summary><b>Expand</b></summary>

<p>
  The <code>join()</code>  method allows one thread to wait for the completion of another. If <code>t</code> is a 
  <code>Thread</code> object whose thread is currently executing.<br>
</p>

```java
 t.join();
```

<p>
  causes the current threat to pause execution until <code>t</code>'s thread terminates. Overloads of <code>join</code>
  allow the programmer to specify a waiting period. However, as with <code>sleep()</code>, <code>join()</code> is dependent
  on the OS for timing, so you should not assume that <code>join</code> will wait exactly as long as you specify.<br>
  Like <code>sleep()</code>, <code>join()</code> responds to an interrupt by exiting with an <code>InterruptedException</code>.
</p>

</details>

---

<h2>The Simple Thread Example</h2>
<p>
  The following example brings together some of the concepts of mentioned so far. <code>SimpleThreads</code> consists of two
  threads. The first is the main thread that every Java application has. The main thread creates a new thread from the 
  <code>Runnable</code> object, <code>MessageLoop</code>, and waits for it to finish. if the <code>MessageLoop</code> 
  thread takes too long to finish, the main thread interrupts it.<br> The <code>MessageLoop</code>, thread prints out a 
  series of messages. If interrupted before it has printed all its messages, the <code>MessageLoop</code> thread prints 
  a message and exits.
</p>
<details>

<summary><b>Example</b></summary>

```java
public class SimpleThread {

  // Display a message, preceded by the name of the current thread
  static void threadMessage(String message) {
    String threadName = Thread.currentThread().getName();
    System.out.format("%s: %s%n", threadName, message);
  }

  private static class MessageLoop implements Runnable {
    public void run() {
      String[] importantInfo = {
              "Mares eat oats",
              "Does eat oats",
              "Little lambs eat ivy",
              "A kid will eat ivy too"
      };
      try {
        for(int i = 0; i < importantInfo.length; i++) {
          // Pause for 4 seconds
          Thread.sleep(4000);
          // Print a message
          threadMessage(importantInfo[i]);
        }
      }catch(InterruptedException e) {
        threadMessage("I wasn't done!");
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // Delay, in milliseconds before we interrupt MessageLoop
    // thread (default one hour).
    long patience = 1000 * 60 * 60;

    // If command line arguments present, gives patience in seconds.
    if(args.length > 0) {
      try {
        patience = Long.parseLong(args[0]) * 1000;
      }catch(NumberFormatException e) {
        System.err.println("Argument mus be an integer.");
        System.exit(1);
      }
    }

    threadMessage("Starting MessageLoop thread");
    long startTime = System.currentTimeMillis();
    Thread t = new Thread(new MessageLoop());
    t.start();

    threadMessage("Waiting for MessageLoop thread");
    // loop until MessageLoop thread exits
    while(t.isAlive()) {
      threadMessage("Still waiting...");
      // Wait maximum of 1 second for MessageLoop thread to finish.
      t.join(1000);
      if(((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
        threadMessage("Tired of waiting!");
        t.interrupt();
        // Shouldn't be long now -- wait indefinitely
        t.join();
      }
    }
    threadMessage("Finally!");
  }
}
```

</details>

---

<h2>Synchronisation</h2>
<p>
  Threads communicate primary by sharing access to fields and the objects reference fields refer to. This form of communication
  is extremely efficient, but makes two kinds of errors possible: <em>thread interference</em> and <em>memory consistency errors</em>.
  The tool needed to prevent these errors is <b>synchronisation</b>
</p>
<p>
  However, synchronisation can introduce <em>thread contention</em>, which occurs when two or more threads try to access the same
  resource simultaneously and cause the Java runtime to execute one or more threads more slowly, or even suspend their execution.
  <a href="#starvation-and-livelock"><u>Starvation and livelock</u></a> are form of thread contention. See the section 
  <a href="#liveness">Liveness</a> for more information.
</p>

<h3>Thread Interference</h3>

<details>

  <summary><b>Expand</b></summary>

<p>Consider a simple class called <code>Counter</code> </p>

```java
  class Counter {
  private int c = 0;

  public void increment() {
    c++;
  }

  public void decrement() {
    c--;
  }

  public int value() {
    return c;
  }

}
```

<p>
  <code>Counter</code> is designed so that each invocation of <code>increment()</code> will add 1 to <code>c</code>, and 
  each invocation of <code>decrement()</code> will subtract 1 from <code>c</code>. However, if a <code>Counter</code> object 
  if referenced from multiple threads, interference between thread may prevent this from happening as expected.<br>
  &nbsp;&nbsp; Interference happens when two operations, running in different threads, but acting on the same data, <i>interleave</i>.<br>
  &nbsp;&nbsp; This means that two operations of multiple steps, and the sequence of steps overlap.<br>
  It might not seem possible for operations on instance of <code>Counter</code> to interleave, since both operations on <code>c</code>
  are single, simple statements. However, even a simple statement can translate to multiple steps by the virtual machine. 
  The single expression c++ can be decomposed into three steps:
</p>

<ol>
  <li>Retrieve the current valie of <code>c</code>.</li>
  <li>Increment the retrieved value by 1.</li>
  <li>Store the incremented value back in <code>c</code>.</li>
</ol>

<p>
  The expression <code>c--;</code> can be decomposed the same way, except that the second step decrements instead of incrementing.<br>
  Suppose Thread <b>A</b> invokes <code>increment()</code> at about the same time Thread <b>B</b> invokes <code>decrement</code>.
  If the initial value of <code>c</code> is 0, their interleaved action might follow this sequence:
</p>

<ol>
  <li><b>Thread A:</b> Retrieve c.</li>
  <li><b>Thread B:</b> Retrieve c.</li>
  <li><b>Thread A:</b> Increment retrieved value; result is <u>1</u></li>
  <li><b>Thread B:</b> Decrement retrieved value; result is <u>-1</u></li>
  <li><b>Thread A:</b> Store result in c; c is now <u>1</u></li>
  <li><b>Thread A:</b> Store result in c; c is now <u>-1</u></li>
</ol>

<p>
  Thread A's result is lost, overwritten by Thread B. This particular interleaving is only one possibility. Under different
  circumstances it might be Thread B's result that gets lost, or there could be no error at all. Because they are unpredictable,
  thread interference bugs can be difficult to detect and fix.
</p>

</details>

---

<h3>Memory Consistency Errors</h3>

<details>

  <summary><b>Expand</b></summary>

<p>
  <em>Memory consistency errors</em> occur when different threads have inconsistent views of what should be the same data.
  The causes of memory consistency errors are complex and beyond the scope of this tutorial. Fortunately, the programmer does not need a
  detailed understanding of these causes. All that is needed is a strategy for avoiding them.<br>
  The key to avoiding memory consistency errors is understanding the <b>happens-before</b> relationship. 
  This relationship is simply a guarantee that memory writes by one specific statement are visible to another specific statement.
  To see this, consider the following example. Suppose a simple <code>int</code> field is defined and initialized:
</p>

```java
  int counter=0;
```

<p>The <code>counter</code> field is shared between two threads, <b>A</b> and <b>B</b>. Suppose thread <b>A</b> increments <code>counter</code>:</p>

```java
  counter++;
```

<p>Then, shortly afterwards, thread <b>B</b> prints out <code>counter</code>:</p>

```java
  System.out.println(counter);
```

<p>
  If the two statements had been executed in the same thread, it would be safe to assume that the value printed out would be
  "1". But if the two statements are executed in separate threads, the value printed out might well be "0", because there's 
  no guarantee that thread <b>A</b>'s change to <code>counter</code> will be visible to thread <b>B</b> - unless the programmer 
  has established a <b>happens-before</b> relationship between these two statements.
</p>
<p>We've already seen two actions that create <b>happens-before</b> relationships.</p>

<ul>
  <li>When a statement invokes <code>Thread.start()</code>, every statement that has a happens-before relationship with 
  that statement also has a happens-before relationship with every statement executed by the new thread. The effect of the 
  code that led up to the creation of the new thread are visible to the new thread.</li>
  <li>When a thread terminates and causes <code>Thread.join()</code> in another thread to return, then all the statements 
  executed by the terminated thread have a happens-before relationship with all the statements following the successful
  join. The effects of the code in the thread are now visible to the thread that performed the join.</li>
</ul>

<p>
  for a list of actions that create happens-before relationships, refer to the 
  <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html#MemoryVisibility">
    Summary page of the <code>java.util.concurrent</code> package
  </a>.
</p>

</details>

---

<h3>Synchronized Methods</h3>

<details>

  <summary><b>Expand</b></summary>

<p>
  The Java programming language provides two basic synchronization idioms: <b>synchronized methods</b> and <b>synchronized statements</b>.
  <br>To make a method synchronized, simply add the <code>synchronized</code> keyword to its declaration.
</p>

```java
  public class SynchronizedCounter {
  private int c = 0;

  public synchronized void increment() {
    c++;
  }

  public synchronized void decrement() {
    c--;
  }

  public synchronized int value() {
    return c;
  }
}
```

<p>If <code>count</code> is an instance of <code>SynchronizedCounter</code>, then making these methods synchronized has two effects:</p>

<ol>
  <li>It is not possible for two invocations of synchronized methods on the same object to interleave.
  When one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods
  for the same object block <em>(suspend execution)</em> until the first thread is done with the object</li>
  <li>When a synchronized method exits, it automatically establishes a <em>happens-before</em> relationship with 
  <em>any subsequent invocation</em> of a synchronized method for the same object. This guarantees that changes to the 
  state of the object are visible to all threads.</li>
</ol>

<p>
  Note that constructors cannot be synchronized - using the <code>synchronized</code> keyword with a constructor is a syntax
  error. Synchronizing constructors doesn't make sense, because only the thread that creates an object should have access 
  to it while it is being constructed.
</p>

<blockquote>
  <b>Warning:</b> When constructing an object that will be shared between threads, be very careful that a reference to 
  the object does not "leak" prematurely. For example, suppose you want to maintain a List called 
  instances containing every instance of class. You might be tempted to add the following line to your constructor:
  <br><code>instances.add(this);</code><br>
  But then other threads ca use <code>instances</code> to access the object before construction of 
  the object is complete.
</blockquote>

<p>
  Synchronized methods enable a simple strategy for preventing thread interfearence and memory consistency errors: if an 
  object is visible to more than one thread, all reads or writes to that object's variables are done through 
  <code>synchronized</code> methods. (An important exception: <code>final</code> fields, which cannot be modified after the 
  object is constructed, can be safely read through <em>non-synchronized</em> methods, once the object is constructed) 
  This strategy is effective, but can present problems with <a href="liveness">liveness</a>.
</p>
</details>

---

<h3>Intrinsic Locks and Synchronization</h3>

<details>

  <summary><b>Expand</b></summary>



</details>

-------

<h2>Liveness</h2>
<p>
  A concurrent application's ability to execute in a timely manner is known as its <b>liveness</b>. This section describes the most
  common kind of liveness problem, <b>deadlock</b>, and goes on to briefly describe two other liveness problems, 
  <b>starvation and livelock</b>.
</p>

<details>

  <summary><b>Expand</b></summary>

</details>

[source](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html)
