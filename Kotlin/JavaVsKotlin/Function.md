#### Compare Java vs Kotlin
##### Function
1. **Default and non-null parameters**
- Java
``` java
    public void hello(String name) {
        if (name == null) {
            name = "World";
        }

        System.out.print("Hello, " + name + "!");
    }
```
- Kotlin
``` kotlin
    fun hello(name: String = "World") {
        println("Hello, $name!")
    }
```

2. **Single-statement function**
- Java
``` java
    public double cube(double x) {
    return x * x * x;
    }
```
- Kotlin
``` kotlin
    fun cube(x: Double) : Double = x * x * x
```

3. **Generic methods**
- Java
``` java
    public void init() {
    var moduleInferred = createList("net");
    }

    public <T> List<T> createList(T item) { }
```
- Kotlin
``` kotlin
    fun init() {
        val module = createList<String>("net")
        val moduleInferred = createList("net")
    }

    fun <T> createList(item: T): List<T> { }
```

4. **Data classes**
- Java
``` java
    public static void main(String[]args) {
        val book = createBook();
        System.out.println(book);
        System.out.println("Title: " + book.title);
    }

    public static Book createBook(){
        return new Book("title_01", "author_01");
    }

    @Data // Lombok
    public class Book {
        private final String title;
        private final String author;
    }
```
- Kotlin
``` kotlin
    fun main(args: Array<String>) {
        val book = createBook();
        // or
        val (title, author) = createBook()

        println(book)
        println("Title: $title")
    }

    fun createBook() : Book{
        return Book("title_01", "author_01")
    }

    data class Book(val title: String, val author: String)
```