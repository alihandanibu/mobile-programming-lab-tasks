package com.example.myapplication.model.repository.quest

import com.example.myapplication.model.data.remote.QuestData

interface QuestRepository {

    suspend fun getQuests(): List<QuestData>

    suspend fun addQuest(
        title: String,
        xp: Int,
        category: String,
        difficulty: String,
        isDaily: Boolean
    )

    suspend fun toggleQuest(
        questId: String,
        isCompleted: Boolean
    )

    suspend fun deleteQuest(
        questId: String
    )

    suspend fun exportQuests(
        quests: List<QuestData>
    ) : Result<String>
}
