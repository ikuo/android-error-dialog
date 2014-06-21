package com.github.ikuo.android_error_dialog

class Tapper[T <: AnyRef](obj: T) {
  def tap(f: T => Any): T = {
    Option(obj).foreach(f)
    obj
  }
}

object Tapper {
  object Implicits {
    implicit def anyRef2Tapper[T <: AnyRef](obj: T) = new Tapper(obj)
  }
}
