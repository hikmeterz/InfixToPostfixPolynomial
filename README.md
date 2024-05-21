# InfixToPostfixPolynomial
# Infix to Postfix Polynomial Evaluation

## Project Description
This repository contains a Java program that converts arithmetic expressions from infix notation to postfix notation and then evaluates the expression. The expressions include polynomials, and the program uses linked lists and stacks to manage the calculations.

## Files
- `Node.java`: Defines the node structure used in the linked list.
- `LinkedList.java`: Implements the linked list structure to store polynomial terms.
- `Stack.java`: Implements the stack structure to manage the conversion and evaluation process.
- `Main.java`: Main class to handle the conversion from infix to postfix notation and the evaluation of the postfix expression.

## Algorithm Explanation

### Node Class
The `Node` class defines the basic unit of the linked list. Each node contains:
- A polynomial term (`int coefficient` and `int exponent`): The data stored in the node.
- A reference to the next node (`Node next`): Points to the next node in the list.

### LinkedList Class
The `LinkedList` class manages the nodes and provides methods to manipulate the list:
- `addTerm(int coefficient, int exponent)`: Adds a new term to the polynomial in the correct position.
- `evaluate(int x)`: Evaluates the polynomial for a given value of x.

### Stack Class
The `Stack` class manages the nodes and provides methods to manipulate the stack:
- `push(Node node)`: Pushes a node onto the stack.
- `pop()`: Pops a node from the stack.
- `isEmpty()`: Checks if the stack is empty.

### Main Class
The `Main` class contains the main method to run the program:
- Reads an arithmetic expression in infix notation.
- Converts the infix expression to postfix notation using a stack.
- Evaluates the postfix expression using a stack and linked lists for polynomial terms.
- Outputs the result of the evaluation.

### Example Workflow
1. The user inputs an arithmetic expression: `(3x^2 + 2x) * (5x^3 + 2) / ((4x^15 + 3x^4 - 12x^2 + 2) + (4x))`.
2. The program converts the expression from infix notation to postfix notation.
3. The program evaluates the postfix expression for a given value of x.
4. Outputs the result of the evaluation.
