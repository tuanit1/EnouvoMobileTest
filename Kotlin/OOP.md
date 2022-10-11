- [#### OOP](#-oop)
      - [1. Classes](#1-classes)
      - [2. Inheritance](#2-inheritance)
      - [3. Interfaces](#3-interfaces)
      - [4. Data class](#4-data-class)
      - [5. Enum class](#5-enum-class)
      - [6. Generic in, out](#6-generic-in-out)
#### OOP
---
##### 1. Classes
- **Cách 1**
    ``` kotlin
        class Person(private val _firstName: String = "Peter", private val _lastName: String = "Packer"){
            var firstName: String? = _firstName
            var lastName: String? = _lastName
            var nickName: String? = null
                set(value){
                    field = "MARVEL " + value
                    println("nickName's setter called")
                }
                get(){
                    println("nickName's getter called")
                    return field
                }

            fun printInfo() {
                println("INFO: $firstName ($nickName) $lastName")
            }
        }


        //main()
        val person = Person()
        person.nickName = "Spider-man"
        println("${person.firstName} ${person.lastName}")
        println("Have a nickname: ${person.nickName}")
        person.printInfo()
    ```
- **Cách 2**
    ``` kotlin
        class Vehicle(private val name: String, private val year: Int) {
            override fun toString(): String {
                return "This is $name, released in $year"
            }
        
        }

        //main
        val car = Car("Mercedes C200", 29, 22000, 90)
    ```
- **Secondary constructor**
    ``` kotlin
        class Person(val name: String) {
            val children: MutableList<Person> = mutableListOf()
            constructor(name: String, parent: Person) : this(name) {
                parent.children.add(this)
            }
        }
    ```
- **Compation object**
    ``` kotlin
        class MyObject{
            companion object {
                fun sayHello(text: String): String = "Hello $text"
                val message: String = "AAAAAAAAAA"
            }
        }

        //main
        println(MyObject.sayHello("Tuan"))
        println(MyObject.message)
    ```
    - Có thể gọi các props và hàm bên trong khối companion object mà không cần khởi tạo đối tượng Class

---
##### 2. Inheritance
``` kotlin 
    open class Vehicle(private val name: String, private val year: Int) {

        open fun drive(){
            println("Vehicle running")
        }

        override fun toString(): String {
            return "This is $name, released in $year"
        }
    }

    class Car(name: String, year: Int, var power: Int, var engineSize: Int) : Vehicle(name, year){

        override fun drive() {
            super.drive()
            println("Car running")
        }

        override fun toString(): String {
            return "${super.toString()} \nhas power $power W \nhas engine size $engineSize cc"
        }
    }


    //main()
    val car = Car("Mercedes C200", 29, 22000, 90)
    car.drive()
    println(car)

    //output
    Vehicle running
    Car running
    This is Mercedes C200, released in 29 
    has power 22000 W 
    has engine size 90 cc

```
---
##### 3. Interfaces
- Implement interface
``` kotlin
    interface MyInterface {
        val prop: Int // abstract

        val propertyWithImplementation: String
            get() = "foo"

        fun foo() {
            print(prop)
        }
    }

    class Child : MyInterface {
        override val prop: Int = 29

        override val propertyWithImplementation: String
            get() = super.propertyWithImplementation

        override fun foo() {
            super.foo()
        }
    }
```
- Functional interface
``` kotlin
    fun interface IntPredicate {
        fun accept(i: Int): Boolean
    }

    //create an instance of class
    val isEven = object : IntPredicate {
        override fun accept(i: Int): Boolean {
            return i % 2 == 0
        }
    }

    //main()
    isEven.accept(2)


```
---
##### 4. Data class
- Giống class thông thường
- Cho phép override toString()
- hàm toString() cho form "User(name=John, age=42)"
- cung cấp hàm copy()
- hashCode() cho giá trị giống nhau nếu cả 2 đối tượng đều giống dữ liệu

``` kotlin
    data class Student(var name: String, var age: Int){
        override fun equals(other: Any?): Boolean {
            return other is Student
                    && this.age == other.age
                    && this.name == other.name
        }
    }

    //main()
    val s1 = Student("Tuan1", 30)
    val s2 = Student("Tuan1", 31)

    println(s1.hashCode())
    println(s2.hashCode())
```
---
##### 5. Enum class

``` kotlin
    enum class ErrorRequest(val message: String) {
        ERROR("Error Server"),
        INTERNAL_ERROR("Internal Error Server"),
        SUCCESS("Yay, Success")
    }


    //main()
    val errorRequest: ErrorRequest = ErrorRequest.SUCCESS
    val errMessage: String = when(errorRequest){
        ErrorRequest.INTERNAL_ERROR -> ErrorRequest.INTERNAL_ERROR.message
        ErrorRequest.ERROR -> ErrorRequest.INTERNAL_ERROR.message
        ErrorRequest.SUCCESS -> ErrorRequest.SUCCESS.message
    }

    println(errMessage)
```
---
##### 6. Generic in, out
- [Giải thích Variance, Invariance, Contravariance](https://viblo.asia/p/su-huu-ich-lon-tu-nhung-dieu-nho-trong-java-va-kotlin-ORNZqaPqZ0n?fbclid=IwAR2f7ijUL-W2kjCJMsx6V83eGCXTku8BgGwOQTdtwWNztNV0zCgLICJACV0)
- [Phân biệt in và out trong kotlin](https://viblo.asia/p/kieu-bien-in-va-out-cua-kotlin-63vKjnOMK2R?fbclid=IwAR31dhz9DpCfpVPCQG7-FbydbgvXRew-bkbPMln_EY7g0f7CHgPT2zhRosU)
    
    