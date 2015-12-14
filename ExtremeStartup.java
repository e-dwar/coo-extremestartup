package core;

import java.io.IOException;
import javax.script.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtremeStartup extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(answer(req.getParameter("q")));
    }

    String answer(String parameter) {
        System.out.println(parameter);
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try {
			return (String) engine.eval("" 
			+ "var r;"
			+ "var p = '" + parameter + "';"
			+ "var m = p.match(/largest|banana|minus|what is|primes|Fib|Brit|Eiffel/);"
			+ "function evaluate() {"
			+     "switch (m && m[0]) {"
			+         "case 'banana':  r = 'yellow'; break;"
			+         "case 'Brit':    r = 'Cameron'; break;"
			+         "case 'Eiffel':  r = 'Paris'; break;"
			+         "case 'largest': r = '' + largest(); break;"
			+         "case 'what is': r = '' + calc(); break;"
			+         "case 'minus':   r = '' + minus(); break;"
			+         "case 'primes':  r = '' + primes(toNumbers()); break;"
			+         "case 'Fib':     r = '' + fib(toNumbers()[0]); break;"
			+         "default: r = ''; break;"
			+     "}"
			+     "print('\\nr = ' + r + '\\n');"
			+     "return r;"
			+ "}"
			+ "function largest () {"
			+     "var numbers = toNumbers();"
			+     "var n, max = 0, i = 0;"
			+     "while (i < numbers.length) {"
			+ 	      "n = numbers[i++];"
			+ 	      "if (n > max) max = n;"
			+     "}"
			+     "println('MAX ' + max + '\\n');"
			+     "return max;"
			+ "}"
			+ "function calc () {"
			+     "var e = p.replace(/^.*?: what is /, '');"
			+     "e = e.replace(/multiplied by/g, '*');"
			+     "e = e.replace(/plus/g, '+');"
			+     "e = e.replace(/(\\d+) to the power of (\\d+)/g, 'Math.pow($1, $2)');"
			+     "return eval('(' + e + ')');"
			+ "}"
			+ "function minus () {"
			+     "var numbers = toNumbers();"
			+     "return numbers[0] - numbers[1];"
			+ "}"
			+ "function fib (n, undefined) {"
			+     "if (fib.cache[n] === undefined){"
			+         "fib.cache[n] = fib(n - 1) + fib(n - 2);"
			+     "}"
			+     "return fib.cache[n];"
			+ "}"
			+ "function primes (numbers) {"
			+     "var i, primes = [];"
			+     "for (i = 0; i < numbers.length; i++) {"
			+         "if (isPrime(numbers[i])) primes.push(numbers[i]);"
			+     "}"
			+     "return primes.join(', ');"
			+ "}"
			+ "function isPrime(value) {"
			+     "for(var i = 2; i < value; i++) {"
			+         "if(value % i === 0) {"
			+             "return false;"
			+         "}"
			+     "}"
			+     "return value > 1;"
			+ "}"
			+ "function toNumbers () {"
			+     "var i, numbers = p.replace(/^.*?:/, '').match(/\\d+/g);"
			+     "for (i = 0; i < numbers.length; i++) {"
			+         "numbers[i] = parseInt(numbers[i], 10);"
			+     "}"
			+     "return numbers;"
			+ "}"
			+ "fib.cache = [0, 1, 1];"
			+ "evaluate();"
			);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return "";
    }

}
