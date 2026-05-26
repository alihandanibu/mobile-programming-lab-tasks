package com.example.myapplication.model.repository.quest

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import com.example.myapplication.model.data.remote.QuestData
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    @ApplicationContext private val context: Context
) : QuestRepository {

    private val questCollection = firestore.collection("quests")

    override suspend fun getQuests(): List<QuestData> {
        val snapshot = questCollection.get().await()

        return snapshot.documents.mapNotNull { document ->
            document.toObject(QuestData::class.java)?.apply {
                id = document.id
            }
        }
    }

    override suspend fun addQuest(
        title: String,
        xp: Int,
        category: String,
        difficulty: String,
        isDaily: Boolean
    ) {
        val quest = QuestData().apply {
            this.title = title
            this.xp = xp
            this.category = category
            this.difficulty = difficulty
            this.daily = isDaily
        }

        questCollection.add(quest).await()
    }

    override suspend fun toggleQuest(
        questId: String,
        isCompleted: Boolean
    ) {
        questCollection
            .document(questId)
            .update("completed", isCompleted)
            .await()
    }

    override suspend fun deleteQuest(questId: String) {
        questCollection
            .document(questId)
            .delete()
            .await()
    }

    override suspend fun exportQuests(
        quests: List<QuestData>
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val csvContent = buildString {
                    appendLine("id,title,xp,isCompleted")

                    quests.forEach { quest ->
                        appendLine(
                            "${escapeCsv(quest.id)}," +
                                    "${escapeCsv(quest.title)}," +
                                    "${quest.xp}," +
                                    "${quest.completed}"
                        )
                    }
                }

                val fileName = "quests_export.csv"

                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                }

                val resolver = context.contentResolver

                val uri = resolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    contentValues
                ) ?: error("Could not create export file")

                resolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(csvContent.toByteArray())
                } ?: error("Could not open export file")

                uri.toString()
            }
        }
    }

    private fun escapeCsv(value: String): String {
        val escapedValue = value.replace("\"", "\"\"")

        return if (
            escapedValue.contains(",") ||
            escapedValue.contains("\"") ||
            escapedValue.contains("\n")
        ) {
            "\"$escapedValue\""
        } else {
            escapedValue
        }
    }
}