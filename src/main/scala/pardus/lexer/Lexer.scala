package pardus.lexer

import fastparse._,
NoWhitespace._

object Lexer {

  /**
    * Cool, we can make a P[Token].
    * Now we have to make
    * - one big uberP[Token] by |-ing all of the individual ones together
    * - a helper function `go`
    *   (inputString, P[Token]) => Option[(remainingString, matchedString, Token)]
    *   by taking the index on which the parsing completes and doing a take/drop.
    * - a helper function that finds the longest match
    *   by taking a (remainingString, matchedString, Token)
    *   and doing a
    *   case (r::rs, m::ms, T) => go(m :: ms :+ r, uberP)
    *   to try to keep matching.
    *   That doesn't look quite right, but something like that.
    *
    * We need one parser for each of type of token
    * fastparse would write typeP[_ : P]: P[Token], but this gives a compiler
    * warning that wildcards are the top level are not allowed.
    */

    /** Reserved words **/
    def typeP[Q : P]: P[Token] = P("t" ~ "y" ~ "p" ~ "e").map(_ => Token.Type)
    def varP[Q : P]: P[Token] = P("v" ~ "a" ~ "r").map(_ => Token.Var)
    def functionP[Q : P]: P[Token] =
      P("f" ~ "u" ~ "n" ~ "c" ~ "t" ~ "i" ~ "o" ~ "n").map(_ => Token.Function)
    def breakP[Q : P]: P[Token] =
      P("b" ~ "r" ~ "e" ~ "a" ~ "k").map(_ => Token.Break)
    def ofP[Q : P]: P[Token] = P("o" ~ "f").map(_ => Token.Of)
    def endP[Q : P]: P[Token] = P("e" ~ "n" ~ "d").map(_ => Token.End)
    def inP[Q : P]: P[Token] = P("i" ~ "n").map(_ => Token.In)
    def nilP[Q : P]: P[Token] = P("n" ~ "i" ~ "l").map(_ => Token.Nil)
    def letP[Q: P]: P[Token] = P("l" ~ "e" ~ "t").map(_ => Token.Let)
    def doP[Q: P]: P[Token] = P("d" ~ "o").map(_ => Token.Do)
    def toP[Q: P]: P[Token] = P("t" ~ "o").map(_ => Token.To)
    def forP[Q: P]: P[Token] = P("f" ~ "o" ~ "r").map(_ => Token.For)
    def whileP[Q: P]: P[Token] =
      P("w" ~ "h" ~ "i" ~ "l" ~ "e").map(_ => Token.While)
    def elseP[Q: P]: P[Token] = P("e" ~ "l" ~ "s" ~ "e").map(_ => Token.Else)
    def thenP[Q: P]: P[Token] = P("t" ~ "h" ~ "e" ~ "n").map(_ => Token.Then)
    def ifP[Q: P]: P[Token] = P("i" ~ "f").map(_ => Token.If)
    def arrayP[Q: P]: P[Token] =
      P("a" ~ "r" ~ "r" ~ "a" ~ "y").map(_ => Token.Array)

    /** Operators **/
    def assignP[Q: P]: P[Token] = P("=").map(_ => Token.Assign)
    def orP[Q: P]: P[Token] = P("||").map(_ => Token.Or)
    def andP[Q: P]: P[Token] = P("&&").map(_ => Token.And)
    def geP[Q: P]: P[Token] = P(">" ~ "=").map(_ => Token.GE)
    def gtP[Q: P]: P[Token] = P(">").map(_ => Token.GT)
    def leP[Q: P]: P[Token] = P("<" ~ "=").map(_ => Token.LE)
    def ltP[Q: P]: P[Token] = P("<").map(_ => Token.LT)
    def neqP[Q: P]: P[Token] = P("!" ~ "=").map(_ => Token.NEQ)
    def eqP[Q: P]: P[Token] = P("==").map(_ => Token.EQ)
    def divideP[Q: P]: P[Token] = P("/").map(_ => Token.Divide)
    def timesP[Q: P]: P[Token] = P("x").map(_ => Token.Times)
    def minusP[Q: P]: P[Token] = P("-").map(_ => Token.Minus)
    def plusP[Q: P]: P[Token] = P("+").map(_ => Token.Plus)

    /** Punctuation **/
    def dotP[Q: P]: P[Token] = P(".").map(_ => Token.Dot)
    def rbraceP[Q: P]: P[Token] = P("}").map(_ => Token.RBrace)
    def lbraceP[Q: P]: P[Token] = P("{").map(_ => Token.LBrace)
    def rbrackP[Q: P]: P[Token] = P("]").map(_ => Token.RBrack)
    def lbrackP[Q: P]: P[Token] = P("[").map(_ => Token. LBrack)
    def rparenP[Q: P]: P[Token] = P(")").map(_ => Token.RParen)
    def lparenP[Q: P]: P[Token] = P("(").map(_ => Token.LParen)
    def semicolonP[Q: P]: P[Token] = P(";").map(_ => Token.Semicolon)
    def colonP[Q: P]: P[Token] = P(":").map(_ => Token.Colon)
    def commaP[Q: P]: P[Token] = P(",").map(_ => Token.Comma)

    //
    def stringTokenP[Q: P]: P[Token] =
      P((CharIn("A-Z") | CharIn("a-z")).rep(1).!.map(s => Token.StringToken(s)))

    //TODO: We probably do not want ~End here, but without it,
    //"0123" parses to "0"
    private def zeroP[Q: P]: P[String] = P("0" ~ End).!
    private def intP[Q: P]: P[String] =
      (CharIn("1-9") ~ CharIn("0-9").rep(1)).!

    def intTokenP[Q: P]: P[Token] = {
      P(zeroP | intP).map(s => Token.IntToken(s.toInt))
    }
    // An identifier is a sequence of letters, digits, underscores,
    // starting with a letter.
    private def letterP[Q: P]: P[String] =
      P(CharIn("A-Z") | CharIn("a-z")).!
    private def digitP[Q: P]: P[String] =
      P(CharIn("0-9")).!
    private def underscoreP[Q: P]: P[String] =
      P("_").!
    private def letterDigitUnderscoreP[Q: P]: P[(String, Seq[String])] =
      P(letterP ~ (letterP | digitP | underscoreP).rep(1))

    def idTokenP[Q: P]: P[Token] =
      P(letterDigitUnderscoreP).map{ case (first, rest)  =>
        val s = first ++ rest.foldLeft("")(_ ++ _)
        Token.Id(s)
      }

    def eofP[Q: P]: P[Token] = P(End).map(_ => Token.EOF)
}

