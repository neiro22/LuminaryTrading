package com.example.luminarytrading.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                    startActivity(Intent(this, GiftsActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        val itemsList: RecyclerView = findViewById(R.id.itemsList)

        val items = createStudyItems().toMutableList()


        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)


    }

    private fun createStudyItems(): List<item> {
        return listOf(
            item(
                id = 1,
                image = "beginners_1",
                title = "Что такое спотовый рынок и как на нем торговать?",
                desc = "Торговля",
                text = AssetReader.readTextFile(this, "beginner1.txt"),
                level = "Новичок"
            ),
            item(
                id = 2,
                image ="beginners_2",
                title = "Что такое книга ордеров и как она работает",
                desc = "Торговля",
                text = AssetReader.readTextFile(this, "beginner2.txt"),
                level = "Новичок"
            ),
            item(
                id = 3,
                image = "beginners_3",
                title = "Что такое майнинг криптовалюты и как он работает",
                desc = "Блокчейн",
                text = AssetReader.readTextFile(this, "beginner3.txt"),
                level = "Новичок"
            ),
            item(
                id = 4,
                image = "experienced_1",
                title = "Что такое фронтраннинг?",
                desc = "Торговля, DeFi",
                text = AssetReader.readTextFile(this, "experienced1.txt"),
                level = "Опытный"
            ),
            item(
                id = 5,
                image = "experienced_2",
                title = "Уровни стоп-лосс и тейк-профит и как их определять",
                desc = "Торговля",
                text = AssetReader.readTextFile(this, "experienced2.txt"),
                level = "Опытный"
            ),
            item(
                id = 6,
                image = "experienced_3",
                title = "Что такое доступность данных?",
                desc = "Блокчейн",
                text = AssetReader.readTextFile(this, "experienced3.txt"),
                level = "Опытный"
            ),
            item(
                id = 7,
                image = "advanced_1",
                title = "Что такое алгоритмическая торговля и как она работает",
                desc = "Торговля",
                text = AssetReader.readTextFile(this, "advanced1.txt"),
                level = "Продвинутый"
            ),
            item(
                id = 8,
                image = "advanced_2",
                title = "Распространенные проблемы безопасности в сфере GameFi",
                desc = "Безопасность",
                text = AssetReader.readTextFile(this, "advanced2.txt"),
                level = "Продвинутый"
            ),
            item(
                id = 9,
                image = "advanced_3",
                title = "Что такое Taproot и какую пользу он принесет биткоину",
                desc = "Биткоин, Блокчейн",
                text = AssetReader.readTextFile(this, "advanced3.txt"),
                level = "Продвинутый"
            ),

        )
    }
}
