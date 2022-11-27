# Complete Guide to `Collectors`.

1. Basics
   1. Understand `Collector` interface and its importance
   2. Understand the internal working of the `Collector` by implementing the `ArrayList` collector
   3. Understand `Transaction` class
2. Collecting data to the `List` interface
   1. `Collectors.toList()`
   2. `Collectors.toUnmodifiableList()`
   3. To Google Guava’s `ImmutableList.toImmutableList()`
3. Collecting data to the `Set` interface
   1. `Collectors.toSet()`
   2. `Collectors.toUnmodifiableSet()`
   3. To Google Guava’s `ImmutableSet.toImmutableSet()`
4. Collecting data to the `Collection` interface
   1. `Collectors.toCollection()`
   2. Collect to `LinkedHashSet`
   3. Collect to `TreeSet` ascending and descending order
   4. Collect to `PriorityQueue` ascending and descending order
5. Collecting data to Map interface
   1. `Collectors.toMap(Function, Function)`: Collect data to `HashMap` using specified key and value `Function`
   2. `Collectors.toMap(Function, Function, BinaryOperator)`: Collect data to `HashMap` using specified key and value `Function` and use `BinaryOperator` as merge function(executes this behavior if two key already exists in Map)
   3. `Collectors.toMap(Function, Function, BinaryOperator, Supplier)`: Collect data to `Supplier` `Map` that extends `Map` using specified key and value `Function` and use `BinaryOperator` as merge function
   4. To Google Guava’s `ImmutableMap.toImmutableMap()`
6. Collecting data to `ConcurrentMap` interface
    1. `Collectors.toConcurrentMap(Function, Function)`: Collect data to `ConcurrentHashMap` using specified key and value `Function`
    2. `Collectors.toConcurrentMap(Function, Function, BinaryOperator)`: Collect data to `ConcurrentHashMap` using specified key and value `Function` and use `BinaryOperator` as merge function(executes this behavior if two key already exists in Map)
    3. `Collectors.toConcurrentMap(Function, Function, BinaryOperator, Supplier)`: Collect data to `Supplier` `Map` that extends `ConcurrentMap` using specified key and value `Function` and use `BinaryOperator` as merge function
7. `Collectors.collectingAndThen()`: Collect elements and then apply additional finishing transformation
   1. Collect data to `List` and return `Collections.unmodifiableList()`
   2. Collect data to `List` and return Google Guava `ImmutableList`
   3. Collect data to `Set` and return `Collections.unmodifiableSet()`
   4. Collect data to `Set` and return Google Guava `ImmutableSet`
   5. Collect data to `Map` and return `Collections.unmodifiableMap()`
   6. Collect data to `Map` and return Google Guava `ImmutableMap`
8. `Collectors.joining()`: Concatenate all input elements
   1. `Collectors.joining()`: Concatenate all input elements
   2. `Collectors.joining(CharSequence delimiter)`: Concatenate all input elements separated by delimiter
   3. `Collectors.joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)`: Concatenate all input elements separated by delimiter, prefix and suffix
9. `Sum` all input elements of `int`, `long` and `double` types
   1. `Collectors.summingInt(ToIntFunction)`
   2. `Collectors.summingLong(ToLongFunction)`
   3. `Collectors.summingDouble(ToDoubleFunction)`
10. `Average` all input elements of `int`, `long` and `double` types
    1. `Collectors.averagingInt(ToIntFunction)`
    2. `Collectors.averagingLong(ToLongFunction)`
    3. `Collectors.averagingDouble(ToDoubleFunction)`
11. `Summarizing` all input elements of `int`, `long` and `double` types. Gives information like min, max, count, sum and average
    1. `Collectors.summarizingInt(ToIntFunction)`
    2. `Collectors.summarizingLong(ToLongFunction)`
    3. `Collectors.summarizingDouble(ToDoubleFunction)`
12. `Collectors.reducing()`: Performs a reduction operation on `Stream` elements. Mostly useful in multi-leveled grouping
    1. `Collectors.reducing(BinaryOperator)`: Reduces input elements to single result using `BinaryOperator` and returns `Optional` result.
    2. `Collectors.reducing(T, BinaryOperator)`: Reduces input elements to single result using `BinaryOperator` and returns result `T`. In case of no elements it returns default result passed as parameter `T`
    3. `Collectors.reducing(U, Function, BinaryOperator)`: Transforms input elements from `T` to `U` and then reduces input elements to single result using `BinaryOperator` and returns result `U`. In case of no elements it returns default result passed as parameter `U`
    4. Specific versions of `Collectors.reducing()` Collectors called as `Collectors.minBy()` and `Collectors.maxBy()`
13. `Collectors.minBy(Comparator)` and `Collectors.maxBy(Comparator)`
    1. `Collectors.minBy(Comparator)` returns the smallest element based on `Comparator`
    2. `Collectors.maxBy(Comparator)` returns the largest element based on `Comparator`
14. Counting `Collector`
    1. `Collectors.counting()` returns number of input elements 
15. Partitioning data into two groups
    1. `Collectors.partitioningBy(Predicate)` partitions data into two groups and inserts elements into respective resultant `List`s
    2. `Collectors.partitioningBy(Predicate, Collector)` partitions data into two groups and reduces each partition based on specified `Collector`
16. Grouping elements into n groups
    1. `Collectors.groupingBy(Function)` collects input elements in `Map<K, List<T>>` based on specified `Function`
    2. `Collectors.groupingBy(Function, Collector)` collects input elements in `Map<K, D>` based on specified `Function` and downstream `Collector`
    3. `Collectors.groupingBy(Function, Supplier, Collector)` collects input elements in Supplied `Map` that extends `Map` based on specified `Function` and downstream `Collector`
    4. `Collectors.groupingByConcurrent(Function)` collects input elements in `ConcurrentMap<K, List<T>>` based on specified `Function`
    5. `Collectors.groupingByConcurrent(Function, Collector)` collects input elements in `ConcurrentMap<K, D>` based on specified `Function` and downstream `Collector`
    6. `Collectors.groupingByConcurrent(Function, Supplier, Collector)` collects input elements in Supplied `Map` that extends `ConcurrentMap` based on specified `Function` and downstream `Collector`
17. Converting elements from type `U` to type `T` before accumulator function in `Collector`
    1. `Collectors.mapping(Function, Collector)` is most useful in multi-level reduction(`groupingBy()` or `partitioningBy()`) to convert element type before accumulation operation
18. Converting elements from type U to type T before accumulator function in `Collector` where type `U` is of `Stream`
    1. `Collectors.flatMapping(Function, Collector)`
19. Filtering elements based on Predicate and accumulating only if `Predicate` returns true
    1. `Collectors.filtering(Predicate, Collector)` is most useful in multi-level reduction
20. Composite Collector of two different Collectors
    1. `Collectors.teeing(Collector, Collector, BiFunction)` processes all elements in both Collectors and result is merged using specified `BiFunction`
21. Collect data to `Optional`
    1. Writing custom `Collector` for `Optional`
    2. Using `Optional.stream()` method and then Collecting using `Collector`
22. Collecting data for `IntStream`, `LongStream` and `DoubleStream` elements using `Stream.boxed()`




