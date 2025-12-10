package com.example.cuidapet.ui.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class BottomCurvedShape(private val curvedRadius: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radiusPx = density.run { curvedRadius.toPx() }
        val offsetPx = radiusPx
        val path = Path().apply {
            // 1. Ponto de partida: Canto Inferior Esquerdo (0, size.height)
            moveTo(0f, size.height)

            // 2. Linha inferior (reta): até o canto inferior direito
            lineTo(size.width, size.height)

            // 3. Linha lateral direita (reta): sobe até a parte superior direita
            // Começa o desenho da volta
            lineTo(size.width, 0f)

            // --- Começa a Curva Superior ---

            // Ponto central da curva
            val centerX = size.width / 2f

            // Curva Quadrática 1: do canto superior direito até o ponto central
            quadraticTo(
                x1 = size.width - radiusPx, // Ponto de controle X (quase no canto)
                y1 = -offsetPx,             // Ponto de controle Y (Topo)
                x2 = centerX,               // Ponto final X (Meio)
                y2 = -offsetPx              // Ponto final Y (Abaixo do topo, para criar a curva)
            )

            // Curva Quadrática 2: do ponto central até o canto superior esquerdo
            quadraticTo(
                x1 = radiusPx,              // Ponto de controle X (quase no canto esquerdo)
                y1 = -offsetPx,             // Ponto de controle Y (Topo)
                x2 = 0f,                    // Ponto final X (Canto esquerdo)
                y2 = 0f                     // Ponto final Y (Topo)
            )

            // 4. Linha lateral esquerda (reta): desce até o canto inferior esquerdo
            // Fecha a forma no ponto de partida
            close()
        }
        return Outline.Generic(path)
    }
}