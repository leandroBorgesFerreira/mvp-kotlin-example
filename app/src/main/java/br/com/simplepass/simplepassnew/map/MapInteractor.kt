package br.com.simplepass.simplepassnew.map

import br.com.simplepass.simplepassnew.domain.VanInMap
import rx.Observable

/**
 * Created by leandro on 1/12/17.
 */
interface MapInteractor {
    fun requestPoints(company: String): Observable<Iterable<VanInMap>>
}