# Are you afraid of the dark? (v 1.1)
## Scalac recruitment task

### Author
Robert Jankowski


### Summary
I implemented this task using Scala.
I was working on a SBT project, which I created using:
```sbt new scala/scala-seed.g8```
My solution uses Zio (and ZioConfig). It allows for parallel processing of multiple images at once. Becouse of this I was focused on reducing JVM memory usage (this would cause heap space problems), which I have achieved using foldLeft in the most problematic part of code.

### Command to run
```sbt run```

### Input directory
There are files with names in range from 01 (01.jpeg) to 28 (28.jpg).
Files 01-14 are the dark ones, the other half is bright.

### Execution time
Computations usually take 4 to 8 seconds.

### Accuracy
My program has successfully attached metadata to all test files.

| input name | output name       | is correct |  score  |
|------------|-------------------|------------|---------|
| 01.jpeg    | 01_dark_4.jpeg    |    YES     |    4    |
| 02.jpg     | 02_dark_14.jpg    |    YES     |   14    |
| 03.png     | 03_dark_1.png     |    YES     |    1    |
| 04.jpeg    | 04_dark_1.jpeg    |    YES     |    1    |
| 05.jpg     | 05_dark_4.jpg     |    YES     |    4    |
| 06.jpg     | 06_dark_1.jpg     |    YES     |    1    |
| 07.jpg     | 07_dark_5.jpg     |    YES     |    5    |
| 08.jpg     | 08_dark_2.jpg     |    YES     |    2    |
| 09.jpg     | 09_dark_1.jpg     |    YES     |    1    |
| 10.jpg     | 10_dark_13.jpg    |    YES     |   13    |
| 11.jpg     | 11_dark_2.jpg     |    YES     |    2    |
| 12.jpg     | 12_dark_14.jpg    |    YES     |   14    |
| 13.jpg     | 13_dark_0.jpg     |    YES     |    0    |
| 14.jpg     | 14_dark_1.jpg     |    YES     |    1    |
| 15.jpg     | 15_bright_33.jpg  |    YES     |   33    |
| 16.jpg     | 16_bright_40.jpg  |    YES     |   40    |
| 17.jpg     | 17_bright_44.jpg  |    YES     |   44    |
| 18.jpg     | 18_bright_69.jpg  |    YES     |   69    |
| 19.jpg     | 19_bright_46.jpg  |    YES     |   46    |
| 20.jpg     | 20_bright_31.jpg  |    YES     |   31    |
| 21.jpg     | 21_bright_46.jpg  |    YES     |   46    |
| 22.jpg     | 22_bright_54.jpg  |    YES     |   54    |
| 23.jpg     | 23_bright_59.jpg  |    YES     |   59    |
| 24.jpg     | 24_bright_50.jpg  |    YES     |   50    |
| 25.jpg     | 25_bright_28.jpg  |    YES     |   28    |
| 26.jpg     | 26_bright_31.jpg  |    YES     |   31    |
| 27.jpg     | 27_bright_66.jpg  |    YES     |   66    |
| 28.jpg     | 28_bright_43.jpg  |    YES     |   43    |