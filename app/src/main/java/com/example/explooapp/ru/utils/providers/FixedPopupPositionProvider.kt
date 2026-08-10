package com.example.explooapp.ru.utils.providers

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

class FixedPopupPositionProvider(
    private val contentOffset: IntOffset,
    private val alignment: Alignment = Alignment.TopStart
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val positionInAnchor = alignment.align(
            IntSize(anchorBounds.width, anchorBounds.height),
            IntSize(popupContentSize.width, popupContentSize.height),
            layoutDirection
        )
        return IntOffset(
            anchorBounds.left + positionInAnchor.x + contentOffset.x,
            anchorBounds.top + positionInAnchor.y + contentOffset.y
        )
    }
}