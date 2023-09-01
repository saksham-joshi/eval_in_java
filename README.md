# eval_in_java

Python has a function called eval() used to solve string mathematical expressions but Java has nothing like this , so i developed this algorithm which
can solve such expressions .

# How to use it ?
1. Just specify the package name on the top of the evaluater.java file and you are good to go .
2. In case you are not using packages, then put the evaluater.java file in the
same directory of the file in which you are calling it.

# How to call it ?
Here is the sample code :
evaluater e1 = new evaluater();
System.out.println(e1.evaluate("1280.33/(12*10.11)"));

# NOTE :
evaluater.evaluate() function returns a empty string when the expression is not valid.
