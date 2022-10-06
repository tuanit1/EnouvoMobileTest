#### Compare Java vs Kotlin
##### Collections
1. **Filter**
- Java
``` java
    var filtered = numbers
        .stream()
        .filter(num -> num > 5)
        .collect(Collectors.toList())
```
- Kotlin
``` kt
    val filtered = numbers.filter { it > 5 }
```

2. **GroupBy**
- Java
``` java
    final var groups = items.stream()
        .collect(
            Collectors.groupingBy(
                item -> (item % 2) == 0 ? "even" : "odd"
            )
    );
```
- Kotlin
``` kt
    val groups = numbers.groupBy {
        if (it % 2 == 0) "even" else "odd"
    }
```

3. **PartitionBy**
- Java
``` java
    final var partitioned = numbers.stream()
            .collect(
                Collectors.partitioningBy(num -> num % 2 == 0)
            );

    final var evens = partitioned.get(true);
    final var odds = partitioned.get(false);
```
- Kotlin
``` kt
    val (evens, odds) = numbers.partition { it % 2 == 0 }
```

4. **SortBy**
- Java
``` java
    final var users = getUsers();
    users.sort(Comparator.comparing(user -> user.lastname));
```
- Kotlin
``` kt
    val users = getUsers()
    users.sortBy { it.lastname }
```

5. **Infix function**
- Java
``` java
    var list = List.of("a", "b", "c")
    list.add("d")
    list.remove("d")
```
- Kotlin
``` kt
    val list = mutableListOf("a", "b", "c")
    list += "d"
    list -= "d"
```

6. **For**
- Java
``` java
    for (int i = 1; i < 11; i++) { }

    for (int i = 1; i < 11; i += 2) { }

    for (String item : collection) { }

    for (var entry: map.entrySet()) { }
```
- Kotlin
``` kt
    for (i in 1 until 11) { }

    for (i in 1..10 step 2) {}

    for (item in collection) {}
    for ((index, item) in collection.withIndex()) {}

    for ((key, value) in map) {}
```

7. **Repeat**
- Java
``` java
    for (int i = 0; i < 3; i++) {
        System.out.println("Hello")
    }
```
- Kotlin
``` kt
    for (i in 1 until 11) { }

    for (i in 1..10 step 2) {}

    for (item in collection) {}
    for ((index, item) in collection.withIndex()) {}

    for ((key, value) in map) {}
```

8. **Comparator**
- Java
``` java
    var persons = List.of(
        new Person("Bob C.", 5), new Person("Alex G.", 10), new Person("Alex G.", 12)
    );
    persons.sort(
        Comparator
            .comparing(Person::getScore, Comparator.reverseOrder())
            .thenComparing((Person::getName), Comparator.naturalOrder())
    );
    // [(Alex G., 12), (Alex G., 10), (Bob C., 5)]

    @Data // Lombok
    class Person {
        private final String name;
        private final Integer score;
    }
```
- Kotlin
``` kt
    val personsSorted = listOf(
        Pair("Bob C.", 5), Pair("Alex G.", 10), Pair("Alex G.", 12)
    ).sortedWith(
        compareByDescending<Pair<String, Int>> { it.second }
            .thenBy { it. first }
    )
```