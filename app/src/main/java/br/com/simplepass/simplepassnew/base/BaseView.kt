package br.com.simplepass.simplepassnew.base

/**
 * Created by leandro on 12/25/16.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}