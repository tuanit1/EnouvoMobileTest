#### Functions
##### 1. Functions
  ``` kotlin 
    fun double(x: Int): Int {
        return 2 * x
    }

    val result = double(2)
  ```

- **Default arguments**
  ``` kotlin 
    fun read(
      b: ByteArray,
      off: Int = 0,
      len: Int = b.size,
    ) { /*...*/ }
  ``` 

- **Unit-returning functions (Tương tự void)**
  ``` kotlin 
    fun printHello(name: String?): Unit {
      if (name != null)
        println("Hello $name")
      else
        println("Hi there!")
      // `return Unit` or `return` is optional
    }
  ``` 
- **Single-expression functions**
  ``` kotlin 
    fun double(x: Int): Int = x * 2
  ``` 
- **Variable number of arguments (varargs)**
  ``` kotlin 
    fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
        return result
    }

    val list = asList(1, 2, 3)
  ``` 

- **Infix notation**
  ``` kotlin 
    infix fun String.greeting(name: String) :String {
        return "Hello $this, welcome to $name"
    }

    //calling
    print("Tuan" greeting "Enouvo")
    print("Tuan".greeting("Enouvo"))

  ``` 
- **Generic functions**
    ``` kotlin 
        fun <T> singletonList(item: T): List<T> { /*...*/ }
    ```
- **Extension functions**
    ``` kotlin 
        fun MutableList<Int>.swap(index1: Int, index2: Int) {
            val tmp = this[index1] // 'this' corresponds to the list
            this[index1] = this[index2]
            this[index2] = tmp
        }

        val list = mutableListOf(1, 2, 3)
        list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
    ```    
    - Generic way
	``` kotlin 
        fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
            val tmp = this[index1] // 'this' corresponds to the list
            this[index1] = this[index2]
            this[index2] = tmp
        }
    ```    

##### 2. Lambda
- Higher-order functions
    ``` kotlin 
        fun doSomething(x: Int, y: Int, completion: (Int) -> Int): Int {
            val z: Int = x * y
            return completion(z)
        }

        //normal calling
        val result = doSomething(10, 5, fun(z: Int): Int {
            return z + 8
        })

        // lambda way
        val result = doSomething(10, 5){z -> run { z + 6}} //fast
        val result = doSomething(10, 5, {z -> z + 6}) //more fast
        val result = doSomething(10, 5){z -> z + 6} // extreme fast
        val result = doSomething(10, 5){it + 6} //super laziest
    ```
- Lambda functions
    ``` kotlin 
        val getFullName: (String, String) -> String = {
            firstName, lastName -> run {
                println("This is a lambda function")
                "$firstName $lastName"
            }
        }

        getFullName("Do Thanh", "Tuan").let {
            println("It mean that return value is not NULL")
            println(it)
        }
    ```
