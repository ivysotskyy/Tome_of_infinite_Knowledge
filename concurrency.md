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

  <summary><u>Expand</u></summary>

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

<summary> <u>Expand</u> </summary>

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

  <summary><u>Expand</u></summary>

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

[source](https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html)
