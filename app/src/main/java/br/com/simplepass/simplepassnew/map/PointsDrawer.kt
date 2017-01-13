package br.com.simplepass.simplepassnew.map


import br.com.simplepass.simplepassnew.domain.MapPoint

/**
 * Created by leandro on 11/18/15.
 */
interface PointsDrawer {
    fun atualizaPontosDoMapa(points: Iterable<MapPoint>)
    fun atualizaPontosDoMapa(mapPoint: MapPoint)
    fun limpaMapa()
}

