package br.com.simplepass.simplepassnew.map

import br.com.simplepass.simplepassnew.domain.VanInMap
import rx.Observable

/**
 * Created by leandro on 1/12/17.
 */
class MapInteractorImpl : MapInteractor {
    override fun requestPoints(company: String): Observable<Iterable<VanInMap>> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}