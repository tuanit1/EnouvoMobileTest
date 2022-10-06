#### Compare Java vs Kotlin
##### Basic
1. **Hello world**
- Java
``` java
    public static void main(final String[] args) {
        System.out.println("Hello world!");
    }
```
- Kotlin
``` kotlin
    fun main() {
        println("Hello world!")
    }

    fun main(args: Array<String>) {
        println("Hello World!")
    }
```

2. **Variable 1**
- Java
``` java
    var x; // compile-time error
    final var xx; // compile-time error
    final var y = 1;
```
- Kotlin
``` kotlin
    val x: Int // compile-time error
    val y // compile-time error
    val z = 2
```

3. **Variable 2**
- Java
``` java
    int w;
    int z = 2;
    z = 3;
    w = 1;
```
- Kotlin
``` kotlin
    var q // compile-time error
    var w: Int // compile-time error
    var z = 2
```

4. **Null 1**
- Java
``` java
    final String name = null;

    String lastName;
    lastName = null;
```
- Kotlin
``` kotlin
    val name: String? = null

    var lastName: String?
    lastName = null

    var firstName: String
    firstName = null // Compilation error!!
```
5. **Null 2**
- Java
``` java
    final Integer length =
        bob != null
            && bob.department != null
            && bob.department.text != null
        ? bob.department.text.length()
        : null;
```
- Kotlin
``` kotlin
    val length = bob?.department?.text?.length
```

6. **Elvis operator**
- Java
``` java
    final var result = Optional.ofNullable(nullableVariable)
        .flatMap(v -> Optional.ofNullable(v.someNullableMethodCall()))
        .orElseGet(() -> fallbackIfNullMethodCall())
```
- Kotlin
``` kotlin
    val result = nullableVariable?.someNullableMethodCall() ?: fallbackIfNullMethodCall()
```

7. **String**
- Java
``` java
    final var name = "John";
    final var lastName = "Smith";
    final var text = "My name is: " + name + " " + lastName;
    final var otherText = "My name is: " + name.substring(2);
```
- Kotlin
``` kotlin
    val name = "John"
    val lastName = "Smith"
    val text = "My name is: $name $lastName"
    val otherText = "My name is: ${name.substring(2)}"
```

8. **String 2**
- Java
``` java
    final var text = "First Line\n" +
                "Second Line\n" +
                "Third Line";
```
- Kotlin
``` kotlin
    val text = """
            |First Line
            |Second Line
            |Third Line
    """.trimMargin()
```

9. **String 2**
- Java
``` java
    final var text = x > 5 ? "x > 5" : "x <= 5";
```
- Kotlin
``` kotlin
    val text = if (x > 5) "x > 5" else "x <= 5"
```

10. **Array**
- Java
``` java
    var size = 1;

    var numbers = new int[size];
    numbers[0] = 1;

    var bigNumbers = new long[size];

    var strings = new String[size];
```
- Kotlin
``` kotlin
    val size = 1

    val numbers = IntArray(size)
    numbers[0] = 1

    val bigNumbers = LongArray(size)

    val strings = Array(size) { String() }
```
11. **Use**
- Java
``` java
    var file = new File("contents.txt");
    try {
        var fileReader = new FileReader(file);
        try (var bufferedReader = new BufferedReader(fileReader)) {
            // the bufferedReader is closed automatically
        }
    } catch (IOException ex) {
        // handle the exception
    }
```
- Kotlin
``` kotlin
    File("contents.txt")
        .bufferedReader()
        .use {
            // the bufferedReader is closed automatically
        }   
```


