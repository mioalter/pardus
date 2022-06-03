package pardus.lexer

sealed trait Token

object Token {
  // Reserved words
  case object Type extends Token
  case object Var extends Token
  case object Function extends Token
  case object Break extends Token
  case object Of extends Token
  case object End extends Token
  case object In extends Token
  case object Nil extends Token
  case object Let extends Token
  case object Do extends Token
  case object To extends Token
  case object For extends Token
  case object While extends Token
  case object Else extends Token
  case object Then extends Token
  case object If extends Token
  case object Array extends Token
  // Operators
  case object Assign extends Token
  case object Or extends Token
  case object And extends Token
  case object GE extends Token
  case object GT extends Token
  case object LE extends Token
  case object LT extends Token
  case object NEQ extends Token
  case object EQ extends Token
  case object Divide extends Token
  case object Times extends Token
  case object Minus extends Token
  case object Plus extends Token
  // Punctuation
  case object Dot extends Token
  case object RBrace extends Token
  case object LBrace extends Token
  case object RBrack extends Token
  case object LBrack extends Token
  case object RParen extends Token
  case object LParen extends Token
  case object Semicolon extends Token
  case object Colon extends Token
  case object Comma extends Token
  //
  case class String(value: String) extends Token
  case class Int(value: Int) extends Token
  case class Id(value: String) extends Token
  case object EOF extends Token
}
