import com.architecturecourse.commandlineinterpreter.components.utils.CmdCategory
import com.architecturecourse.commandlineinterpreter.components.utils.Token

interface Lexer {
    fun lex(input: String): Pair<List<Token>, CmdCategory>
}