package com.example.myapplication
import kotlin.math.round

import android.util.Log
    fun task1A(){
        val university="UNSA"
        val city="Sarajevo"
        val year=2025

        var course = "Mobile Programming"
        var level = 3
        var isActive = true

        course="Android Development"
        level=4
        isActive=false

        Log.d("Lab2", "Task1A: $university, $city, $year | $course, $level, $isActive")

    }

fun task1B(){
    val name="Emir"
    val age=25
    val gpa=8.75

    val isStudent: Boolean=true
    val gradeLetter: Char='A'

    Log.d("Lab2", "Task1B: $name, $age, $gpa, Student: $isStudent, Grade: $gradeLetter")
}

fun task1C(){
    var nickname: String? = "sara"
    Log.d("Lab2", "Task1C Safe-call: ${nickname?.length}")
    Log.d("Lab2", "Task1C Elvis operator: ${nickname?.length?:0}")
    Log.d("Lab2", "Task1C Assertion: ${nickname!!.length}")

    nickname=null

    Log.d("Lab2", "Task1C after nulll - safe call: ${nickname?.length}")
    Log.d("Lab2", "Task1C after Elvis operator: ${nickname?.length?:0}")
}
fun calculateGrade(score: Int):String{
    return when(score){
        in 90..100  -> "A"
        in 80..89   -> "B"
        in 70.. 79  -> "C"
        in 60..69   -> "D"
        in 0..59    -> "E"
        else              -> "Invalid"
    }
}
fun task1D(){
    val scores=listOf(95, 82, 44, 105, -44)
    for(s in scores){
        val grade=calculateGrade(s)
        Log.d("Lab2", "Task1D: Score $s -> Grade $grade")
    }
}

fun task1E(){
    for(i in 1..100){
        val result=when{
            i % 15 == 0 -> "FizzBuzz"
            i % 3 == 0 -> "Fizz"
            i % 5 == 0 -> "Buzz"
            else -> i.toString()
        }
        Log.d("Lab2", "Task1E: $result")
    }
}
fun isPrime(n: Int): Boolean{
    if(n<2) return false
    for(i in 2..Math.sqrt(n.toDouble()).toInt()){
        if(n%i==0) return false
    }
    return true
}

fun printPrimes1To100(){
    Log.d("Lab2", "Task1 Bonus: Primes 1-100")
    for(i in 1..100){
        if(isPrime(i)) Log.d("Lab2", "$i")
    }
}

class Person(val name: String, val age: Int){
    constructor(name: String): this(name, age=18)
}

fun task2A(){
    val p1=Person("Sara", 22)
    val p2=Person("Sara")

    Log.d("Lab2", "Task2A ${p1.name} ${p1.age}")
    Log.d("Lab2", "Task2A ${p2.name} ${p2.age}")
}

class PersonWGetter(val name: String, val age: Int){
    val isAdult:Boolean
        get()=age>=18
}

fun task2B(){
    val a = PersonWGetter("Sara", 17)
    val b = PersonWGetter("Sara", 19)
    Log.d("Lab2", "Task2B: ${a.name} adult? ${a.isAdult}")
    Log.d("Lab2", "Task2B: ${b.name} adult? ${b.isAdult}")
}

class BankAccount(initialBalance: Double){
    var balance: Double=initialBalance
        set(value){
            if(value>=0)
                field=value
            else
                Log.d("Lab2", "Task2C: Cannot set negative balance: $value")
        }
}

fun task2C(){
    val acc=BankAccount(100.0)
    Log.d("Lab2", "Task2C: Start balance: ${acc.balance}")

    acc.balance=200.00
    Log.d("Lab2","Task2C: Updated balance: ${acc.balance}")

    acc.balance=-50.0
    Log.d("Lab2", "Task2C: After invalid set: ${acc.balance}")
}

class SecureBankAccount(initialBalance: Double){
    private var balance: Double=initialBalance

