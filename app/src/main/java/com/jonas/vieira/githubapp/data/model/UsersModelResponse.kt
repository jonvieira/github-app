package com.jonas.vieira.githubapp.data.model

import com.google.gson.annotations.SerializedName

data class UsersModelResponse(
    @SerializedName("login") var login: String,
    @SerializedName("id") var id: Int,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("avatar_url") var avatar: String,
    @SerializedName("gravatar_id") var gravatarId: String,
    @SerializedName("url") var url: String,
    @SerializedName("html_url") var htmlUrl: String,
    @SerializedName("followers_url") var followers: String,
    @SerializedName("following_url") var followings: String,
    @SerializedName("gists_url") var gists: String,
    @SerializedName("starred_url") var starred: String,
    @SerializedName("subscriptions") var subscriptions: String,
    @SerializedName("organizations") var organizations: String,
    @SerializedName("repos_url") var repo: String,
    @SerializedName("events_url") var events: String,
    @SerializedName("received_event") var receivedEvent: String,
    @SerializedName("type") var type: String,
    @SerializedName("site_admin") var siteAdmin: String,
)