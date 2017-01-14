
package br.com.simplepass.simplepassnew.map

import br.com.simplepass.simplepassnew.TestUtils
import br.com.simplepass.simplepassnew.domain.User
import br.com.simplepass.simplepassnew.domain.VanInMap
import br.com.simplepass.simplepassnew.login.LoginPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import rx.Observable

/**
 * Created by leandro on 1/13/17.
 */
class MapPresenterTest {

    @Mock
    lateinit var mMapView : MapView
    @Mock
    lateinit var mMapInteractor : MapInteractor

    lateinit var mMapPresenter : MapPresenter

    val errorMessage = "Error"
    val errorMessageNoMsg = "Error no msg"
    val mCompany = "Velox"

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`(mMapInteractor.requestPoints(mCompany)).thenReturn(Observable.just(TestUtils.provideDefaultVans()))

        `when`(mMapInteractor.requestPoints(errorMessage)).thenReturn(
                Observable.create(Observable.OnSubscribe<Iterable<VanInMap>> {
            sub -> sub.onError(Exception(errorMessage))
        }))
        `when`(mMapInteractor.requestPoints(errorMessageNoMsg)).thenReturn(
                Observable.create(Observable.OnSubscribe<Iterable<VanInMap>> {
                    sub -> sub.onError(Exception())
                }))

        mMapPresenter = MapPresenterImpl(mMapView, mMapInteractor)
    }

    @Test
    fun pointsRequestTest(){
        mMapPresenter.updateMap(mCompany)

        verify(mMapView).showProgress(true)
        verify(mMapView).showProgress(false)
        verify(mMapView).drawPoints(TestUtils.provideDefaultVans())
    }

    @Test
    fun pointsRequestTestFailWithMessage(){
        mMapPresenter.updateMap(errorMessage)

        verify(mMapView).showProgress(true)
        verify(mMapView).showProgress(false)
        verify(mMapView).showError(errorMessage)
    }

    @Test
    fun pointsRequestTestFailNoMessage(){
        mMapPresenter.updateMap(errorMessageNoMsg)

        verify(mMapView).showProgress(true)
        verify(mMapView).showProgress(false)
        verify(mMapView).showError("Erro ao conectar")
    }


}