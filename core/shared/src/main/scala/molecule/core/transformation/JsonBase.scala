package molecule.core.transformation;


trait JsonBase {

  // Shamelessly adopted from lift-json:
  // https://github.com/lift/framework/blob/db05d863c290c5fd1081a7632263433153fc9fe3/core/json/src/main/scala/net/liftweb/json/JsonAST.scala#L813-L883

  protected def appendEscapedString(buf: StringBuffer, s: String): Unit = {
    /**
     * Ranges of charSet that should be escaped if this JSON is to be evaluated
     * directly as JavaScript (rather than by a valid JSON parser).
     */
    val jsEscapeChars: Set[Char] =
      List(
        ('\u00ad', '\u00ad'),
        ('\u0600', '\u0604'),
        ('\u070f', '\u070f'),
        ('\u17b4', '\u17b5'),
        ('\u200c', '\u200f'),
        ('\u2028', '\u202f'),
        ('\u2060', '\u206f'),
        ('\ufeff', '\ufeff'),
        ('\ufff0', '\uffff')
      ).foldLeft(Set[Char]()) {
        case (set, (start, end)) =>
          set ++ (start to end).toSet
      }

    s.foreach { c =>
      val strReplacement = c match {
        case '"'  => "\\\""
        case '\\' => "\\\\"
        case '\b' => "\\b"
        case '\f' => "\\f"
        case '\n' => "\\n"
        case '\r' => "\\r"
        case '\t' => "\\t"
        // Set.contains will cause boxing of c to Character, try and avoid this
        case c if (c >= '\u0000' && c < '\u0020') || jsEscapeChars.contains(c) =>
          "\\u%04x".format(c: Int)

        case _ => ""
      }

      // Use Char version of append if we can, as it's cheaper.
      if (strReplacement.isEmpty) {
        buf.append(c)
      } else {
        buf.append(strReplacement)
      }
    }
  }

  protected def quote(buf: StringBuffer, s: String): StringBuffer = {
    buf.append('"') //open quote
    appendEscapedString(buf, s)
    buf.append('"') //close quote
  }

  protected def set2json[T](set: Set[T], value2json: (StringBuffer, T) => StringBuffer): String = {
    val buf = new StringBuffer
    buf.append("[") // start array
    val it = set.iterator
    value2json(buf, it.next())
    while (it.hasNext) {
      buf.append(", ")
      value2json(buf, it.next())
    }
    buf.append("]") // close array
    buf.toString
  }
}
