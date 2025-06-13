package com.example.luminarytrading.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luminarytrading.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                    true
                }
                R.id.nav_study -> {
                    true
                }
                R.id.nav_news -> {
                    startActivity(Intent(this, NewsActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)

        val items = arrayListOf<item>()
        val beginner : String = "Узнай, что такое криптовалюта, зачем она нужна и как с ней безопасно обращаться. Подходит для тех, кто впервые сталкивается с этой темой.\n" +
                "\n" +
                "Ключевые темы:\n" +
                "\n" +
                "Что такое криптовалюта и блокчейн — простыми словами\n" +
                "\n" +
                "Разница между Bitcoin и Ethereum\n" +
                "\n" +
                "Как создать криптокошелёк и где хранить монеты\n" +
                "\n" +
                "Как купить криптовалюту и не попасть на мошенников\n"

        val advanced : String = "Погружаемся глубже: разберёмся, как работает блокчейн, как зарабатывать с помощью крипты и какие технологии стоят за известными проектами.\n" +
                "Ключевые темы:\n" +
                "\n" +
                "Как работает майнинг и стейкинг\n" +
                "\n" +
                "Смарт-контракты и децентрализованные приложения (dApps)\n" +
                "\n" +
                "Децентрализованные биржи (DEX) и обмен токенов\n" +
                "\n" +
                "Основы анализа рынка и управления рисками\n"


        val pro : String = "Разбираем продвинутые стратегии, анализируем проекты и используем профессиональные инструменты. Подходит для тех, кто уже торгует или участвует в криптопроектах.\n" +
                "\n" +
                "Ключевые темы:\n" +
                "\n" +
                "Продвинутый DeFi: лендинг, фарминг, протоколы ликвидности\n" +
                "\n" +
                "NFT, DAO и токеномика\n" +
                "\n" +
                "Инструменты анализа блокчейна и портфеля\n" +
                "\n" +
                "Безопасность: аппаратные кошельки, мультиподписи, анонимность\n"

        items.add(item(1, "beginner", "\uD83D\uDFE2 Новичок", "Начни сейчас", beginner, 5000))

        items.add(item(2, "advanced", "\uD83D\uDFE1 Недавно в крипте", "Не останавливайся, это только начало", advanced, 7000))

        items.add(item(3, "pro", "\uD83D\uDD34 Опытный", "Продолжай расти", pro, 9000))


        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)


    }
}