    fun deposit(amount: Double){
        if(amount>0) balance+=amount
        else Log.d("Lab2", "Task2D: Deposit must be positive.")
    }
    fun withdraw(amount: Double){
        if(amount>0 && amount<=balance) balance-=amount
        else Log.d("Lab2", "Task2D: Invalid withdraw amount.")
    }
    fun getBalance(): Double = balance
}

fun task2D(){
    val acc=SecureBankAccount(100.0)
    acc.deposit(50.0)
    acc.withdraw(30.0)
    Log.d("Lab2", "Task2D: Balance via method: ${acc.getBalance()}")
}

open class Vehicle(val brand: String){
    open fun description(): String = "Vehicle brand: $brand"
}

class Car(brand: String, val model: String): Vehicle(brand){
    override fun description(): String {
        return "Car: $brand $model"
    }
}

fun task2E(){
    val v: Vehicle=Vehicle("Generic")
    val c: Vehicle=Car("Toyota", "Corolla")

    Log.d("Lab2","Task2E: ${v.description()}")
    Log.d("Lab2", "Task2E ${c.description()}")
}

data class Student(
    val name: String,
    val id: Int,
    val grade: List<Int>
)
data class Student2(
    val name: String,
    val id: Int,
    val grades: List<Int>
){
    fun average(): Double{
        if(grades.isEmpty()) return 0.00
        return grades.average()
    }
    fun letterGrade(): String{
        val avg=average()
        return when{
            avg>=90->"A"
            avg>=80->"B"
            avg>=70->"C"
            avg>=60->"D"
            else->"F"
        }
    }
}

fun Student2.isPassing(): Boolean=average()>=55.0

fun task3(){
    val students=listOf(
        Student2("Sara", 1, listOf(90, 92, 88)),
        Student2("Sara", 2, listOf(70, 75, 72)),
        Student2("Sara", 3, listOf(55, 60, 58)),
        Student2("Sara", 4, listOf(40, 45, 42)),
        Student2("Sara", 5, listOf(95, 98, 97)),
        Student2("Sara", 6, listOf(85, 82, 88)),
        Student2("Sara", 7, listOf(60, 62, 65)),
        Student2("Sara", 8, listOf(30, 35, 40)),
        Student2("Sara", 9, listOf(100, 100, 100)),
        Student2("Sara", 10, listOf(78, 82, 80))
    )
    val top3=students.sortedByDescending { it.average() }.take(3)
    Log.d("Lab2", "Task3: top 3 students")
    top3.forEach{
        Log.d("Lab3", "${it.name} (${it.id}) -> avg=${"%.2f".format(it.average())}, grade=&{it.letterGrade()}")
    }
    Log.d("Lab2", "Task3: Passing Students")
    students.filter { it.isPassing() }.forEach {
        Log.d("Lab2", "${it.name} -> ${"%.2f".format(it.average())}")
    }
}

class Calculator {
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b
    fun divide(a: Double, b: Double): Double? = if (b != 0.0) a / b else null
}

class Calculator2 {
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b
    fun divide(a: Double, b: Double): Double? {
        if (b == 0.0) return null
        return safeRound(a / b)
    }

    private fun safeRound(x: Double): Double = round(x * 100) / 100
}
fun calculate(a: Double, b: Double, op: String): Double? {
    return when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else null
        else -> null
    }
}

fun Double.formatResult(): String = "%.2f".format(this)
fun task4() {
    val calc = Calculator2()
    val result = calc.divide(10.0, 3.0)
    Log.d("Lab2", "Task4: 10/3 = ${result?.formatResult() ?: "Error"}")

    val result2 = calculate(10.0, 3.0, "/")
    Log.d("Lab2", "Task4: calculate(10,3,\"/\") = ${result2?.formatResult() ?: "Error"}")
}

fun runAllLab2Tasks() {
    task1A()
    task1B()
    task1C()
    task1D()
    task1E()
    printPrimes1To100()
    task2A()
    task2B()
    task2C()
    task2D()
    task2E()
    task3()
    task4()
}