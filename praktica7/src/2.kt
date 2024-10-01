fun main() {
    // Ввод слова от пользователя
    println("Введите слово:")
    val word = readLine()!!.lowercase()

    // Проверка, является ли слово палиндромом
    if (isPalindrome(word)) {
        println("$word - это палиндром")
    } else {
        println("$word - это не палиндром")
    }
}

// Функция для проверки, является ли слово палиндромом
fun isPalindrome(word: String): Boolean {
    // Переворачиваем слово
    val reversedWord = word.reversed()

    // Сравниваем исходное слово с перевернутым
    return word == reversedWord
}