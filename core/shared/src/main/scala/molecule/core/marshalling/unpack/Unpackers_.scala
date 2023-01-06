// GENERATED CODE ********************************
package molecule.core.marshalling.unpack

import molecule.boilerplate.ast.Model._

trait Unpackers_[Tpl] { self: DTO2tpls[Tpl] =>

  def getUnpacker(elements: Seq[Element], level: Int): () => Any = {
    val unpackers: List[() => Any] = resolveUnpackers(elements, Nil, level)
    unpackers.length match {
      case 1 => resolve1(unpackers)
      case 2 => resolve2(unpackers)
      case 3 => resolve3(unpackers)
      case 4 => resolve4(unpackers)
      case 5 => resolve5(unpackers)
      case 6 => resolve6(unpackers)
      case 7 => resolve7(unpackers)
      case 8 => resolve8(unpackers)
      case 9 => resolve9(unpackers)
      case 10 => resolve10(unpackers)
      case 11 => resolve11(unpackers)
      case 12 => resolve12(unpackers)
      case 13 => resolve13(unpackers)
      case 14 => resolve14(unpackers)
      case 15 => resolve15(unpackers)
      case 16 => resolve16(unpackers)
      case 17 => resolve17(unpackers)
      case 18 => resolve18(unpackers)
      case 19 => resolve19(unpackers)
      case 20 => resolve20(unpackers)
      case 21 => resolve21(unpackers)
      case 22 => resolve22(unpackers)
    }
  }

  final private def resolve1(unpackers: List[() => Any]): () => Any = {
    val List(u1) = unpackers
    () => {
      (
        u1()
      )
    }
  }

  final private def resolve2(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2) = unpackers
    () => {
      (
        u1(),
        u2()
      )
    }
  }

  final private def resolve3(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3) = unpackers
    () => {
      (
        u1(),
        u2(),
        u3()
      )
    }
  }

  final private def resolve4(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4) = unpackers
    () => {
      (
        u1(),
        u2(),
        u3(),
        u4()
      )
    }
  }

  final private def resolve5(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5) = unpackers
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

  final private def resolve6(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6) = unpackers
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

  final private def resolve7(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7) = unpackers
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

  final private def resolve8(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8) = unpackers
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

  final private def resolve9(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9) = unpackers
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

  final private def resolve10(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10) = unpackers
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

  final private def resolve11(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11) = unpackers
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

  final private def resolve12(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12) = unpackers
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

  final private def resolve13(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13) = unpackers
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

  final private def resolve14(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14) = unpackers
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

  final private def resolve15(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15) = unpackers
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

  final private def resolve16(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16) = unpackers
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

  final private def resolve17(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17) = unpackers
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

  final private def resolve18(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18) = unpackers
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

  final private def resolve19(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19) = unpackers
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

  final private def resolve20(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20) = unpackers
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

  final private def resolve21(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21) = unpackers
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

  final private def resolve22(unpackers: List[() => Any]): () => Any = {
    val List(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21, u22) = unpackers
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