package org.example.performancetestexample

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun main() {
    val file = File("data.sql")
    file.printWriter().use { out ->
        val random = Random(System.currentTimeMillis())
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        out.println("""
            CREATE TABLE IF NOT EXISTS post (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                title VARCHAR(100) NOT NULL,
                content TEXT NOT NULL,
                author VARCHAR(50) NOT NULL,
                created_at DATETIME NOT NULL,
                updated_at DATETIME NOT NULL
            );
        """.trimIndent())
        for (i in 1..100_000) {
            val title = "Post Title $i"
            val content = "Content of post $i. ${"A".repeat(random.nextInt(50, 500))}"
            val author = "Author $i"
            val createdAt = LocalDateTime.now().minusDays(random.nextLong(0, 365)).format(dateFormatter)
            val updatedAt = LocalDateTime.now().format(dateFormatter)

            val insertStatement = """
                INSERT INTO post (title, content, author, created_at, updated_at)
                VALUES ('$title', '$content', '$author', '$createdAt', '$updatedAt');
            """.trimIndent()

            out.println(insertStatement)
        }
    }
}