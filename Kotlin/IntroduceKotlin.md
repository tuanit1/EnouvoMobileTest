## 1. Cài đặt Kotlin
- Kotlin đã được bao gồm trong [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) và [Android Studio IDE](https://developer.android.com/studio)
- Để thực thi lệnh Kotlin trên các IDE khác cần phải cài đặt [kotlin-compiler-1.7.20.zip](https://github.com/JetBrains/kotlin/releases/tag/v1.7.20) cho Kotlin 
- Các extension kotlin dành cho Visual Studio Code:
  - Kotlin
  - Kotlin Language
  - Code Runner

## 2. Các khái niệm cơ bản
#### Data type
1. **Number**
  - **Integer Type**

      | Type  | Size (bits) |
      | ----- | ----------- |
      | Byte  | 8           |
      | Short | 16          |
      | Int   | 32          |
      | Long  | 64          |

      ``` kt
        val one = 1 // Int
        val threeBillion = 3000000000 // Long
        val oneLong = 1L // Long
        val oneByte: Byte = 1
      ```

  - **Float Type**
      | Type   | Size (bits) |
      | ------ | ----------- |
      | Float  | 32          |
      | Double | 64          |

      ``` kt
        val pi = 3.14 // Double
        // val one: Double = 1 // Error: type mismatch
        val oneDouble = 1.0 // Double
      ```
      ``` kt
        val e = 2.7182818284 // Double
        val eFloat = 2.7182818284f // Float, actual value is 2.7182817
      ```


2. **Booleans**
   - Chỉ có hai giá trị: **true** và **false**
   - Toán tử logic
     - **||** – disjunction (logical OR)
     - **&&** – conjunction (logical AND)
     - **!** – negation (logical NOT)
    
    ```kt
      val myTrue: Boolean = true
      val myFalse: Boolean = false
      val boolNull: Boolean? = null

      println(myTrue || myFalse)
      println(myTrue && myFalse)
      println(!myTrue)

      --------------------------------------------------------
      true
      false
      false
    ```
3. **Characters**
    - Character đại diện cho kiểu dữ lệu **Char**
    - Các ký tự đặc biệt bắt đầu bởi dấu \
    
    | Ký tự |                   |
    | ----- | ----------------- |
    | \t    | tab               |
    | \b    | backspace         |
    | \n    | newline           |
    | \r    | carriage return   |
    | \\'   | single quote mark |
    | \\"   | double quote mark |
    | \\\   | backslash         |
    | \\$   | dollar sign       |
4. **Strings**
    - Chuỗi trong Kotlin được thể hiện bằng kiểu **String**, là những chuỗi ký tự bên trong dấu nháy đôi "text"
    
    ```kt
      val str = "abcd 123"
    ```

    - Duyệt ký tự trong chuỗi
    ``` kt
      for (c in str) {
        println(c)
      }
    ```

    - String template
    ``` kt
      val i = 10
      println("i = $i") // Prints "i = 10"  
    ```
5. **Arrays**
  - **Primitive type arrays**
    ``` kt 
      val x: IntArray = intArrayOf(1, 2, 3)
      x[0] = x[1] + x[2]
    ```

    ``` kt 
      // Array of int of size 5 with values [0, 0, 0, 0, 0]
      val arr = IntArray(5)

      // Example of initializing the values in the array with a constant
      // Array of int of size 5 with values [42, 42, 42, 42, 42]
      val arr = IntArray(5) { 42 }

      // Example of initializing the values in the array using a lambda
      // Array of int of size 5 with values [0, 1, 2, 3, 4] (values initialized to their index value)
      var arr = IntArray(5) { it * 1 }
    ```
   - **Mutable list**
     - Kiểu dữ liệu trong list có thể được định nghĩa qua cập dấu <> và có thể chứa được các dữ liệu có kiểu khác nhau

      ``` kt

        val someNumbers = mutableListOf<Int>(1, 5 ,6 ,2 ,6, 10 ,-3, 10, 3, 8)

        val someAny = mutableListOf<Any>(1, "String" ,6 ,"AHA" ,6, 10.0f ,-3, 10, 3, 8)

        val someNames = mutableListOf<String>("Tuan", "Tri" ,"Truong" ,"Loc" ,"Phuoc")

      ```

  - **Xử lý mảng**
    - Kiểm tra tồn tại với điều kiện
    ``` kt 
      if(someNumbers.any {it < 0}){
        println("At least 1 item is negative")
      }
      //At least 1 item is negative

      if(someNumbers.all {it < 100}){
          println("All item is smaller than 100")
      }
      //All item is smaller than 100

      if(someNumbers.none {it == 0}){
          println("No item equal 0")
      }
      //No item equal 0
    ```
    - Lọc mảng với điều kiện
    ``` kt 
      val filterNumber = someNumbers.filter { it in 4..10 } 
      //[5, 6, 6, 10, 10, 8]

      val containName = someNames.filter { it.contains("c", ignoreCase = true) }
      //[Loc, Phuoc]

    ``` 
    - Sắp xếp mảng
    ``` kt 
      val sortedName = someNames.sort()
      //[Loc, Phuoc, Tri, Truong, Tuan]

      val sortedNameDes = someNames.sortedDescending()
      //[Tuan, Truong, Tri, Phuoc, Loc]
    ```
#### Conditions
- **Điều kiện if**
  ``` kt 
    var max = a
    if (a < b) max = b

    // With else
    var max: Int
    if (a > b) {
        max = a
    } else {
        max = b
    }

    // As expression
    val max = if (a > b) a else b
  ```
  - Dùng để return giá trị
  ``` kt 
    val max = if (a > b) {
    print("Choose a")
    a
    } else {
        print("Choose b")
        b
    }
  ```

- **Biểu thức when**
  - Là biểu thức điều kiện rẽ nhánh, khá tương tự với biểu thức switch

  ``` kt 
    when (x) {
      1 -> print("x == 1")
      2 -> print("x == 2")
      else -> {
          print("x is neither 1 nor 2")
      }
    }
  ``` 
#### Loops

  - **For loop**

  ``` kt

    for (item in collection) print(item)

    for (item: Int in ints) {
      // ...
    }

    for (i in 1..3) {
      println(i)
    }

    for (i in 6 downTo 0 step 2) {
      println(i)
    }

    // loop with get index
    for (i in array.indices) {
      println(array[i])
    }

    // loop with get index and value
    for ((index, value) in array.withIndex()) {
      println("the element at $index is $value")
    }
  ```

  ``` kt
    val array = arrayOf("a", "b", "c", "d", "e")

    print("\nFor in: \n")
    for (item in array) {
        print(item + "")
    }

    print("\nFor Each: \n")
    array.forEach { 
        print(it + "") 
    }

    print("\nFor Each with modify item-name: \n")
    array.forEach { item ->
        print(item + "") 
    }

    print("\nFor Each with index: \n")
    array.forEachIndexed { index, item ->
        println("item[$index] = $item") 
    }

    val map = mapOf("key1" to "a", "key2" to "b", "key3" to "c")

    print("\nFor each with key map: \n")
    map.forEach { key, value ->
        println("item['$key'] = $value") 
    }

    print("\nMutableList: \n")
    val mutableList = mutableListOf("Kotlin", "Programming", "Comic Books")
    mutableList.add("New")
    mutableList.forEach { value ->
        println("$value") 
    }

    print("\nMutableMap: \n")
    val mutableMap = mutableMapOf("key1" to "Kotlin", "key2" to "Programming", "key3" to "Comic Books")
    mutableMap.put("key4", "BKDN")
    mutableMap.forEach { key, value ->
        println("item['$key'] = $value") 
    }
  ```

  - **When loop**
  ``` kt
    while (x > 0) {
       x--
    }

    do {
        val y = retrieveData()
    } while (y != null) // y is visible here!
  ```
#### Null safety
- Trong Kotlin, hệ thống loại phân biệt giữa các tham chiếu có thể null (non-null reference) và các tham chiếu không thể null (nullable reference). Ví dụ: một biến thông thường kiểu String không thể giữ null
``` kt
  var a: String = "abc" // Regular initialization means non-null by default
  a = null // compilation error
```
- Để cho phép null:
``` kt
  var b: String? = "abc" // can be set to null
  b = null // ok
  print(b)
```

- **Safe calls**
  - Trả về b.length nếu b khác null, và null nếu ngược lại.
  ``` kt
    val a = "Kotlin"
    val b: String? = null
    println(b?.length)
    println(a?.length) // Unnecessary safe call
  ```
-  **Toán tử let**
    - Để thực hiện một đoạn chương trình chỉ cho dữ liệu khác null.
    ``` kt 
      val listWithNulls: List<String?> = listOf("Kotlin", null)
      for (item in listWithNulls) {
          item?.let { println(it) } // prints Kotlin and ignores null
      }
    ```

  - **Elvis operator**
    ``` kt
      //normal way
      val l: Int = if (b != null) b.length else -1

      //same but faster
      val l = b?.length ?: -1
    ```


#### Exceptions

  ``` kt 
    try {
    // some code
    } catch (e: SomeException) {
        // handler
    } finally {
        // optional finally block
    }

    //with return value
    val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
  ```