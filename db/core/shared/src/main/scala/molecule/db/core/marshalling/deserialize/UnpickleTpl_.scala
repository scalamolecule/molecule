// GENERATED CODE ********************************
package molecule.db.core.marshalling.deserialize

import molecule.core.dataModel.*

trait UnpickleTpl_[Tpl] { self: UnpickleTpls[Tpl] =>

  def getUnpickler(elements: List[Element]): () => Any = {
    val unpicklers: List[() => Any] = resolveUnpicklers(elements, Nil)
    unpicklers.length match {
      case 1  => resolve1(unpicklers)
      case 2  => resolve2(unpicklers)
      case 3  => resolve3(unpicklers)
      case 4  => resolve4(unpicklers)
      case 5  => resolve5(unpicklers)
      case 6  => resolve6(unpicklers)
      case 7  => resolve7(unpicklers)
      case 8  => resolve8(unpicklers)
      case 9  => resolve9(unpicklers)
      case 10 => resolve10(unpicklers)
      case 11 => resolve11(unpicklers)
      case 12 => resolve12(unpicklers)
      case 13 => resolve13(unpicklers)
      case 14 => resolve14(unpicklers)
      case 15 => resolve15(unpicklers)
      case 16 => resolve16(unpicklers)
      case 17 => resolve17(unpicklers)
      case 18 => resolve18(unpicklers)
      case 19 => resolve19(unpicklers)
      case 20 => resolve20(unpicklers)
      case 21 => resolve21(unpicklers)
      case 22 => resolve22(unpicklers)
      case n  =>
        val last = n - 1
        () => {
          var i          = last
          var tpl: Tuple = EmptyTuple
          while (i >= 0) {
            tpl = unpicklers(i)() *: tpl
            i -= 1
          }
          tpl
        }
    }
  }

  final private def resolve1(unpicklers: List[() => Any]): () => Any = {
    val List(u1) = unpicklers
    () => {
      (
        u1()
        )
    }
  }

  final private def resolve2(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2) = unpicklers
    () => {
      (
        u1(),
        u2()
      )
    }
  }

  final private def resolve3(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3()
      )
    }
  }

  final private def resolve4(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4()
      )
    }
  }

  final private def resolve5(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5()
      )
    }
  }

  final private def resolve6(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6()
      )
    }
  }

  final private def resolve7(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7()
      )
    }
  }

  final private def resolve8(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8()
      )
    }
  }

  final private def resolve9(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9()
      )
    }
  }

  final private def resolve10(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10()
      )
    }
  }

  final private def resolve11(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11()
      )
    }
  }

  final private def resolve12(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12()
      )
    }
  }

  final private def resolve13(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13()
      )
    }
  }

  final private def resolve14(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14()
      )
    }
  }

  final private def resolve15(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15()
      )
    }
  }

  final private def resolve16(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16()
      )
    }
  }

  final private def resolve17(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17()
      )
    }
  }

  final private def resolve18(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17(),
        u18()
      )
    }
  }

  final private def resolve19(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17(),
        u18(),
        u19()
      )
    }
  }

  final private def resolve20(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17(),
        u18(),
        u19(),
        u20()
      )
    }
  }

  final private def resolve21(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17(),
        u18(),
        u19(),
        u20(),
        u21()
      )
    }
  }

  final private def resolve22(unpicklers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21, u22) = unpicklers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4(),
        u5(),
        u6(),
        u7(),
        u8(),
        u9(),
        u10(),
        u11(),
        u12(),
        u13(),
        u14(),
        u15(),
        u16(),
        u17(),
        u18(),
        u19(),
        u20(),
        u21(),
        u22()
      )
    }
  }
}