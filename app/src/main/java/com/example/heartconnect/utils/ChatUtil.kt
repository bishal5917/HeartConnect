package com.example.heartconnect.utils

import com.example.heartconnect.features.data.models.conversation.ConversationModel

class ChatUtil {
    fun isFriendAlready(users: List<ConversationModel>, id: String): Boolean {
        for (item in users) {
            if (item.friendId == id) {
                return true;
            }
        }
        return false;
    }
}