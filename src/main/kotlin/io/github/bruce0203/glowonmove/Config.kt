package io.github.bruce0203.glowonmove

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("지속시간(틱)")
    val period: Long,
    @SerialName("움직였을 때")
    val onMoved: TitleInfo,
    @SerialName("멈췄을 때")
    val onStopped: TitleInfo,
    @SerialName("발광이 꺼졌을 때")
    val onGlowCleared: TitleInfo
)

@Serializable
@SerialName("타이틀")
data class TitleInfo(
    @SerialName("큰타이틀")
    val title: String? = null,
    @SerialName("서브타이틀")
    val subtitle: String? = null,
    @SerialName("페이드인")
    val fadeIn: Int? = null,
    @SerialName("지속시간")
    val stay: Int? = null,
    @SerialName("페이드아웃")
    val fadeOut: Int? = null
)
