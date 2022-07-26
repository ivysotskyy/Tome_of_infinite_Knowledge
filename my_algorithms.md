Find min and max number in an array of size **N** with just 3\*N/2 comparisons

```JavaScript

  let randomNumbers = [74, 2, 24, 31, 96, 45, 74, 43, -121, 96, -20, 33, 29, 5, 6, 99, 39, 94, 41, 22, 17, 5, 3, 49, 51, 22, 195, 1, 196, 98, 195, 22, 396, 52, 91, 97, 70, 456, 219, -1, 222, 15];
  console.log(randomNumbers.length)
  let max = Number.MIN_SAFE_INTEGER
  let min = Number.MAX_SAFE_INTEGER
  let count = 0;
  for (let i = 1; i < randomNumbers.length; i+2 >= randomNumbers.length ? i++ : i+=2) {
      count++
      if (randomNumbers[i] > randomNumbers[i-1]) {
          max = max > randomNumbers[i] ? max : randomNumbers[i];
          min = min < randomNumbers[i-1] ? min : randomNumbers[i-1];
      }else if (randomNumbers[i] < randomNumbers[i-1]) {
          min = min < randomNumbers[i] ? min : randomNumbers[i];
          max = max > randomNumbers[i - 1] ? max : randomNumbers[i-1];
      }
  }
  console.log("cnt: " + count);
  console.log("Max: " + max);
  console.log("Min: " + min);

```
