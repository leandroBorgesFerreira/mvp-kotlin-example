package br.com.simplepass.simplepassnew.map

import br.com.simplepass.simplepassnew.base.BaseView
import br.com.simplepass.simplepassnew.domain.MapPoint

/**
 * Created by hinovamobile on 10/01/17.
 */
interface MapView : PointsDrawer, BaseView<MapPresenter> {
    fun drawPoints(points: Iterable<MapPoint>)
    fun drawPooints(point: MapPoint)
    fun showProgress(show: Boolean)
    fun showError(error: String)
}
