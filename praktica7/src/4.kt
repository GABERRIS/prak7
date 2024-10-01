import kotlin.random.Random

// Класс карты
class Card(val suit: String, val rank: String) {
    // Значение карты (от 2 до 11)
    val value: Int = when (rank) {
        "J", "Q", "K" -> 10
        "A" -> 11 // Туз может быть 1 или 11
        else -> rank.toInt()
    }

    // Строковое представление карты
    override fun toString(): String {
        return "$rank $suit"
    }
}

// Класс колоды карт
class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    // Инициализация колоды 52 картами
    init {
        val suits = listOf("♥", "♦", "♣", "♠")
        val ranks = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
        for (suit in suits) {
            for (rank in ranks) {
                cards.add(Card(suit, rank))
            }
        }
        // Перемешивание колоды
        cards.shuffle()
    }

    // Метод для взятия карты из колоды
    fun takeCard(): Card {
        return cards.removeAt(0)
    }
}

// Класс игрока
class Player(val name: String) {
    val hand: MutableList<Card> = mutableListOf()
    var score: Int = 0

    // Метод для добавления карты в руку
    fun takeCard(card: Card) {
        hand.add(card)
        updateScore()
    }

    // Метод для обновления счета
    private fun updateScore() {
        score = 0
        var hasAce = false
        for (card in hand) {
            score += card.value
            if (card.rank == "A") {
                hasAce = true
            }
        }
        // Если в руке есть туз, и счет больше 21, то считаем туз за 1
        if (hasAce && score > 21) {
            score -= 10
        }
    }

    // Метод для вывода информации о руке игрока
    fun showHand() {
        println("$name, у вас в руке:")
        for (card in hand) {
            println(card)
        }
        println("Счет: $score")
    }
}

// Основная функция
fun main() {
    // Создание колоды
    val deck = Deck()

    // Создание игроков
    val player = Player("Игрок")
    val dealer = Player("Дилер")

    // Начальные карты для игроков
    player.takeCard(deck.takeCard())
    dealer.takeCard(deck.takeCard())
    player.takeCard(deck.takeCard())
    dealer.takeCard(deck.takeCard())

    // Вывод информации о начале игры
    println("Игра 21 началась!")
    println("Карты игрока:")
    player.showHand()
    println("Карты дилера:")
    println(dealer.hand[0])
    println("скрытая карта")

    // Игровой цикл
    while (true) {
        // Ход игрока
        println("\nВаши действия? (взять/стоп)")
        val action = readLine()!!.toLowerCase()
        if (action == "взять") {
            player.takeCard(deck.takeCard())
            player.showHand()
            // Проверка на перебор
            if (player.score > 21) {
                println("Перебор! Вы проиграли!")
                break
            }
        } else if (action == "стоп") {
            println("Вы остановились!")
            break
        } else {
            println("Неверный ввод.")
        }
    }

    // Ход дилера
    println("\nХод дилера:")
    while (dealer.score < 17) {
        dealer.takeCard(deck.takeCard())
        println("Дилер взял карту: ${dealer.hand.last()}")
        println("Счет дилера: ${dealer.score}")
    }
    if (dealer.score > 21) {
        println("У дилера перебор! Вы выиграли!")
    } else {
        // Определение победителя
        if (player.score > dealer.score) {
            println("Вы выиграли!")
        } else if (dealer.score > player.score) {
            println("Дилер выиграл!")
        } else {
            println("Ничья!")
        }
    }
}