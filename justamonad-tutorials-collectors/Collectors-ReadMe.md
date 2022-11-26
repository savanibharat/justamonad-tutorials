# Collectors in Java

## Complete Guide to Collectors.

1. Basics
   1. What is `Collector` interface and why is it important?
   2. Explain the internal working of the `Collector` by implementing the ArrayList collector
   3. Understanding `Transaction` class
2. Collecting data to the List interface
   1. `Collectors.toList()`
   2. `Collectors.toUnmodifiableList()`
   3. To Google Guava’s toImmutableList
3. Collecting data to the Set interface
   1. Collectors.toSet()
   2. Collectors.toUnmodifiableSet()
   3. To Google Guava’s toImmutableSet
4. Collecting data to the Collection interface
   1. Collect to LinkedHashSet
   2. Collect to TreeSet ascending and descending order
   3. Collect to PriorityQueue ascending and descending order
5. Collecting data to Map interface
   1. 3 variations of Collectors.toMap()
   2. To Google Guava’s toImmutableMap
6. Collecting data to ConcurrentMap interface
    1. 3 variations of Collectors.toMap()
7. collectingAndThen: Collect data and then apply additional finishing transformation
   1. To unmodifiable list, Set, Map, immutable list set and Map
   2. Collect data to List and return Collections.unmodifiableList
   3. Collect data to List and return Google Guava ImmutableList
   4. Collect data to Set and return Collections.unmodifiableSet
   5. Collect data to Set and return Google Guava ImmutableSet
   6. Collect data to Map and return Collections.unmodifiableMap
   7. Collect data to Map and return Google Guava ImmutableMap
8. Collecting data using the joining method
   1. Collectors.joining(): Concatenate all elements in stream
   2. Collectors.joining(CharSequence delimiter): Concatenate all elements in stream separated by delimiter
   3. Collectors.joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix): Concatenate all elements in stream separated by delimiter, prefix and suffix
9. Sum all input elements of int, long and double types
   1. `Collectors.summingInt`
   2. `Collectors.summingLong`
   3. `Collectors.summingDouble`
10. Average all input elements of int, long and double types
    1. Collectors.averagingInt
    2. Collectors.averagingLong
    3. Collectors.averagingDouble
11. Summarizing all input elements of int, long and double types. Gives information like min, max, count, sum and average
    1. Collectors.summarizingInt
    2. Collectors.summarizingLong
    3. Collectors.summarizingDouble
12. Reducing: Performs a reduction operation on Stream elements. Mostly useful in multi-leveled grouping
    1. 3 variations of reducing() Collector
    2. Specific versions of reducing() Collectors called as Collectors.minBy and Collectors.maxBy
13. Collectors.minBy and Collectors.maxBy
    1. Collectors.minBy() returns the smallest element based on Comparator
    2. Collectors.maxBy() returns the largest element based on Comparator
14. Counting Collector
    1. Collectors.counting() returns number of input elements 
15. Partitioning data into two groups
    1. Collectors.partitioningBy(Predicate) partitions data into two groups and inserts elements into respective resultant Lists
    2. Collectors.partitioningBy(Predicate, Collector) partitions data into two groups and reduces each partition based on specified Collector
16. Grouping elements into n groups
    1. Collectors.groupingBy(Function) collects input elements in Map<K, List<T>> based on specified Function
    2. Collectors.groupingBy(Function, Collector) collects input elements in Map<K, D> based on specified Function and downstream Collector
    3. Collectors.groupingBy(Function, Supplier, Collector) collects input elements in Supplied Map that extends java.util.Map based on specified Function and downstream Collector
    4. Collectors.groupingByConcurrent(Function) collects input elements in ConcurrentMap<K, List<T>> based on specified Function
    5. Collectors.groupingByConcurrent(Function, Collector) collects input elements in ConcurrentMap<K, D> based on specified Function and downstream Collector
    6. Collectors.groupingByConcurrent(Function, Supplier, Collector) collects input elements in Supplied Map that extends java.util.concurrent.ConcurrentMap based on specified Function and downstream Collector
17. Converting elements from type U to type T before accumulator function in Collector
    1. Collectors.mapping(Function, Collector) is most useful in multi-level reduction(groupingBy() or partitioningBy()) to convert element type before accumulation operation
18. Converting elements from type U to type T before accumulator function in Collector where type U is of java.util.stream.Stream
    1. Collectors.flatMapping(Function, Collector)
19. Filtering elements based on Predicate and accumulating only if Predicate returns true
    1. Collectors.filtering(Predicate, Collector) is most useful in multi-level reduction
20. Composite Collector of two different Collectors
    1. teeing(Collector, Collector, BiFunction) processes all elements in both Collectors and result is merged using specified BiFunction
21. Collect data to Optional
    1. Writing custom Collector for Optional
    2. Using Optional.stream() method and then Collecting using Collector
22. Collecting data using IntStream, LongStream and DoubleStream




