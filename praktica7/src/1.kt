import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("Введите первое число:")
        val num1 = scanner.nextDouble()

        println("Введите операцию (+, -, , /):")
        val operator = scanner.next()

        println("Введите второе число:")
        val num2 = scanner.nextDouble()

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "" -> num1 * num2
                    "/" -> {
                if (num2 == 0.0) {
                    println("Деление на ноль невозможно!")
                    continue
                } else {
                    num1 / num2
                }
            }
            else -> {
                println("Неверная операция!")
                continue
            }
        }

        println("Результат: $result")

        println("Хотите продолжить? (да/нет)")
        val continueChoice = scanner.next()
        if (continueChoice != "да") {
            break
        }
    }
}