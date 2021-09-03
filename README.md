# <div Style="text-align:center; color: goldenRod"> _Tome of infinite Knowledge_ </div>

---

# <span Style="color: DarkMagenta;">_Java stuff_ <3</span>

+ **Sorting Algorithms**
  * [Selection Sort](#selection-sort)
  + [Insertion Sort](#insertion-sort)
  * [Bubble Sort](#optimized-bubblesort)
  + [Quick Sort](#quicsort)
  * [Merge Sort](#merge-sort)
  + [Counting Sort](#counting-sort)
+ **Inheritance**
  * [Type casting](#inheritance)
+ **File I/O & Exceptions**
  * [File I/O](#file I/O)
  + [Exceptons](#exceptions)
+ **JDBC**
  * [DB Connection](#dbConnection)
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

## Inheritance
