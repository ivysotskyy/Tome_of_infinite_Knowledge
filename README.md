# <div Style="text-align:center; color: goldenRod"> _Tome of infinite Knowledge_ </div>

---

# <span Style="color: DarkMagenta;">_Java stuff_ <3</span>
[**Important keyWords**](#keywords-dictionary)
+ **Sorting Algorithms**
  * [Selection Sort](#selection-sort)
  + [Insertion Sort](#insertion-sort)
  * [Bubble Sort](#optimized-bubblesort)
  + [Quick Sort](#quicsort)
  * [Merge Sort](#merge-sort)
  + [Counting Sort](#counting-sort)
+ **Inheritance**
  * [Abstract Classes](#abstract-classes)
  + [Type casting](#type-casting)
+ **File I/O & Exceptions**
  * [File I/O](#file-I/O)
  + [Exceptions](#exceptions)
+ **JDBC**
  * [DB Connection](#database-connection)
+ **Design Patterns** [_`link`_](https://sourcemaking.com/design_patterns)
  + [Design Patterns & refactoring](https://refactoring.guru/design-patterns)
    * [DAO](#data-acces-object)
+ **Java Functional Programming**
  * [Functional Interfaces](#functional-intefraces) [_`link`_](http://tutorials.jenkov.com/java-functional-programming/functional-interfaces.html)
  * [Pure & High order Functions](#pure--high-order-functions) [_`link`_](http://tutorials.jenkov.com/java-functional-programming/index.html)
  * [Lambda](#lambda-expressions) [_`link`_](http://tutorials.jenkov.com/java/lambda-expressions.html)
  * [Streams](#streams-in-java) [_`link`_](http://tutorials.jenkov.com/java-functional-programming/streams.html)
+ **Multithreading** [_`link`_](http://tutorials.jenkov.com/java-concurrency/index.html)
  * [Concurrency](#java-concurrency-and-multithreading-tutorial)  
  * [Multithreading](#java-concurrency-and-multithreading-tutorial)
---

# <span Style="color: DarkMagenta;">Sorting Algorithms</span>

### Selection sort

```Java
public class Sorting {
  public int[] selectionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int temp = arr[i];
      for (int j = 0; j < arr.length; j++) {
        if (temp > arr[j]) //find lowest
          temp = arr[j];
        if (temp < arr[i]) //replace
          swap(arr, i, j);
      }
    }
    return arr;
  }
}
```

> _Random array of size 101:_  
> _Selection Sort finished in: 642400 nanoseconds._  
> _Sorted array of size 101:_  
> _Selection Sort finished in: 432200 nanoseconds._

### Insertion Sort

```Java
public class Sorting {
  public int[] insertionSort(int[] arr) {
    int length = arr.length;

    for (int i = 1; i < length; i++) {
      int temp = arr[i];
      int j = i - 1;
      while ((j >= 0) && (arr[j] > temp)) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = temp;
    }
    return arr;
  }
}
```
> _Random array of size 101:_  
> _Insertion Sort finished in: 85700 nanoseconds._  
> _Sorted array of size 101:_  
> _Insertion Sort finished in: 7700 nanoseconds._


### Optimized Bubblesort
```Java
public class Sorting {
  public int[] bubbleSort(int[] arr) {
    int temp;
    boolean isSorted = false;
    for (int j = 0; j < arr.length; j++) {
      isSorted = true;
      for (int i = 0; i < arr.length - j; i++) {
        temp = arr[i];
        if (i + 1 < arr.length && arr[i + 1] < temp) {
          isSorted = false;
          arr[i] = arr[i + 1];
          arr[i + 1] = temp;
        }
      }
      if (isSorted)
        return arr;
    }
    return arr;
  }
}

```

> _Random array of size 101:_  
> _Bubble Sort finished in: 445300 nanoseconds._  
> _Sorted array of size 101:_  
> _Bubble Sort finished in: 7200 nanoseconds._
### Quicsort

> _Divide and conquer_ 
```Java
public class Sorting {
  private int partition(int[] arr, int lo, int hi) {
    int pivot = arr[lo];    //get first element as pivot to compare to
    int i = lo - 1;         //set i pointer to lo-1
    int j = hi + 1;         //set j pointer to hi+1
    while (true) {          //while i pointer is less than j pointer
      do {
        i += 1;         //find value bigger than pivot on the left side, or pivot
      } while (arr[i] < pivot);
      do {
        j -= 1;         //find value less than pivot on left side
      } while (arr[j] > pivot);
      if (i >= j) {
        return j;       //when pointers cross return
      }
      swap(arr, i, j);
    }
  }

  public int[] quickSort(int[] arr, int lo, int hi) {
    if (lo < hi) {
      int p = partition(arr, lo, hi);
      quickSort(arr, lo, p);              //recursively partition left part of arr
      quickSort(arr, p + 1, hi);      //recursively partition left part of arr
    }
    return arr;
  }
}
```
> _Random array of size 101:_  
> _Quick Sort finished in: 76900 nanoseconds._  
> _Sorted array of size 101:_  
> _Quick Sort finished in: 108100 nanoseconds._

### Merge Sort

```Java
public class Sorting {
  public int[] mergeSort(int[] arr) {
    int midPoint = arr.length / 2;
    int[] left = new int[midPoint]; // 1/2 arr always = 1/2 of the initial arr
    int[] right;                    // second 1/2
    if (arr.length < 2) {
      return arr;                 //exit recursion if arr has only 1 element
    }
    if (arr.length % 2 == 0) {
      right = new int[midPoint];  //if initial arr is even
    } else {
      right = new int[midPoint + 1]; //if initial arr is not even
    }
    /*for (int i = 0; i < left.length; i++) {
        left[i] = arr[i];               //save the first part of initial arr
    }*/
    System.arraycopy(arr, 0, left, 0, left.length);
    /*for (int i = 0; i < right.length; i++) {
        right[i] = arr[midPoint + i];   //save second part of initial arr starting from mid point
    }*/
    System.arraycopy(arr, left.length, right, 0, right.length);
    int[] result = new int[arr.length];
    left = mergeSort(left); //recursion call to divide the first part
    right = mergeSort(right);   //recursion call to divide the second part
    result = merge(left, right); //merge divided arrays back together
    return result;
  }

  private int[] merge(int[] left, int[] right) {
    int leftPointer, rightPointer, resPointer;
    leftPointer = rightPointer = resPointer = 0;
    int[] res = new int[left.length + right.length]; //Result
    while (leftPointer < left.length || rightPointer < right.length) {  //while there still elements in one of the arrays
      if (leftPointer < left.length && rightPointer < right.length) { //elements in both arrays
        if (left[leftPointer] < right[rightPointer]) {              //save left item if bigger
          res[resPointer++] = left[leftPointer++];
        } else {
          res[resPointer++] = right[rightPointer++];  //save right item if bigger
        }
      } else if (leftPointer < left.length) {             //element only in left
        res[resPointer++] = left[leftPointer++];    //save elements
      } else if (rightPointer < right.length) {       //elements only in right
        res[resPointer++] = right[rightPointer++];  //save elements
      }
    }
    return res;     //return merged arr in to recursiv method
  }
}
```
> _Random array of size 101:_  
> _Merge Sort finished in: 167300 nanoseconds._  
> _Sorted array of size 101:_  
> _Merge Sort finished in: 152400 nanoseconds._

### Counting Sort

```Java
public class Sortting {
  public int[] countingSort(int[] nums) {
    if(nums == null) {
      return new int[]{};
    }
    int[] sorted = new int[nums.length];
    int maxLength = 0;  //find biggest number in the given array
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] > maxLength)
        maxLength = nums[i];
    }
    int[] allNumbers = new int[maxLength + 1]; //save the numbers as indexes with the count of each number as the value
    for(int i = 0; i < nums.length; i++) {
      allNumbers[nums[i]] += 1;
    }
    int j = 0;
    for(int i = 0; i < allNumbers.length; i++) {//copy index as number in to result array
      while(allNumbers[i] > 0) { //if there was a number of that index
        sorted[j] = i;
        allNumbers[i] -= 1;
        j++;
      }
    }
    return sorted;
  }
}
```
> _Random array of size 101:_  
> _Counting Sort finished in: 26000 nanoseconds._  
> _Sorted array of size 101:_  
> _Counting Sort finished in: 19800 nanoseconds._
---

# <span Style="color: DarkMagenta;">Inheritance</span>

## Abstract Classes

Basically describes everything that derived classes have in common.  

Abstract methods have to be implemented and provide an abstraction layer for the derived Classes  
to implement.

~ **_Weapon.java_**
```Java
public abstract class Weapon {
  int damage;
  abstract void doDamage(Character target);
  //Default Constructor
  Weapon(int damage) {
      this.damage = damage;
  }
}
```
~ **_Character.java_**
```Java
public abstract class Character {
  private final String name;
  //Every character has a Weapon and can equip it.
  private Weapon weapon;
  abstract void equip(Weapon w);

  /* Implementation of damage type depends on the Type of weapon */
  public void attack(Character target) {
      weapon.doDamage(target);
  }
  Character(String name){
      this.name = name;
  }
  /* We can do default getters and setters as well */
  public String getName() {
      return this.name;
  }
  public void setWeapon(Weapon w) {
      weapon = w;
  }
  public Weapon getWeapon() {
      return weapon;
  }
}
```
Now we can just create solid implementation of our abstract classes, and the compiler will even tell us what should be implemented.

~ **_MeleeWeapon.java_**
```Java
public class MeleeWeapon extends Weapon{
  MeleeWeapon(int damage) {
      super(damage);
  }
  @Override
  void doDamage(Character target) {
      //Sword do a Slash
      System.out.println("Slash " + target.getName() +" : "+"("+ target.getClass()+")"+ " and do " + damage + " Damage!");
  }
}
```

~ **_MagicWeapon.java_**
```Java
public class MagicWeapon extends Weapon {

  MagicWeapon(int damage) {
      super(damage);
  }

  @Override
  void doDamage(Character target) {
      //Stuff do Magic
      System.out.println("Cast spell on " + target.getName() +" : "+"("+ target.getClass()+")"+ " and do " + damage + " Damage!");
  }
}
```
~ **_Wizard.java_**
```Java
public class Wizard extends Character{
  Wizard(String name) {
      super(name);
  }
  @Override
  void equip(Weapon w) {
      setWeapon(w);
  }
}
```
~ **_Warrior.java_**
```Java
public class Warrior extends Character{
  Warrior(String name) {
      super(name);
  }
  @Override
  void equip(Weapon w) {
      setWeapon(w);
  }
}
```
## Type casting

After we got our inheritance down we can make further use of the **Parent** classes that we derive from.

I'll create some sort of storage where we can store **weapons** for our **characters**.  
It should not care what Objects we store as long as the item derives from **Weapon** class.

~ **_WeaponStorage.java_**
```Java
public class WeaponStorage{
  /* Does not care what weapons to store */
  private final List<Weapon> weapons = new LinkedList<>();
  public void storeWeapon(Weapon weapon) {
      weapons.add(weapon);
  }
  /* Our Weapon dispenser methods */
  public MagicWeapon getMagicWeapon() {
      /* Just a stream to filter out MagickWeapons and get the first one from the list */
      Optional<Weapon> o = weapons.stream().filter((x) -> x instanceof MagicWeapon).findAny();
      if(o.isPresent()) {
          weapons.remove(o.get());        //Don't forget to remove it from the storage
          return (MagicWeapon) o.get();   //Optional contains a Weapon from the list so we have to explicitly cast it to a MagicWeapon  
      }else {
          System.out.println("No magic weapon could be found");
          return null;
      }
  }
  public MeleeWeapon getMeleeWeapon() {
      /* Just a stream to filter out MeleeWeapons and get the first one from the list */
      Optional<Weapon> o = weapons.stream().filter((x) -> x instanceof MeleeWeapon).findAny();
      if(o.isPresent()) {
          weapons.remove(o.get());        //Don't forget to remove it from the storage
          return (MeleeWeapon) o.get();   //Optional contains a Weapon from the list so we have to explicitly cast it to a MeleeWeapon  
      }else {
          System.out.println("No magic weapon could be found");
          return null;
      }
  }
}
```
To make sure our characters get the right weapons we implement two getter methods  
to downcast **Weapons** out of the storage back to their child class.

####So now we can just...
~ **_RpgGame.java_**
```Java
public class RpgGame {
  public static void main(String[] args) {
      /* Instantiating our RPG Characters */
      Character barbarian = new Warrior("Conan");         //Java knows that Warrior *is a* child of Character class
      Character harry = new Wizard("Harry Potter");       //and can upcast implicitly
      /* Instantiating new Weapons for them */
      MeleeWeapon axe = new MeleeWeapon(15);
      MagicWeapon magicStick = new MagicWeapon(10);
      /* Deposit weapons in to storage */
      WeaponStorage storage = new WeaponStorage();
      storage.storeWeapon(axe);
      storage.storeWeapon(magicStick);
      //Add some more weapons to give Harry a fair chance
      storage.storeWeapon(new MagicWeapon(5));
      storage.storeWeapon(new MagicWeapon(999));
      /* Equip our characters with weapons */
      barbarian.equip(storage.getMeleeWeapon());
      harry.equip(storage.getMagicWeapon());
      /* Make them fight */
      barbarian.attack(harry);
      harry.attack(barbarian);
  }
}
```
 
**Console output:**
> Slash Harry Potter : (class Wizard) and do 15 Damage!   
> Cast spell on Conan : (class Warrior) and do 10 Damage!

# <span Style="color: DarkMagenta;">JDBC</span>

## Database connection

Implements Singleton Pattern to ensure only one instance of the connection is present at runtime.
```Java
public class Database {
    final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    final String CONNECTION = "jdbc:oracle:thin:@dbexpress.westeurope.cloudapp.azure.com:1521:xe";
    static Database instance = null;
    Connection connection;
    
    public static synchronized Database getInstance() {
        //Singleton
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    private Database() {
        connection = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        }catch (SQLException ex) {
            System.out.println("Connection to the database not available" + ex + "\n");
            System.exit(1);
        }
    }
}
```

---

## Design Patterns:
<a href="https://refactoring.guru/ru/design-patterns">refactoring.guru</a> </br>
<a href="https://sourcemaking.com/design_patterns">sourcemaking.com</a>
---

## Java Functional Programming:
###  Functional Intefraces.
###  Pure & High Order Functions.

<a href="http://tutorials.jenkov.com/java-functional-programming/index.html">tutorials.jenkov.com/java-functional-programming</a>

---

## Java Concurrency and Multithreading Tutorial
<a href="http://tutorials.jenkov.com/java-concurrency/index.html">tutorials.jenkov.com/java-concurrency</a>

---

## KeyWords Dictionary

|   Term  |    Defenition    |
| ---- |-------- |
| `Fat jar / Uber jar` | an uber-jar is an "over-jar", one level up from a simple JAR (a), defined as one that contains both your package and all its dependencies in one single JAR file. <a href="https://stackoverflow.com/questions/11947037/what-is-an-uber-jar">_<stackoverflow.com/questions/what-is-an-uber-jar>_</a> |
| `Maven / gradle` | Package Manager aka Dependency resolver. Assists in resolving the dependencies of a project build. | 
| `JavaBean` | **A JavaBean is just a standard.** All properties are private (use getters/setters), A public no-argument constructor, Implements Serializable. <a href="https://stackoverflow.com/questions/3295496/what-is-a-javabean-exactly">_<stackoverflow.com/questions/what-is-a-javabean-exactly>_</a>
| `Serializable` | Serializability of a class is enabled by the class implementing the java.io.Serializable interface. Classes that do not implement this interface will not have any of their state serialized or deserialized. All subtypes of a serializable class are themselves serializable. The serialization interface has no methods or fields and serves only to identify the semantics of being serializable. |

 ---
