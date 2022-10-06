#### Compare Java vs Kotlin
##### Classes
1. **Constructor Call**
- Java
``` java
    final var file = new File("file.txt");
```
- Kotlin
``` kt
    val file = File("file.txt")
```

2. **Class**
- Java
``` java
    public final class User {
    }
```
- Kotlin
``` kt
    class User
```

3. **Open class**
- Java
``` java
    protected class User {
    }   
```
- Kotlin
``` kt
    open class User
```
4. **Final attribute**
- Java
``` java
    final class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }   
```
- Kotlin
``` kt
    class User(val name: String)
```

5. **Primary constructor**
- Java
``` java
    final class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }  
```
- Kotlin
``` kt
    class User(var name: String)
```

6. **Properties**
- Java
``` java
    public class Document {

        private String id = "00x";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            if (id != null && !id.isEmpty()) {
                this.id = id;
            }
        }

    }
```
- Kotlin
``` kt
    class Document{
        var id : String = "00x"
            set(value) {
                if(value.isNotEmpty())  field = value
            }
    }
```


7. **Abstract Class**
- Java
``` java
    public abstract class Document{
    public abstract int calculateSize();
    }

    public class Photo extends Document{
        @Override
        public int calculateSize() {

        }
    }
```
- Kotlin
``` kt
    object Document {

    }
```