# Java course

* **BinarySearch** <ol>
  <li>Реализуйте итеративный и рекурсивный варианты бинарного поиска в массиве. </li>
  <li>На вход подается целое число x и массив целых чисел <code>a</code>, отсортированный по невозрастанию. Требуется найти минимальное значение индекса <code>i</code>, при котором <code>a[i] <= x</code>. </li>
  <li>Для функций бинарного поиска и вспомогательных функций должны быть указаны, пред- и постусловия. Для реализаций методов должны быть приведены доказательства соблюдения контрактов в терминах троек Хоара. </li>
  <li>Интерфейс программы. 
    <ul>
      <li>Имя основного класса — <code>BinarySearch</code>.</li>
      <li>Первый аргумент командной строки — число <code>x</code>.</li>
      <li>Последующие аргументы командной строки — элементы массива <code>a</code>.</li> 
    </ul>
  </li>
  <li>Пример запуска: <code>java BinarySearch 3 5 4 3 2 1</code>. Ожидаемый результат: <code>2</code>. </li>
</ol>

* **ExpressionParser** <ol type="1">
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
</ol>
