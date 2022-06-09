# The ***SOLID*** Principle

## What is SOLID?

The **SOLID** principles are a time-tested rubric for creating quality software, intended to make object-oriented
designs more understandable, flexible, and maintainable.

As a whole, the **SOLID** principles make arguments for how code should be split up, which parts should be internal or
exposed, and how code should use other code.

---

### The SOLID ideas are

+ **The** [**single-responsibility principle:**](https://en.wikipedia.org/wiki/Single-responsibility_principle)
  "There should never be more than one reason for a class to change." In other words, every class should have only one
  responsibility.
+ **The** [**open-closed principle:**](https://en.wikipedia.org/wiki/Open-closed_principle)
  "Software <del>entities</del> interfaces should be open for extension, but closed for modification."
+ **The** [**liskov substitution principle:**](https://en.wikipedia.org/wiki/Liskov_substitution_principle)
  "Functions that use pointers or references tp base classes must be able to use object of derived classes without
  knowing it." e.g. If S is a subtype of T, then objects of type T may be replaced with objects of type S without
  altering the correctness of the program.
+ **The** [**interface segregation principle:**](https://en.wikipedia.org/wiki/Interface_segregation_principle)
  "Many client-specific interfaces are better than one general-purpose interface."
+ **The** [**dependency inversion principle:**](https://en.wikipedia.org/wiki/Dependency_inversion_principle)
  "Depend upon abstractions, not concretions."
  High-level modules should not depend on low-level modules. Both should depend on abstractions.

---

## "Modern" SOLID

The industry has changed quit a bit now.<br>
**Dynamically-typed languages** such as Python, Ruby, and especially JavaScript have become just as popular as
Java. <br> **Non-object-oriented paradigms**, most notably functional programming(FP)
are also more common in these new languages.<br>
Many of the things that SOLID really cared about - such as classes and interfaces, data hiding, and polymorphism - are
no longer things that programmers deal with every day.

---

### Single Responsibility principle

**Original definition:** *"There should never be more than one reason for a class to change."*

If your write a class with many concerns, or "reasons to change", then you need to change the same code whenever any of
those concerns has to change. This increases the likelihood that a change to one feature will accidentally break a
different feature.

*Don't*

```java
class AllInOne {
  public void saveUserDetails(User user) {
    // save user details
  }

  public void performOrder(Order order) {
    // perform order
  }

  public void shipItem(Item item, String address) {
    // ship item
  }
}
```

**New definition:** *"Each module should do one thing and do it well."*

This could also apply in microservice design; if you have a single service that handles all three of these functions,
it's trying to do too much.

### Open-closed principle

**Original definition:** *"Software <del>entities</del> contracts should be open for extension, but closed for
modification."*

One reason for making things "open for extension" is to limit the dependency on the author of the class - if you need a
change to the class, you'd constantly need to ask the author to make the change for you, or dive into it to change it
yourself.

The reason for closing classes for modification is to eliminate downstream consumer dependencies. Who might not
understand all the "private" code we use to get our features working, and we want to protect it from unskilled hands.

```java
class Notifier {
  public void notify(String message) {
    // send an e-mail
  }
}

class LoggingNotifier extends Notifier {
  public void notify(String message) {
    super.notify(message);
    // also log the message
  }
}
```

**New definition:** *"You should be able to use and add to a module without rewriting it."*

Here's an example in *functional programming*, where not only before and after hooks are allowed, but also the base
implementation can be overridden by passing a function to your function:

```javaScript
const saveRecord = (record, save, beforeSave, afterSave) => {
    const defaultSave = (record) => {
        // default save functionality
    }

    if (beforeSave) beforeSave(record);
    if (save) {
        save(record);
    } else {
        defaultSave(record);
    }
    if (afterSave) afterSave(record);
}

const customsave = (record) => {/*...;*/
}
saverecord(myRecord, customSave);
```

### Liskov substitution principle

**Original definition** *"If* **S** *is a subtype of* **T**, *then objects of type* **T** *may be replaced with objects
of type*
**S** *without altering the correctness of the program."*

This is a basic attribute of OO languages. It means that you should be able to use any subclass in place of their parent
class . This allows for confidence in your contract - you can safely depend on any object that "is a" type T to continue
to behave like a T. Here it is in practice:

```java
class Vehicle {
  public int getNumberOfWheels() {
    return 4;
  }

  class Bicycle extends Vehicle {
    public int getNumberOfWheels() {
      return 2;
    }
  }

  // calling code
  public static int COST_PER_TIRE = 50;

  public int tireCost(Vehicle vehicle) {
    return COST_PER_TIRE * vehicle.getNumberOfWheels();
  }

  Bicycle bicycle = new Bicycle();
  System.out.println(

  tireCost(bicycle)); // 100
```

**New definition:** *"You should be able to substitute one thing for another if those things are declared to behave the
same way."*

In dynamic languages, the important thing to take from this is that if your program "promises" to do something (such as
implement an interface or a function), you need to keep to your promise and not surprise your clients.

````javascript
const isEven = (number) => x % 2 == 0;
const isOdd = (x) => x % 2 != 0;

const printFiltered = (arr, filreFunc) => {
    aff.foreach((item) => {
        if (filerFunc(item)) {
            console.log(item);
        }
    })
}

const array = [1, 2, 3, 4, 5, 6];
printFiltered(array, isEven);
printFiltered(array, isOdd);
````

### Interface segregation principle

**Original definition:** *"Many client-specific interfaces are better than one general-purpose interface."*

In OO, you can think of this as providing a "view" into your class. Rather than giving your full implementation to all
your clients, you create interface on top of the with just the methods relevant to that client, and ask your clients to
use those interfaces.

As with the single responsibility principle, this decreases coupling between systems, and ensures that a client doesn't
need to know about, or depend on, features that it has no intentions of using.

Here's an example that passes the SRP test:

```java
class PrintRequest {
  public void createRequest() {
  }

  public void deleteRequest() {
  }

  public void worOnrequest() {
  }
}
```

This code will generally have only one "reason to change" - it's all related to print requests, which are all part of
the same domain, and all three methods will likely change the same state. However, it's not likely that the same client
that's creating requests is the one that's working on request. It makes more sense to separate these interfaces out:

```java
interface PrintRequestModifier {
  public void createRequest();

  public void deleteRequest();
}

interface PrintRequestWorker {
  public void workOnRequest();
}

class PrintRequest implements PrintRequestModiifer, PrintRequestWorker {
  public void createRequest() {
  }

  public void deleteRequest() {
  }

  public void workOnRequest() {
  }
}
```

**New definition:** *"Don't show your clients more than they need to see"*

In the microservice world, you can either use documentation or true separation to enforce clarity. For example, your
external customers may only be able to log in as a user, but your internal services might need to get lists of users or
additional attributes. You could either create a separate “external only” user service that calls your main service, or
you could output specific documentation just for external users that hides the internal routes.

### Dependency inversion principle

**Original definition:** *"Depend upon abstractions, not concretions."*

In OO, this means that clients should depend on interfaces rather than concrete classes as much as possible. This
ensures that code is relying on the smallest possible surface area - in fact, it doesn't depend on code at all, just a
contract defining how taht code should behave. As with other principles, this reduces the risk of a breakage in one
place causing breakage elsewhere accidentally. Here's a simplified example.

```java
interface Logger {
  public void write(String message);
}

class FileLogger implements Logger {
  public void write(String message) {
    // write to file
  }
}

class StandardOutLogger implements Logger {
  public void write(String message) {
    // write to standard out
  }
}

  public void doStuff(Logger logger) {
    // do stuff
    logger.write("some message");
  }
```

If you’re writing code that needs a logger, you don’t want to limit yourself to writing to file, because you don’t care.
You just call the write method and let the concrete class sort it out.

**New definition:** *"Depend upon abstractions, not concretions."*

The idea of keeping things abstract where possible is still an important one, even if the mechanism of abstracting in
modern code is not as strong as it is in a strict OO world.

Practically, this is almost identical to the Liskov substitution principle discussed above. The main difference is that
here, there is no default implementation. Because of this, the discussion involving duck typing and hook functions in
that section equally applies to dependency inversion.

You can also apply abstraction to the microservice world. For example, you can replace direct communication between
services with a message bus or queue platform such as Kafka or RabbitMQ. Doing this allows the services to send messages
to a single generic place, without caring which specific service will pick those messages up and perform its task.

#### To restate "modern SOLID" one more time:

+ Don't surprise the people who read your code.
+ Don't surprise the people who use your code.
+ Don't overwhelm the people who read your code.
+ Use sane boundaries for your code.
+ Use the right level of coupling - keep things together that belong together, and keep them apart if they belong apart.



[*source*](https://stackoverflow.blog/2021/11/01/why-solid-principles-are-still-the-foundation-for-modern-software-architecture/)
