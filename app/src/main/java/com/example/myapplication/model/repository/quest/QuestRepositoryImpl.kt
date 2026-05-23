package com.example.myapplication.model.repository.quest

import com.example.myapplication.model.data.remote.QuestData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
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
}
