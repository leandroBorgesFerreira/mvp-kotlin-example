package br.com.simplepass.simplepassnew.map

import android.content.Context
import br.com.simplepass.simplepassnew.domain.MapPoint
import br.com.simplepass.simplepassnew.domain.VanInMap

import com.google.android.gms.maps.model.LatLng
import rx.Subscriber

/**
 * Created by hinovamobile on 10/01/17.
 */
class MapPresenterImpl(private val mMapView: MapView, private val mMapInteractor: MapInteractor) : MapPresenter {

    override fun updateMap(company: String) {
        mMapView.showProgress(true)

        mMapInteractor.requestPoints(company).subscribe(object: Subscriber<Iterable<VanInMap>>(){
            override fun onNext(t: Iterable<VanInMap>) {
                mapDrawMapPoints(t)
            }

            override fun onError(e: Throwable) {
                mMapView.showProgress(false)
                mapPointsErro(e.message ?: "Erro ao conectar")
            }

            override fun onCompleted() {
                mMapView.showProgress(false)
            }
        })

    }

    override fun mapDrawMapPoints(mapPoints: Iterable<MapPoint>) = mMapView.drawPoints(mapPoints)

    override fun mapPointsErro(error: String) = mMapView.showError(error)

}
