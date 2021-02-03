* **ExpressionParser** <ol>
  <li>Разработайте классы <code>Const, Variable, Add, Subtract, Multiply, Divide</code> для вычисления выражений с одной переменной в типе <code>int</code>.</li>
  <li>Классы должны позволять составлять выражения вида:<br>
   <code>new Subtract(new Multiply(new Const(2), new Variable("x")), new Const(3)).evaluate(5)</code><br>
    При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу <code>evaluate</code> (на данном этапе имена переменных игнорируются). Таким образом, результатом вычисления приведенного примера должно стать число <code>7</code>.</li>
  <li>Метод <code>toString</code> должен выдавать запись выражения в полноскобочной форме. Например,<br>
      <code>new Subtract(new Multiply(new Const(2), new Variable("x")), new Const(3)).toString()</code><br>
      должен выдавать <code>((2 * x) - 3)</code>.</li>
  <li>Метод <code>toMiniString</code> должен выдавать выражение с минимальным числом скобок. Например,<br>
    <code>new Subtract(new Multiply(new Const(2), new Variable("x")), new Const(3)).toMiniString()</code><br>
      должен выдавать <code>2 * x - 3</code>.</li>
  <li>Реализуйте метод <code>equals</code>, проверяющий, что два выражения совпадают. Например,<br>
    <code>new Multiply(new Const(2), new Variable("x")).equals(new Multiply(new Const(2), new Variable("x")))</code><br>
    должно выдавать <code>true</code>, а<br>
    <code>new Multiply(new Const(2), new Variable("x")).equals(new Multiply(new Variable("x"), new Const(2)))</code><br>   
    должно выдавать <code>false</code>.</li>
  <li>Для тестирования программы должен быть создан класс <code>Main</code>, который вычисляет значение выражения<br> <code>x * x − 2 * x + 1</code>, для <code>x</code>, заданного в командной строке.</li>
    <li>При выполнении задания следует обратить внимание на:
        <ul>
        <li>Выделение общего интерфейса создаваемых классов.</li>
        <li>Выделение абстрактного базового класса для бинарных операций.</li>
        </ul>
        </li> 
  <li>Выражение должно строиться по записи вида<br>
    <code>x * (x - 2) * x + 1</code></li>
  <li>В записи выражения могут встречаться: умножение <code>*</code>, деление <code>/</code>, сложение <code>+</code>, вычитание <code>-</code>, унарный минус <code>-</code>, целочисленные константы (в десятичной системе счисления, которые помещаются в 32-битный знаковый целочисленный тип), круглые скобки, переменные <code>x</code> и произвольное число пробельных символов в любом месте (но не внутри констант).</li>
  <li>Приоритет операторов, начиная с наивысшего
    <ul>
      <li>унарный минус</li>
      <li>умножение и деление</li>
      <li>сложение и вычитание</li>
    </ul>
  </li>
  <li>Разбор выражений рекомендуется производить методом рекурсивного спуска. Алгоритм должен работать за линейное время.</li> 
  <li>Добавьте в программу вычисляющую выражения обработку ошибок, в том числе:
    <ul>
      <li>ошибки разбора выражений</li>
      <li>ошибки вычисления выражений</li>
    </ul>
  </li>
  <li>Для выражения <code>1000000 * x * x * x * x * x / (x - 1)</code> вывод программы должен иметь следующий вид:
    <table>
      <tr>
        <th><code>x</code></th>
        <th><code>f</code></th>
      </tr>
      <tr>
        <td><code>0</code></td>
        <td><code>0</code></td>
      </tr>
      <tr>
        <td><code>1</code></td>
        <td><code>division by zero</code></td>
      </tr>
      <tr>
        <td><code>2</code></td>
        <td><code>32000000</code></td>
      </tr>
      <tr>
        <td><code>3</code></td>
        <td><code>121500000</code></td>
      </tr>
      <tr>
        <td><code>4</code></td>
        <td><code>341333333</code></td>
      </tr>
      <tr>
        <td><code>5</code></td>
        <td><code>overflow</code></td>
      </tr>
      <tr>
        <td><code>6</code></td>
        <td><code>overflow</code></td>
      </tr>
      <tr>
        <td><code>7</code></td>
        <td><code>overflow</code></td>
      </tr>
      <tr>
        <td><code>8</code></td>
        <td><code>overflow</code></td>
      </tr>
      <tr>
        <td><code>9</code></td>
        <td><code>overflow</code></td>
      </tr>
      <tr>
        <td><code>10</code></td>
        <td><code>overflow</code></td>
      </tr>
    </table>
    Результат <code>division by zero (overflow)</code> означает, что в процессе вычисления произошло деление на ноль (переполнение).</li>
    <li>При выполнении задания следует обратить внимание на дизайн и обработку исключений.</li>
    <li>Человеко-читаемые сообщения об ошибках должны выводится на консоль.</li>
    <li>Программа не должна «вылетать» с исключениями (как стандартными, так и добавленными).</li> 
</ol>
